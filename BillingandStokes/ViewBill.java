package com.BillingandStokes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ViewBill extends JFrame implements ActionListener {

    JPanel p;
    JLabel l,l1,l_h;
    JTextField t;
    JButton b,b1;
    JComboBox c1,c2,c3;

    String[] arr1 ={"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31",};
    String[] arr2 ={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
    String[] arr3 ={"2022"};

    int d,m,y;
    String mm;
    Connection connection;
    PreparedStatement statement;
    ResultSet set;
    public ViewBill(){
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
        l_h=new JLabel("View Bill");
        l=new JLabel("Buyer's Name");
        l1=new JLabel("Enter the Date");
        t=new JTextField();
        b=new JButton("View Bill");
        b1=new JButton("Back");
        c1=new JComboBox(arr1);
        c2=new JComboBox(arr2);
        c3=new JComboBox(arr3);
        l_h.setBounds(175,30,200,30);
        l.setBounds(40,80,100,50);
        l1.setBounds(40,160,100,30);
        t.setBounds(230,90,100,30);
        c1.setBounds(140,165,80,20);
        c2.setBounds(240,165,80,20);
        c3.setBounds(340,165,80,20);
        b.setBounds(130,230,100,30);
        b1.setBounds(280,230,100,30);

        p.add(l_h);
        p.add(l1);
        p.add(l);
        p.add(t);
        p.add(c1);
        p.add(c2);
        p.add(c3);
        p.add(b);
        p.add(b1);

        Font font = new Font("Times New Roman", Font.BOLD, 27);
        l_h.setFont(font);

        Font ft = new Font("Times New Roman", Font.BOLD,14 );
        l1.setFont(ft);
        l.setFont(ft);

        Color c = new Color(26, 24, 24);
        b.setBackground(c);
        b1.setBackground(c);

        Color cl = new Color(255, 215, 0);
        b.setForeground(cl);
        b1.setForeground(cl);

        Color cl1 = new Color(225, 173, 1);
        p.setBackground(cl1);

        add(p);
        setSize(500, 500);
        setVisible(true);
        setResizable(false);
        setBounds(500,100,500,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        b.addActionListener(this);
        b1.addActionListener(this);
        c1.addActionListener(this);
        c2.addActionListener(this);
        c3.addActionListener(this);
    }

    public static void main(String[] args) {
            ViewBill vb = new ViewBill();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(c1))
        {
            d=Integer.parseInt(String.valueOf(c1.getSelectedItem()));
        }
        else if(e.getSource().equals(c2))
        {
            mm=String.valueOf(c2.getSelectedItem());

            try {
                Date date = new SimpleDateFormat("MMMM", Locale.ENGLISH).parse(mm);
                Calendar calendar=Calendar.getInstance();
                calendar.setTime(date);
                m=calendar.get(Calendar.MONTH)+1;

            } catch (ParseException parseException) {
                parseException.printStackTrace();
            }
        }
        else if(e.getSource().equals(c3))
        {
            y=Integer.parseInt(String.valueOf(c3.getSelectedItem()));
        }
        else if(e.getSource().equals(b))
        {
            String n=t.getText();
            String dt1=d+"/"+m+"/"+y;

            System.out.println(dt1);

            try
            {
                statement=connection.prepareStatement("select * from billing where name=? and bdate =?");
                statement.setString(1,n);
                statement.setString(2,dt1);
                set=statement.executeQuery();
                if(set.next())
                {

                    String fn=set.getString(5);

                    System.out.println(fn);

                    Runtime rt = Runtime.getRuntime();

                    rt.exec("C:\\Program Files (x86)\\Microsoft Office\\Office12\\WINWORD.exe D:\\billingsystem\\"+fn+".docx");
                }
                else
                {
                    JOptionPane.showMessageDialog(ViewBill.this, "No Record Found");
                }

            } catch (SQLException | IOException throwables) {
                throwables.printStackTrace();
            }
        }
        if (e.getSource().equals(b1)){
            Home.main(new String[10]);
            dispose();
        }
    }
}
