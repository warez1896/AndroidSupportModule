package com.ediwow.supportmodule.backend;

import android.os.Environment;

import com.ediwow.supportmodule.objectholder.Meta;

import java.io.File;
import java.io.PrintStream;
import java.util.Date;

public class TextToFile {
    public static void print(String fileName, String output) {
        if (!Meta.isProduction) {
            try {
                File file = new File(createDir("verbose"), fileName + DateTimeManager.ToFormat.toStraightTimestamp(new Date()) + ".txt");
                PrintStream ps = new PrintStream(file);
                ps.print(output);
                ps.flush();
                ps.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void print(Class<?> sourceClass, String output) {
        if (!Meta.isProduction) {
            try {
                if (sourceClass.getEnclosingMethod() != null) {
                    String fileName = String.format("%s.%s-%s.txt", sourceClass.getName(), sourceClass.getEnclosingMethod().getName(), DateTimeManager.ToFormat.toStraightTimestamp(new Date()));
                    File file = new File(createDir("verbose"), fileName);
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

    private static void print(Class<?> sourceClass, Exception eOutput) {
        try {
            if (sourceClass.getEnclosingMethod() != null) {
                String fileName = String.format("%s.%s-%s.txt", sourceClass.getName(), sourceClass.getEnclosingMethod().getName(), DateTimeManager.ToFormat.toStraightTimestamp(new Date()));
                File file = new File(createDir("error"), fileName);
                PrintStream ps = new PrintStream(file);
                eOutput.printStackTrace(ps);
                ps.flush();
                ps.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void print(String fileName, Exception eOutput) {
        try {
            File file = new File(createDir("error"), fileName + DateTimeManager.ToFormat.toStraightTimestamp(new Date()) + ".txt");
            PrintStream ps = new PrintStream(file);
            eOutput.printStackTrace(ps);
            ps.flush();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static File createDir(String sSubFolder) {
        File storage = Environment.getExternalStorageDirectory();
        File folder = new File(storage.getAbsolutePath() + File.separator + "MTR_RDNG");
        File fSubFolder = new File(folder, sSubFolder);
        if (folder.mkdir()) {
            if (fSubFolder.mkdir())
                return fSubFolder;
            else
                return null;
        } else
            return null;
    }

    public static File getFolder() {
        File storage = Environment.getExternalStorageDirectory();
        File folder = new File(storage.getAbsolutePath() + File.separator + "MTR_RDNG");
        return new File(folder, "captures");
    }
}
