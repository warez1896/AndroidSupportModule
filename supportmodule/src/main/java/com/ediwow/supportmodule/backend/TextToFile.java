package com.ediwow.supportmodule.backend;

import android.os.Environment;

import com.ediwow.supportmodule.objectholder.Meta;

import java.io.File;
import java.io.PrintStream;
import java.util.Date;

public class TextToFile {
    private static final String VERBOSE = "verbose", ERROR = "error";
    private static String currentFolder = null;

    public static void print(String folderName, String fileName, String output) {
        if (!Meta.isProduction) {
            try {
                File file = new File(createDir(folderName, VERBOSE), fileName + DateTimeManager.ToFormat.toStraightTimestamp(new Date()) + ".txt");
                PrintStream ps = new PrintStream(file);
                ps.print(output);
                ps.flush();
                ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void print(String folderName, Class<?> sourceClass, String output) {
        if (!Meta.isProduction) {
            try {
                if (sourceClass.getEnclosingMethod() != null) {
                    String fileName = String.format("%s.%s-%s.txt", sourceClass.getName(), sourceClass.getEnclosingMethod().getName(), DateTimeManager.ToFormat.toStraightTimestamp(new Date()));
                    File file = new File(createDir(folderName, VERBOSE), fileName);
                    PrintStream ps = new PrintStream(file);
                    ps.print(output);
                    ps.flush();
                    ps.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void print(String folderName, Class<?> sourceClass, Exception eOutput) {
        try {
            if (sourceClass.getEnclosingMethod() != null) {
                String fileName = String.format("%s.%s-%s.txt", sourceClass.getName(), sourceClass.getEnclosingMethod().getName(), DateTimeManager.ToFormat.toStraightTimestamp(new Date()));
                File file = new File(createDir(folderName, ERROR), fileName);
                PrintStream ps = new PrintStream(file);
                eOutput.printStackTrace(ps);
                ps.flush();
                ps.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void print(String folderName, String fileName, Exception eOutput) {
        try {
            File file = new File(createDir(folderName, ERROR), fileName + DateTimeManager.ToFormat.toStraightTimestamp(new Date()) + ".txt");
            PrintStream ps = new PrintStream(file);
            eOutput.printStackTrace(ps);
            ps.flush();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static File createDir(String folderName, String sSubFolder) {
        currentFolder = folderName;
        File storage = Environment.getExternalStorageDirectory();
        File folder = new File(storage.getAbsolutePath() + File.separator + folderName);
        File fSubFolder = new File(folder, sSubFolder);
        folder.mkdir();
        fSubFolder.mkdir();
        return fSubFolder;
    }

    public static File getFolder() {
        File file = null;
        if (currentFolder != null) {
            File storage = Environment.getExternalStorageDirectory();
            File folder = new File(storage.getAbsolutePath() + File.separator + currentFolder);
            file = new File(folder, "captures");
        }
        return file;
    }
}
