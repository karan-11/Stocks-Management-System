package com.BillingandStokes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;

public class Income extends JFrame implements ActionListener {
    JPanel p;
    JLabel l1,l2,l3,l_t,l4;

    JComboBox c1,c2,c3,c4,c5,c6;
    String[] arr1 ={"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31",};
    String[] arr2 ={"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
    String[] arr3 ={"2022"};
    JTable t;
    JScrollPane sp;


    String date1,date2;

    int da1,mo1,ye1,da2,mo2,ye2;

    JButton btn,btn1;
    Connection connection;
    PreparedStatement statement;
    ResultSet set;
    Vector<String> col;

    Vector<Vector<String>> v1;

    String name,phnno, price,bill,bill_date;

    float total;

    int d1,d2,m1,m2,y1,y2;
    String mm1,mm2;

    public Income(){
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

            col=new Vector<>();
            v1=new Vector<>();

            col.add("S.No.");
            col.add("Name");
            col.add("Phone No");
            col.add("Price");
            col.add("Bill Date");


        p=new JPanel(null);
            l1= new JLabel("Start Date");
            l2=new JLabel("End Date");
            l3=new JLabel("INCOME");
            l_t= new JLabel("");
            l4=new JLabel("Total Income:");
            c1=new JComboBox(arr1);
            c2=new JComboBox(arr2);
            c3=new JComboBox(arr3);
            c4=new JComboBox(arr1);
            c5=new JComboBox(arr2);
            c6=new JComboBox(arr3);
            t=new JTable();
            sp=new JScrollPane(t);
            btn=new JButton("SHOW");
            btn1=new JButton("Back");
            l1.setBounds(105,80,100,20);
            l2.setBounds(405,80,100,20);
            l3.setBounds(250,20,250,20);
            c1.setBounds(2,140,80,20);
            c2.setBounds(100,140,80,20);
            c3.setBounds(200,140,80,20);
            c4.setBounds(300,140,80,20);
            c5.setBounds(400,140,80,20);
            c6.setBounds(500,140,80,20);
            btn.setBounds(100,210,80,30);
            btn1.setBounds(400,210,80,30);
            l4.setBounds(230,210,80,20);
            l_t.setBounds(310,210,80,20);

            p.add(l_t);
            p.add(l1);
            p.add(l2);
            p.add(l3);
            p.add(c1);
            p.add(c2);
            p.add(c3);
            p.add(c4);
            p.add(c5);
            p.add(c6);
            p.add(btn);
            p.add(l4);
            p.add(btn1);
            p.add(sp);
            add(p);

            Calendar ca = Calendar.getInstance();
            ca.add(Calendar.DATE, -1);
            da1=ca.get(Calendar.DAY_OF_MONTH);
            mo1=ca.get(Calendar.MONTH)+1;
            ye1=ca.get(Calendar.YEAR);

            Calendar ca2 = Calendar.getInstance();
            da2=ca2.get(Calendar.DAY_OF_MONTH);
            mo2=ca2.get(Calendar.MONTH)+1;
            ye2=ca2.get(Calendar.YEAR);

            date1=da1+"/"+mo1+"/"+ye1;
            date2=da2+"/"+mo2+"/"+ye2;

            y1=ye2;
            y2=ye2;

            m1=mo2;
            m2=mo2;

            d1=da2;
            d2=da2;

            System.out.println(date1);
            System.out.println(date2);

            setSize(600, 500);
            setVisible(true);
            setResizable(false);
        setBounds(500,100,600,500);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setTitle("Income");

            Font font = new Font("Times New roman",Font.PLAIN,28);
            l3.setFont(font);

            Font ft = new Font("Times New Roman", Font.BOLD,18 );
            l1.setFont(ft);
            l2.setFont(ft);

        Color c = new Color(26, 24, 24);
        btn.setBackground(c);
        btn1.setBackground(c);

        Color cl = new Color(255, 215, 0);
        btn.setForeground(cl);
        btn1.setForeground(cl);

        Color cl1 = new Color(225, 173, 1);
        p.setBackground(cl1);


        c1.addActionListener(this);
            c2.addActionListener(this);
            c3.addActionListener(this);
            c4.addActionListener(this);
            c5.addActionListener(this);
            c6.addActionListener(this);
            btn.addActionListener(this);
            btn1.addActionListener(this);

        }

        public static void main(String[] args) {
            Income i=new Income();
        }

        @Override
        public void actionPerformed(ActionEvent e)
        {

            if(e.getSource().equals(c1))
            {
                d1=Integer.parseInt(String.valueOf(c1.getSelectedItem()));
            }
            else if(e.getSource().equals(c2))
            {
                    mm1=String.valueOf(c2.getSelectedItem());

                try {
                    Date date = new SimpleDateFormat("MMMM", Locale.ENGLISH).parse(mm1);
                    Calendar calendar=Calendar.getInstance();
                    calendar.setTime(date);
                    m1=calendar.get(Calendar.MONTH)+1;
                    System.out.println(m1);

                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }
            }
            else if(e.getSource().equals(c3))
            {
                y1=Integer.parseInt(String.valueOf(c3.getSelectedItem()));
            }
            else if(e.getSource().equals(c4))
            {
                d2=Integer.parseInt(String.valueOf(c4.getSelectedItem()));
            }
            else if(e.getSource().equals(c5))
            {
                mm2=String.valueOf(c5.getSelectedItem());

                try {
                    Date date = new SimpleDateFormat("MMMM", Locale.ENGLISH).parse(mm2);
                    Calendar calendar=Calendar.getInstance();
                    calendar.setTime(date);
                    m2=calendar.get(Calendar.MONTH)+1;
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }
            }
            else if(e.getSource().equals(c6))
            {
                y2=Integer.parseInt(String.valueOf(c6.getSelectedItem()));
            }
            else if(e.getSource().equals(btn))
            {
                v1.removeAllElements();

                String dt1=d1+"/"+m1+"/"+y1;
                String dt2=d2+"/"+m2+"/"+y2;

                System.out.println("dt1:"+dt1);
                System.out.println("dt2:"+dt2);

                try
                {
                    statement=connection.prepareStatement("select * from billing where str_to_date(bdate, '%d/%m/%Y') between str_to_date(?, '%d/%m/%Y') and str_to_date(?, '%d/%m/%Y');");
                    statement.setString(1,dt1);
                    statement.setString(2,dt2);
                    set=statement.executeQuery();

                    int i=1;
                    while(set.next()) {
                        Vector<String> v2 = new Vector<>();

                        name=set.getString(2);
                        phnno=set.getString(3);
                        price=set.getString(4);
                        bill=set.getString(5);
                        bill_date=set.getString(6);

                        total=total+Float.parseFloat(price);

                        v2.add(String.valueOf(i));
                        v2.add(name);
                        v2.add(phnno);
                        v2.add(price);
                        v2.add(bill_date);

                        i++;
                        v1.add(v2);

                    }
                    t=new JTable(v1, col);
                    sp=new JScrollPane(t);

                    sp.setBounds(50, 250, 450, 150);

                    l_t.setText(String.valueOf(total));
                    p.add(sp);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
                date1=d1+"/"+m1+"/"+y1;
                date2=d2+"/"+m2+"/"+y2;

                if (e.getSource().equals(btn1)){
                Home.main(new String[10]);
                dispose();
            }
        }
    }
