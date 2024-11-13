package com.example.repository;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Employee;
/**
 * employees テーブルを操作するリポジトリ(Dao) 
 * @author kanaiminami
 */
@Repository
public class EmployeeRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    /**
     * Employeeオブジェクトの⽣成(ラムダ式)
     * @return employee
     */
    private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER = (rs,i) ->{
        Employee employee = new Employee();
        employee.setId(rs.getInt("id"));
        employee.setName(rs.getString("name"));
        employee.setImage(rs.getString("image"));
        employee.setGender(rs.getString("gender"));
        employee.setHireDate(rs.getDate("hire_date"));
        employee.setMailAddress(rs.getString("mail_address"));
        employee.setZipCode(rs.getString("zip_code"));
        employee.setAddress(rs.getString("address"));
        employee.setTelephone(rs.getString("telephone"));
        employee.setSalary(rs.getInt("salary"));
        employee.setCharacteristics(rs.getString("characteristics"));
        employee.setDependentsCount(rs.getInt("dependents_count"));
        return employee;
    };

    /**
     * 処理内容従業員⼀覧情報を⼊社⽇順(降順)で取得する(従業員が存在しない場合はサイズ0件の従業員⼀覧を返す)
     * @return employeeList
     */
    public List<Employee> findAll(){
        String sql = "SELECT id, name, image, gender, hire_date, mail_address, zip_code, address, telephone, salary, characteristics, dependents_count,"
            + "FROM employees ORDER BY hire_date DESC;";

        List<Employee> employeesList
            =template.query(sql, EMPLOYEE_ROW_MAPPER);
        if (employeesList.size() == 0) { 
            return null; 
        } return employeesList;
    }

    /**
     * 主キーから従業員情報を取得する(従業員が存在しない場合はSpringが⾃動的に例外を発⽣します)
     * @param id
     * @return employee
     */
    public Employee load(Integer id){
        String sql = "SELECT id, name, image, gender, hire_date, mail_address, zip_code, address, telephone, salary, characteristics, dependents_count,"
            + "FROM employees WHERE id=:id;";
        SqlParameterSource param = new MapSqlParameterSource();
        Employee employee = new Employee();
        try { 
        employee = template.queryForObject(sql, param, EMPLOYEE_ROW_MAPPER);
        } catch (Exception e) {
            return null; 
        }return employee;
    }

    /**
     * 従業員情報を変更する
     * @param employee
     */
    public void update(Employee employee){
        SqlParameterSource param = new BeanPropertySqlParameterSource(employee);
        String sql = "UPDATE employees SET name=:name, image=:image, gender=:gender, hire_date=:hireDate,"
            + " mail_address=:mailAddress, zip_code=:zipCode, address=:address, telephone=:telephone,"
            + " salary=:salary, characteristics=:characteristics, dependents_count=:dependentsCount"
            + " WHERE id=:id;";

        template.update(sql, param);
    }
}
