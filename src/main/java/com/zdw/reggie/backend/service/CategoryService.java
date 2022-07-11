package com.zdw.reggie.backend.service;

import com.zdw.reggie.backend.domain.Category;
import com.zdw.reggie.common.R;

import java.util.List;


public interface CategoryService {

    int insertCategory(Category category);

    List<Category> selectCategoryByPage(int pageNo, int pageSize);

    int selectCont();


    R<String> delect(long ids);

    int updata(Category category);


    List<Category> getCategoryName(Category category);

}
