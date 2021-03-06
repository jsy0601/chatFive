package com.daelim.five.Panel;

import com.daelim.five.Data.SettingData;
import com.daelim.five.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

public class SettingPanel extends JPanel {
    private JTextField server, port;

    public SettingPanel(){
        setView();

        setBackground(Color.PINK);
        setSize(500,500);
        setLayout(null);
    }

    private void setView() {
        JLabel label_server = new JLabel("SERVER");
        label_server.setBounds(120,100,60,30);
        add(label_server);
        server = new JTextField();
        server.setBounds(180,100,200,30);
        add(server);
        JLabel label_port = new JLabel("PORT");
        label_port.setBounds(120,150,60,30);
        add(label_port);
        port = new JTextField();
        port.setBounds(180,150,200,30);
        add(port);

        JButton bt_save = new JButton("저장");
        bt_save.setBounds(120,220,260,30);
        bt_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("SERVER : "+server.getText()+", PORT: "+port.getText());

                if(server.getText().equals("")){
                    JOptionPane.showMessageDialog(Main.f,"서버 정보가 입력되지 않았습니다.");
                    return;
                }
                if(port.getText().equals("")){
                    JOptionPane.showMessageDialog(Main.f,"포트 정보가 입력되지 않았습니다.");
                    return;
                }
                try {
                    SettingData data;
                    data = new SettingData(server.getText(), port.getText());

                    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("d://settingData.txt"));
                    out.writeObject(data);
                    out.close();

                    FileInputStream fis = new FileInputStream("d://settingData.txt");
                    ObjectInputStream in = new ObjectInputStream(fis);
                    in.readObject();
                    in.close();
                }catch (Exception ex){
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(Main.f,"파일이 정상적으로 생성되지 않았습니다.");
                    return;
                }
                Main.movePage(1);
            }
        });
        add(bt_save);
        JButton bt_back = new JButton("취소");
        bt_back.setBounds(120,260,260,30);
        bt_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.movePage(1);
            }
        });
        add(bt_back);
    }
}
