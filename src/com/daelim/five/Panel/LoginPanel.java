package com.daelim.five.Panel;

import com.daelim.five.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {
    private JPasswordField passwordField;
    private JTextField textField;

    public LoginPanel(){
        setView();

        setBackground(Color.ORANGE);
        setSize(500,500);
        setLayout(null);
    }

    private void setView() {
        JLabel label_name = new JLabel("아이디");
        label_name.setBounds(120,100,60,30);
        add(label_name);
        textField = new JTextField();
        textField.setBounds(180,100,200,30);
        add(textField);

        JLabel label_pw = new JLabel("비밀번호");
        label_pw.setBounds(120,150,60,30);
        add(label_pw);
        passwordField = new JPasswordField();
        passwordField.setBounds(180,150,200,30);
        add(passwordField);

        JButton bt_login = new JButton("로그인");
        bt_login.setBounds(120,200,260,30);
        bt_login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("ID: "+textField.getText());
                System.out.println("PW: "+passwordField.getText());

                Main.connection(textField.getText());

                if(textField.getText().equals("")){
                    JOptionPane.showMessageDialog(Main.f,"아이디를 입력해주세요.");
                    return;
                }
                if(passwordField.getText().equals("")){
                    JOptionPane.showMessageDialog(Main.f,"비밀번호를 입력해주세요.");
                    return;
                }
                Main.movePage(2);
            }
        });
        add(bt_login);

        JButton bt_join = new JButton("회원가입");
        bt_join.setBounds(120,250,260,30);
        bt_join.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.movePage(3);
            }
        });
        add(bt_join);
        JButton bt_setting = new JButton("환경설정");
        bt_setting.setBounds(120,300,260,30);
        bt_setting.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.movePage(4);
            }
        });
        add(bt_setting);
    }
}
