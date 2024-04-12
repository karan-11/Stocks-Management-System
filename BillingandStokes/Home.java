package com.BillingandStokes;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Home extends JFrame implements ActionListener {
    JPanel p;
    JLabel l;
    JButton b1,b2,b3,b4,b5,b6,b7;
    ImageIcon img;

    public Home()
    {

        p=new JPanel(null);
        b1=new JButton("All Products");
        b2=new JButton("Generate Bill");
        b3=new JButton("Stock");
        b4=new JButton("View Income");
        b5=new JButton("Buyer's Detail");
        b6=new JButton("View Bill");
        b7=new JButton("Log Out");
        l=new JLabel();

        b1.setBounds(30,300,150,35);
        b2.setBounds(200,300,150,35);
        b3.setBounds(380,300,150,35);
        b4.setBounds(30,390,150,35);
        b5.setBounds(200,390,150,35);
        b6.setBounds(380,390,150,35);
        b7.setBounds(200,450,150,35);
        l.setBounds(30,15,500,260);

        BufferedImage bi = null;
        try {
            bi = ImageIO.read(Home.class.getResource("cs1.png"));

            Image img = bi.getScaledInstance(l.getWidth(), l.getHeight(), Image.SCALE_SMOOTH);

            ImageIcon ic = new ImageIcon(img);
            l.setIcon(ic);

        } catch (IOException e) {
            e.printStackTrace();
        }


        p.add(l);
        p.add(b1);
        p.add(b2);
        p.add(b3);
        p.add(b4);
        p.add(b5);
        p.add(b6);
        p.add(b7);
        add(p);

        setSize(580, 550);
        setVisible(true);
        setResizable(false);
        setBounds(500,100,580,550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Second");

        Color c = new Color(26, 24, 24);
        b1.setBackground(c);
        b2.setBackground(c);
        b3.setBackground(c);
        b4.setBackground(c);
        b5.setBackground(c);
        b6.setBackground(c);
        b7.setBackground(c);

        Color c1 = new Color(255, 215, 0);
        b1.setForeground(c1);
        b2.setForeground(c1);
        b3.setForeground(c1);
        b4.setForeground(c1);
        b5.setForeground(c1);
        b6.setForeground(c1);
        b7.setForeground(c1);

        Color cl = new Color(225, 173, 1);
        p.setBackground(cl);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
        b7.addActionListener(this);
    }
    public static void main(String[] args)
    {
        Home s = new Home();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(b1)){
            See_all_prod.main(new String[10]);
            dispose();
        }
        if (e.getSource().equals(b2)){
            Generate_bill.main(new String[10]);
            dispose();
        }
        if (e.getSource().equals(b3)){
            Stocks.main(new String[10]);
            dispose();
        }
        if (e.getSource().equals(b4)){
            Income.main(new String[10]);
            dispose();
        }
        if (e.getSource().equals(b5)){
            BuyersDetails.main(new String[10]);
            dispose();
        }
        if (e.getSource().equals(b6)){
            ViewBill.main(new String[10]);
            dispose();
        }
        if (e.getSource().equals(b7)){
            Main.main(new String[10]);
            dispose();
        }
    }
}
