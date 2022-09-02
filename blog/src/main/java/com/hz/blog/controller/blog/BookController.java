package com.hz.blog.controller.blog;


import com.hz.blog.entity.ResponseResult;
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
    public ResponseResult getBook(){
        return ResponseResult.successResult(100000,"成功");
    }


    @ApiOperation(value ="获取book列表",notes="获取book列表")
    @GetMapping("getBookList")
    public ResponseResult getBookList(){
        return ResponseResult.successResult(100000,"成功");
    }

    @ApiOperation(value ="通过Id获取book",notes="通过Id获取book")
    @GetMapping("getBookById")
    public ResponseResult getBookById(String id){
        return ResponseResult.successResult(100000,"成功");
    }

    @ApiOperation(value ="通过Name获取book",notes="通过Name获取book")
    @GetMapping("getBookByName")
    public ResponseResult getBookByName(String name){
        return ResponseResult.successResult(100000,"成功");
    }


}
