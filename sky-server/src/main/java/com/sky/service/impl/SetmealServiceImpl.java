package com.sky.service.impl;

import com.github.pagehelper.PageHelper;
import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.entity.SetmealDish;
import com.sky.mapper.SetmealDishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.result.PageResult;
import com.sky.service.SetmealService;
import com.sky.vo.SetmealVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.Page;

import java.util.List;

@Service
public class SetmealServiceImpl implements SetmealService {
    @Autowired
    private SetmealMapper setmealMapper;
    @Autowired
    private SetmealDishMapper setmealDishMapper;

    /**
     * 新增套餐
     *
     * @param setmealDTO
     * @return
     */
    @Transactional
    @Override
    public void save(SetmealDTO setmealDTO) {
        //操作setmeal表
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO, setmeal);
        setmealMapper.save(setmeal);
        //操作setmeal_dish表
        List<SetmealDish> setmealDishes = setmealDTO.getSetmealDishes();
        setmealDishes.forEach(v -> {
            v.setSetmealId(setmeal.getId());
        });
        setmealDishMapper.save(setmealDishes);
    }

    /**
     * 套餐分页查询
     *
     * @param setmealPageQueryDTO
     * @return
     */
    @Override
    public PageResult pageQuery(SetmealPageQueryDTO setmealPageQueryDTO) {
        PageHelper.startPage(setmealPageQueryDTO.getPage(), setmealPageQueryDTO.getPageSize());
        Page<SetmealVO> setmealPage = setmealMapper.pageQuery(setmealPageQueryDTO);
        return new PageResult(setmealPage.getTotal(), setmealPage.getResult());
    }

    /**
     * 根据id查询套餐
     *
     * @param id
     * @return
     */
    @Override
    public SetmealVO querySetmeal(Long id) {
        //先查套餐信息
        SetmealVO setmealVO = setmealMapper.querySetmeal(id);
        //再查套餐含有的菜品信息
        List<SetmealDish> setmealDishList = setmealDishMapper.queryDishes(id);
        //最后整合
        setmealVO.setSetmealDishes(setmealDishList);
        return setmealVO;
    }
}
