package com.hz.blog.service.impl;

import com.hz.blog.dao.PostDao;
import com.hz.blog.dao.TagDao;
import com.hz.blog.entity.Post;
import com.hz.blog.entity.ResultList;
import com.hz.blog.entity.Tag;
import com.hz.blog.service.TagService;
import com.hz.blog.utils.EntityConvertDtoAndVOUtils;
import com.hz.blog.utils.SnowflakeManager;
import com.hz.blog.vo.TagVo;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class TagServiceImpl implements TagService {

    @Autowired
    private TagDao tagDao;

    @Autowired
    private SnowflakeManager snowflakeManager;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public int addTag(TagVo tagvo) {
        try {
            tagvo.setTagId(String.valueOf(snowflakeManager.nextValue()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Tag tag = EntityConvertDtoAndVOUtils.convertBean(tagvo, Tag.class);
        return tagDao.addTag(tag);
    }

    @Override
    public int updateTag(Tag tag) {
        return tagDao.updateTag(tag);
    }

    @Override
    public ResultList<String> deleteTag(List<String> tags) {
        //int i = tagDao.deleteTag(id);



        //设置true的话以后的增删改就不用提交事务
        ResultList<String> longResultList = new ResultList<>();
        int successNum=1;
        int failNum =0;
        List<String> successList =new ArrayList<>();
        List<String> failList = new ArrayList<>();
        try (SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH,false)) {

            TagDao mapper = sqlSession.getMapper(TagDao.class);
            for (int i=0;i<tags.size();i++){
                int deletePost = mapper.deleteTag(tags.get(i));
                System.out.println("==="+deletePost);
                if (deletePost>0){
                    successNum++;
                    successList.add(tags.get(i));
                }else {
                    failNum++;
                    failList.add(tags.get(i));
                }
                if (i%1000 ==999){
                    sqlSession.commit();
                    sqlSession.clearCache();
                }
            }
            sqlSession.commit();
            sqlSession.clearCache();
        }
        longResultList.setFailNum(failNum);
        longResultList.setSuccessNum(successNum);
        longResultList.setSuccessList(successList);
        longResultList.setFailList(failList);
        return longResultList;

        //return i;
    }

    @Override
    public Tag getTagByName(String name) {
        return tagDao.getTagByName(name);
    }

    @Override
    public Tag getTagById(String id) {
        return tagDao.getTagById(id);
    }

    @Override
    public List<Tag> getTags() {
        return tagDao.getTags();
    }
}
