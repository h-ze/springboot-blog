package com.hz.blog.controller.blog;


import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.BucketInfo;
import com.hz.blog.cloud.OSSFactory;
import com.hz.blog.exception.RRException;
import com.hz.blog.response.ServerResponseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.util.List;


@RestController
@RequestMapping("oss")
@Api(tags = "云存储器接口")
public class OssController {

    /**
     * 上传文件
     */
    //@RequiresPermissions("sys:oss:all")
    @ApiOperation(value = "上传文件",notes = "上传文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "docName",value = "文档名",paramType = "query",dataType = "String",required = false),
            //@ApiImplicitParam(name = "encryptConfig",value = "加密策略",paramType = "query",dataType ="String",required = false) ,
            //@ApiImplicitParam(name = "docId",value = "文档id",paramType = "query",dataType = "String",required = true),
            @ApiImplicitParam(name ="file",value = "文件",paramType = "form",dataType = "__file",required = true)
    })
    @PostMapping("uploadFile")
    public ServerResponseEntity upload(@RequestParam("file") MultipartFile file) throws Exception {
		if (file.isEmpty()) {
			throw new RRException("上传文件不能为空");
		}
		//上传文件
		String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		String url = OSSFactory.build().uploadSuffix(file.getBytes(),file.getSize(), suffix);
        //OSSFactory.build().uploadObject2OSS(file,"","test/");

		//保存文件信息
		/*SysOssEntity ossEntity = new SysOssEntity();
		ossEntity.setUrl(url);
		ossEntity.setCreateDate(new Date());
		sysOssService.save(ossEntity);*/

		//存储的信息需要入库

        return ServerResponseEntity.success(url);

    }


    @ApiOperation(value = "删除文件",notes = "删除文件")
    @DeleteMapping("deleteFile")
    @ApiImplicitParams({
            @ApiImplicitParam(name ="bucketName",value = "bucket名称",paramType = "query",dataType = "String",required = true),
            @ApiImplicitParam(name ="fileName",value = "bucket名称",paramType = "query",dataType = "String",required = true)

    })
    public ServerResponseEntity deleteFile(@RequestParam("bucketName")String bucketName,@RequestParam("fileName")String fileName) throws Exception {
        //存储的信息需要入库
        String deleteFile = OSSFactory.build().deleteFile(bucketName,"", fileName);
        return ServerResponseEntity.success(deleteFile);
    }

    @ApiOperation(value = "查询文件",notes = "查询文件")
    @GetMapping("fileList")
    @ApiImplicitParam(name ="bucketName",value = "bucket名称",paramType = "query",dataType = "String",required = true)
    public ServerResponseEntity selectFile(@RequestParam("bucketName") String bucketName){
        List fileList = OSSFactory.build().getFileList(bucketName);
        return ServerResponseEntity.success(fileList);
    }

    @GetMapping("bucketList")
    @ApiOperation(value = "获取bucket列表",notes = "获取bucket列表")
    public ServerResponseEntity getBucketList(){
        List<Bucket> allBucketList = OSSFactory.build().getAllBucketList();
        return ServerResponseEntity.success(allBucketList);
    }

    @GetMapping("bucketInfo")
    @ApiOperation(value = "获取bucket信息",notes = "获取bucket信息")
    public ServerResponseEntity getBucketInfo(@RequestParam("bucketName")String bucketName){
        BucketInfo bucketInfo = OSSFactory.build().getBucketInfo(bucketName);
        return ServerResponseEntity.success(bucketInfo);
    }


    @GetMapping("fileUrl")
    @ApiOperation(value = "获取文件的url",notes = "获取文件的url")
    @ApiImplicitParams({
            @ApiImplicitParam(name ="bucketName",value = "bucket名称",paramType = "query",dataType = "String",required = true),
            @ApiImplicitParam(name ="key",value = "关键字",paramType = "query",dataType = "String",required = true)

    })
    public ServerResponseEntity getFileURL(@RequestParam("bucketName")String bucketName,@RequestParam("key")String key){
        URL downloadPath = OSSFactory.build().getDownloadPath(bucketName, key);
        return ServerResponseEntity.success(downloadPath);
    }



}
