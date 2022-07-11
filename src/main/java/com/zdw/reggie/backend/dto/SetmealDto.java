package com.zdw.reggie.backend.dto;
import com.zdw.reggie.backend.domain.Setmeal;
import com.zdw.reggie.backend.domain.SetmealDish;
import lombok.Data;
import java.util.List;

@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
