package com.zdw.reggie.backend.dto;

import com.zdw.reggie.backend.domain.Dish;
import com.zdw.reggie.backend.domain.DishFlavor;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class DishDto extends Dish {

    private List<DishFlavor> flavors = new ArrayList<>();

    private String categoryName;

    private Integer copies;
}
