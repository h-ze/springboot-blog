package com.hz.blog.controller;

import com.hz.blog.response.ServerResponseEntity;
import com.hz.blog.websocket.WebSocketServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

//import com.hz.blog.test.MyClient;


/**
 * 测试websocket连接
 */
@RestController
@RequestMapping("webSocket")
@Slf4j
public class WebSocketController {

//    @Bean//这个注解会从Spring容器拿出Bean
//    public SystemWebSocketHandler infoHandler() {
//        return new SystemWebSocketHandler();
//    }
    /*@Autowired
    SimpMessagingTemplate simpMessagingTemplate;*/

    @Value("${server.port}")
    private String serverPort;

    @PostMapping("/sendMessage")
    public ServerResponseEntity sendMessage(@RequestParam String message, @RequestParam String username, @RequestParam String token){
            //WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            //MyClient client = new MyClient();
            //container.connectToServer(client, new URI("ws://localhost:"+serverPort+"/websocket/"+username+"?token="+token));
            //client.send("客户端发送消息:" + message);

            WebSocketServer.sendInfo(message,username);
            //SystemWebSocketHandler.sendMessageToUser("1",new TextMessage("123"));
            return ServerResponseEntity.success("测试websocket成功");
    }

    /**
     * 主动给websocket发送消息
     * @param message 发送的消息
     * @param token 用户的token
     * @return
     * @throws IOException
     */
//    @PostMapping("/push")
//    public ResponseEntity<String> pushToWeb(@RequestParam String message, @RequestParam String token) throws IOException {
//        WebSocket.sendInfo(message,"zhangsan");
//        return ResponseEntity.ok("MSG SEND SUCCESS");
//    }

}
