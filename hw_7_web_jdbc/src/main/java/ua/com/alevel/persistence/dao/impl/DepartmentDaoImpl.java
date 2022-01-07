package ua.com.alevel.persistence.dao.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.config.jpa.JpaConfig;
import ua.com.alevel.persistence.dao.DepartmentDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Department;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DepartmentDaoImpl implements DepartmentDao {

    private final JpaConfig jpaConfig;
    private static final String CREATE_DEPARTMENT_QUERY = "insert into departments values(default,?,?,?,?,?,?)";
    private static final String UPDATE_DEPARTMENT_QUERY = "update departments set department_name = ?,location = ?,budget = ?,updated = ? where id = ";
    private static final String DELETE_DEPARTMENT_QUERY = "delete from departments where id = ";
    private static final String FIND_ALL_DEPARTMENTS_QUERY = "select * from departments";
    private static final String FIND_DEPARTMENT_BY_ID_QUERY = "select * from departments where id = ";
    private static final String FIND_ALL_SIMPLE_DEPARTMENTS_BY_EMPLOYEE_ID_QUERY = "select id, department_name from departments left join employee_department ab on departments.id = ab.department_id where ab.employee_id = ";
    private static final String FIND_ALL_DEPARTMENTS_BY_EMPLOYEE_ID_QUERY = "select * from departments left join employee_department ab on departments.id = ab.department_id where ab.employee_id = ";

    public DepartmentDaoImpl(JpaConfig jpaConfig) {
        this.jpaConfig = jpaConfig;
    }

    @Override
    public void create(Department entity) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(CREATE_DEPARTMENT_QUERY)) {
            preparedStatement.setTimestamp(1, new Timestamp(entity.getCreated().getTime()));
            preparedStatement.setTimestamp(2, new Timestamp(entity.getUpdated().getTime()));
            preparedStatement.setBoolean(3, entity.getVisible());
            preparedStatement.setString(4, entity.getDepartmentName());
            preparedStatement.setString(5, entity.getLocation());
            preparedStatement.setInt(6, entity.getBudget());
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("e = " + e.getMessage());
        }
    }

    @Override
    public void update(Department entity) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(UPDATE_DEPARTMENT_QUERY + entity.getId())) {
            preparedStatement.setString(1, entity.getDepartmentName());
            preparedStatement.setString(2, entity.getLocation());
            preparedStatement.setInt(3, entity.getBudget());
            preparedStatement.setTimestamp(4, new Timestamp(entity.getUpdated().getTime()));
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("e = " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement(DELETE_DEPARTMENT_QUERY + id)) {
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
    public Department findById(Long id) {
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_DEPARTMENT_BY_ID_QUERY + id)) {
            while (resultSet.next()) {
                return initDepartmentByResultSet(resultSet);
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return null;
    }

    @Override
    public DataTableResponse<Department> findAll(DataTableRequest request) {
        List<Department> departments = new ArrayList<>();
        Map<Object, Object> otherParamMap = new HashMap<>();

        int limit = (request.getCurrentPage() - 1) * request.getPageSize();

        String sql = "select id,created,updated,visible,department_name, location,budget, count(department_id) as employeeCount " +
                "from departments as department left join employee_department as ab on department.id = ab.department_id " +
                "group by department.id order by " +
                request.getSort() + " " +
                request.getOrder() + " limit " +
                limit + "," +
                request.getPageSize();

        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(sql)) {

            // try(ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_ALL_DEPARTMENTS_QUERY)) {
            while (resultSet.next()) {
                DepartmentResultSet departmentResultSet = convertResultSetToSimpleDepartment(resultSet);
                departments.add(initDepartmentByResultSet(resultSet));
                otherParamMap.put(departmentResultSet.getDepartment().getId(), departmentResultSet.getEmployeeCount());
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        DataTableResponse<Department> dataTableResponse = new DataTableResponse<>();
        dataTableResponse.setItems(departments);
        dataTableResponse.setOtherParamMap(otherParamMap);
        return dataTableResponse;
    }

    @Override
    public long count() {
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery("select count(*) as count from departments")) {
            while (resultSet.next()) {
                return resultSet.getLong("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private DepartmentResultSet convertResultSetToSimpleDepartment(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        String departmentName = resultSet.getString("department_name");
        String location = resultSet.getString("location");
        int budget = resultSet.getInt("budget");
        int employeeCount = resultSet.getInt("employeeCount");

        Department department = new Department();
        department.setId(id);
        department.setDepartmentName(departmentName);
        department.setLocation(location);
        department.setBudget(budget);

        return new DepartmentResultSet(department, employeeCount);
    }

    private Department initDepartmentByResultSet(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        Timestamp created = resultSet.getTimestamp("created");
        Timestamp updated = resultSet.getTimestamp("updated");
        Boolean visible = resultSet.getBoolean("visible");
        String departmentName = resultSet.getString("department_name");
        String location = resultSet.getString("location");
        int budget = resultSet.getInt("budget");

        Department department = new Department();
        department.setId(id);
        department.setCreated(created);
        department.setUpdated(updated);
        department.setVisible(visible);
        department.setDepartmentName(departmentName);
        department.setLocation(location);
        department.setBudget(budget);

        return department;
    }

    @Override
    public Map<Long, String> findByEmployeeId(Long employeeId) {
        Map<Long, String> map = new HashMap<>();
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_ALL_SIMPLE_DEPARTMENTS_BY_EMPLOYEE_ID_QUERY + employeeId)) {
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String departmentName = resultSet.getString("department_name");
                map.put(id, departmentName);
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return map;
    }

    @Override
    public List<Department> findAllByEmployeeId(Long id) {
        List<Department> departments = new ArrayList<>();
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(FIND_ALL_DEPARTMENTS_BY_EMPLOYEE_ID_QUERY + id)) {
            while (resultSet.next()) {
                departments.add(initDepartmentByResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return departments;
    }

    private static class DepartmentResultSet {

        private final Department department;
        private final int employeeCount;

        public DepartmentResultSet(Department department, int employeeCount) {
            this.department = department;
            this.employeeCount = employeeCount;
        }

        public Department getDepartment() {
            return department;
        }

        public int getEmployeeCount() {
            return employeeCount;
        }
    }
}
