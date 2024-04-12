package com.BillingandStokes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Stocks_Available extends JFrame implements ActionListener {
    JPanel p1;
    JTable t;
    JButton b;
    JLabel l;
    JScrollPane sp;

    Connection connection;
    PreparedStatement statement;
    ResultSet set;
    int id;
    String [] col={"Product ID","Product Name","Size","Stocks Available"};
    String [][] row;

    public Stocks_Available()
    {

        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/billing_stokes", "root", "qwerty@1234");

            statement=connection.prepareStatement("select count(id) from product");
            set=statement.executeQuery();
            set.next();
            id=set.getInt(1);

            row=new String[id][4];

            statement=connection.prepareStatement("select * from product");
            set=statement.executeQuery();


            int i=0;
            while(set.next())
            {
                row[i][0]=String.valueOf(set.getInt(1));
                row[i][1]=set.getString(2);
                row[i][2]=set.getString(3);
                row[i][3]=String.valueOf(set.getInt(5));
                i++;
            }
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        p1=new JPanel(null);
        t=new JTable(row, col);
        sp=new JScrollPane(t);
        b=new JButton("Back");
        l=new JLabel("Available Stocks");

        sp.setBounds(35,60,415,325);
        b.setBounds(200,410,100,40);
        l.setBounds(150,20,200,20);

        p1.add(sp);
        p1.add(l);
        p1.add(b);

        add(p1);
        setSize(500, 500);
        setVisible(true);
        setResizable(false);
        setBounds(500,100,500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Stocks Available");

        Font font = new Font("Times New Roman", Font.BOLD, 25);
        l.setFont(font);

        Color c = new Color(26, 24, 24);
        b.setBackground(c);
        t.setBackground(c);

        Color c1 = new Color(255, 215, 0);
        b.setForeground(c1);
        t.setForeground(c1);

        Color c2 = new Color(225, 173, 1);
        p1.setBackground(c2);

        b.addActionListener(this);

    }

    public static void main(String[] args)
    {
        Stocks_Available sa=new Stocks_Available();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(b)){
            Stocks.main(new String[10]);
            dispose();
        }
    }
}
