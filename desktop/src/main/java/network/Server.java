package main.java.network;

import main.java.functions.Bot;
import main.java.functions.Score;
import main.java.screens.GameScreen;
import main.java.screens.OnlineScreen;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class Server extends Thread {

    //public static ServerSomthing serverList;
    public static ServerSocket server;
    public static Integer p;
    public final int PORT = 0;
    public static Socket socket;
    private BufferedReader in; // поток чтения из сокета
    private BufferedWriter out; // поток завписи в сокет


    /**
     * //* @param args
     */

    public void run() {

synchronized (this){
        try {
            server = new ServerSocket(PORT);
            p = server.getLocalPort();
            System.out.println("Server Started");
            socket = server.accept();
            wait();
            while (true) {
                    if (Score.timeSeconds > 180f) {
                        downService(); // харакири
                        break; // если пришла пустая строка - выходим из цикла прослушки
                    }
                    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    send();
                    String[] pos;
                    pos = in.readLine().split(" ");
                  //  synchronized (Bot.xp)
                    Bot.xp = Float.parseFloat(pos[0]);
                //    synchronized (Bot.yp)
                    Bot.yp = Float.parseFloat(pos[1]);}

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }}


    private void send() {
        try {
            out.write(GameScreen.control1.getX() + " " + GameScreen.control1.getY() + "\n");
            out.flush();
        } catch (IOException ignored) {
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

}
