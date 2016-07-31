package org.sunears;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.MultimediaInfo;

public class VideoDuration {

    public void getVideoDuration(String path) {
        // get all files in specified "path"
//        File[] files = new File(path).listFiles();
        File file=new File(path);
        Encoder encoder = new Encoder();
        MultimediaInfo multimediaInfo;

        long totalTime = 0L;
        long duration = 0L;
        if (!file.isDirectory() && file.toString().endsWith(".mp4")) {
            try {
                multimediaInfo = encoder.getInfo(file);
                duration = multimediaInfo.getDuration();
                totalTime += duration;
            } catch (EncoderException e) {
                e.printStackTrace();
            }
        }
//        for (int i = 0; i < files.length; i++) {
//            // here, the format of video can be changed, JAVE upports dozens of formats
//            if (!files[i].isDirectory() && files[i].toString().endsWith(".mp4")) {
//                try {
//                    multimediaInfo = encoder.getInfo(files[i]);
//                    duration = multimediaInfo.getDuration();
//                    totalTime += duration;
//                } catch (EncoderException e) {
//                    e.printStackTrace();
//                }
//            }
//        }

        // long --> hh:mm: calculate the time manually
        System.out.print(totalTime/(3600*1000) + ":" + totalTime%(3600*1000)/(60*1000) + ":" + totalTime%(3600*1000)%(60*1000)/1000);
        System.out.println("==>Manually");

        // set a default TimeZone before using Date, Calendar and SimpleDateFormat
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+00:00")); // January 1, 1970, 00:00:00 GMT(can be found in Date.class)

        // long --> hh:mm:ss by means of java.util.Date
        Date date = new Date(totalTime);
        System.out.print(date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds());
        System.out.println("==>By Date");

        // long --> hh:mm:ss by means of java.util.Calendar, Date
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        System.out.print(calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND));
        System.out.println("==>By Calendar");

        // long --> hh:mm:ss by means of java.text.SimpleDateFormat, java.util.Date
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        System.out.print(simpleDateFormat.format(date));
        System.out.println("==>By SimpleDateFormat");
    }
    public static void main(String[] args) {
        String filePath = "/Users/sunears/Downloads/20160729210301.mp4";

        VideoDuration videoDuration = new VideoDuration();
        videoDuration.getVideoDuration(filePath);
    }
}