package ua.com.alevel.dto.department;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import ua.com.alevel.dto.ResponseDto;
import ua.com.alevel.entity.Department;

@Getter
@Setter
@NoArgsConstructor
public class DepartmentResponseDto extends ResponseDto {
    private String name;
    private int countOfEmployees;
    private Integer budget;
    private String location;

    public DepartmentResponseDto(Department department) {
        super(department.getId());
        if (department.getName() != null) {
            this.name = department.getName();
        }
        if (CollectionUtils.isNotEmpty(department.getEmployees())) {
            this.countOfEmployees = department.getEmployees().size();
        }

        if (department.getLocation() != null) {
            this.location = department.getLocation();
        }
        if (department.getBudget() != null) {
            this.budget = department.getBudget();
        }
    }
}
