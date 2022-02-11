package ua.com.alevel.dao.impl;

import org.apache.commons.collections4.MapUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ua.com.alevel.dao.EmployeeDao;
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
public class EmployeeDaoImpl implements EmployeeDao {

    private final SessionFactory sessionFactory;

    public EmployeeDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(Employee entity) {
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
    public void update(Employee entity) {
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
            session.createQuery("delete from Employee as employee " +
                            "where employee.id = :id").
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
                .createQuery("select count(employee.id) from Employee as employee " +
                        "where employee.id = :id")
                .setParameter("id", id);
        boolean existById = (Long) query.getSingleResult() == 1;
        closeSession(session);
        return existById;
    }

    @Override
    public Optional<Employee> findById(Integer id) {
        Session session = getSession();
        Optional<Employee> optionalDepartment = Optional.ofNullable(session.find(Employee.class, id));
        closeSession(session);
        return optionalDepartment;
    }

    @Override
    public List<Employee> findAll() {
        Session session = getSession();
        Query query = session
                .createQuery("select employee from Employee as employee");
        List<Employee> employees= (List<Employee>) query.getResultList();
        closeSession(session);
        return employees;
    }

    @Override
    public List<Employee> findAll(DataTableRequest request) {
        int page = (request.getPage() - 1) * request.getSize();
        int size = request.getSize();
        String sort = request.getSort();
        String order = request.getOrder();
        Session session = getSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Tuple> cq = cb.createTupleQuery();
        Root<Employee> root = cq.from(Employee.class);
        Join<Employee, Department> join = root.join("departments", JoinType.LEFT);
        cq.select(cb.tuple(root, cb.count(join)));
        boolean isNotEmptyMap = MapUtils.isNotEmpty(request.getQueryMap());
        if (isNotEmptyMap) {
            Integer departmentId = (Integer) request.getQueryMap().get("departmentId");
            cq.where(cb.equal(join.get("id"), departmentId));
        }
        cq.groupBy(root.get("id"));
        Expression<?> orderBy = "departments".equals(sort) ? cb.count(join) : root.get(sort);
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
                .map(tuple -> (Employee) tuple.get(0))
                .collect(Collectors.toList());
    }

    @Override
    public long count() {
        Session session = getSession();
        Query query = getSession()
                .createQuery("select count(employee) from Employee as employee");
        long count = (Long) query.getSingleResult();
        closeSession(session);
        return count;
    }

    @Override
    public long countByDepartmentId(Integer departmentId) {
        Session session = getSession();
        Query query = session
                .createQuery("select count(employee) from Employee as employee " +
                        "join employee.departments as departments " +
                        "where departments.id = :departmentId");
        long count = (Long) query.setParameter("departmentId", departmentId).getSingleResult();
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
