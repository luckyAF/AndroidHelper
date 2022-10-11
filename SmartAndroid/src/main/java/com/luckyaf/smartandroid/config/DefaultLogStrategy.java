package com.luckyaf.smartandroid.config;

import android.app.Application;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import com.blankj.utilcode.BuildConfig;
import com.blankj.utilcode.constant.TimeConstants;
import com.blankj.utilcode.util.FileIOUtils;
import com.blankj.utilcode.util.TimeUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;

import androidx.annotation.NonNull;

/**
 * 类描述：
 *
 * @author Created by luckyAF on 2021/10/22
 */
public class DefaultLogStrategy implements ILogStrategy{
    private final String logPath;

    private final static String LOG_MESSAGE = "log_message";
    private final static String LOG_TYPE_NAME = "log_type_name";
    private final HandlerThread handlerThread;
    private final Handler handler;

    public DefaultLogStrategy(Application app){
        logPath = app.getExternalCacheDir() + "/log/";
        handlerThread = new HandlerThread("handle_app_log");
        handlerThread.start();
        handler = new Handler(handlerThread.getLooper()){
            @Override
            public void dispatchMessage(@NonNull Message msg) {
                String type = msg.getData().getString(LOG_TYPE_NAME);
                String content = msg.getData().getString(LOG_MESSAGE);
                saveLog(type,content);
            }
        };
    }



    @Override
    public void print(String tag, String message) {
        Log.i(tag,message);
    }
    @Override
    public void log(String type, String info) {
        Message message= handler.obtainMessage();
        Bundle bundle = new Bundle();
        bundle.putString(LOG_TYPE_NAME,type);
        bundle.putString(LOG_MESSAGE,info);
        message.setData(bundle);
        handler.dispatchMessage(message);
    }


    private void  saveLog(String type,String  message) {
        String time = TimeUtils.getFitTimeSpanByNow(new Date(),2);
        String fileName = type + time + ".txt";
        String dirPath = logPath + type;
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file =new File(dirPath + "/" + fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e ) {
                e.printStackTrace();
            }
        }
        String tag = "\n" + TimeUtils.getFitTimeSpanByNow(new Date(),5) + "    " + BuildConfig.VERSION_NAME + "      ";
        FileIOUtils.writeFileFromBytesByStream(file, tag.getBytes(StandardCharsets.UTF_8), true);
        FileIOUtils.writeFileFromBytesByStream(file, message.getBytes(StandardCharsets.UTF_8), true);

    }

    @Override
    public void clearLog() {

    }

    @Override
    public void clearLogByType(String type, int saveDay) {
        File logDir = new File(logPath + type);
        if (!logDir.exists()) {
            return;
        }
        long cut = TimeUtils.getDateByNow(-saveDay, TimeConstants.DAY).getTime();
        try {
            File[] subFiles = logDir.listFiles();
            if(subFiles != null){
                for (File oneFile : subFiles) {
                    if (!oneFile.isDirectory() && oneFile.lastModified() < cut) {
                        oneFile.delete();
                    }
                }
            }

        } catch (Exception e ) {
            e.printStackTrace();
        }
    }
}
