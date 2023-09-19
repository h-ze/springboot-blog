package com.hz.blog.controller.blog;


import com.hz.blog.controller.BaseController;
import com.hz.blog.response.ServerResponseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(tags = "bookController接口")
@RestController
@RequestMapping("bookController")
public class BookController extends BaseController {

    @ApiOperation(value ="获取book",notes="获取book")
    @GetMapping("getBook")
    public ServerResponseEntity<String> getBook(){
        return ServerResponseEntity.success();
    }


    @ApiOperation(value ="获取book列表",notes="获取book列表")
    @GetMapping("getBookList")
    public ServerResponseEntity<String> getBookList(){
        return ServerResponseEntity.success();
    }

    @ApiOperation(value ="通过Id获取book",notes="通过Id获取book")
    @GetMapping("getBookById")
    public ServerResponseEntity<String> getBookById(String id){
        return ServerResponseEntity.success();
    }

    @ApiOperation(value ="通过Name获取book",notes="通过Name获取book")
    @GetMapping("getBookByName")
    public ServerResponseEntity<String> getBookByName(String name){
        return ServerResponseEntity.success();
    }


}
