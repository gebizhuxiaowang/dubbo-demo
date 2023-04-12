package com.example.dubbo.grpcservice;


import com.example.dubbo.excepiton.BzException;
import com.example.dubbo.interfaces.user.DubboUserServiceTriple;
import com.example.dubbo.interfaces.user.HelloReply;
import com.example.dubbo.interfaces.user.HelloRequest;
import org.apache.dubbo.common.stream.StreamObserver;
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

    /**
     * 异常捕获测试
     * @param request
     * @return
     */
    @Override
    public HelloReply helloException(HelloRequest request) {
        throw new BzException(1000, "自定义异常");
        // return super.helloException(request);
    }
}

