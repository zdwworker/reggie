package com.zdw.reggie.backend.service.impl;

import com.zdw.reggie.backend.domain.Category;
import com.zdw.reggie.backend.mapper.CategoryMapper;
import com.zdw.reggie.backend.service.CategoryService;
import com.zdw.reggie.backend.service.DishService;
import com.zdw.reggie.backend.service.SetmealService;
import com.zdw.reggie.common.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private DishService dishService;
    @Autowired
    private SetmealService setmealService;

    @Override
    public int insertCategory(Category category) {
        return categoryMapper.insertCategory(category);
    }

    @Override
    public List<Category> selectCategoryByPage(int pageNo, int pageSize) {
        return categoryMapper.selectCategoryByPage(pageNo,pageSize);
    }

    @Override
    public int selectCont() {
        return categoryMapper.selectCont();
    }

    @Override
    public R<String> delect(long ids) {

        int cont1=dishService.selectCont(ids);
        if(cont1>0){
            //分类关联了菜品，不能删除
            return R.error("该分类关联了菜品，删除失败");
        }
        int cont2= setmealService.selectCont(ids);
        if(cont2>0){
            //分类关联了套餐，不能删除
            return R.error("该分类关联了套餐，删除失败");
        }
        int cont3= categoryMapper.delect(ids);
        if(cont3>0){
            //删除成功
            return R.success("删除成功...");
        }

        return R.error("删除失败...");
    }

    @Override
    public int updata(Category category) {
        return categoryMapper.updata(category);
    }

    @Override
    public List<Category> getCategoryName(Category category) {
        return categoryMapper.getCategoryName(category);
    }
}
