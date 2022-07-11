package com.zdw.reggie.backend.controller;

import com.zdw.reggie.backend.domain.Setmeal;
import com.zdw.reggie.backend.domain.SetmealDish;
import com.zdw.reggie.backend.dto.SetmealDto;
import com.zdw.reggie.backend.service.SetmealDishService;
import com.zdw.reggie.backend.service.SetmealService;
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

@RestController
@Slf4j
public class SetmealController {

    @Autowired
    private SetmealService setmealService;

    @Autowired
    private SetmealDishService setmealDishService;

    /**
     * 保存新建套餐
     * @param setmealDto
     * @return
     */
    @PostMapping("/setmeal")
    @Transactional
    public R<String> sava(@RequestBody SetmealDto setmealDto, HttpServletRequest request){
        //27.0.0.1:8080/setmeal
        log.info("保存新建套餐执行中...{}",setmealDto);
        setmealDto.setUpdateUser((Long) request.getSession().getAttribute("userId"));
        setmealDto.setUpdateTime(DateTimeUtil.getSysTime());
        setmealDto.setIsDeleted(0);
        setmealDto.setCreateUser((Long) request.getSession().getAttribute("userId"));
        setmealDto.setCreateTime(DateTimeUtil.getSysTime());
        setmealDto.setId(XHID.nextId());

        int i=setmealService.insertSetmeal(setmealDto);

        if(i>0){
            //说明套餐添加成功
            //添加套餐菜品关联信息
            List<SetmealDish> setmealDishList=setmealDto.getSetmealDishes();
            for(SetmealDish setmealDish:setmealDishList){
                setmealDish.setId(XHID.nextId());
                setmealDish.setSetmealId(setmealDto.getId());
                setmealDish.setSort(0);
                setmealDish.setCreateTime(DateTimeUtil.getSysTime());
                setmealDish.setCreateUser((Long) request.getSession().getAttribute("userId"));
                setmealDish.setIsDeleted(0);
                setmealDish.setUpdateTime(DateTimeUtil.getSysTime());
                setmealDish.setUpdateUser((Long) request.getSession().getAttribute("userId"));
                int j=setmealDishService.insert(setmealDish);
            }
        }

        return R.success("添加成功...");

    }

    /**
     * 分页查询套餐信息
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/setmeal/page")
    public R<Map> selectByPage(int page,int pageSize,String name){
        log.info("分页查询套餐执行中...page={},pageSize={}",page,pageSize);

        int pageNo=(page-1)*pageSize;

        int cont=setmealService.selectConts(name);
        List<Setmeal> setmealList=setmealService.selectByPage(pageNo,pageSize,name);
        Map<String,Object> map=new HashMap<>();
        map.put("records",setmealList);
        map.put("total",cont);
        return R.success(map);
    }

    /**
     * 查询套餐信息，回填到修改页面
     * @param id
     * @return
     */
    @GetMapping("/setmeal/{id}")
    public R<SetmealDto> selectSetmaleDto(@PathVariable long id){
        log.info("修改页面查询套餐信息执行中...{}",id);
        ///setmeal/1415580119015145474
        SetmealDto setmealDto=setmealService.selectById(id);
        List<SetmealDish> setmealDishList=setmealDishService.selectBySetmealId(id);
        setmealDto.setSetmealDishes(setmealDishList);
        if(setmealDto!=null) {
            return R.success(setmealDto);
        }
        return R.error("查询失败...");
    }

    /**
     * 保存修改后的套餐
     * @param setmealDto
     * @return
     */
    @Transactional
    @PutMapping("/setmeal")
    public R<String> updata(@RequestBody SetmealDto setmealDto,HttpServletRequest request){
        //http://127.0.0.1:8080/setmeal
        log.info("保存修改后的套餐进行中....");

        setmealDto.setUpdateTime(DateTimeUtil.getSysTime());
        setmealDto.setUpdateUser((Long) request.getSession().getAttribute("userId"));
        List<SetmealDish> setmealDishList=setmealDto.getSetmealDishes();
        int i=setmealService.updata(setmealDto);
        if(i>0){
            //先将绑定的套餐菜品信息删除
            setmealDishService.delete(setmealDto.getId());
            for (SetmealDish setmealDish:setmealDishList){
                setmealDish.setId(XHID.nextId());
                setmealDish.setSetmealId(setmealDto.getId());
                setmealDish.setSort(0);
                setmealDish.setCreateTime(DateTimeUtil.getSysTime());
                setmealDish.setCreateUser((Long) request.getSession().getAttribute("userId"));
                setmealDish.setIsDeleted(0);
                setmealDish.setUpdateTime(DateTimeUtil.getSysTime());
                setmealDish.setUpdateUser((Long) request.getSession().getAttribute("userId"));
                int j=setmealDishService.insert(setmealDish);
            }
        }
        return R.success("修改成功...");
    }

    /**
     * 修改套餐销售状态
     * @param status
     * @param ids
     * @return
     */
    @PostMapping("/setmeal/status/{status}")
    public R<String> updataStatus(@PathVariable int status,@RequestParam List<Long> ids){
        ///setmeal/status/0?ids=1415580119015145474
        ///setmeal/list?categoryId=1397844263642378242&status=1
        log.info("修改套餐销售状态执行中...{},{}",status,ids);
        for (Long id:ids){
            setmealService.updataStatus(id,status);
        }
        return R.success("修改成功...");
    }

    @Transactional
    @DeleteMapping("/setmeal")
    public R<String> delete(@RequestParam List<Long> ids){
        //setmeal?ids=8231438257604308992
        log.info("删除套餐执行中....{}",ids);
        for(Long id:ids){
            int i=setmealService.delete(id);
            if(i>0){
                //表示删除成功,同时删除套餐菜品联系表
                setmealDishService.delete(id);
            }
        }
        return R.success("删除成功...");
    }

    @GetMapping("/setmeal/list")
    public R<List<SetmealDto>> selectSetmeal(Setmeal setmeal){
        ///setmeal/list?categoryId=1397844263642378242&status=1
        log.info("查询套餐信息执行中....{}",setmeal);
        List<SetmealDto> setmealDtos=setmealService.selectSetmeal(setmeal);
        if (setmealDtos!=null){
            for (SetmealDto setmealDto:setmealDtos){
                List<SetmealDish> setmealDishList = setmealDishService.selectBySetmealId(setmealDto.getId());
                setmealDto.setSetmealDishes(setmealDishList);
            }

        }

        return R.success(setmealDtos);
    }
}
