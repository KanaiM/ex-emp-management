package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Employee;
import com.example.form.UpdateEmployeeForm;
import com.example.service.EmployeeService;

import jakarta.servlet.http.HttpSession;

/**
 * 従業員情報を検索する処理を記述する 
 * @author kanaiminami
 */
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    /**
     * 従業員⼀覧を出⼒する
     * @param model
     * @return 全件取得した従業員情報
     */
    @GetMapping("/showList")
    public String showList(Model model) {
        List<Employee> employeeList = employeeService.showList();
        model.addAttribute("emlpoyeeList", employeeList);
        return "employee/list";
    }
    
    /**
     * 従業員情報を検索する処理を記述する 
     * @param id
     * @param model
     * @param form
     * @return リストで選択した従業員の従業員情報
     */
    @GetMapping("/showDetail")
    public String showDetail(String id, Model model, UpdateEmployeeForm form){
        Employee employee = employeeService.showDetail(Integer.parseInt(id));
        model.addAttribute("employee", employee);
        return "employee/detail";       
    }

    /**
     * 扶養⼈数を更新する
     * @param form
     * @return 従業員⼀覧を出⼒する
     */
    @PostMapping("/update")
    public String update(UpdateEmployeeForm form) {
        System.out.println(form.getId());
        employeeService.showDetail(Integer.parseInt(form.getId()));
        Employee employee = new Employee();
        employee.setDependentsCount(Integer.parseInt(form.getDependentsCount()));
        employeeService.update(employee);
        return "redirect:/employee/showList";
    }
    
}
