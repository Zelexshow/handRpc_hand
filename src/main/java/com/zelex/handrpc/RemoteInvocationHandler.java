package com.zelex.handrpc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class RemoteInvocationHandler implements InvocationHandler {
    private String host;
    private int port;

    public RemoteInvocationHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("come in");
        //请求数据的包装
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setClassName(method.getDeclaringClass().getName());
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setParameters(args);
        //远程通信
        RpcNetTransport rpcNetTransport = new RpcNetTransport(host, port);
        System.out.println("成功生成动态代理...");
        Object send = rpcNetTransport.send(rpcRequest);
        System.out.println("完成调用，准备返回");
        return send;
    }
}
