package main.java.network;

import main.java.functions.Score;
import main.java.functions.Velocity;
import main.java.screens.GameScreen;

import java.io.*;
import java.net.Socket;

/**
 * создание клиента со всеми необходимыми утилитами, точка входа в программу в классе Client
 */

public class Client {

    public static String ipAddr;
    public static int port;
    public static Socket socket;
    private BufferedReader in; // поток чтения из сокета
    private BufferedWriter out; // поток чтения в сокет
    private BufferedReader inputUser; // поток чтения с консоли

    /**
     * создание клиент-соединения с узананными адресом и номером порта
     * <p>
     * //* @param args
     */
    public Client(String t) {
        ipAddr = t;
    }

    public void main() throws IOException {
        synchronized (this) {
            port = Integer.parseInt(ipAddr.split(":")[1]);
            ipAddr = ipAddr.split(":")[0];
         //   System.out.println(ipAddr + ":" + port);
            try {
                socket = new Socket(ipAddr, port);
            } catch (IOException e) {
                System.err.println("Socket failed");
            }
            try {
                // Server.server.close();
                // потоки чтения из сокета / записи в сокет, и чтения с консоли
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
//                GameScreen.game.wait();
                new ReadMsg().start(); // нить читающая сообщения из сокета в бесконечном цикле
                new WriteMsg().start(); // нить пишущая сообщения в сокет приходящие с консоли в бесконечном цикле
            } catch (IOException e) {
                // Сокет должен быть закрыт при любой
                // ошибке, кроме ошибки конструктора сокета:
                this.downService();
            }
            // В противном случае сокет будет закрыт
            // в методе run() нити.
        }
    }


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
                    if (Score.timeSeconds > 180f) {
                        Client.this.downService(); // харакири
                        break; // выходим из цикла если пришло "stop"
                    }
                    if (!in.readLine().isEmpty()) {
                        pos = in.readLine().split(" "); // ждем сообщения с сервера
                        Velocity.xo2 = 1 - Float.parseFloat(pos[0]);
                        Velocity.yo2 = 2 - Float.parseFloat(pos[1]);
                        if (!Boolean.parseBoolean(pos[2])) GameScreen.stage.keyDown(131);
                    }
                }
            } catch (IOException e) {
                Client.this.downService();
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
                        Client.this.downService(); // харакири
                        break; // выходим из цикла если пришло "stop"
                    } else {
                        if (GameScreen.stage != null) {
                            out.write(GameScreen.control1.getX() + " " + GameScreen.control1.getY() + " " + GameScreen.k + "\n"); // отправляем на сервер
                          //  System.out.println(out);
                        }
                    }
                    out.flush(); // чистим
                } catch (IOException e) {
                    Client.this.downService(); // в случае исключения тоже харакири

                }

            }
        }
    }
}
