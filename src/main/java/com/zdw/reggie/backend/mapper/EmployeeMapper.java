package com.zdw.reggie.backend.mapper;

import com.zdw.reggie.backend.domain.Employee;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface EmployeeMapper {

  Employee selectByUsername(String username);

    int savaEmployee(Employee employee);

    int selectCont(String name);

  List<Employee> selectBypage(Map map);

    int updata(Employee employee);

  Employee selectById(long id);
}
