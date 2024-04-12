package com.BillingandStokes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class New_Product extends JFrame implements ActionListener {
    JPanel p;
    JLabel l1,l2,l3,l4,l5,l6,l7;
    JTextField t1,t2,t3,t4,t5;
    JButton b,b1;
    Connection connection;
    PreparedStatement statement;

    int id;
    String name,size;
    float price;
    int quantity;

    public New_Product()
    {
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
        l1=new JLabel("Product ID");
        l2=new JLabel("Product Name");
        l3=new JLabel("Enter the size");
        l4=new JLabel("Enter the price");
        l5=new JLabel("Enter quantity");
        l6=new JLabel();
        l7=new JLabel("Add New Product");
        t1=new JTextField();
        t2=new JTextField();
        t3=new JTextField();
        t4=new JTextField();
        t5=new JTextField();
        b=new JButton("ADD");
        b1=new JButton("Back");

        l1.setBounds(110,100,100,30);
        l2.setBounds(110,150,100,30);
        l3.setBounds(110,200,100,30);
        l4.setBounds(110,250,100,30);
        l5.setBounds(110,300,100,30);
        l7.setBounds(120,30,200,30);
        t1.setBounds(220,100,100,30);
        t2.setBounds(220,150,100,30);
        t3.setBounds(220,200,100,30);
        t4.setBounds(220,250,100,30);
        t5.setBounds(220,300,100,30);
        b.setBounds(110,350,100,30);
        b1.setBounds(220,350,100,30);
        l6.setBounds(140,420,200,30);
        l6.setForeground(Color.RED);

        p.add(l1);
        p.add(l2);
        p.add(l3);
        p.add(l4);
        p.add(l5);
        p.add(l6);
        p.add(l7);
        p.add(t1);
        p.add(t2);
        p.add(t3);
        p.add(t4);
        p.add(t5);
        p.add(b);
        p.add(b1);

        Font font = new Font("Times New Roman", Font.BOLD, 25);
        l7.setFont(font);

        Font ft = new Font("Times New Roman", Font.BOLD,14 );
        l1.setFont(ft);
        l2.setFont(ft);
        l3.setFont(ft);
        l4.setFont(ft);
        l5.setFont(ft);

        add(p);
        Color c = new Color(26, 24, 24);
        b.setBackground(c);
        b1.setBackground(c);

        Color c1 = new Color(255, 215, 0);
        b.setForeground(c1);
        b1.setForeground(c1);

        Color c2 = new Color(225, 173, 1);
        p.setBackground(c2);

        setSize(500, 500);
        setVisible(true);
        setResizable(false);
        setBounds(500,100,500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("New Product");

        b.addActionListener(this);
        b1.addActionListener(this);
    }

    public static void main(String[] args)
    {
        New_Product np=new New_Product();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource().equals(b))
        {
            id=Integer.parseInt(t1.getText());
            name=String.valueOf(t2.getText());
            size=String.valueOf(t3.getText());
            price=Float.parseFloat(t4.getText());
            quantity=Integer.parseInt(t5.getText());
            try
            {
                statement=connection.prepareStatement("insert into product values(?,?,?,?,?)");
                statement.setInt(1,id);
                statement.setString(2, name);
                statement.setString(3, size);
                statement.setFloat(4, price);
                statement.setInt(5, quantity);
                int i=statement.executeUpdate();
                if(i>0)
                {
                    l6.setText("product has been added");
                }
                else
                {
                    l6.setText("an error occurred... try again");
                }
                t1.setText("");t2.setText("");t3.setText("");t4.setText("");t5.setText("");l6.setText("");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
        if (e.getSource().equals(b1)){
            Stocks.main(new String[10]);
            dispose();
        }
    }
}
