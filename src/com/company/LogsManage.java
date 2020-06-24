package com.company;

/*
 *
 * Date: 24 June 2020
 * @author Dmitriy Stavnichuk
 *
 *
 */
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;



public class LogsManage {
    private String filePath;


    // constructor
    public LogsManage(String filePath) {
        this.filePath = filePath;
    }

    // getters + setters
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void getErrorByDate(String date) throws IOException {
        // start counting
        LocalDateTime start = LocalDateTime.now();



        //  get errors by date
        List<String> errorLines= Files.lines(Paths.get(this.getFilePath()))
                .filter(line -> line.contains(date))
                .filter(line -> line.contains("ERROR"))
                .collect(Collectors.toList());

        // count number of logs
        int linesCount = errorLines.size();



        // end time count
        LocalDateTime finish = LocalDateTime.now();
        // count execution time
        long timeExecuted = ChronoUnit.MILLIS.between(start, finish);



        System.out.println(linesCount + " ERROR lines." + " on this date " + date);
        System.out.println("Execution time: " + timeExecuted);




        String stringData = "";
        for (String line : errorLines) {
            stringData += line + "\n";
        }


        // output of different files by date value
        String output = date + ".txt";
        Files.write(Paths.get(output), stringData.getBytes());
    }

}
