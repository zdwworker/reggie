package com.zdw.reggie.front.service;

import com.zdw.reggie.front.domain.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    ShoppingCart selectByDishidCont(Long dishId, Long id, Long setmealId);

    int updata(ShoppingCart sc);

    int detele(Long id);

    int insert(ShoppingCart sc);

    List<ShoppingCart> selectByUserId(Long id);

    int sub(ShoppingCart shoppingCart);

    int cleanByUserId(Long id);
}
