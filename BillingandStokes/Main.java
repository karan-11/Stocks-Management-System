package com.BillingandStokes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
public class Main extends JFrame implements ActionListener {
    JPanel p;
    JLabel l1,l2,l3,l4,l5,l6,l;
    JTextField t1;
    JPasswordField pf;
    JButton b1;
    String username, password;
    Connection connection;
    PreparedStatement statement;
    ResultSet set;

    public Main(){

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/billing_stokes", "root", "qwerty@1234");

        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        p=new JPanel(null);
        l1=new JLabel("Chandigarh Sales");
        l=new JLabel("The Best or Nothing");
        l2=new JLabel("Login");
        l3=new JLabel("to continue to CS");
        l4=new JLabel("Admin");
        l5=new JLabel("Password");
        l6=new JLabel("");
        t1=new JTextField();
        pf=new JPasswordField();
        b1=new JButton("Log in");

        l1.setBounds(150,10,300,50);
        l.setBounds(140,50,300,50);
        l2.setBounds(230,100,100,50);
        l3.setBounds(187,140,200,50);
        l4.setBounds(80,190,100,50);
        l5.setBounds(85,260,100,50);
        t1.setBounds(105,240,300,30);
        pf.setBounds(105,310,300,30);
        b1.setBounds(190,370,100,30);
        l6.setBounds(165,410,180,50);
        p.add(l1);
        p.add(l);
        p.add(l2);
        p.add(l3);
        p.add(l4);
        p.add(l5);
        p.add(l6);
        p.add(t1);
        p.add(pf);
        p.add(b1);

        add(p);
        setSize(500, 500);
        setVisible(true);
        setResizable(false);
        setBounds(500,100,500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setTitle("Home");
        Font font = new Font("Algerian", Font.BOLD, 20);
        l1.setFont(font);
        l.setFont(font);
        Font ft = new Font("Times New roman",Font.PLAIN,18);
        l2.setFont(ft);
        l3.setFont(ft);

        Font ft1 = new Font("Times New Roman", Font.BOLD,14 );
        l4.setFont(ft1);
        l5.setFont(ft1);

        Color c = new Color(26, 24, 24);
        b1.setBackground(c);

        Color c1 = new Color(255, 215, 0);
        b1.setForeground(c1);

        Color c2 = new Color(225, 173, 1);
        p.setBackground(c2);

        b1.addActionListener(this);
    }
    public static void main(String[] args)
    {
        Main m = new Main();

    }

    @Override
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource().equals(b1))
        {
            username=t1.getText();
            password=String.valueOf(pf.getPassword());

            try
            {
                statement=connection.prepareStatement("select * from user where username=? and password=?");
                statement.setString(1, username);
                statement.setString(2, password);
                set=statement.executeQuery();

                if(set.next())
                {
                    Home h = new Home();
                    this.dispose();
                }
                else
                {
                    l6.setText("invalid username or password");
                }
            }
            catch (SQLException throwables)
            {
                throwables.printStackTrace();
            }

        }
    }
}