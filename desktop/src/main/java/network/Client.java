package main.java.network;

import main.java.functions.Bot;
import main.java.functions.Score;
import main.java.screens.GameScreen;
import main.java.screens.OnlineScreen;

import java.io.*;
import java.net.Socket;

/**
 * создание клиента со всеми необходимыми утилитами, точка входа в программу в классе Client
 */

class ClientSomthing {

    private Socket socket;
    private BufferedReader in; // поток чтения из сокета
    private BufferedWriter out; // поток чтения в сокет
    private BufferedReader inputUser; // поток чтения с консоли
    private String addr; // ip адрес клиента
    private int port; // порт соединения



    public ClientSomthing(String addr, int port) {
        synchronized (this){
        this.addr = addr;
        this.port = port;
        try {
            this.socket = new Socket(addr, port);
            wait();
        } catch (IOException | InterruptedException e) {
            System.err.println("Socket failed");
        }
        try {
            // потоки чтения из сокета / записи в сокет, и чтения с консоли
           // Server.server.close();
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        //      synchronized (OnlineScreen.game){
            //            OnlineScreen.game.setScreen(new GameScreen(OnlineScreen.game));
            //            wait();}
            new ReadMsg().start(); // нить читающая сообщения из сокета в бесконечном цикле
            new WriteMsg().start(); // нить пишущая сообщения в сокет приходящие с консоли в бесконечном цикле
        } catch (IOException e) {
            // Сокет должен быть закрыт при любой
            // ошибке, кроме ошибки конструктора сокета:
            ClientSomthing.this.downService();
        }
        // В противном случае сокет будет закрыт
        // в методе run() нити.
    }}


    /**
     * закрытие сокета
     */
    private void downService() {
        try {
            if (!socket.isClosed()) {
                socket.close();
                in.close();
                out.close();
            }
        } catch (IOException ignored) {
        }
    }

    // нить чтения сообщений с сервера
    private class ReadMsg extends Thread {
        @Override
        public void run() {
            String[] pos;
            try {
                while (true) {
                    pos = in.readLine().split(" "); // ждем сообщения с сервера
                    if (Score.timeSeconds > 180f) {
                        ClientSomthing.this.downService(); // харакири
                        break; // выходим из цикла если пришло "stop"
                    }
                    Bot.xp = Float.parseFloat(pos[0]);
                    Bot.yp = Float.parseFloat(pos[1]);
                }
            } catch (IOException e) {
                ClientSomthing.this.downService();
            }
        }
    }

    // нить отправляющая сообщения приходящие с консоли на сервер
    public class WriteMsg extends Thread {

        @Override
        public void run() {
            while (true) {
                try {
                    if (Score.timeSeconds > 180f) {
                        ClientSomthing.this.downService(); // харакири
                        break; // выходим из цикла если пришло "stop"
                    } else {
                      out.write(GameScreen.control1.getX() + " " + GameScreen.control1.getY()+"\n"); // отправляем на сервер
                    }
                    out.flush(); // чистим
                } catch (IOException e) {
                    ClientSomthing.this.downService(); // в случае исключения тоже харакири

                }

            }
        }
    }
}

public class Client {

    public static String ipAddr;
    public static int port;

    /**
     * создание клиент-соединения с узананными адресом и номером порта
     *
     //* @param args
     */

    public static void main() throws IOException {
        port=Integer.parseInt(ipAddr.split(":")[1]);
        ipAddr = ipAddr.split(":")[0];
        System.out.println(ipAddr+":"+port);

        new ClientSomthing(ipAddr, port);
    }
}