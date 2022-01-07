package ua.com.alevel.persistence.dao.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.config.jpa.JpaConfig;
import ua.com.alevel.persistence.dao.EmployeeDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Employee;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

@Service
public class EmployeeDaoImpl implements EmployeeDao {

    private final JpaConfig jpaConfig;

    public EmployeeDaoImpl(JpaConfig jpaConfig) {
        this.jpaConfig = jpaConfig;
    }

    private static final String CREATE_EMPLOYEE_QUERY = "insert into employees values(default, ?,?,?,?,?)";
    private static final String UPDATE_EMPLOYEE_QUERY = "update employees set first_name = ?,last_name = ?,updated = ? where id =  ";
    private static final String DELETE_EMPLOYEE_QUERY = "delete from employees where id = ";
    private static final String FIND_EMPLOYEE_BY_ID_QUERY = "select * from employees where id = ";
    private static final String FIND_EMPLOYEE_BY_DEPARTMENT_ID_QUERY = "select id, first_name, last_name from employees left join employee_department ab on employees.id = ab.employee_id where department_id = ";

    @Override
    public void create(Employee entity) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(CREATE_EMPLOYEE_QUERY)) {
            preparedStatement.setTimestamp(1, new Timestamp(entity.getCreated().getTime()));
            preparedStatement.setTimestamp(2, new Timestamp(entity.getUpdated().getTime()));
            preparedStatement.setBoolean(3, entity.getVisible());
            preparedStatement.setString(4, entity.getFirstName());
            preparedStatement.setString(5, entity.getLastName());
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("e = " + e.getMessage());
        }
    }

    @Override
    public void update(Employee entity) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(UPDATE_EMPLOYEE_QUERY + entity.getId())) {
            preparedStatement.setString(1, entity.getFirstName());
            preparedStatement.setString(2, entity.getLastName());
            preparedStatement.setTimestamp(3, new Timestamp(entity.getUpdated().getTime()));
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("e = " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(DELETE_EMPLOYEE_QUERY + id)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean existById(Long id) {
        return false;
    }

    @Override
    public Employee findById(Long id) {
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_EMPLOYEE_BY_ID_QUERY + id)) {
            while (resultSet.next()) {
                return convertResultSetToEmployee(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public DataTableResponse<Employee> findAll(DataTableRequest request) {
        List<Employee> employees = new ArrayList<>();
        Map<Object, Object> otherParamMap = new HashMap<>();

        int limit = (request.getCurrentPage() - 1) * request.getPageSize();

        String sql = "select id, first_name, last_name, count(employee_id) as departmentCount " +
                "from employees as employee left join employee_department as ab on employee.id = ab.employee_id " +
                "group by employee.id order by " +
                request.getSort() + " " +
                request.getOrder() + " limit " +
                limit + "," +
                request.getPageSize();

        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(sql)) {
            while (resultSet.next()) {
                EmployeeResultSet employeeResultSet = convertResultSetToSimpleEmployee(resultSet);
                employees.add(employeeResultSet.getEmployee());
                otherParamMap.put(employeeResultSet.getEmployee().getId(), employeeResultSet.getDepartmentCount());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        DataTableResponse<Employee> tableResponse = new DataTableResponse<>();
        tableResponse.setItems(employees);
        tableResponse.setOtherParamMap(otherParamMap);
        return tableResponse;
    }

    @Override
    public long count() {
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery("select count(*) as count from employees")) {
            while (resultSet.next()) {
                return resultSet.getLong("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private EmployeeResultSet convertResultSetToSimpleEmployee(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        int departmentCount = resultSet.getInt("departmentCount");

        Employee employee = new Employee();
        employee.setId(id);
        employee.setFirstName(firstName);
        employee.setLastName(lastName);

        return new EmployeeResultSet(employee, departmentCount);
    }

    private Employee convertResultSetToEmployee(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        Timestamp created = resultSet.getTimestamp("created");
        Timestamp updated = resultSet.getTimestamp("updated");
        Boolean visible = resultSet.getBoolean("visible");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");

        Employee employee = new Employee();
        employee.setId(id);
        employee.setCreated(created);
        employee.setUpdated(updated);
        employee.setVisible(visible);
        employee.setFirstName(firstName);
        employee.setLastName(lastName);

        return employee;
    }

    @Override
    public Map<Long, String> findAllByDepartmentId(Long departmentId) {
        Map<Long, String> map = new HashMap<>();
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_EMPLOYEE_BY_DEPARTMENT_ID_QUERY + departmentId)) {
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                map.put(id, firstName + " " + lastName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    private static class EmployeeResultSet {

        private final Employee employee;
        private final int departmentCount;

        private EmployeeResultSet(Employee employee, int departmentCount) {
            this.employee = employee;
            this.departmentCount = departmentCount;
        }

        public Employee getEmployee() {
            return employee;
        }

        public int getDepartmentCount() {
            return departmentCount;
        }
    }
}
