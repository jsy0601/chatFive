package com.daelim.five.Panel;

import com.daelim.five.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class MainPanel extends JPanel {
    static JTextField textField;
    static JTextArea textArea;

    public MainPanel(){
        setView();

        setBackground(Color.CYAN);
        setSize(500,500);
        setLayout(null);
    }

    private void setView() {

        textField = new JTextField();
        textField.setBounds(70,10,330,30);

        Action ok = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(textField.getText().equals("")){
                    return;
                }
                Main.sendMessage(textField.getText());
                textField.setText("");
                textArea.setCaretPosition(textArea.getDocument().getLength());
            }
        };
        KeyStroke enter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0,false);
        textField.getInputMap(JTable.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(enter,"ENTER");
        textField.getActionMap().put("ENTER",ok);

        add(textField);

        JButton bt_send = new JButton("전송");
        bt_send.setBounds(410,10,70,30);
        bt_send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.sendMessage(textField.getText());
                textField.setText("");
                textArea.setCaretPosition(textArea.getDocument().getLength());
            }
        });
        add(bt_send);

        textArea = new JTextArea();
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(10,50,470,400);
        add(scrollPane);

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

    public void recvMsg(String s) {
        textArea.append(s+"\n");
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }
}
