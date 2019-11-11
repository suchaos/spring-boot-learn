package com.suchaos.cache.service;

import com.suchaos.cache.mapper.auto.EmployeeMapper;
import com.suchaos.cache.model.auto.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * EmployeeService
 *
 * @author suchao
 * @date 2019/11/11
 */
@Service
@Slf4j
@CacheConfig(cacheNames = "emp")
public class EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Cacheable
    public Employee getEmployeeById(Integer id) {
        log.info("getEmployeeById 通过数据库");
        return employeeMapper.selectByPrimaryKey(id);
    }

    @CachePut(key = "#result.id")
    public Employee updateEmployee(Employee employee) {
        log.info("updateEmployee 更新数据");
        employeeMapper.updateByPrimaryKeySelective(employee);
        return employee;
    }

    @CacheEvict
    public String deleteEmployeeById(Integer id) {
        log.info("updateEmployee 删除数据");
        int num = employeeMapper.deleteByPrimaryKey(id);
        if (num > 0) {
            return "success";
        } else {
            return "fail";
        }
    }
}
