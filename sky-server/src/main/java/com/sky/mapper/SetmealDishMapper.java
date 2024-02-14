package com.sky.mapper;

import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetmealDishMapper {
    /**
     * 根据菜品id查询套餐id
     * @param dishIds
     * @return
     */
    List<Long> getSetmealIdsByDishIds(List<Long> dishIds);


    /**
     * 对setmeal_dish表新增对应套餐下的菜品
     * @param setmealDishes
     */
    void save(List<SetmealDish> setmealDishes);

    /**
     * 根据套餐id来查套餐下的菜品信息
     * @param setmealId
     * @return
     */
    List<SetmealDish> queryDishes(Long setmealId);

    /**
     * 根据套餐id删除套餐下的菜品
     * @param setmealId
     */
    @Delete("delete from setmeal_dish where setmeal_id=#{setmealId}")
    void delete(Long setmealId);
}
