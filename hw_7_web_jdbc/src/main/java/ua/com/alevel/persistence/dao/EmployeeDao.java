package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.entity.Employee;

import java.util.Map;

public interface EmployeeDao extends BaseDao<Employee> {

    Map<Long, String> findAllByDepartmentId(Long departmentId);
}
