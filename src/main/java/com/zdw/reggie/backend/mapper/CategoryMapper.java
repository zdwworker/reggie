package com.zdw.reggie.backend.mapper;


import com.zdw.reggie.backend.domain.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {

    int insertCategory(Category category);

    List<Category> selectCategoryByPage(int pageNo, int pageSize);

    int selectCont();

    int delect(long ids);

    int updata(Category category);

    List<Category> getCategoryName(Category category);
}
