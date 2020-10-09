package com.company;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {
    private static ServerSocket server;
    public static final int SERVER_PORT = 60001;

    public static void main (String[] args)
    {
        try
        {
            server = new ServerSocket(SERVER_PORT);
            new Thread(new Server()).start();
            new Thread(new Server()).start();
            new Thread(new Server()).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        ObjArray Obj;
        String message =null;
        while(!"STOP".equals(message)){
//принятие входящего клиента
            System.out.println("Ожидание соединения");
            try{//настройка сокета соединения с клиентом
                Socket connectionSocket = server.accept();
//Настройка считывателя входного потока (от клиента)
                ObjectInputStream incoming= new
                        ObjectInputStream(connectionSocket.getInputStream());
//Настройка записи иходящего потока (к клиенту)
                ObjectOutputStream outgoing =new
                        ObjectOutputStream(connectionSocket.getOutputStream());
//блок try
//получение отправленного клиентом сообщения
                System.out.println("Ожидание сообщения клиента");
                Obj= (ObjArray) incoming.readObject();
                message = Obj.getM();
                System.out.println("Получено от клиента сообщение "+ message);
//Отправить сообщение клиенту
                String s = new String("STOP");
                String str=new String("");
                if (!message.equals(s)) {
                    Variant varray;
                    switch (message){
                        case "array" : varray = new Variant(Obj.getArray());break;
                        case "string" : varray = new Variant(Obj.getReq());break;
                        case "arraylist" : varray = new Variant(Obj.getReq1());break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + message);
                    }

                    String max = varray.getResult();
                    str="Сообщение сервером получено " ;
                    //outgoing.writeObject(str);
                    str = str + max;
                    //outgoing.writeUTF(str);
                } else {
                    str="Соединение с сервером закрыто";
                    Thread.currentThread().interrupt();
                    return;
                    // outgoing.writeObject(str);
                }

                outgoing.writeUTF(str);
//Закрытьвыходной поток, так как больше нету данных для
// оправки клиенту
                outgoing.close();
            }catch (IOException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
                return;
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
                return;
            }
        }

    }
}