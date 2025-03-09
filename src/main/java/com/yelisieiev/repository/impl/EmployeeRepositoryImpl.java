package com.yelisieiev.repository.impl;

import com.yelisieiev.model.Employee;
import com.yelisieiev.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeeRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Employee save(Employee employee) {
        String sql = "INSERT INTO employees (first_name, last_name, email, position, salary) VALUES (?, ?, ?, ?, ?) RETURNING id";
        Long id = jdbcTemplate.queryForObject(sql, Long.class,
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getPosition(),
                employee.getSalary());
        
        employee.setId(id);
        return employee;
    }

    @Override
    public List<Employee> findAll() {
        String sql = "SELECT * FROM employees";
        return jdbcTemplate.query(sql, new EmployeeRowMapper());
    }

    @Override
    public Optional<Employee> findById(Long id) {
        String sql = "SELECT * FROM employees WHERE id = ?";
        try {
            Employee employee = jdbcTemplate.queryForObject(sql, new EmployeeRowMapper(), id);
            return Optional.ofNullable(employee);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Employee update(Employee employee) {
        String sql = "UPDATE employees SET first_name = ?, last_name = ?, email = ?, position = ?, salary = ? WHERE id = ?";
        jdbcTemplate.update(sql,
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getPosition(),
                employee.getSalary(),
                employee.getId());
        return employee;
    }

    @Override
    public boolean deleteById(Long id) {
        String sql = "DELETE FROM employees WHERE id = ?";
        return jdbcTemplate.update(sql, id) > 0;
    }

    private static class EmployeeRowMapper implements RowMapper<Employee> {
        @Override
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
            Employee employee = new Employee();
            employee.setId(rs.getLong("id"));
            employee.setFirstName(rs.getString("first_name"));
            employee.setLastName(rs.getString("last_name"));
            employee.setEmail(rs.getString("email"));
            employee.setPosition(rs.getString("position"));
            employee.setSalary(rs.getDouble("salary"));
            return employee;
        }
    }
}