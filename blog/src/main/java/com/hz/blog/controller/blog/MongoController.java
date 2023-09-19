package com.hz.blog.controller.blog;


import com.hz.blog.entity.MongoEntity;
import com.hz.blog.mongo.MongoService;
import com.hz.blog.response.ServerResponseEntity;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("mongo")
@Api(tags = "Mongo接口")
public class MongoController {

    @Autowired
    private MongoService mongoService;

    @GetMapping("list")
    @ApiOperation(notes = "获取mongo列表",value = "获取mongo列表")
    @ApiImplicitParam(name = "id",value = "集合名称",dataType = "Long",paramType = "query")
    public ServerResponseEntity getMongoList(@RequestParam("id")Long id){
        MongoEntity mongoEntity = (MongoEntity) mongoService.selectById(id, MongoEntity.class, "hz");
        return ServerResponseEntity.success(mongoEntity);
    }

    @PostMapping("addMongo")
    @ApiOperation(notes = "插入数据",value = "往mongo中指定的集合中插入一条数据")
    public ServerResponseEntity addMongo(@RequestBody @ApiParam(name = "body",value = "添加一条mongo信息",required = true) MongoEntity mongoEntity){
        mongoService.insert(mongoEntity,"hz");
        return ServerResponseEntity.success("插入成功");
    }

    @DeleteMapping("deleteMongo")
    @ApiOperation(notes = "删除一条数据",value ="删除一条数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "id名称",dataType = "Long",paramType = "query")

    })
    public ServerResponseEntity deleteMongo(@RequestParam("id")Long id){
        mongoService.deleteById(id,MongoEntity.class,"hz");
        return ServerResponseEntity.success(100000,"删除成功");
    }

    @PutMapping("updateMongo")
    @ApiOperation(notes = "更新一条数据",value ="更新一条数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "id名称",dataType = "Long",paramType = "query")

    })
    public ServerResponseEntity updateMongo(@RequestParam("id")Long id,@RequestBody @ApiParam(name = "body",value = "添加一条mongo信息",required = true) MongoEntity mongoEntity){
        mongoService.updateById(id,"hz",mongoEntity);
        return ServerResponseEntity.success(100000,"更新成功");
    }


    @PostMapping("index")
    @ApiOperation(notes = "设置索引",value ="设置索引")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "collectionName",value = "集合名",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "indexName",value = "索引名称",dataType = "String",paramType = "query")

    })
    public ServerResponseEntity insertIndex(@RequestParam("collectionName")String collectionName,@RequestParam("indexName")String indexName){
        String docId = mongoService.createIndex(collectionName, indexName);
        return ServerResponseEntity.success(100000,"设置成功");
    }

    @DeleteMapping("index")
    @ApiOperation(notes = "删除索引",value ="删除索引")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "collectionName",value = "集合名",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "indexName",value = "索引名称",dataType = "String",paramType = "query")
    })
    public ServerResponseEntity deleteIndex(@RequestParam("collectionName")String collectionName,@RequestParam("indexName")String indexName){
        String docId = mongoService.dropIndex(collectionName, indexName);
        return ServerResponseEntity.success(100000,"删除成功");
    }

    @GetMapping("index")
    @ApiOperation(notes = "获取所有索引",value ="获取所有索引")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "collectionName",value = "集合名",dataType = "String",paramType = "query")

    })
    public ServerResponseEntity getIndex(@RequestParam("collectionName")String collectionName){
        List allIndexes = mongoService.getAllIndexes(collectionName);
        return ServerResponseEntity.success(100000,allIndexes);
    }

}
