package ua.com.alevel.view.dto.request;

public class DepartmentRequestDto extends RequestDto {

    private String departmentName;
    private Integer budget;
    private String location;

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

    @Override
    public String toString() {
        return "DepartmentRequestDto{" +
                "departmentName='" + departmentName + '\'' +
                ", budget=" + budget +
                ", location='" + location + '\'' +
                '}';
    }
}
