package ua.com.alevel.view.dto.response;

import ua.com.alevel.persistence.entity.Department;

public class DepartmentResponseDto extends ResponseDto {

    private String departmentName;
    private Integer budget;
    private String location;
    private Integer employeeCount;

    public DepartmentResponseDto() {
        this.employeeCount = 0;
    }

    public DepartmentResponseDto(Department department) {
        this();
        setId(department.getId());
        setCreated(department.getCreated());
        setUpdated(department.getUpdated());
        setVisible(department.getVisible());
        this.departmentName = department.getDepartmentName();
        this.budget = department.getBudget();
        this.location = department.getLocation();
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(Integer employeeCount) {
        this.employeeCount = employeeCount;
    }
}
