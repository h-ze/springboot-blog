package com.hz.blog.service;


import com.hz.blog.response.ServerResponseEntity;
import com.hz.blog.vo.LoginQrcodeVO;

/**
 * 登录二维码业务逻辑
 *
 * @author wangck
 * @date 2019/7/9
 */
public interface ILoginQrcodeService {


    /**
     * 生成登录二维码图片并且返回图片地址
     *
     * @return
     */
    public ServerResponseEntity<LoginQrcodeVO> /*Response<LoginQrcodeVO>*/ createLoginQrcode();

    /**
     * 扫描二维码登录
     *
     * @param qrcodeId
     * @param userId
     * @return
     */
    public ServerResponseEntity<Boolean> qrcodeLogin(String qrcodeId, String userId);


    /**
     * 查询二维码的登录状态
     *
     * @param qrcodeId
     * @return
     */

    public ServerResponseEntity getLoginQrcodeStatus(String qrcodeId);


}
