package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.dao.DepartmentDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Department;
import ua.com.alevel.service.DepartmentService;
import ua.com.alevel.util.WebResponseUtil;

import java.util.Map;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentDao departmentDao;

    public DepartmentServiceImpl(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    @Override
    public void create(Department entity) {
        departmentDao.create(entity);
    }

    @Override
    public void update(Department entity) {
        departmentDao.update(entity);
    }

    @Override
    public void delete(Long id) {
        departmentDao.delete(id);
    }

    @Override
    public Department findById(Long id) {
        return departmentDao.findById(id);
    }

    @Override
    public DataTableResponse<Department> findAll(DataTableRequest request) {
        DataTableResponse<Department> dataTableResponse = departmentDao.findAll(request);
        long count = departmentDao.count();
        WebResponseUtil.initDataTableResponse(request, dataTableResponse, count);
        return dataTableResponse;
    }

    @Override
    public Map<Long, String> findByEmployeeId(Long id) {
        return departmentDao.findByEmployeeId(id);
    }
}
