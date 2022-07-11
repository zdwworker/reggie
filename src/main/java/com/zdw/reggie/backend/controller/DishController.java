package com.zdw.reggie.backend.controller;


import com.zdw.reggie.backend.domain.Dish;
import com.zdw.reggie.backend.domain.DishFlavor;
import com.zdw.reggie.backend.dto.DishDto;
import com.zdw.reggie.backend.service.DishFlavorService;
import com.zdw.reggie.backend.service.DishService;
import com.zdw.reggie.common.DateTimeUtil;
import com.zdw.reggie.common.R;
import com.zdw.reggie.common.XHID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class DishController {

    @Autowired
    private DishService dishService;

    @Autowired
    private DishFlavorService dishFlavorService;

    /**
     * 分页查询菜单的操作
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/dish/page")
    public R<Object> selectDishByPage(int page,int pageSize,String name){
                ///dish/page?page=1&pageSize=10
        log.info("分页查询菜单执行中....");
        int pageNo=(page-1)*pageSize;
        List<Dish> dishList=dishService.selectDishByPage(pageNo,pageSize,name);
        int cont =dishService.selectContByname(name);

        Map<String,Object> map=new HashMap<>();
        map.put("records",dishList);
        map.put("total",cont);
        if(dishList!=null){
            //查询成功
            return R.success(map);

        }
        return R.error("菜品查询错误....");
    }




    /**
     *增加菜品的方法
     * @param dishDto
     * @return
     */
    @PostMapping("/dish")
    public R<String> addDish(@RequestBody DishDto dishDto, HttpServletRequest request){
        log.info("添加菜品执行中...");

        long id = XHID.nextId();
        dishDto.setIsDeleted(0);
        dishDto.setSort(0);
        dishDto.setId(id);
        dishDto.setCreateTime(DateTimeUtil.getSysTime());
        dishDto.setUpdateTime(DateTimeUtil.getSysTime());
        dishDto.setUpdateUser((Long) request.getSession().getAttribute("userId"));
        dishDto.setCreateUser((Long) request.getSession().getAttribute("userId"));

        List<DishFlavor> flavors = dishDto.getFlavors();


        int cont=dishService.insertDish(dishDto);
        int cont2=0;
        if(cont>0){
            //表示DISH插入成功，继续添加flacor
            for(DishFlavor flavor:flavors){
                flavor.setDishId(id);
                flavor.setId(XHID.nextId());
                flavor.setCreateTime(DateTimeUtil.getSysTime());
                flavor.setCreateUser((Long) request.getSession().getAttribute("userId"));
                flavor.setIsDeleted(0);
                flavor.setUpdateUser((Long) request.getSession().getAttribute("userId"));
                flavor.setUpdateTime(DateTimeUtil.getSysTime());
                System.out.println(flavor);
                cont2=dishFlavorService.insertFlacor(flavor);
            }
            if(cont2>0){
                return R.success("添加成功...");
            }
        }
        return R.error("添加失败...");
    }

    /**
     * 查询菜品信息，赋到修改框
     * @param id
     * @return
     */
    @GetMapping("/dish/{id}")
    public R<DishDto> selectDishById(@PathVariable Long id){

        log.info("查询菜品信息执行中....{}",id);

        DishDto dishDto=dishService.selectDishById(id);
        List<DishFlavor> dishFlavors=dishFlavorService.selectDishById(id);
        dishDto.setFlavors(dishFlavors);
        if(dishDto!=null){
            return R.success(dishDto);
        }
        return R.error("查询失败...");
    }

    /**
     * 修改菜品信息
     * @param dishDto
     * @return
     */
    @PutMapping("/dish")
    @Transactional
    public R<String> updata(@RequestBody DishDto dishDto,HttpServletRequest request){

        log.info("修改菜品信息执行中...");

        dishDto.setUpdateUser((Long) request.getSession().getAttribute("userId"));
        dishDto.setUpdateTime(DateTimeUtil.getSysTime());
         int i= dishService.updata(dishDto);
         //先将菜品关联的口味删除，再添加口味
         dishFlavorService.delete(dishDto.getId());

            List<DishFlavor> flavors = dishDto.getFlavors();
            for(DishFlavor dishFlavor:flavors){
                    //否则是添加新的口味信息
                    dishFlavor.setId(XHID.nextId());
                    dishFlavor.setCreateTime(DateTimeUtil.getSysTime());
                    dishFlavor.setCreateUser((Long) request.getSession().getAttribute("userId"));
                    dishFlavor.setUpdateTime(DateTimeUtil.getSysTime());
                    dishFlavor.setUpdateUser((Long) request.getSession().getAttribute("userId"));
                    dishFlavor.setIsDeleted(0);
                    dishFlavor.setDishId(dishDto.getId());
                   int  j=dishFlavorService.insertFlacor(dishFlavor);
            }


        return R.success("修改成功...");
    }

    /**
     * 删除菜品操作
     * @param ids
     * @return
     */
    @Transactional
    @DeleteMapping("/dish")
    public R<String> delete(@RequestParam List<Long> ids){
        log.info("菜品删除操作执行中...{}",ids);
        for(Long id:ids){
            dishService.delete(id);
            dishFlavorService.delete(id);
        }
        return R.success("删除成功...");

    }

    /**
     * 改变菜品出售状态
     * @param status
     * @param ids
     * @return
     */
    @Transactional
    @PostMapping("/dish/status/{status}")
    public R<String> updataStatus(@PathVariable int status,@RequestParam List<Long> ids){
        ///dish/status/1?ids=8230829535375962112
        log.info("改变菜品是否出售执行中...{},{}",status,ids);
        for(Long id:ids){
            int i=dishService.updataStatus(status,id);
        }

        return R.success("修改成功...");
    }

    /**
     * 按菜品分类查询菜品
     * @param dish
     * @return
     */

    @GetMapping("/dish/list")
    public R<List<DishDto>> selectDishByCategoryId(Dish dish) {
        ////127.0.0.1:8080/dish/list?categoryId=1397844263642378242
        log.info("查询菜品分类执行中....{}", dish);
        List<DishDto> dishDtos = dishService.selectDishByCategoryId(dish);
        if (dishDtos!=null){
            for (DishDto dishDto:dishDtos){
                dishDto.setFlavors(dishFlavorService.selectDishById(dishDto.getId()));
            }
        }


        return R.success(dishDtos);
    }
}
