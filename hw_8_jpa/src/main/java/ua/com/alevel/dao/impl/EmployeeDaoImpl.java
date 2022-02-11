package ua.com.alevel.dao.impl;

import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.dao.EmployeeDao;
import ua.com.alevel.datatable.DataTableRequest;
import ua.com.alevel.entity.Department;
import ua.com.alevel.entity.Employee;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.criteria.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    private final EntityManager entityManager;

    public EmployeeDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void create(Employee entity) {
        entityManager.persist(entity);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void update(Employee entity) {
        entityManager.merge(entity);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void delete(Integer id) {
        entityManager.createQuery("delete from Employee as employee " +
                        "where employee.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existById(Integer id) {
        Query query = entityManager
                .createQuery("select count(employee.id) from Employee employee " +
                        "where employee.id = :id")
                .setParameter("id", id);
        return (Long) query.getSingleResult() == 1;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Employee> findById(Integer id) {
        return Optional.ofNullable(entityManager.find(Employee.class, id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> findAll() {
        return (List<Employee>) entityManager
                .createQuery("select employee from Employee employee")
                .getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> findAll(DataTableRequest request) {
        int page = (request.getPage() - 1) * request.getSize();
        int size = request.getSize();
        String sort = request.getSort();
        String order = request.getOrder();

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
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
        List<Tuple> result = entityManager.createQuery(cq)
                .setFirstResult(page)
                .setMaxResults(size)
                .getResultList();
        return result.stream()
                .map(tuple -> (Employee) tuple.get(0))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        Query query = entityManager
                .createQuery("select count(employee) from Employee employee");
        return (Long) query.getSingleResult();
    }

    @Override
    @Transactional(readOnly = true)
    public long countByDepartmentId(Integer departmentId) {
        Query query = entityManager
                .createQuery("select count(employee) from Employee as employee " +
                        "join employee.departments as departments " +
                        "where departments.id = :departmentId");
        return (Long) query.setParameter("departmentId", departmentId).getSingleResult();
    }
}
