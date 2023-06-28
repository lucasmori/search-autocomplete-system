package com.lucasmori.search.autocomplete.system.util;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class CSVUtil {

    public static void read(String csvFile, String csvSplitBy) {
        try (InputStream inputStream = CSVUtil.class.getClassLoader().getResourceAsStream(csvFile);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(csvSplitBy);
                // Process the values as needed

                for (String value : values) {
                    System.out.print(value + " ");
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
