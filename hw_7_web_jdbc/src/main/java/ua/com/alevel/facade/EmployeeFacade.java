package ua.com.alevel.facade;

import ua.com.alevel.view.dto.request.EmployeeRequestDto;
import ua.com.alevel.view.dto.response.EmployeeResponseDto;

import java.util.Map;

public interface EmployeeFacade extends BaseFacade<EmployeeRequestDto, EmployeeResponseDto> {

    Map<Long, String> findAllByDepartmentId(Long departmentId);
}
