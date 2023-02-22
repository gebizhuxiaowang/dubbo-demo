## dubbo 示例项目 整合了springboot+nacos

本项目为服务端

### 本地调试注意事项

```
需要先编译 proto文件，生成代码
 mvn clean protobuf:compile

编译完成后 将target/generated-sources/protobuf/java 目录设置为 Generated Source Root

然后就可以启动项目了
```
