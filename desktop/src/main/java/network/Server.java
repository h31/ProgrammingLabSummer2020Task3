package main.java.network;

import main.java.functions.Score;
import main.java.functions.Velocity;
import main.java.screens.GameScreen;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class Server extends Thread {

    //public static ServerSomthing serverList;
    public static ServerSocket server = null;
    public static Integer p;
    public static Socket socket;
    public final int PORT = 0;
    private BufferedReader in; // поток чтения из сокета
    private BufferedWriter out; // поток завписи в сокет


    /**
     * //* @param args
     */

    public void run() {

        synchronized (this) {
            try {
                server = new ServerSocket(PORT);
                p = server.getLocalPort();
                //   System.out.println("Server Started");
                socket = server.accept();
                //  GameScreen.game.wait();
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                new ReadMsg().start(); // нить читающая сообщения из сокета в бесконечном цикле
                new WriteMsg().start(); // нить пишущая сообщения в сокет приходящие с консоли в бесконечном цикле
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

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

    private class ReadMsg extends Thread {
        @Override
        public void run() {
            String[] pos;
            try {
                while (true) {

                    if (Score.timeSeconds > 180f) {
                        Server.this.downService(); // харакири
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
                Server.this.downService();
            }
        }
    }

    // нить отправляющая сообщения приходящие с консоли на сервер
    public class WriteMsg extends Thread {

        @Override
        public void run() {
            while (true) {
                //  System.out.println("Yes");
                try {
                    if (Score.timeSeconds > 180f) {
                        Server.this.downService(); // харакири
                        break; // выходим из цикла если пришло "stop"
                    } else {
                        if (GameScreen.stage != null) {
                            out.write(GameScreen.control1.getX() + " " + GameScreen.control1.getY() + " " + GameScreen.k + "\n"); // отправляем на сервер
                            //    System.out.println(out);
                        }
                    }
                    out.flush(); // чистим
                } catch (IOException e) {
                    Server.this.downService(); // в случае исключения тоже харакири

                }

            }
        }

    }

}
