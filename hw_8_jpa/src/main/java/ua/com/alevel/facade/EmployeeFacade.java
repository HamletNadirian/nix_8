package ua.com.alevel.facade;

import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.dto.PageData;
import ua.com.alevel.dto.employee.EmployeeRequestDto;
import ua.com.alevel.dto.employee.EmployeeResponseDto;

public interface EmployeeFacade extends BaseFacade<EmployeeRequestDto, EmployeeResponseDto> {

    PageData<EmployeeResponseDto> findAllByDepartmentId(Integer courseId, WebRequest webRequest);
}
