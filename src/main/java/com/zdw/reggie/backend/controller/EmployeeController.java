package com.zdw.reggie.backend.controller;

import com.zdw.reggie.backend.domain.Employee;
import com.zdw.reggie.backend.service.EmployeeService;
import com.zdw.reggie.common.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @PostMapping("/employee")
    public R<String> sava(HttpServletRequest request, @RequestBody Employee employee){
        log.info("添加功能进行中...");
        System.out.println(request.getRequestURL());

        //当前使用者的ID
        Long userId= (Long) request.getSession().getAttribute("userId");

        employee.setCreateTime(DateTimeUtil.getSysTime());
        //初始化密码为123456
        employee.setPassword(MD5Util.getMD5("123456"));
        employee.setId(XHID.nextId());
        employee.setCreateUser(userId);
        employee.setStatus(1);
        employee.setUpdateUser(userId);
        employee.setUpdateTime(DateTimeUtil.getSysTime());

        int i=employeeService.savaEmployee(employee);
        if (i!=0){
            return R.success("添加成功...");
        }
        return R.error("添加失败，未知错误...");
    }

    /**
     * 查询员工信息，分页展示
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/employee/page")
    public  R<Object> page(int page, int pageSize, String name, HttpServletResponse response){
       log.info("接收到了前端信息，page={},pageSize={},name={}",page,pageSize,name);
        int pageNo=(page-1)*pageSize;
        Map map=new HashMap();
        map.put("pageNo",pageNo);
        map.put("pageSize",pageSize);
        map.put("name",name);

       int cont=employeeService.selectCont(name);
       List<Employee> employeeList=employeeService.selectBypage(map);

       Map map1=new HashMap<>();
      // map1.put("code","1");
       map1.put("records",employeeList);
       map1.put("total",cont);
      // PrintJson.printJsonObj(response,map1);
        return R.success(map1);
    }


    /**
     * 修改员工信息的方法
     * @param employee
     * @param request
     * @return
     */
    @PutMapping("/employee")
    public R<String> updata(@RequestBody Employee employee,HttpServletRequest request){

        employee.setUpdateTime(DateTimeUtil.getSysTime());
        employee.setUpdateUser((Long) request.getSession().getAttribute("userId"));

        int i=employeeService.updata(employee);
        if(i!=0){
            //修改成功
            return R.success("修改成功...");
        }
        return R.error("修改失败...");
    }

    /**
     * 根据ID查询员工信息
     * @param id
     * @return
     */
    @GetMapping("/employee/{id}")
    public R<Employee> selectById(@PathVariable long id){
        Employee employee= employeeService.selectById(id);
        if(employee!=null){
            return R.success(employee);
        }
        return R.error("查询员工信息失败...");
    }
}
