package com.zdw.reggie.backend.service;

import com.zdw.reggie.backend.domain.Setmeal;
import com.zdw.reggie.backend.dto.SetmealDto;

import java.util.List;

public interface SetmealService {
    int selectCont(long ids);

    int insertSetmeal(SetmealDto setmealDto);

    int selectConts(String name);

    List<Setmeal> selectByPage(int pageNo, int pageSize, String name);

    SetmealDto selectById(long id);

    int updata(SetmealDto setmealDto);

    void updataStatus(Long id, int status);

    int delete(Long id);

    List<SetmealDto> selectSetmeal(Setmeal setmeal);
}
