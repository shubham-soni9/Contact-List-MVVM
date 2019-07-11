package com.contactdata.utils;

import android.os.Environment;

import java.io.File;
import java.io.FileWriter;

/**
 * Custom log class
 */
public class Log {
    private static final boolean PRINT = true;

    private static final String TAG = Log.class.getSimpleName();

    private Log() {
    }

    public static void i(String tag, String message) {
        if (PRINT) {
            android.util.Log.i(tag, message);
        }
    }

    public static void d(String tag, String message) {
        if (PRINT) {
            android.util.Log.d(tag, message);
        }
    }

    public static void e(String tag, String message) {
        if (PRINT) {
            android.util.Log.e(tag, message);
        }
    }

    public static void v(String tag, String message) {
        if (PRINT) {
            android.util.Log.v(tag, message);
        }
    }

    public static void w(String tag, String message) {
        if (PRINT) {
            android.util.Log.w(tag, message);
        }
    }

    public static void writeLogToFile(final String filePrefix, final String response) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    String fileName = Environment.getExternalStorageDirectory() + "/" + filePrefix + "_" + "LOGFILE" + ".txt";
                    File gpxfile = new File(fileName);

                    if (!gpxfile.exists()) {
                        gpxfile.createNewFile();
                    }

                    FileWriter writer = new FileWriter(gpxfile, true);
                    writer.append("\n").append(response);
                    writer.flush();
                    writer.close();

                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * Method to write a Log file, or append it if it already exists
     *
     * @param filename without extension
     * @param response
     */
    public static void writeLogToInternalDir(final String filename, final String response) {


        e(TAG, "writeLogToInternalDir: initiated");

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    File logFile = new File(getAppDirectory(), filename + ".log");

                    boolean exists = logFile.exists();

                    e(TAG, "writeLogToInternalDir: File exists = " + exists);

                    if (exists) {
                        e(TAG, "writeLogToInternalDir: File Path = " + logFile.getAbsolutePath());
                    } else {
                        if (!logFile.createNewFile()) {
                            e(TAG, "writeLogToInternalDir: File was not created");
                            return;
                        }
                    }

                    FileWriter writer = new FileWriter(logFile, true);
                    writer.append("\n").append(response);
                    writer.flush();
                    writer.close();

                    e(TAG, "writeLogToInternalDir: File was written");

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * Method to retrieve the App Directory,
     * where files like logs can be Saved
     *
     * @return
     */
    private static File getAppDirectory() {
        try {
            String strFolder = Environment.getExternalStorageDirectory() + "/TookanData";
            File folder = new File(strFolder);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            return folder;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method to get the Log File from the App Dir
     *
     * @param filePrefix
     * @return
     */
    public static File getPathLogFile(final String filePrefix) {
        try {
            String fileName = getAppDirectory() + "/" + filePrefix + ".txt";
            File gpxfile = new File(fileName);
            if (!gpxfile.exists()) {
                gpxfile.createNewFile();
            }
            return gpxfile;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}