package ua.com.alevel.dto.employee;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import ua.com.alevel.dto.ResponseDto;
import ua.com.alevel.entity.Employee;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeResponseDto extends ResponseDto {

    private String firstName;
    private String lastName;
    private int countOfDepartments;

    public EmployeeResponseDto(Employee employee) {
        super(employee.getId());
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        if (CollectionUtils.isNotEmpty(employee.getDepartments())) {
            this.countOfDepartments = employee.getDepartments().size();
        }
    }
}
