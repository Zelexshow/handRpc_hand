package com.zelex.handrpc;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        RpcProxyClient rpcProxyClient = new RpcProxyClient();
        IHelloService service= rpcProxyClient.clientProxy(IHelloService.class,"localhost",8088);//生成一个动态代理类
        String result = service.sayHello("Mic");
        System.out.println(result);
//        service.saveUser();

    }
}
