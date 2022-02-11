package ua.com.alevel.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "departments")
public class Department extends BaseEntity {

    private Integer budget;
    private String location;
    private String name;

    @ManyToMany(
            cascade = CascadeType.ALL, fetch = FetchType.LAZY
    )
    @JoinTable(
            name = "departments_employees",
            joinColumns = @JoinColumn(name = "department_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    private Set<Employee> employees = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Department that = (Department) o;
        return name == that.name && Objects.equals(budget, that.budget) && Objects.equals(location, that.location) && Objects.equals(employees, that.employees);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, budget, location, employees);
    }
}
