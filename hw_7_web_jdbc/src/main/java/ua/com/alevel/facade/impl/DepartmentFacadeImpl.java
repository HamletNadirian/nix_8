package ua.com.alevel.facade.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.DepartmentFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Department;
import ua.com.alevel.service.DepartmentService;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.util.WebResponseUtil;
import ua.com.alevel.view.dto.request.DepartmentRequestDto;
import ua.com.alevel.view.dto.response.DepartmentResponseDto;
import ua.com.alevel.view.dto.response.PageData;

import java.sql.Timestamp;
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
        department.setLocation(departmentRequestDto.getLocation());
        department.setBudget(departmentRequestDto.getBudget());
        department.setDepartmentName(departmentRequestDto.getDepartmentName());
        departmentService.create(department);
    }

    @Override
    public void update(DepartmentRequestDto departmentRequestDto, Long id) {
        Department department = departmentService.findById(id);
        department.setDepartmentName(departmentRequestDto.getDepartmentName());
        department.setLocation(departmentRequestDto.getLocation());
        department.setBudget(departmentRequestDto.getBudget());
        department.setUpdated(new Timestamp(System.currentTimeMillis()));
        departmentService.update(department);
    }

    @Override
    public void delete(Long id) {
        departmentService.delete(id);
    }

    @Override
    public DepartmentResponseDto findById(Long id) {
        return new DepartmentResponseDto(departmentService.findById(id));
    }

    @Override
    public PageData<DepartmentResponseDto> findAll(WebRequest request) {
        DataTableRequest dataTableRequest = WebRequestUtil.initDataTableRequest(request);
        String employeeId = request.getParameter("employeeId");
        if (StringUtils.isNotBlank(employeeId)) {
            dataTableRequest.getQueryParam().put("employeeId", employeeId);
        }
        DataTableResponse<Department> tableResponse = departmentService.findAll(dataTableRequest);
        List<DepartmentResponseDto> departments = tableResponse.getItems().stream().
                map(DepartmentResponseDto::new).
                collect(Collectors.toList());

        PageData<DepartmentResponseDto> pageData = (PageData<DepartmentResponseDto>) WebResponseUtil.initPageData(tableResponse);
        pageData.setItems(departments);
        return pageData;
    }

    @Override
    public Map<Long, String> findByEmployeeId(Long id) {
        return departmentService.findByEmployeeId(id);
    }
}
