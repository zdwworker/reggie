package com.zdw.reggie.front.mapper;


import com.zdw.reggie.front.domain.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {
    ShoppingCart selectByDishidCont(Long dishId, long userId, Long setmealId);

    int updata(ShoppingCart sc);

    int detele(Long id);

    int insert(ShoppingCart sc);

    List<ShoppingCart> selectByUserId(Long id);

    int sub(ShoppingCart shoppingCart);

    int cleanByUserId(Long userId);
}
