package com.BillingandStokes;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

public class Generate_bill extends JFrame implements ActionListener , FocusListener {
    JPanel p;
    JLabel l,l1, l2, l3, l4, l5, l6, l7, l8, l9;
    JTextField t1, t2, t3, t4, t5, t6, t7;

    DefaultTableModel dtm;
    JTable t;

    JButton b1, b2,b3;
    JScrollPane sp;
//    String s;
    String[] col = {"Product Name", "Size", "Quantity", "1 Pc. Price", "Price"};
    String[][] row;
    ArrayList<ArrayList<String>> arr = new ArrayList<>();

    Connection connection;
    PreparedStatement statement;
    ResultSet set;

    String bname, ph_no, pname, size, quantity,per_pc,price;
    float t_price;
    int a,quantity1;
    float b, c;

    String filename;

    public Generate_bill() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/billing_stokes", "root", "qwerty@1234");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        p = new JPanel(null);
        l=new JLabel("Generate Bill");
        l1 = new JLabel("Buyer's Name");
        l2 = new JLabel("Phone Number");
        l3 = new JLabel("Product Name");
        l4 = new JLabel("Size");
        l5 = new JLabel("Quantity");
        l6 = new JLabel("1 Pc. Price");
        l9 = new JLabel("Price");
        l7 = new JLabel("Total Bill:");
        l8 = new JLabel();
        t1 = new JTextField();
        t2 = new JTextField();
        t3 = new JTextField();
        t4 = new JTextField();
        t5 = new JTextField();
        t6 = new JTextField();
        t7 = new JTextField();
        b1 = new JButton("Generate Bill");
        b2 = new JButton("ADD");
        b3 = new JButton("Back");
        dtm = new DefaultTableModel(row, col);

        t = new JTable(dtm);

        sp = new JScrollPane(t);

        l.setBounds(195,20,200,20);
        l1.setBounds(20, 50, 100, 50);
        l2.setBounds(285, 50, 100, 50);
        l3.setBounds(25, 100, 100, 50);
        l4.setBounds(160, 100, 100, 50);
        l5.setBounds(265, 100, 100, 50);
        l6.setBounds(365, 100, 100, 50);
        l9.setBounds(490, 100, 100, 50);
        l7.setBounds(160, 420, 100, 50);
        l8.setBounds(260, 420, 100, 50);
        t1.setBounds(110, 65, 100, 25);
        t2.setBounds(380, 65, 100, 25);
        t3.setBounds(20, 150, 100, 25);
        t4.setBounds(130, 150, 100, 25);
        t5.setBounds(240, 150, 100, 25);
        t6.setBounds(350, 150, 100, 25);
        t7.setBounds(460, 150, 100, 25);
        b2.setBounds(240, 180, 100, 30);
        sp.setBounds(50, 220, 450, 150);
        b1.setBounds(60, 380, 170, 30);
        b3.setBounds(320, 380, 160, 30);
        p.add(sp);
        p.add(l);
        p.add(l1);
        p.add(l2);
        p.add(l3);
        p.add(l4);
        p.add(l5);
        p.add(l6);
        p.add(l7);
        p.add(l8);
        p.add(l9);
        p.add(t1);
        p.add(t2);
        p.add(t3);
        p.add(t4);
        p.add(t5);
        p.add(t6);
        p.add(t7);
        p.add(b1);
        p.add(b2);
        p.add(b3);
        add(p);

        Color c = new Color(26, 24, 24);
        b1.setBackground(c);
        b2.setBackground(c);
        b3.setBackground(c);

        Color c1 = new Color(255, 215, 0);
        b1.setForeground(c1);
        b2.setForeground(c1);
        b3.setForeground(c1);

        Color c2 = new Color(225, 173, 1);
        p.setBackground(c2);

        setSize(600, 520);
        setVisible(true);
        setResizable(false);
        setBounds(500,100,600,520);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Generate Bill");

        Font font = new Font("Times New Roman", Font.BOLD, 25);
        l.setFont(font);

