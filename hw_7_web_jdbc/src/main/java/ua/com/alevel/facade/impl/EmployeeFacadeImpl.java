package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.EmployeeFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Employee;
import ua.com.alevel.service.EmployeeService;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.util.WebResponseUtil;
import ua.com.alevel.view.dto.request.EmployeeRequestDto;
import ua.com.alevel.view.dto.response.EmployeeResponseDto;
import ua.com.alevel.view.dto.response.PageData;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeFacadeImpl implements EmployeeFacade {

    private final EmployeeService employeeService;

    public EmployeeFacadeImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void create(EmployeeRequestDto employeeRequestDto) {
        Employee employee = new Employee();
        employee.setFirstName(employeeRequestDto.getFirstName());
        employee.setLastName(employeeRequestDto.getLastName());
        employeeService.create(employee);
    }

    @Override
    public void update(EmployeeRequestDto employeeRequestDto, Long id) {
        Employee employee = employeeService.findById(id);
        employee.setFirstName(employeeRequestDto.getFirstName());
        employee.setLastName(employeeRequestDto.getLastName());
        employee.setUpdated(new Timestamp(System.currentTimeMillis()));
        employeeService.update(employee);
    }

    @Override
    public void delete(Long id) {
        employeeService.delete(id);
    }

    @Override
    public EmployeeResponseDto findById(Long id) {
        return new EmployeeResponseDto(employeeService.findById(id));
    }

    @Override
    public PageData<EmployeeResponseDto> findAll(WebRequest request) {
        DataTableRequest dataTableRequest = WebRequestUtil.initDataTableRequest(request);
        DataTableResponse<Employee> tableResponse = employeeService.findAll(dataTableRequest);

        List<EmployeeResponseDto> employees = tableResponse.getItems().stream().
                map(EmployeeResponseDto::new).
                peek(employeeResponseDto -> employeeResponseDto.setDepartmentCount((Integer) tableResponse.
                        getOtherParamMap().get(employeeResponseDto.getId()))).
                collect(Collectors.toList());

        PageData<EmployeeResponseDto> pageData = (PageData<EmployeeResponseDto>) WebResponseUtil.initPageData(tableResponse);
        pageData.setItems(employees);

        return pageData;
    }

    @Override
    public Map<Long, String> findAllByDepartmentId(Long departmentId) {
        return employeeService.findAllByDepartmentId(departmentId);
    }
}
