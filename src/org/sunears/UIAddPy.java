package org.sunears;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.py.Pinyin;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.List;

/**
 * Created by sunears on 16/8/27.
 */
public class UIAddPy extends JFrame implements ActionListener , ChangeListener {
    public static String footer="</div></body></html>";
    JButton open=null;
    JLabel label=null;
    JProgressBar progressbar;
    Timer timer;
    JButton start, stop;
    public UIAddPy(){
        JPanel panel=(JPanel)this.getContentPane();
        panel.setLayout(new GridLayout(5,5,2,2));
        open=new JButton("请选择文件");


        start = new JButton("start");
        start.addActionListener(this);
        stop = new JButton("stop");
        stop.addActionListener(this);
        progressbar = new JProgressBar();

        progressbar.setOrientation(JProgressBar.HORIZONTAL);

        progressbar.setMinimum(0);

        progressbar.setMaximum(100);

        progressbar.setValue(0);

        progressbar.setStringPainted(true);

        progressbar.addChangeListener(this);

        progressbar.setPreferredSize(new Dimension(300, 20));

        progressbar.setBorderPainted(true);

        progressbar.setBackground(Color.pink);
        int leave =  100-progressbar.getValue();
        label = new JLabel("已完成：" + leave, JLabel.CENTER);


        timer = new Timer(50, this);
        panel.add(open);
        panel.add(start);
        panel.add(stop);
        panel.add(progressbar);
        panel.add(label);
        this.setTitle("加拼音工具0.1");
        this.setBounds(400, 200, 600, 300);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        open.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {


        if (e.getSource() == start) {
            timer.start();
        } else if (e.getSource() == stop) {
            timer.stop();
            progressbar.setValue(0);
        } else if (e.getSource() == timer) {
            int value = progressbar.getValue();
            if (value < 100) {
                value++;
                progressbar.setValue(value);
            }
        } else if (e.getSource()==open){
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
    }
    public void stateChanged(ChangeEvent e1) {

        // TODO Auto-generated method stub
        int value = progressbar.getValue();
//        int leave = 100 - value;
        if (e1.getSource() == progressbar) {
            label.setText("已完成：" + value + "%");
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
