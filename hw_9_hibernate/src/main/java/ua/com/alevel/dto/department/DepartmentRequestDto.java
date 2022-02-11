package ua.com.alevel.dto.department;

import lombok.Getter;
import lombok.Setter;
import ua.com.alevel.dto.RequestDto;

@Getter
@Setter
public class DepartmentRequestDto extends RequestDto {
    private String name;
    private Integer budget;
    private String location;
}
