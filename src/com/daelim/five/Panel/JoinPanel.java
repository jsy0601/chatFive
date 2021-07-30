package com.daelim.five.Panel;

import com.daelim.five.Main;
import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JoinPanel extends JPanel {
    private JPasswordField pwField, pwcField;
    private JTextField nameField, emailField, idField;

    public JoinPanel(){
        setView();

        setBackground(Color.LIGHT_GRAY);
        setSize(500,500);
        setLayout(null);
    }
    private void setView() {
        JLabel label_name = new JLabel("이름");
        label_name.setBounds(120,50,60,30);
        add(label_name);
        nameField = new JTextField();
        nameField.setBounds(180,50,200,30);
        add(nameField);

        JLabel label_email = new JLabel("이메일");
        label_email.setBounds(120,100,60,30);
        add(label_email);
        emailField = new JTextField();
        emailField.setBounds(180,100,200,30);
        add(emailField);

        JLabel label_id = new JLabel("아이디");
        label_id.setBounds(120,150,60,30);
        add(label_id);
        idField = new JTextField();
        idField.setBounds(180,150,200,30);
        add(idField);

        JLabel label_pw = new JLabel("비밀번호");
        label_pw.setBounds(120,200,60,30);
        add(label_pw);
        pwField = new JPasswordField();
        pwField.setBounds(180,200,200,30);
        add(pwField);

        JLabel label_pwc = new JLabel("비밀번호 확인");
        label_pwc.setBounds(120,250,90,30);
        add(label_pwc);
        pwcField = new JPasswordField();
        pwcField.setBounds(210,250,200,30);
        add(pwcField);

        JButton bt_join = new JButton("가입하기");
        bt_join.setBounds(120,350,260,30);
        bt_join.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("name: "+nameField.getText());
                System.out.println("email: "+emailField.getText());
                System.out.println("id: "+idField.getText());
                System.out.println("pw: "+pwField.getText());
                if(pwField.getText().equals(pwcField.getText())){
                    JOptionPane.showMessageDialog(Main.f,"회원가입이 완료되었습니다.");
                    return;
                }else {
                    JOptionPane.showMessageDialog(Main.f,"비밀번호 확인이 일치하지 않습니다.");
                }

            }
        });
        add(bt_join);

        JButton bt_back = new JButton("<");
        bt_back.setBounds(10,10,50,30);
        bt_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.movePage(1);
            }
        });
        add(bt_back);
    }
}
