package com.zdw.reggie.backend.mapper;

import com.zdw.reggie.backend.domain.DishFlavor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DishFlavorMapper {
    int insertFlacor(DishFlavor flavors);

    List<DishFlavor> selectDishById(Long id);

    int updata(DishFlavor flavor);

    void delete(Long id);


}
