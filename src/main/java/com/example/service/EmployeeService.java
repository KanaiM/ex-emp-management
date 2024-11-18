package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Employee;
import com.example.repository.EmployeeRepository;

/**
 * 従業員情報更新時に使⽤するフォーム 
 * @author kanaiminami
 */

@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * 従業員情報を全件取得する
     * @return 全件取得した従業員情報
     */
    public List<Employee> showList(){
        return employeeRepository.findAll();
    }

    /**
     * 従業員情報を取得する
     * @param id
     * @return IDから呼び出された従業員情報
     */
    public Employee showDetail(Integer id){
        return employeeRepository.load(id);
    }

    /**
     * 従業員情報を更新する
     * @param employee
     */
    public void update(Employee employee){
        employeeRepository.update(employee);
    } 
}
