package com.zdw.reggie.backend.service.impl;

import com.zdw.reggie.backend.domain.Setmeal;
import com.zdw.reggie.backend.dto.SetmealDto;
import com.zdw.reggie.backend.mapper.SetmealMapper;
import com.zdw.reggie.backend.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private SetmealMapper setmealMapper;

    @Override
    public int selectCont(long ids) {
        return setmealMapper.selectCont(ids);
    }

    @Override
    public int insertSetmeal(SetmealDto setmealDto) {
        return setmealMapper.insertSetmeal(setmealDto);
    }

    @Override
    public int selectConts(String name) {
        return setmealMapper.selectConts(name);
    }

    @Override
    public List<Setmeal> selectByPage(int pageNo, int pageSize, String name) {
        return setmealMapper.selectByPage(pageNo,pageSize,name);
    }

    @Override
    public SetmealDto selectById(long id) {
        return setmealMapper.selectById(id);
    }

    @Override
    public int updata(SetmealDto setmealDto) {
        return setmealMapper.updata(setmealDto);
    }

    @Override
    public void updataStatus(Long id, int status) {
        setmealMapper.updataStatus(id,status);
    }

    @Override
    public int delete(Long id) {
        return setmealMapper.delete(id);
    }

    @Override
    public List<SetmealDto> selectSetmeal(Setmeal setmeal) {
        return setmealMapper.selectSetmeal(setmeal);
    }
}
