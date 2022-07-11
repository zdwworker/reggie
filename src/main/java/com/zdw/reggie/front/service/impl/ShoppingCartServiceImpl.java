package com.zdw.reggie.front.service.impl;

import com.zdw.reggie.front.domain.ShoppingCart;
import com.zdw.reggie.front.mapper.ShoppingCartMapper;
import com.zdw.reggie.front.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;


    @Override
    public ShoppingCart selectByDishidCont(Long dishId, Long id, Long setmealId) {
        long userId=id;
        return shoppingCartMapper.selectByDishidCont(dishId,userId,setmealId);
    }

    @Override
    public int updata(ShoppingCart sc) {
        return shoppingCartMapper.updata(sc);
    }

    @Override
    public int detele(Long id) {
        return shoppingCartMapper.detele(id);
    }

    @Override
    public int insert(ShoppingCart sc) {
        return shoppingCartMapper.insert(sc);
    }

    @Override
    public List<ShoppingCart> selectByUserId(Long id) {
        return shoppingCartMapper.selectByUserId(id);
    }

    @Override
    public int sub(ShoppingCart shoppingCart) {


        return shoppingCartMapper.sub(shoppingCart);
    }

    @Override
    public int cleanByUserId(Long id) {
        return shoppingCartMapper.cleanByUserId(id);
    }
}
