package com.BillingandStokes;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Stocks extends JFrame implements ActionListener {
    JPanel p;
    JButton b1,b2,b3,b4;
    JLabel l,l1,l2,l_h;

    public Stocks(){
        p=new JPanel(null);
        b1=new JButton("Update Stocks");
        b2=new JButton("Stocks Available");
        b3=new JButton("Add new Product");
        b4=new JButton("Back");
        l_h=new JLabel("Stocks");
        l=new JLabel();
        l1=new JLabel();
        l2=new JLabel();

        b1.setBounds(10,250,150,30);
        b2.setBounds(330,250,150,30);
        b3.setBounds(170,250,150,30);
        b4.setBounds(190,360,100,30);
        l.setBounds(35,110,100,100);
        l1.setBounds(195,110,100,100);
        l2.setBounds(355,110,100,100);
        l_h.setBounds(210,30,100,20);
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(Stocks.class.getResource("updateicon.jpg"));

            Image img = bi.getScaledInstance(l.getWidth(), l.getHeight(), Image.SCALE_SMOOTH);

            ImageIcon ic = new ImageIcon(img);
            l.setIcon(ic);

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            bi = ImageIO.read(Stocks.class.getResource("st.png"));

            Image img = bi.getScaledInstance(l.getWidth(), l.getHeight(), Image.SCALE_SMOOTH);

            ImageIcon ic = new ImageIcon(img);
            l1.setIcon(ic);

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            bi = ImageIO.read(Stocks.class.getResource("sto.png"));

            Image img = bi.getScaledInstance(l.getWidth(), l.getHeight(), Image.SCALE_SMOOTH);

            ImageIcon ic = new ImageIcon(img);
            l2.setIcon(ic);

        } catch (IOException e) {
            e.printStackTrace();
        }

        p.add(l_h);
        p.add(l);
        p.add(l1);
        p.add(l2);
        p.add(b1);
        p.add(b2);
        p.add(b3);
        p.add(b4);

        add(p);
//        setSize(510, 500);
        setVisible(true);
        setResizable(false);
        setBounds(500,100,510,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Stocks");

        Font font = new Font("Times New Roman", Font.BOLD, 30);
        l_h.setFont(font);


        Color c = new Color(26, 24, 24);
        b1.setBackground(c);
        b2.setBackground(c);
        b3.setBackground(c);
        b4.setBackground(c);

        Color c1 = new Color(255, 215, 0);
        b1.setForeground(c1);
        b2.setForeground(c1);
        b3.setForeground(c1);
        b4.setForeground(c1);

        Color c2 = new Color(225, 173, 1);
        p.setBackground(c2);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
    }

    public static void main(String[] args)
    {
        Stocks sk=new Stocks();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(b1)){
            Update_Stocks.main(new String[10]);
            dispose();
        }
        if (e.getSource().equals(b2)){
            Stocks_Available.main(new String[10]);
            dispose();
        }
        if (e.getSource().equals(b3)){
            New_Product.main(new String[10]);
            dispose();
        }
        if (e.getSource().equals(b4)){
            Home.main(new String[10]);
            dispose();
        }
    }
}