        Font ft = new Font("Times New Roman", Font.BOLD,14 );
        l1.setFont(ft);
        l2.setFont(ft);
        l3.setFont(ft);
        l4.setFont(ft);
        l5.setFont(ft);
        l6.setFont(ft);
        l7.setFont(ft);
        l9.setFont(ft);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        t3.addFocusListener(this);
        t5.addFocusListener(this);
    }

    public static void main(String[] args) {
        Generate_bill gb = new Generate_bill();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(b2)) {
            pname = t3.getText();
            size = t4.getText();
            quantity = t5.getText();
            per_pc = t6.getText();
            price = t7.getText();

            Object obj[] = {pname, size, quantity, per_pc, price};

            dtm.addRow(obj);
        } else if (e.getSource().equals(b1)) {
            float total = 0;
            bname = t1.getText();
            ph_no = t2.getText();

            for (int i = 0; i < dtm.getRowCount(); i++) {
                Float amount = Float.parseFloat((String) dtm.getValueAt(i, 4));
                total += amount;

                pname = String.valueOf(dtm.getValueAt(i, 0));
                size = String.valueOf(dtm.getValueAt(i, 1));
                quantity = String.valueOf(dtm.getValueAt(i, 2));
                per_pc = String.valueOf(dtm.getValueAt(i, 3));
                price = String.valueOf(dtm.getValueAt(i, 4));

                ArrayList<String> al = new ArrayList();
                al.add(pname);
                al.add(size);
                al.add(quantity);
                al.add(per_pc);
                al.add(price);

                arr.add(al);
            }

            l8.setText(String.valueOf(total));

            System.out.println(arr);

            Calendar ca = Calendar.getInstance();
            int d = ca.get(Calendar.DAY_OF_MONTH);
            int m = ca.get(Calendar.MONTH)+1;
            int y = ca.get(Calendar.YEAR);
            int ho = ca.get(Calendar.HOUR);
            int mi = ca.get(Calendar.MINUTE);

            String date = d + "/" + m + "/" + y;
            String time = ho + ":" + mi;
            String datetime = d + "" + m + "" + y + "" + ho + "" + mi;

            try {
                SaveInFile si = new SaveInFile(bname, ph_no, date, time, datetime);
                String s = si.writeList(arr, total);

                filename = bname + datetime;

                if (s != null) {

                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
                t_price=Float.parseFloat(l8.getText());
            try {
                statement = connection.prepareStatement("insert into billing(name, phone_num,price, bill, bdate) values(?,?,?,?,?)");
                statement.setString(1, bname);
                statement.setString(2, ph_no);
                statement.setFloat(3, t_price);
                statement.setString(4, filename);
                statement.setString(5, date);
                int i = statement.executeUpdate();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            ;
            quantity1=Integer.parseInt(t5.getText());
            int quan=0;

            try {
                statement=connection.prepareStatement("select * from product where name=?");
                statement.setString(1, pname);
                set=statement.executeQuery();
                if(set.next()){
                    quan=set.getInt(5);
                }

                int qu=quan-quantity1;
                statement = connection.prepareStatement("update product set quantity=? where name=?");
                statement.setInt(1, qu);
                statement.setString(2, pname);
                statement.executeUpdate();
            }
                catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (e.getSource().equals(b3)){
            Home.main(new String[10]);
            dispose();
        }
    }

        @Override
        public void focusGained (FocusEvent e){

        }

        @Override
        public void focusLost (FocusEvent e){
            if (e.getSource().equals(t3)) {
                try {
                    String na = t3.getText();
                    statement = connection.prepareStatement("select * from product where name=? ");
                    statement.setString(1, na);
                    set = statement.executeQuery();

                    if (set.next()) {
                        float price = set.getFloat(4);

                        t6.setText(String.valueOf(price));
                    }

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if (e.getSource().equals(t5)) {
                a = Integer.parseInt(t5.getText());
                b = Float.parseFloat(t6.getText());
                c = a * b;
                t7.setText(String.valueOf(c));
            }
        }
    }