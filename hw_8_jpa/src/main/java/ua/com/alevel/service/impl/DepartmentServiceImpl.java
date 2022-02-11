package ua.com.alevel.service.impl;

import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;
import ua.com.alevel.dao.DepartmentDao;
import ua.com.alevel.datatable.DataTableRequest;
import ua.com.alevel.datatable.DataTableResponse;
import ua.com.alevel.entity.Department;
import ua.com.alevel.exception.EntityExistException;
import ua.com.alevel.service.DepartmentService;

import java.util.List;
import java.util.Objects;

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
        checkByExist(entity.getId());
        departmentDao.update(entity);
    }

    @Override
    public void delete(Integer id) {
        checkByExist(id);
        departmentDao.delete(id);
    }

    @Override
    public Department findById(Integer id) {
        checkByExist(id);
        return departmentDao.findById(id).get();
    }

    @Override
    public List<Department> findAll() {
        return departmentDao.findAll();
    }

    @Override
    public DataTableResponse<Department> findAll(DataTableRequest request) {
        DataTableResponse<Department> dataTableResponse = new DataTableResponse<>();
        dataTableResponse.setEntities(departmentDao.findAll(request));
        dataTableResponse.setCount(departmentDao.count());
        if (MapUtils.isNotEmpty(request.getQueryMap())) {
            Object o = request.getQueryMap().get("employeeId");
            if (Objects.nonNull(o)) {
                try {
                    Integer employeeId = (Integer) o;
                    dataTableResponse.setCount(departmentDao.countByEmployeeId(employeeId));
                } catch (Exception e) {
                    dataTableResponse.setCount(departmentDao.count());
                }
            }
        }
        return dataTableResponse;
    }

    private void checkByExist(Integer id) {
        if (!departmentDao.existById(id)) {
            throw new EntityExistException("entity not found");
        }
    }
}
