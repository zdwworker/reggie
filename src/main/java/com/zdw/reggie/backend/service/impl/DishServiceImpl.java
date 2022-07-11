package com.zdw.reggie.backend.service.impl;

import com.zdw.reggie.backend.domain.Dish;
import com.zdw.reggie.backend.dto.DishDto;
import com.zdw.reggie.backend.mapper.DishMapper;
import com.zdw.reggie.backend.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishMapper dishMapper;

    @Override
    public int selectCont(long ids) {
        return dishMapper.selectCont(ids);
    }

    @Override
    public List<Dish> selectDishByPage(int pageNo, int pageSize,String name) {
        return dishMapper.selectDishByPage(pageNo,pageSize,name);
    }

    @Override
    public int selectContByname(String name) {
        return dishMapper.selectContByname(name);
    }

    @Override
    public int insertDish(DishDto dishDto) {
        return dishMapper.insertDish(dishDto);
    }

    @Override
    public DishDto selectDishById(Long id) {
        return dishMapper.selectDishById(id);
    }

    @Override
    public int updata(DishDto dishDto) {
        return dishMapper.updata(dishDto);
    }

    @Override
    public int updataStatus(int status, long ids) {
        return dishMapper.updataStatus(status,ids);
    }

    @Override
    public void delete(Long id) {
        dishMapper.delete(id);
    }

    @Override
    public List<DishDto> selectDishByCategoryId(Dish dish) {
        return dishMapper.selectDishByCategoryId(dish);
    }
}
