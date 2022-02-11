package ua.com.alevel.dto.employee;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ua.com.alevel.dto.RequestDto;

@Getter
@Setter
@ToString
public class EmployeeRequestDto extends RequestDto {

    private Integer departmentId;
    private String firstName;
    private String lastName;
}
