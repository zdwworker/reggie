package com.zdw.reggie.backend.service.impl;

import com.zdw.reggie.backend.domain.Employee;
import com.zdw.reggie.backend.mapper.EmployeeMapper;
import com.zdw.reggie.backend.service.EmployeeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Resource
    private EmployeeMapper employeeMapper;

    @Override
    public Employee selectByUsername(String username) {
        return employeeMapper.selectByUsername(username) ;
    }

    @Override
    public int savaEmployee(Employee employee) {
        return employeeMapper.savaEmployee(employee);
    }

    @Override
    public int selectCont(String name) {
        return employeeMapper.selectCont(name);
    }

    @Override
    public List<Employee> selectBypage(Map map) {
        return employeeMapper.selectBypage(map);
    }

    @Override
    public int updata(Employee employee) {
        return employeeMapper.updata(employee);
    }

    @Override
    public Employee selectById(long id) {
        return employeeMapper.selectById(id);
    }
}
