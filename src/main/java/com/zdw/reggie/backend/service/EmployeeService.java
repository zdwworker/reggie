package com.zdw.reggie.backend.service;

import com.zdw.reggie.backend.domain.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeService {

    Employee selectByUsername(String username);

    int savaEmployee(Employee employee);

    int selectCont(String name);

    List<Employee> selectBypage(Map map);

    int updata(Employee employee);

    Employee selectById(long id);
}
