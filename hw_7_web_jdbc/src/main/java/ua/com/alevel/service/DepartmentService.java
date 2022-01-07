package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.Department;

import java.util.Map;

public interface DepartmentService extends BaseService<Department> {

    Map<Long, String> findByEmployeeId(Long id);
}
