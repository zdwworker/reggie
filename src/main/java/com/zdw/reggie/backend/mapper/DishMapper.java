package com.zdw.reggie.backend.mapper;

import com.zdw.reggie.backend.domain.Dish;
import com.zdw.reggie.backend.dto.DishDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DishMapper {
    int selectCont(long ids);

    List<Dish> selectDishByPage(int pageNo, int pageSize,String name);

    int selectContByname(String name);

    int insertDish(DishDto dishDto);

    DishDto selectDishById(Long id);

    int updata(DishDto dishDto);

    int updataStatus(int status, long id);

    void delete(Long id);

    List<DishDto> selectDishByCategoryId(Dish dish);

}
