package com.example.dubbo.filter;

import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.cluster.filter.ClusterFilter;

/**
 * @version 1.0
 * @classname DubboFilter
 * @description 实现调用dubbo 服务时隐式参数的传递
 * filter使用参考 https://dubbo.apache.org/zh/docs3-v2/java-sdk/concepts-and-architecture/service-invocation/
 * 隐式参数传递参考 https://dubbo.incubator.apache.org/zh/docs3-v2/java-sdk/advanced-features-and-usage/service/attachment/
 */
@Activate(group = CommonConstants.CONSUMER, order = 2)
public class DubboConsumerFilter implements ClusterFilter {


    /**
     * Always call invoker.invoke() in the implementation to hand over the request to the next filter node.
     *
     * @param invoker
     * @param invocation
     */
    @SuppressWarnings("checkstyle:NPathComplexity")
    @Override
    public Result invoke(final Invoker<?> invoker, final Invocation invocation) throws RpcException {
        // TODO 拦截所有client 的调用，进行自定义操作，例如：将验证信息放入header
        final RpcContext serverContext = RpcContext.getServerAttachment();
        RpcContext.getClientAttachment()
                .setAttachment("user-agent", "userAgent")
                .setAttachment("token", "token");
        return invoker.invoke(invocation);
    }
}
