package com.zdw.reggie.backend.service.impl;

import com.zdw.reggie.backend.domain.DishFlavor;
import com.zdw.reggie.backend.mapper.DishFlavorMapper;
import com.zdw.reggie.backend.service.DishFlavorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DishFlavorServiceImpl implements DishFlavorService {

    @Autowired
    private DishFlavorMapper dishFlavorMapper;

    @Override
    public int insertFlacor(DishFlavor flavor) {
        return dishFlavorMapper.insertFlacor(flavor);
    }

    @Override
    public List<DishFlavor> selectDishById(Long id) {
        return dishFlavorMapper.selectDishById(id);
    }

    @Override
    public int updata(DishFlavor flavor) {
        return dishFlavorMapper.updata(flavor);
    }

    @Override
    public void delete(Long id) {
        dishFlavorMapper.delete(id);
    }


}
