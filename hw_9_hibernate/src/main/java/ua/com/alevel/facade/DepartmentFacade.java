package ua.com.alevel.facade;

import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.dto.PageData;
import ua.com.alevel.dto.department.DepartmentRequestDto;
import ua.com.alevel.dto.department.DepartmentResponseDto;

public interface DepartmentFacade extends BaseFacade<DepartmentRequestDto, DepartmentResponseDto> {

    PageData<DepartmentResponseDto> findAllByEmployeeId(Integer studentId, WebRequest webRequest);
}
