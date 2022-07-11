package com.zdw.reggie.backend.controller;

import com.zdw.reggie.common.MD5Util;
import com.zdw.reggie.common.R;
import com.zdw.reggie.backend.domain.Employee;
import com.zdw.reggie.backend.service.EmployeeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class LoginController {

    @Resource
    private EmployeeService employeeService;

    @PostMapping("/employee/login")
    public R login(@RequestBody Employee employee, HttpServletRequest request){
        System.out.println("执行到了.............");
        System.out.println(request.getContextPath()+"/backend/page/login/login.html");

        String password=employee.getPassword();
         password=MD5Util.getMD5(password);
        Employee employee1 = employeeService.selectByUsername(employee.getUsername());
        if(employee1==null){
            //账号不存在
            return R.error("账号不存在..");
        }
        //账号存在，验证密码是否正确
        if(!password.equals(employee1.getPassword())){
            //密码不正确
            return R.error("密码不正确...");
        }
        //账号存在，且密码正确
        if(employee1.getStatus()==0){
            //账号已被禁用
            return R.error("账号被禁用...");
        }
        //登录成功
        request.getSession().setAttribute("userId",employee1.getId());

        return R.success(employee1);
    }
    @PostMapping("/employee/logout")
    public R logout(HttpServletRequest request){
        //安全退出，清除Session
        request.getSession().removeAttribute("userId");

        return R.success("成功退出...");
    }
}
