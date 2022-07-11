package com.zdw.reggie.backend.service;

import com.zdw.reggie.backend.domain.Dish;
import com.zdw.reggie.backend.dto.DishDto;

import java.util.List;

public interface DishService {
    int selectCont(long ids);

    List<Dish> selectDishByPage(int pageNo, int pageSize,String name);

    int selectContByname(String name);

    int insertDish(DishDto dishDto);

    DishDto selectDishById(Long id);

    int updata(DishDto dishDto);

    int updataStatus(int status, long ids);

    void delete(Long id);


    List<DishDto> selectDishByCategoryId(Dish dish);
}
