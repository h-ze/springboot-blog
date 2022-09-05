package com.hz.blog.utils;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

public class EntityConvertDtoAndVOUtils {

    private static Mapper mapper = new DozerBeanMapper();

    public static <D,E> E convertBean(D t, Class<E> clazz){
        if(t == null){
            return null;
        }
        return mapper.map(t, clazz);
    }

    /**
     * 实例数组之间转换
     */
    public static <D,E> List<E> convertArray(D[] ts, Class<E> clazz){
        List<E> es = new ArrayList<E>();
        if(ts == null){
            return es;
        }
        for(D d:ts) {
            E e = (E)convertBean(d,clazz);
            if(e != null)
                es.add(e);
        }

        return es;
    }

    /**
     * List集合之间转换
     */
    public static <D,E> List<E> convertList(List<D> ts, Class<E> clazz){
        List<E> es = new ArrayList<E>();
        if(ts == null ){
            return es;
        }
        for(D d:ts) {
            E e = (E)convertBean(d,clazz);
            if(e != null)
                es.add(e);
        }
        return es;
    }
}
