package com.example.demo.dao;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

/**
 * 数据操作基础类
 * 
 * @author xuly
 * @param <T>
 * @since 2018-04-12
 */

@Component
@Repository
public class BaseDaoImpl<T> implements BaseDao<T>
{
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public MongoTemplate getMongoTemplate()
    {
        return mongoTemplate;
    }

    @SuppressWarnings("unchecked")
    private Class<T> getClazz()
    {
        return (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    private Field[] getField(Class<T> entityClass)
    {
        Field[] fields = entityClass.getDeclaredFields();
        // 取消每个属性的安全检查
        for (Field f : fields)
        {
            f.setAccessible(true);
        }
        return fields;
    }

    private String determineEntityCollectionName(Class<T> clazz)
    {
        if (!StringUtils.isEmpty(clazz.getSimpleName()))
        {
            return clazz.getSimpleName().substring(0, 1).toLowerCase()
                   + clazz.getSimpleName().substring(1, clazz.getSimpleName().length());
        }

        return null;
    }

    @Override
    public T save(T t)
    {
        try
        {
            // Class<T> entityClass = this.getClazz();
            // Field[] fields = this.getField(entityClass);
            //
            // boolean ifHasId = false;
            // for (int i = 0; i < fields.length; i++)
            // {
            //
            // if (fields[i].getName().equals("id"))
            // {
            // ifHasId = true;
            //
            // if (fields[i].get(t) == null)
            // {
            // fields[i].set(t, value);
            // }
            // }
            //
            // System.out.println(fields[i].getName() + ":" + fields[i].get(t));
            // }

            mongoTemplate.save(t);
            return t;

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public int deleteById(Object id)
    {
        Class<T> entityClass =
            (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, entityClass);
        return 0;
    }

    @SuppressWarnings("unchecked")
    @Override
    public T update(T t)
    {
        try
        {
            Class<T> entityClass = this.getClazz();
            Field[] fields = this.getField(entityClass);

            // 获取传入的每个对象的所有类成员属性值
            Query query = null;
            Update update = new Update();
            for (int i = 0; i < fields.length; i++)
            {

                if (fields[i].getName().equals("id"))
                {
                    if (fields[i].get(t) != null)
                    {
                        query = new Query(Criteria.where("_id").is(fields[i].get(t)));
                    }
                }
                else
                {
                    update.set(fields[i].getName(), fields[i].get(t));
                }

                System.out.println(fields[i].getName() + ":" + fields[i].get(t));
            }

            if (query != null)
            {
                // 更新查询返回结果集的第一条
                return (T)mongoTemplate.updateFirst(query, update, determineEntityCollectionName(entityClass));
            }
            else
            {
                throw new RuntimeException("entity id must be set, and id can not be null!");
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<T> selectAll()
    {
        return mongoTemplate.findAll(this.getClazz());
    }

    @Override
    public T getById(Object id)
    {
        return mongoTemplate.findById(id, this.getClazz());
    }

    @Override
    public T selectOne(T t)
    {
        List<T> rs = this.select(t);

        if (rs != null && rs.size() > 0)
        {
            return this.select(t).get(0);
        }
        else
        {
            return null;
        }
    }

    @Override
    public List<T> select(T t)
    {
        try
        {
            Class<T> entityClass = this.getClazz();
            Field[] fields = this.getField(entityClass);

            // 获取传入的每个对象的所有类成员属性值
            Query query = null;
            for (int i = 0; i < fields.length; i++)
            {
                if (fields[i].getName().equals("serialVersionUID"))
                    continue;

                if (fields[i].getName().equals("id"))
                {
                    if (fields[i].get(t) != null)
                    {
                        if (query == null)
                        {
                            query = new Query(Criteria.where("_id").is(fields[i].get(t)));
                        }
                        else
                        {
                            query.addCriteria(Criteria.where("_id").is(fields[i].get(t)));
                        }

                    }
                }
                else
                {
                    if (fields[i].get(t) != null)
                    {
                        if (query == null)
                        {
                            query = new Query(Criteria.where(fields[i].getName()).is(fields[i].get(t)));
                        }
                        else
                        {
                            query.addCriteria(Criteria.where(fields[i].getName()).is(fields[i].get(t)));
                        }
                    }
                }
            }

            if (query != null)
            {
                return ((List<T>)mongoTemplate.find(query, this.getClazz()));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    // @Override
    // public List<T> selectAndPaging(T t, Integer pageNum, Integer pageSize)
    // {
    // // TODO Auto-generated method stub
    // return null;
    // }

}
