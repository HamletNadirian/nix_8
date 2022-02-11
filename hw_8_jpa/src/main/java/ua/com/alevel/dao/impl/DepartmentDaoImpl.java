package ua.com.alevel.dao.impl;

import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.dao.DepartmentDao;
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
public class DepartmentDaoImpl implements DepartmentDao {

    private final EntityManager entityManager;

    public DepartmentDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void create(Department entity) {
        entityManager.persist(entity);

    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void update(Department entity) {
        entityManager.merge(entity);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void delete(Integer id) {
        entityManager.createQuery("delete from Department as department " +
                        "where department.id = :id").
                setParameter("id", id).
                executeUpdate();
    }

    @Override
    @Transactional(readOnly = true)
        public boolean existById(Integer id) {
        Query query = entityManager
                .createQuery("select count(department.id) from Department as department " +
                        "where department.id = :id")
                .setParameter("id", id);
            return (Long) query.getSingleResult() == 1;

    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Department> findById(Integer id) {
        return Optional.ofNullable(entityManager.find(Department.class, id));

    }

    @Override
    @Transactional(readOnly = true)
    public List<Department> findAll() {
        return (List<Department>) entityManager
                .createQuery("select department from Department department")
                .getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Department> findAll(DataTableRequest request) {
        int page = (request.getPage() - 1) * request.getSize();
        int size = request.getSize();
        String sort = request.getSort();
        String order = request.getOrder();

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

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
        List<Tuple> result = entityManager.createQuery(cq)
                .setFirstResult(page)
                .setMaxResults(size)
                .getResultList();
        return result.stream()
                .map(tuple -> (Department) tuple.get(0))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        Query query = entityManager
                .createQuery("select count(department) from Department as department");
        return (Long) query.getSingleResult();

    }

    @Override
    @Transactional(readOnly = true)
    public long countByEmployeeId(Integer employeeId) {
        Query query = entityManager
                .createQuery("select count(department) from Department as department " +
                        "join department.employees as employees " +
                        "where employees.id = :employeeId");
        return (Long) query.setParameter("employeeId", employeeId).getSingleResult();

    }

}
