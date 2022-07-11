package com.zdw.reggie.backend.service;

import com.zdw.reggie.backend.domain.SetmealDish;

import java.util.List;

public interface SetmealDishService {
    int insert(SetmealDish setmealDish);

    List<SetmealDish> selectBySetmealId(long id);

    void delete(Long id);
}
