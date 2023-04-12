package com.example.dubbo.filter;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.Filter;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcContext;
import org.apache.dubbo.rpc.RpcException;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import java.util.List;

/**
 * @version 1.0
 * @classname DubboProviderAuthFilter
 * @description todo Dubbo服务提供端 filter，用于鉴权
 */
@Slf4j
@Activate(group = CommonConstants.PROVIDER, order = 2)
public class DubboProviderAuthFilter implements Filter {

    private String excludesPattern = "/**/*MetadataService";
    private List<String> excludeMethodPatterns = Lists.newArrayList("login");
    private PathMatcher excludePatternMatcher = new AntPathMatcher();

    /**
     * dubbo通过setter方式自动注入 无法使用Autowired
     *
     * @param clientConfig
     */
    // public void setClientConfig(final ClientConfig clientConfig) {
    //     this.clientConfig = clientConfig;
    // }

    /**
     * Filter自动拦截Dubbo请求
     *
     * @param invoker
     * @param invocation
     * @return
     */
    @SuppressWarnings({"checkstyle:IllegalCatch", "checkstyle:NPathComplexity"})
    @Override
    public Result invoke(final Invoker<?> invoker, final Invocation invocation) throws RpcException {
        // 白名单url 直接放行
        String uri = invoker.getUrl().getUrlAddress().toString();
        uri = uri.substring(uri.indexOf(":") + 1);
        if (this.isExclusion(uri)) {
            return invoker.invoke(invocation);
        }

        // 获取客户端传过来的附加参数
        final String userAgent = RpcContext.getServerAttachment().getAttachment("user-agent");
        final String tokenType = RpcContext.getServerAttachment().getAttachment("token");
        // TODO 执行校验逻辑

        return invoker.invoke(invocation);
    }


    /**
     * 检查uri 是否在排除名单中
     *
     * @param uri
     * @return
     */
    private boolean isExclusion(final String uri) {
        if (this.excludesPattern == null) {
            return false;
        }
        return this.excludePatternMatcher.match(this.excludesPattern, uri);
    }

    /**
     * 检查是否 要验证token
     *
     * @param method
     * @return
     */
    private boolean isExclusionMethod(final String method) {
        if (this.excludeMethodPatterns == null) {
            return false;
        }
        return this.excludeMethodPatterns.stream()
                .anyMatch(pattern -> this.excludePatternMatcher.match(pattern, method));
    }
}
