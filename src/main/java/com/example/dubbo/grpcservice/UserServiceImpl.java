package com.example.dubbo.grpcservice;


import com.example.dubbo.interfaces.user.DubboUserServiceTriple;
import com.example.dubbo.interfaces.user.HelloReply;
import com.example.dubbo.interfaces.user.HelloRequest;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @version 1.0
 * @classname UserServiceImpl
 * @description todo
 */
@DubboService
public class UserServiceImpl extends DubboUserServiceTriple.UserServiceImplBase {

    @Override
    public HelloReply hello(HelloRequest request) {
        HelloReply reply = HelloReply.newBuilder()
                .setAge("18")
                .setUsername(request.getUsername())
                .setHello("欢迎")
                .build();
        return reply;
    }
}

