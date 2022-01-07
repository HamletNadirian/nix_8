package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.entity.Department;

import java.util.List;
import java.util.Map;

public interface DepartmentDao extends BaseDao<Department> {

    Map<Long, String> findByEmployeeId(Long id);

    List<Department> findAllByEmployeeId(Long id);
}
