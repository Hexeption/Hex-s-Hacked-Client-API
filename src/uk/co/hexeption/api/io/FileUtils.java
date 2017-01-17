package uk.co.hexeption.api.io;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Hexeption on 17/01/2017.
 */
public class FileUtils {

    /**
     * @param file
     * @return A String array from a file.
     */
    public static List<String> readFile(File file) {

        List<String> tmplist = new ArrayList<>();

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            for (String s; (s = reader.readLine()) != null; ) {
                tmplist.add(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return tmplist;
    }

    /**
     * @param url
     * @return A String from a URL.
     */
    public static String readURL(URL url) {

        String temp = "";

        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
            for (String s; (s = reader.readLine()) != null; ) {
                temp += s;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return temp;

    }

    /**
     * @param url
     * @return A String array from a url.
     */
    public static List<String> readURLList(URL url) {

        List<String> temp = new ArrayList<>();

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(url.openConnection().getInputStream()));
            for (String s; (s = reader.readLine()) != null; ) {
                temp.add(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return temp;
    }

    /**
     * Writes text to the given file.
     *
     * @param file
     * @param text
     */
    public static void writeFile(File file, String[] text) {

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new FileWriter(file));
            for (String s : text) {
                writer.println(s);
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }

    }

    /**
     * Writes a String Collection to a file.
     *
     * @param file
     * @param text
     */
    public static void writeFile(File file, Collection<String> text) {

        writeFile(file, text.toArray(new String[text.size()]));
    }


}
