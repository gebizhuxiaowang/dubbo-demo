package com.example.dubbo.filter;

import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.common.logger.Logger;
import org.apache.dubbo.common.logger.LoggerFactory;
import org.apache.dubbo.common.utils.ReflectUtils;
import org.apache.dubbo.common.utils.StringUtils;
import org.apache.dubbo.rpc.Filter;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.service.GenericService;

import java.lang.reflect.Method;

/**
 * @version 1.0
 * @classname DubboProviderExceptionFilter
 * @description todo
 */
@Activate(group = CommonConstants.PROVIDER)
public class DubboProviderExceptionFilter2 implements Filter {

    private static Logger log = LoggerFactory.getLogger(DubboProviderExceptionFilter2.class);

    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        // return invoker.invoke(invocation);
        Result appResponse = invoker.invoke(invocation);
        if (appResponse.hasException() && GenericService.class != invoker.getInterface()) {
            try {
                Throwable e = appResponse.getException();
                log.error(" [MAGICAL-DUBBO] Fail to MagicalDubboRpcExceptionFilter when called by " + RpcContext.getContext().getRemoteHost() + ". service: " + invoker.getInterface().getName() + ", method: " + invocation.getMethodName() + ", exception: " + e.getClass().getName() + ": " + e.getMessage(), e);
            } catch (Throwable e) {
                log.error(" [MAGICAL-DUBBO] Fail to MagicalDubboRpcExceptionFilter when called by " + RpcContext.getContext().getRemoteHost() + ". service: " + invoker.getInterface().getName() + ", method: " + invocation.getMethodName() + ", exception: " + e.getClass().getName() + ": " + e.getMessage(), e);
                // return AsyncRpcResult.newDefaultAsyncResult(R.fail(65510, "远程服务调用发生异常"), invocation);
            }
        }
        return appResponse;
    }

}
