package com.zdw.reggie.front.controller;

import com.zdw.reggie.common.R;
import com.zdw.reggie.common.ValidateCodeUtils;
import com.zdw.reggie.common.XHID;
import com.zdw.reggie.front.domain.User;
import com.zdw.reggie.front.mapper.UserMapper;
import com.zdw.reggie.front.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@Slf4j
public class FrontLoginController {

    @Autowired
    private UserService userService;

    /**
     * 获取验证码
     */
    @GetMapping("/user/getCode")
    public void getCode(@RequestParam Map map, HttpSession session){
        log.info("获取验证码执行中...{}",map);
        String integer = ValidateCodeUtils.generateValidateCode(4).toString();
        log.info("code:---------->{}",integer);
        session.setAttribute("code",integer);

    }
    @PostMapping("/user/login")
    public R<String> login(@RequestBody Map map, HttpSession session, HttpServletRequest request){
        ///user/login
        log.info("用户登录执行中...{}",map);
        //验证验证码CODE是否正确
        String code = (String) map.get("code");
        String code1 = (String) session.getAttribute("code");
        String phone=(String)map.get("phone");
        if (code!=null && code!=""){
            if (code.equals(code1)){
                //相等，验证通过
                //判断是否为新用户
               User u= userService.selectByphone(phone);
               if (u!=null){
                   //不是新用户
                   request.getSession().setAttribute("userId",u);
                   return R.success("登录成功...");
               }
               //新用户，增加用户
                User user=new User();
               user.setId(XHID.nextId());
               user.setPhone(phone);
               int j= userService.insertUser(user);
               if (j>0){
                   request.getSession().setAttribute("userId",user);
                  // session.setAttribute("userId",user);
                   return R.success("登录成功...");
               }
            }
        }
        return R.error("登录失败...");
    }

}
