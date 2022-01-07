package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.dao.EmployeeDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Employee;
import ua.com.alevel.service.EmployeeService;
import ua.com.alevel.util.WebResponseUtil;

import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDao employeeDao;

    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public void create(Employee entity) {
        employeeDao.create(entity);
    }

    @Override
    public void update(Employee entity) {
        employeeDao.update(entity);
    }

    @Override
    public void delete(Long id) {
        employeeDao.delete(id);
    }

    @Override
    public Employee findById(Long id) {
        return employeeDao.findById(id);
    }

    @Override
    public DataTableResponse<Employee> findAll(DataTableRequest request) {
        DataTableResponse<Employee> dataTableResponse = employeeDao.findAll(request);
        long count = employeeDao.count();
        WebResponseUtil.initDataTableResponse(request, dataTableResponse, count);
        return dataTableResponse;
    }

    @Override
    public Map<Long, String> findAllByDepartmentId(Long departmentId) {
        return employeeDao.findAllByDepartmentId(departmentId);
    }
}
