package com.example.demo.dao;

import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * 数据操作基础接口
 * 
 * @author xuly
 * @param <T>
 * @since 2018-04-12
 */
public interface BaseDao<T>
{
    MongoTemplate getMongoTemplate();

    /**
     * 保存实体
     * 
     * @param t
     *        实体对象
     * @return 受影响行数
     */
    T save(T t);

    /**
     * 根据id删除实体
     * 
     * @param id
     *        主键id
     * @return 受影响行数
     */
    int deleteById(Object id);

    /**
     * 更新实体
     * 
     * @param t
     *        实体对象
     * @return 受影响行数
     */
    T update(T t);

    /**
     * 查询所有实体
     * 
     * @return 实体集合
     */
    List<T> selectAll();

    /**
     * 根据id查询实体
     * 
     * @param id
     *        主键id
     * @return 实体对象
     */
    T getById(Object id);

    /**
     * 根据实体对象属性查询，属性条件使用and拼接
     * 
     * @author Demon
     * @param t
     *        实体对象
     * @return 实体对象
     */
    T selectOne(T t);

    /**
     * 根据实体中的属性值进行查询，查询条件使用等号
     * 
     * @param t
     *        实体对象
     * @return 实体对象列表
     */
    List<T> select(T t);

    // /**
    // * 根据实体中的属性值进行查询，查询条件使用等号，并分页
    // *
    // * @param t 实体对象
    // * @param pageNum 第几页
    // * @param pageSize 每页多少条数据
    // * @return 实体对象列表
    // */
    // public List<T> selectAndPaging(T t, Integer pageNum, Integer pageSize);
}
