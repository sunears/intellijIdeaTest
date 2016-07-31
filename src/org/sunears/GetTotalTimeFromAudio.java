package org.sunears;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by sunears on 16/7/30.
 */
public class GetTotalTimeFromAudio {
    public static int textLines=0;

    public static ArrayList<String> readTxtFile(String filePath){
        try {
            String encoding="UTF8";
            ArrayList <String> textList=new ArrayList<String>();
            File file=new File(filePath);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while((lineTxt = bufferedReader.readLine()) != null){
                    textLines++;
                    textList.add(lineTxt);
//                    System.out.println(lineTxt);
                }
                read.close();
            }else{
                System.out.println("找不到指定的文件");
            }
            return textList;
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
        System.out.println(textLines);
        return null;
    }
    // a integer to xx:xx:xx
    public static String secToTime(int time) {
        String timeStr = null;
        int hour = 0;
        int minute = 0;
        int second = 0;
        if (time <= 0)
            return "00:00";
        else {
            minute = time / 60;
            if (minute < 60) {
                second = time % 60;
                timeStr = unitFormat(minute) + ":" + unitFormat(second);
            } else {
                hour = minute / 60;
                if (hour > 99)
                    return "99:59:59";
                minute = minute % 60;
                second = time - hour * 3600 - minute * 60;
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
            }
        }
        return timeStr;
    }

    public static String unitFormat(int i) {
        String retStr = null;
        if (i >= 0 && i < 10)
            retStr = "0" + Integer.toString(i);
        else
            retStr = "" + i;
        return retStr;
    }
    public static String timeToSec(String totaltime){

        int totalSecond=0;

        String [] array_totaltime=totaltime.split(":");
        for (int i = 0; i <array_totaltime.length; i++) {
            if(array_totaltime.length==3)
                totalSecond=(Integer.parseInt(array_totaltime[0])*60+Integer.parseInt(array_totaltime[1]))*60+Integer.parseInt(array_totaltime[2]);
        }
        return String.valueOf(totalSecond);
    }
    public static void main(String args[]){
        String filePath = "/Users/sunears/1.txt";
//      "res/";
        ArrayList<String> tl=readTxtFile(filePath);


        int totalLines=textLines;
        String srtFormate="00:00:53,286 --> 00:00:57,164";
        String totaltime="02:45:40";
        String totalSecond=GetTotalTimeFromAudio.timeToSec(totaltime);
        int jg=Integer.parseInt(totalSecond)/totalLines;
        System.out.println(totalSecond);
        System.out.println(totalLines);
        System.out.println(jg);
        int start=0;
        int end=jg;
        int talkSpeed=1;
        String startString="";
        String endString="";
        for (int i = 0; i <totalLines ; i++) {
            jg=tl.get(i).length()*talkSpeed;
            end=(start+jg)<=Integer.parseInt(totalSecond)?(start+jg):Integer.parseInt(totalSecond);
            if(end<= Integer.parseInt(totalSecond)){
                if(GetTotalTimeFromAudio.secToTime(start).split(":").length<3){
                    startString="00:"+GetTotalTimeFromAudio.secToTime(start);
                }
                else{
                    startString=GetTotalTimeFromAudio.secToTime(start);
                }
                if(GetTotalTimeFromAudio.secToTime(end).split(":").length<3){
                    endString="00:"+GetTotalTimeFromAudio.secToTime(end);
                }
                else{
                    endString=GetTotalTimeFromAudio.secToTime(end);
                }
                System.out.println((i+1)+"\n"+startString+",000 --> "+endString+",000");
                start=end+1;
                System.out.println(tl.get(i)+"length:");
                System.out.println();
            }

        }
//        System.out.print(GetTotalTimeFromAudio.secToTime(Integer.parseInt(totalSecond)/totalLines));
    }
}
