package com.zdw.reggie.backend.service.impl;

import com.zdw.reggie.backend.domain.SetmealDish;
import com.zdw.reggie.backend.mapper.SetmealDishMapper;
import com.zdw.reggie.backend.service.SetmealDishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SetmealDishServiceImpl implements SetmealDishService {

    @Autowired
    private SetmealDishMapper setmealDishMapper;

    @Override
    public int insert(SetmealDish setmealDish) {
        return setmealDishMapper.insert(setmealDish);
    }

    @Override
    public List<SetmealDish> selectBySetmealId(long id) {
        return setmealDishMapper.selectBySetmealId(id);
    }

    @Override
    public void delete(Long id) {
        setmealDishMapper.delete(id);
    }
}
