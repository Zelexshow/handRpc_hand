package com.zelex.handrpc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RpcNetTransport {
    private String host;
    private int port;

    public RpcNetTransport(String host, int port) {
        this.host = host;
        this.port = port;
    }
    public Object send(RpcRequest request){
        Socket socket=null;
        Object result=null;
        ObjectOutputStream objectOutputStream=null;
        ObjectInputStream objectInputStream=null;

        try {
            socket = new Socket(host, port);//建立连接
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(request);//序列化

            objectOutputStream.flush();
            System.out.println("成功发出序列化指令");
            objectInputStream=new ObjectInputStream(socket.getInputStream());
            result = objectInputStream.readObject();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            if (objectInputStream != null){
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (objectOutputStream != null){
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
