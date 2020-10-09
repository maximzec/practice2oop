package com.company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class MArrayClient {
    public static void main(String[] args) throws UnsupportedEncodingException {
//Прослушивание входящих сообщений на порте 50001
        try (
// создание сокета для подключения к серверу
                Socket clientSocket = new Socket("localhost", 60001);
//настройка считывателя входного потока
//для ввода с клавиатуры
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
//настройка считывателя входного потока от сервера
                // ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
//настройка записи исходяего потока потока от сервера
                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
        ) { //ввод сообщения пользователя с клавиатуры
            System.out.println("Введите сообщение для отправки серверу");
            String message = br.readLine();
//отправить сообщение на сервер
            Double reqArray [] = new Double[4];
            for (int x = 0 ; x < 4 ; x++){
                String number = br.readLine();
                reqArray[x] = Double.parseDouble(number);
            }


            ObjArray Obj = new ObjArray(reqArray, message);
            out.writeObject(Obj);

//считать ответ от сервера
            try {
                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());
                //BufferedReader br1 = new BufferedReader(new InputStreamReader(in));
                //String reply = br1.readLine();
                //System.out.println(reply);
                String  obj = (String)in.readUTF();
                System.out.println(obj);

            } catch (EOFException eof) {
//ничего не делать, сервер закрыл соединение
            }
//закрыть соединение
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}