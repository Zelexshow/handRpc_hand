package com.zelex.handrpc;

import java.lang.reflect.Proxy;

public class RpcProxyClient {
    public <T> T clientProxy(final Class<T> interfaceClass,final String host,final int port){
        return (T)Proxy.newProxyInstance(interfaceClass.getClassLoader(),
                new Class<?>[]{interfaceClass},new RemoteInvocationHandler(host,port));
    }
}
