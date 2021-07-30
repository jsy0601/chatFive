package com.daelim.five;

import com.daelim.five.Data.SettingData;
import com.daelim.five.Panel.JoinPanel;
import com.daelim.five.Panel.LoginPanel;
import com.daelim.five.Panel.MainPanel;
import com.daelim.five.Panel.SettingPanel;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import javax.swing.*;
import java.awt.*;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {
    public static JFrame f;
    private static LoginPanel loginPage;
    private static MainPanel mainPage;
    private static JoinPanel joinPage;
    private static SettingPanel settingPage;
    private static String user;

    public static void main(String[] args) {
        f = new JFrame();

        loginPage = new LoginPanel();
        f.add(loginPage);
        mainPage = new MainPanel();
        f.add(mainPage);
        joinPage = new JoinPanel();
        f.add(joinPage);
        settingPage = new SettingPanel();
        f.add(settingPage);

        f.setSize(500, 500);
        f.setLayout(null);
        f.setVisible(true);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        movePage(1);

    }

    public static void movePage(int index) {
        loginPage.setVisible(false);
        mainPage.setVisible(false);
        joinPage.setVisible(false);
        settingPage.setVisible(false);

        switch (index) {
            case 1:
                f.setTitle("Chat - 로그인 화면");
                loginPage.setVisible(true);
                break;
            case 2:
                f.setTitle("Chat - 채팅 화면");
                mainPage.setVisible(true);
                break;
            case 3:
                f.setTitle("Chat - 회원가입 화면");
                joinPage.setVisible(true);
                break;
            case 4:
                f.setTitle("Chat - 환경설정 화면");
                settingPage.setVisible(true);
                break;
        }
    }

    static WebSocketClient ws;
    public static void connection(String userid){
        user = userid;
        try {

            FileInputStream fis = new FileInputStream("d://settingData.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fis);

            SettingData v = (SettingData)objectInputStream.readObject();
            System.out.println("PORT "+v.getPort());
            System.out.println("SERVER "+v.getServer());
            System.out.println("SERVER:PORT "+"ws://"+v.getServer()+":"+v.getPort());
            ws = new WebSocketClient(new URI("ws://"+v.getServer()+":"+v.getPort())) {
                @Override
                public void onOpen(ServerHandshake serverHandshake) {
                    System.out.println("onOpen");
                    ws.send(user+"|start");
                    movePage(2);
                }

                @Override
                public void onMessage(String s) {
                    System.out.println("onMessage s : " + s);
                    mainPage.recvMsg(s);
                }

                @Override
                public void onClose(int i, String s, boolean b) {
                    System.out.println("onClose");
                }

                @Override
                public void onError(Exception e) {
                    System.out.println("onError");
                }
            };

            ws.connect();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void sendMessage(String msg) {
        String nowTime = "";
        nowTime = new SimpleDateFormat("HH:mm:ss").format(new Date());
        ws.send(user+"|"+nowTime+"|"+msg);
    }
}
