package com.BillingandStokes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Update_Stocks extends JFrame implements ActionListener {
    JPanel p;
    JLabel l1,l2,l3,l4;
    JTextField t1,t2;
    JButton b,b1;
    Connection connection;
    PreparedStatement statement;
    ResultSet set;

    int id;
    int quantity;

    public Update_Stocks()
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
       l2=new JLabel("Enter the quantity");
       l3=new JLabel();
       l4=new JLabel("Update Stocks");
       t1=new JTextField();
       t2=new JTextField();
       b=new JButton("Update");
       b1=new JButton("Back");

       l1.setBounds(100,100,100,30);
       l2.setBounds(100,150,100,30);
       t1.setBounds(210,100,100,30);
       t2.setBounds(210,150,100,30);
       b.setBounds(100,210,100,30);
       b1.setBounds(210,210,100,30);
       l3.setBounds(150,280,200,30);
       l4.setBounds(140,30,200,30);

       l3.setForeground(Color.RED);

       p.add(l1);
       p.add(l2);
       p.add(l3);
       p.add(l4);
       p.add(t1);
       p.add(t2);
       p.add(b);
       p.add(b1);

       add(p);
       setSize(500, 500);
       setVisible(true);
       setResizable(false);
       setDefaultCloseOperation(EXIT_ON_CLOSE);
       setTitle("Update_Stocks");

       Font font = new Font("Times New Roman", Font.BOLD, 25);
       l4.setFont(font);

        Color c = new Color(26, 24, 24);
        b.setBackground(c);
        b1.setBackground(c);

        Color c1 = new Color(255, 215, 0);
        b.setForeground(c1);
        b1.setForeground(c1);

        Color c2 = new Color(225, 173, 1);
        p.setBackground(c2);

       b.addActionListener(this);
       b1.addActionListener(this);
    }

    public static void main(String[] args)
    {
        Update_Stocks us = new Update_Stocks();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(b))
        {
            id=Integer.parseInt(t1.getText());
            quantity=Integer.parseInt(t2.getText());
            try
            {
                statement=connection.prepareStatement("update product set quantity=?+quantity where id=?");
                statement.setInt(1,quantity);
                statement.setInt(2, id);
                int i=statement.executeUpdate();

                if(i>0)
                {
                    l3.setText("record updated");
                }
                else
                {
                    l3.setText("an error occurred... try again");
                }

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
