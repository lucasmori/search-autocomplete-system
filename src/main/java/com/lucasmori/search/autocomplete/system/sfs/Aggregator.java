package com.lucasmori.search.autocomplete.system.sfs;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Aggregator {

    public void execute() {
        String csvFile = "logs.csv";
        String csvSplitBy = ",";

        List<AggregatedData> aggregatedData = new ArrayList<>();
        try (InputStream inputStream = Aggregator.class.getClassLoader().getResourceAsStream(csvFile);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            String line;

            while ((line = reader.readLine()) != null) {
                String[] values = line.split(csvSplitBy);

                String searchTerm = values[0];
                String timeStart = LocalDateTime.parse(values[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                aggregatedData.stream()
                        .filter(aggregated -> aggregated.getSearchTerm().equals(searchTerm))
                        .findFirst()
                        .ifPresentOrElse(
                                aggregated -> aggregated.setFrequency(aggregated.getFrequency() + 1),
                                () -> aggregatedData.add(new AggregatedData(searchTerm, timeStart, 1))
                        );


            }
            System.out.println("search_term | time_start | frequency");
            for (AggregatedData value : aggregatedData) {
                System.out.println(value.getSearchTerm() + " | " + value.getTimeStart() + " | " + value.getFrequency());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String csvFileOutput = "C:\\Users\\lucas\\IdeaProjects\\search-autocomplete-system\\src\\main\\resources\\aggregated_data.csv";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFileOutput))) {
            writer.write("search_term,time_start,frequency");
            writer.newLine();
            for (AggregatedData value : aggregatedData) {
                System.out.println(value.getSearchTerm() + " | " + value.getTimeStart() + " | " + value.getFrequency());
                writer.write(value.getSearchTerm() + " | " + value.getTimeStart() + " | " + value.getFrequency());
                writer.newLine();
            }
            System.out.println("Data has been written to " + csvFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class AggregatedData {
    private String searchTerm;
    private String timeStart;
    private Integer frequency;

    public AggregatedData(String searchTerm, String timeStart, Integer frequency) {
        this.searchTerm = searchTerm;
        this.timeStart = timeStart;
        this.frequency = frequency;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }
}