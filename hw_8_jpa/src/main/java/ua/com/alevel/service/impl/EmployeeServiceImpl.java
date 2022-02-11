package ua.com.alevel.service.impl;

import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;
import ua.com.alevel.dao.EmployeeDao;
import ua.com.alevel.datatable.DataTableRequest;
import ua.com.alevel.datatable.DataTableResponse;
import ua.com.alevel.entity.Employee;
import ua.com.alevel.exception.EntityExistException;
import ua.com.alevel.service.EmployeeService;

import java.util.List;
import java.util.Objects;

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
        checkByExist(entity.getId());
        employeeDao.update(entity);
    }

    @Override
    public void delete(Integer id) {
        checkByExist(id);
        employeeDao.delete(id);
    }

    @Override
    public Employee findById(Integer id) {
        checkByExist(id);
        return employeeDao.findById(id).get();
    }

    @Override
    public List<Employee> findAll() {
        return employeeDao.findAll();
    }

    @Override
    public DataTableResponse<Employee> findAll(DataTableRequest request) {
        DataTableResponse<Employee> dataTableResponse = new DataTableResponse<>();
        dataTableResponse.setEntities(employeeDao.findAll(request));
        dataTableResponse.setCount(employeeDao.count());
        if (MapUtils.isNotEmpty(request.getQueryMap())) {
            Object o = request.getQueryMap().get("departmentId");
            if (Objects.nonNull(o)) {
                try {
                    Integer departmentId = (Integer) o;
                    dataTableResponse.setCount(employeeDao.countByDepartmentId(departmentId));
                } catch (Exception e) {
                    dataTableResponse.setCount(employeeDao.count());
                }
            }
        }
        return dataTableResponse;
    }

    private void checkByExist(Integer id) {
        if (!employeeDao.existById(id)) {
            throw new EntityExistException("entity not found");
        }
    }
}
