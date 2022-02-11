package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.datatable.DataTableRequest;
import ua.com.alevel.datatable.DataTableResponse;
import ua.com.alevel.dto.PageData;
import ua.com.alevel.dto.department.DepartmentRequestDto;
import ua.com.alevel.dto.department.DepartmentResponseDto;
import ua.com.alevel.entity.Department;
import ua.com.alevel.facade.DepartmentFacade;
import ua.com.alevel.service.DepartmentService;
import ua.com.alevel.util.WebRequestUtil;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentFacadeImpl implements DepartmentFacade {

    private final DepartmentService departmentService;

    public DepartmentFacadeImpl(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Override
    public void create(DepartmentRequestDto departmentRequestDto) {
        Department department = new Department();
        department.setName(departmentRequestDto.getName());
        department.setBudget(departmentRequestDto.getBudget());
        department.setLocation(departmentRequestDto.getLocation());
        department.setCreated(new Timestamp(System.currentTimeMillis()));
        departmentService.create(department);
    }

    @Override
    public void update(DepartmentRequestDto departmentRequestDto, Integer id) {
        Department department = departmentService.findById(id);
        department.setName(departmentRequestDto.getName());
        department.setLocation(departmentRequestDto.getLocation());
        department.setBudget(departmentRequestDto.getBudget());
        department.setUpdated(new Timestamp(System.currentTimeMillis()));
        departmentService.update(department);
    }

    @Override
    public void delete(Integer id) {
        departmentService.delete(id);
    }

    @Override
    public DepartmentResponseDto findById(Integer id) {
        return new DepartmentResponseDto(departmentService.findById(id));
    }

    @Override
    public List<DepartmentResponseDto> findAll() {
        List<Department> depart = departmentService.findAll();
        return depart.
                stream().
                map(DepartmentResponseDto::new).
                collect(Collectors.toList());
    }

    @Override
    public PageData<DepartmentResponseDto> findAll(WebRequest request) {
        DataTableRequest dataTableRequest = WebRequestUtil.generateDataTableRequest(request);
        DataTableResponse<Department> dataTableResponse = departmentService.findAll(dataTableRequest);
        List<DepartmentResponseDto> dtos = dataTableResponse.getEntities().
                stream().
                map(DepartmentResponseDto::new).toList();
        PageData<DepartmentResponseDto> pageData = new PageData<>(dtos);
        pageData.setCurrentPage(dataTableRequest.getPage());
        pageData.setPageSize(dataTableRequest.getSize());
        pageData.setSort(dataTableRequest.getSort());
        pageData.setOrder(dataTableRequest.getOrder());
        pageData.initPageDataState(dataTableResponse);
        return pageData;
    }

    @Override
    public PageData<DepartmentResponseDto> findAllByEmployeeId(Integer employeeId, WebRequest webRequest) {
        DataTableRequest dataTableRequest = WebRequestUtil.generateDataTableRequest(webRequest);
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("employeeId", employeeId);
        dataTableRequest.setQueryMap(queryMap);
        DataTableResponse<Department> dataTableResponse = departmentService.findAll(dataTableRequest);
        List<DepartmentResponseDto> dtos = dataTableResponse.
                getEntities().
                stream().
                map(DepartmentResponseDto::new).
                toList();
        PageData<DepartmentResponseDto> pageData = new PageData<>(dtos);
        pageData.setCurrentPage(dataTableRequest.getPage());
        pageData.setPageSize(dataTableRequest.getSize());
        pageData.setSort(dataTableRequest.getSort());
        pageData.setOrder(dataTableRequest.getOrder());
        pageData.initPageDataState(dataTableResponse);
        return pageData;
    }
}
