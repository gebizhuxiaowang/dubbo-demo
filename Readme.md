## dubbo 示例项目 整合了springboot+nacos

本项目为服务端

### 本地调试注意事项

```
需要先编译 proto文件，生成代码
 mvn clean protobuf:compile

编译完成后 将target/generated-sources/protobuf/java 目录设置为 Generated Source Root

然后就可以启动项目了
```

###待处理的问题
- [x] 预留选中示例
- [ ] dubbo 3.2.0 以上版本 ，Triple 协议 无法用postman，进行调试
- [ ] 使用Triple（gRPC）服务端全局异常处理问题
```shell
1、 无法捕获自定义异常，
2、 没找到示例项目
```