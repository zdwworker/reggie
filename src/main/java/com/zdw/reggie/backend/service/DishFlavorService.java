package com.zdw.reggie.backend.service;

import com.zdw.reggie.backend.domain.DishFlavor;

import java.util.List;


public interface DishFlavorService {
    int insertFlacor(DishFlavor flavor);

    List<DishFlavor> selectDishById(Long id);

    int updata(DishFlavor flavor);

    void delete(Long id);


}
