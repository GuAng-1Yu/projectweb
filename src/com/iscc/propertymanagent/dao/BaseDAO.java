package com.iscc.propertymanagent.dao;

import java.util.List;

/**
 * @author ZHOUB
 * @create 2020-08-09-23:08
 */
public interface BaseDAO <T> {
    /**
     * 添加
     *
     * @param t
     * @return
     */
    int add(T t);

    /**
     * 更新操作
     *
     * @param t
     * @return
     */
    int update(T t);

    /**
     * 删除操作
     *
     * @param id 主键
     * @return
     */
    int del(int id);

    /**
     * 显示所有信息
     *
     * @return
     */
    List<T> queryAll();

    /**
     * 根据主键获取某一个信息
     *
     * @param id
     * @return
     */
    T queryById(int id);
}
