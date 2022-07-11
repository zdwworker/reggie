package com.zdw.reggie.front.controller;

import com.zdw.reggie.common.DateTimeUtil;
import com.zdw.reggie.common.R;
import com.zdw.reggie.common.XHID;
import com.zdw.reggie.front.domain.ShoppingCart;
import com.zdw.reggie.front.domain.User;
import com.zdw.reggie.front.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

@RestController
@Slf4j
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    /**
     * 展示购物车数据
     * @return
     */
    @GetMapping("/shoppingCart/list")
    public R<List<ShoppingCart>> shoppingCart(HttpServletRequest request){
        log.info("购物车执行了....");
        User user= (User) request.getSession().getAttribute("userId");
        List<ShoppingCart> shoppingCartList=shoppingCartService.selectByUserId(user.getId());
        return R.success(shoppingCartList);
    }

    /**
     * 添加购物车
     * @param shoppingCart
     * @param request
     * @return
     */
    @Transactional
    @PostMapping("/shoppingCart/add")
    public R<String> add(@RequestBody ShoppingCart shoppingCart, HttpServletRequest request){
        log.info("添加购物车操作执行...{}",shoppingCart);
        ///shoppingCart/add
        User user = (User) request.getSession().getAttribute("userId");

        int i=0;

        //先查询该用户购物车中是否有该菜品

        ShoppingCart sc=shoppingCartService.selectByDishidCont(shoppingCart.getDishId(),user.getId(),shoppingCart.getSetmealId());
        if (sc!=null){
            //说明购物车已有该菜品
            sc.setNumber(sc.getNumber()+1);
            BigDecimal a=sc.getAmount();
            BigDecimal b=shoppingCart.getAmount();
            BigDecimal money= a.add(b);
            sc.setAmount(money);
            //删除旧数据 添加新数据
            int j=shoppingCartService.detele(sc.getId());
            if (j>0){
                i= shoppingCartService.insert(sc);
            }
        }else {
            //第一次添加菜品进购物车
            shoppingCart.setId(XHID.nextId());
            shoppingCart.setUserId(user.getId());
            shoppingCart.setCreateTime(DateTimeUtil.getSysTime());
            shoppingCart.setNumber(1);
            i=shoppingCartService.insert(shoppingCart);
        }
        if (i>0){
            return  R.success("添加成功...");
        }

        return R.error("添加失败...");
    }


    /**
     * 在购物车删除菜品或套餐
     * @param shoppingCart
     * @return
     */
    @PostMapping("/shoppingCart/sub")
    public R<String> sub(@RequestBody ShoppingCart shoppingCart){

        int i=shoppingCartService.sub(shoppingCart);
        if(i>0){
            //删除成功
            return R.success("删除成功...");
        }
        return R.error("删除失败...");

    }

    /**
     * 清空购物车
     * @param request
     * @return
     */
    @DeleteMapping("/shoppingCart/clean")
    public R<String> clean(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("userId");
        int detele = shoppingCartService.cleanByUserId(user.getId());
        if (detele>0){
            return R.success("删除成功...");
        }
        return R.error("删除失败...");
    }
}
