package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.Employee;

import java.util.Map;

public interface EmployeeService extends BaseService<Employee> {

    Map<Long, String> findAllByDepartmentId(Long departmentId);
}
