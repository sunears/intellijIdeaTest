package org.sunears;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.py.Pinyin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.List;

/**
 * Created by sunears on 16/8/27.
 */
public class UIAddPy extends JFrame implements ActionListener {
    public static String footer="</div></body></html>";
    JButton open=null;

    public UIAddPy(){
        JPanel panel=(JPanel)this.getContentPane();
        panel.setLayout(new BorderLayout());
        open=new JButton("请选择文件");
        panel.add(open,BorderLayout.NORTH);
        this.setTitle("加拼音工具0.1");
        this.setBounds(400, 200, 600, 300);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        open.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        JFileChooser jfc=new JFileChooser();
        jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        jfc.showDialog(new JLabel(), "选择");
        File file=jfc.getSelectedFile();
        if(file!=null) {
            if (file.isDirectory()) {
                System.out.println("文件夹:" + file.getAbsolutePath());
            } else if (file.isFile()) {
                System.out.println("文件:" + file.getAbsolutePath());
            }
            System.out.println(jfc.getSelectedFile().getName());
        }
    }


    public static String getPy(String text){
        String reText="";
        String py="";
        HanLP.Config.enableDebug();
        List<Pinyin> pinyinList = HanLP.convertToPinyinList(text);
        for(int i=0;i<text.length();i++){
            py=pinyinList.get(i).getPinyinWithToneMark()=="none"?"":pinyinList.get(i).getPinyinWithToneMark();
            reText+="<ruby>"+text.charAt(i)+"<rt>"+py+"</rt></ruby>";

        }
        return  reText;
    }
    public void readAndWrite(String filepath,String outFilePath,String fhpath){

        try{

            File fh=new File(fhpath);
            BufferedInputStream fhi = new BufferedInputStream(new FileInputStream(fh));
            BufferedReader fhr = new BufferedReader(new InputStreamReader(fhi,"utf-8"),5*1024*1024);// 用5M的缓冲读取文本文件


            File file = new File(filepath);
            BufferedInputStream fis = new BufferedInputStream(new FileInputStream(file));
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis,"utf-8"),5*1024*1024);// 用5M的缓冲读取文本文件

            File outfile=new File(outFilePath);
            BufferedOutputStream fos=new BufferedOutputStream(new FileOutputStream(outfile));
            BufferedWriter writer=new BufferedWriter(new OutputStreamWriter(fos,"utf-8"),5*1024*1024);// 用5M的缓冲读取文本文件

            String line = "";
            while((line = fhr.readLine()) != null){
                writer.write(line);
                writer.newLine();
            }

            while((line = reader.readLine()) != null){
                //TODO: write your business
                writer.write("<p>");
                writer.write(UIAddPy.getPy(line));
                writer.write("</p>");
                writer.newLine();
            }
            writer.write(UIAddPy.footer);
            reader.close();
            fis.close();

            writer.close();
            fos.close();

            fhr.close();
            fhi.close();


        }
        catch (Exception e){
            System.out.println(e.toString());
        }
    }
    public static void main(String args[]){
        String filepath= "1.txt";
        String outFilePath="out.html";
        String fhpath="header.txt";
        new UIAddPy();
    }
}
