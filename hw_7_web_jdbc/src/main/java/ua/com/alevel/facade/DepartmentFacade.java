package ua.com.alevel.facade;

import ua.com.alevel.view.dto.request.DepartmentRequestDto;
import ua.com.alevel.view.dto.response.DepartmentResponseDto;

import java.util.Map;

public interface DepartmentFacade extends BaseFacade<DepartmentRequestDto, DepartmentResponseDto> {

    Map<Long, String> findByEmployeeId(Long id);
}
