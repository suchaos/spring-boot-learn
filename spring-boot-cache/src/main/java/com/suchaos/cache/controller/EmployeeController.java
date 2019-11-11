package com.suchaos.cache.controller;

import com.suchaos.cache.model.auto.Employee;
import com.suchaos.cache.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * EmployeeController
 *
 * @author suchao
 * @date 2019/11/11
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/get/{id}")
    public Employee getEmployeeById(@PathVariable(name = "id") Integer id) {
        return employeeService.getEmployeeById(id);
    }

    @PostMapping("/update")
    public Employee updateEmployee(@RequestBody Employee employee) {
        return employeeService.updateEmployee(employee);
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployeeById(@PathVariable(name = "id") Integer id) {
        return employeeService.deleteEmployeeById(id);
    }

}
