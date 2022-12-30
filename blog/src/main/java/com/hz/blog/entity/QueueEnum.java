package com.hz.blog.entity;

import lombok.Getter;

@Getter
public enum QueueEnum {


//    public static final String DLX_QUEUE = "queue.postdlx";//死信队列
//    public static final String DLX_EXCHANGE = "exchange.postdlx";//死信交换机
//    public static final String DLX_ROUTING_KEY = "routingkey.postdlx";//死信队列与死信交换机绑定的routing-key
//
//    public static final String POST_QUEUE = "queue.post";//订单的延时队列
//    public static final String POST_EXCHANGE = "exchange.post";//订单交换机
//    public static final String POST_ROUTING_KEY = "routingkey.post";//延时队列与订单交换机绑定的routing-key

    /**
     * 发送博客通知ttl队列
     */
    POST_TTL_MQ("exchange.postdlx","queue.postdlx","routingkey.postdlx"),


    /**
     * 发送博客
     */
    POST_MQ("exchange.post","queue.post","routingkey.post"),

    /**
     * 商城订单消息通知队列
     */
    QUEUE_ORDER_CANCEL("mall.order.direct","mall.order.cancel","mall.order.cancel"),
    /**
     * 商城订单消息通知ttl队列
     */
    QUEUE_TTL_ORDER_CANCLE("mall.order.direct.ttl","mall.order.cancel.ttl","mall.order.cancel.ttl");

    /**
     * 交换机名称
     */
    private String exchange;
    /**
     * 队列名称
     */
    private  String name;
    /**
     * 路由键
     */
    private String routeKey;

    QueueEnum(String exchange, String name, String routeKey) {
        this.exchange=exchange;
        this.name=name;
        this.routeKey=routeKey;
    }

}
