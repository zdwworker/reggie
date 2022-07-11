package com.zdw.reggie.backend.controller;

import com.zdw.reggie.backend.domain.Category;
import com.zdw.reggie.backend.service.CategoryService;
import com.zdw.reggie.common.DateTimeUtil;
import com.zdw.reggie.common.R;
import com.zdw.reggie.common.XHID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class CategoryController {

   @Autowired
    private CategoryService categoryService;


    /**
     * 获取菜品分类的方法
     * @return
     */
    @GetMapping("/category/list")
    public R<List<Category>> selectBycategory( Category category){
        log.info("获取菜品分类执行中...");
        List<Category> list= categoryService.getCategoryName(category);

        return R.success(list);
    }

    /**
     * 新增菜品、套餐分类
     * @param category
     * @param request
     * @return
     */
    @PostMapping("/category")
    public R<String> addCategory(@RequestBody Category category, HttpServletRequest request){
        log.info("新增菜品执行中...");

        category.setId(XHID.nextId());
        category.setCreateTime(DateTimeUtil.getSysTime());
        category.setCreateUser((Long) request.getSession().getAttribute("userId"));
        category.setUpdateTime(DateTimeUtil.getSysTime());
        category.setUpdateUser((Long) request.getSession().getAttribute("userId"));
        log.info(category.toString());

        int cont=categoryService.insertCategory(category);
        if(cont!=0){
            //增加成功
            return R.success("添加成功...");
        }
        return R.error("添加失败...");
    }



    /**
     * 分页查询所有菜品分类、套餐
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/category/page")
    public R<Object> selectCategory(int page,int pageSize){
        //http://127.0.0.1:8080/category/page?page=1&pageSize=10
        log.info("分页查询分类执行中...");
        int pageNo=(page-1)*pageSize;
        List<Category> categoryList=categoryService.selectCategoryByPage(pageNo,pageSize);
        int cont=categoryService.selectCont();
        Map<String,Object> map=new HashMap<>();
        map.put("records",categoryList);
        map.put("total",cont);

        if(categoryList!=null){
            return R.success(map);
        }
        return R.error("未知查询错误...");
    }


    /**
     * 删除菜品分类和套餐分类
     * @param ids
     * @return
     */
    @DeleteMapping("/category")
    public R<String> delete(long ids){
        //	http://127.0.0.1:8080/category?ids=1397844263642378200
        R<String> r=categoryService.delect(ids);
        return r;
    }

    /**
     * 修改菜品信息
     * @param category
     * @return
     */
    @PutMapping("/category")
    public R<String> updata(@RequestBody Category category,HttpServletRequest request){
        log.info("修改菜品信息中....");

        category.setUpdateUser((Long) request.getSession().getAttribute("userId"));
        category.setUpdateTime(DateTimeUtil.getSysTime());

        int i=categoryService.updata(category);
        if(i>0){
            //修改成功。。
            return R.success("修改成功...");
        }
        return R.error("修改失败...");
    }
}
