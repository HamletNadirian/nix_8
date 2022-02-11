package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.datatable.DataTableRequest;
import ua.com.alevel.datatable.DataTableResponse;
import ua.com.alevel.dto.PageData;
import ua.com.alevel.dto.employee.EmployeeRequestDto;
import ua.com.alevel.dto.employee.EmployeeResponseDto;
import ua.com.alevel.entity.Department;
import ua.com.alevel.entity.Employee;
import ua.com.alevel.facade.EmployeeFacade;
import ua.com.alevel.service.DepartmentService;
import ua.com.alevel.service.EmployeeService;
import ua.com.alevel.util.WebRequestUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeFacadeImpl implements EmployeeFacade {

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    public EmployeeFacadeImpl(
            EmployeeService employeeService,
            DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    @Override
    public void create(EmployeeRequestDto employeeRequestDto) {
        Employee employee = new Employee();
        employee.setFirstName(employeeRequestDto.getFirstName());
        employee.setLastName(employeeRequestDto.getLastName());
        Department department = departmentService.findById(employeeRequestDto.getDepartmentId());
        department.getEmployees().add(employee);
        departmentService.update(department);
    }

    @Override
    public void update(EmployeeRequestDto employeeRequestDto, Integer id) {
        Employee employee = employeeService.findById(id);
        employee.setFirstName(employeeRequestDto.getFirstName());
        employee.setLastName(employeeRequestDto.getLastName());
        employeeService.update(employee);
    }

    @Override
    public void delete(Integer id) {
        employeeService.delete(id);
    }

    @Override
    public EmployeeResponseDto findById(Integer id) {
        return new EmployeeResponseDto(employeeService.findById(id));
    }

    @Override
    public List<EmployeeResponseDto> findAll() {
        List<Employee> employees = employeeService.findAll();
        return employees.stream().map(EmployeeResponseDto::new).collect(Collectors.toList());
    }

    @Override
    public PageData<EmployeeResponseDto> findAll(WebRequest request) {
        DataTableRequest dataTableRequest = WebRequestUtil.generateDataTableRequest(request);
        DataTableResponse<Employee> dataTableResponse = employeeService.findAll(dataTableRequest);
        List<EmployeeResponseDto> dtos = dataTableResponse.
                getEntities().
                stream().
                map(EmployeeResponseDto::new).
                toList();
        PageData<EmployeeResponseDto> pageData = new PageData<>(dtos);
        pageData.setCurrentPage(dataTableRequest.getPage());
        pageData.setPageSize(dataTableRequest.getSize());
        pageData.setSort(dataTableRequest.getSort());
        pageData.setOrder(dataTableRequest.getOrder());
        pageData.initPageDataState(dataTableResponse);
        return pageData;
    }

    @Override
    public PageData<EmployeeResponseDto> findAllByDepartmentId(Integer departmentId, WebRequest request) {
        DataTableRequest dataTableRequest = WebRequestUtil.generateDataTableRequest(request);
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("departmentId", departmentId);
        dataTableRequest.setQueryMap(queryMap);
        DataTableResponse<Employee> dataTableResponse = employeeService.findAll(dataTableRequest);
        List<EmployeeResponseDto> dtos = dataTableResponse.
                getEntities().
                stream().
                map(EmployeeResponseDto::new).
                toList();
        PageData<EmployeeResponseDto> pageData = new PageData<>(dtos);
        pageData.setCurrentPage(dataTableRequest.getPage());
        pageData.setPageSize(dataTableRequest.getSize());
        pageData.setSort(dataTableRequest.getSort());
        pageData.setOrder(dataTableRequest.getOrder());
        pageData.initPageDataState(dataTableResponse);
        return pageData;
    }
}
