package com.BillingandStokes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import java.util.ArrayList;
import java.util.Iterator;

public class SaveInFile {

    FileOutputStream out;

    XWPFParagraph paragraph;
    XWPFRun run;
    XWPFDocument document;
    String n;

    public SaveInFile(String name, String contact, String date, String time, String datetime) throws IOException, Exception
    {
        //Blank Document
        document = new XWPFDocument();

        n=name+datetime;

        //Write the Document in file system
        File file = new File("D:\\billingsystem\\"+n+".docx");

        if(!file.exists())
        {
            file.createNewFile();
        }

        out = new FileOutputStream(file, true);

        paragraph = document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        run = paragraph.createRun();
        run.setFontFamily("Times New Roman");
        run.setBold(true);
        run.setFontSize(26);
        run.setTextPosition(40);

        run.setText("Chandigarh Sales");

        paragraph=document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.LEFT);
        run = paragraph.createRun();
        run.setText(name+"   "+contact);

        paragraph=document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.LEFT);
        run = paragraph.createRun();
        run.setText("DATE-   "+date);

        paragraph=document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.LEFT);
        run = paragraph.createRun();
        run.setText("TIME-   "+time);

        paragraph=document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.LEFT);

        run = paragraph.createRun();
        run.setFontSize(22);
        run.setTextPosition(50);
        run.setText("Your purchase summary ");
        run.addBreak();

        paragraph=document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.LEFT);

        run = paragraph.createRun();
        run.setFontSize(10);
        run.setTextPosition(40);
        run.setText("Name       Size       Quantity       U Price       T Price");


        System.out.println("createparagraph.docx written successfully");
    }

    public String writeData(String name, String price) throws IOException
    {
        System.out.println("NAME : " +name);
        System.out.println("PRICE : " +price);

        paragraph=document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.LEFT);

        run = paragraph.createRun();
        run.setFontSize(10);
        run.setTextPosition(20);
        run.setText(name+"              "+price);



        document.write(out);


        return n;
    }

    public String writeList(ArrayList<ArrayList<String>> al, float total)
    {
        Iterator<ArrayList<String>> itr = al.iterator();

        while(itr.hasNext())
        {
            ArrayList<String> al2 = itr.next();

            String pname=al2.get(0);
            String psize=al2.get(1);
            String quantity=al2.get(2);
            String per_pc=al2.get(3);
            String price=al2.get(4);

            System.out.println(pname+"    "+price);

            paragraph=document.createParagraph();
            paragraph.setAlignment(ParagraphAlignment.LEFT);

            run = paragraph.createRun();
            run.setFontSize(10);
            run.setTextPosition(20);
            run.setText(pname+"        "+psize+"        "+quantity+"        "+per_pc+"        "+price);
        }

        paragraph=document.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.LEFT);

        run = paragraph.createRun();
        run.setFontSize(20);
        run.setTextPosition(40);

        run.setText("TOTAL = "+total);

        try {
            document.write(out);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            out.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return n;
    }

    public void closeFile() throws IOException
    {
        out.close();
    }
    public static void main(String[] args) throws IOException, Exception {

        new SaveInFile("","","","","");


    }
}