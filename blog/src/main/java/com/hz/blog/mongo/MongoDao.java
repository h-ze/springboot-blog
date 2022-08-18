package com.hz.blog.mongo;

import com.hz.blog.entity.MongoEntity;

public interface MongoDao {
    void saveDemo(MongoEntity mongoEntity);

    void removeDemo(Long id);

    void updateDemo(MongoEntity mongoEntity);

    MongoEntity findMongoById(Long id);

}
