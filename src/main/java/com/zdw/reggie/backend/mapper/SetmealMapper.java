package com.zdw.reggie.backend.mapper;

import com.zdw.reggie.backend.domain.Setmeal;
import com.zdw.reggie.backend.dto.SetmealDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetmealMapper {
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
