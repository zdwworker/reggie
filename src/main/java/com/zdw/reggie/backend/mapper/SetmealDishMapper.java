package com.zdw.reggie.backend.mapper;

import com.zdw.reggie.backend.domain.SetmealDish;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetmealDishMapper {

    int insert(SetmealDish setmealDish);

    List<SetmealDish> selectBySetmealId(long id);

    void delete(Long id);
}
