package ua.com.alevel.view.dto.response;

import ua.com.alevel.persistence.entity.Employee;

public class EmployeeResponseDto extends ResponseDto {

    private String firstName;
    private String lastName;
    private Integer departmentCount;

    public EmployeeResponseDto() {
    }

    public EmployeeResponseDto(Employee employee) {
        setId(employee.getId());
        setCreated(employee.getCreated());
        setUpdated(employee.getUpdated());
        setVisible(employee.getVisible());
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getDepartmentCount() {
        return departmentCount;
    }

    public void setDepartmentCount(Integer departmentCount) {
        this.departmentCount = departmentCount;
    }

    @Override
    public String toString() {
        return "EmployeeResponseDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", departmentCount=" + departmentCount +
                '}';
    }
}
