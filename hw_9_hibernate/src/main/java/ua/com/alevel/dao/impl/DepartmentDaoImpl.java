package ua.com.alevel.dao.impl;

import org.apache.commons.collections4.MapUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ua.com.alevel.dao.DepartmentDao;
import ua.com.alevel.datatable.DataTableRequest;
import ua.com.alevel.entity.Department;
import ua.com.alevel.entity.Employee;

import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.criteria.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class DepartmentDaoImpl implements DepartmentDao {

    private final SessionFactory sessionFactory;

    public DepartmentDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Department entity) {
        Session session = getSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            session.save(entity);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            closeSession(session);
        }
    }

    @Override
    public void update(Department entity) {
        Session session = getSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            session.update(entity);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            closeSession(session);
        }
    }

    @Override
    public void delete(Integer id) {
        Session session = getSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            session.createQuery("delete from Department as department " +
                            "where department.id = :id").
                    setParameter("id", id).
                    executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            closeSession(session);
        }
    }

    @Override
    public boolean existById(Integer id) {
        Session session = getSession();
        Query query = session
                .createQuery("select count(department.id) from Department as department " +
                        "where department.id = :id")
                .setParameter("id", id);
        boolean existById = (Long) query.getSingleResult() == 1;
        closeSession(session);
        return existById;
    }

    @Override
    public Optional<Department> findById(Integer id) {
        Session session = getSession();
        Optional<Department> optionalDepartment = Optional.ofNullable(session.find(Department.class, id));
        closeSession(session);
        return optionalDepartment;
    }

    @Override
    public List<Department> findAll() {
        Session session = getSession();
        Query query = session
                .createQuery("select department from Department as department");
        List<Department> departments= (List<Department>) query.getResultList();
        closeSession(session);
        return departments;
    }

    @Override
    public List<Department> findAll(DataTableRequest request) {
        int page = (request.getPage() - 1) * request.getSize();
        int size = request.getSize();
        String sort = request.getSort();
        String order = request.getOrder();

        Session session = getSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<Department> root = cq.from(Department.class);
        Join<Department, Employee> join = root.join("employees", JoinType.LEFT);
        cq.select(cb.tuple(root, cb.count(join)));
        boolean isNotEmptyMap = MapUtils.isNotEmpty(request.getQueryMap());
        if (isNotEmptyMap) {
            Integer employeeId = (Integer) request.getQueryMap().get("employeeId");
            cq.where(cb.equal(join.get("id"), employeeId));
        }
        cq.groupBy(root.get("id"));
        Expression<?> orderBy = "employees".equals(sort) ? cb.count(join) : root.get(sort);
        if (order.equals("desc")) {
            cq.orderBy(cb.desc(orderBy));
        } else {
            cq.orderBy(cb.asc(orderBy));
        }
        List<Tuple> result = session.createQuery(cq)
                .setFirstResult(page)
                .setMaxResults(size)
                .getResultList();
        closeSession(session);
        return result.stream()
                .map(tuple -> (Department) tuple.get(0))
                .collect(Collectors.toList());
    }

    @Override
    public long count() {
        Session session = getSession();
        Query query = getSession()
                .createQuery("select count(department) from Department as department");
        long count = (Long) query.getSingleResult();
        closeSession(session);
        return count;
    }

    @Override
    public long countByEmployeeId(Integer employeeId) {
        Session session = getSession();
        Query query = session
                .createQuery("select count(department) from Department as department " +
                        "join department.employees as employees " +
                        "where employees.id = :employeeId");
        long count = (Long) query.setParameter("employeeId", employeeId).getSingleResult();
        closeSession(session);
        return count;
    }

    private Session getSession() {
        try {
            return sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            return sessionFactory.openSession();
        }
    }

    private void closeSession(Session session) {
        try {
            session.close();
        } catch (Exception e) {
            System.out.println("e = " + e.getMessage());
        }
    }
}
