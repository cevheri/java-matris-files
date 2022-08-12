package luxoft.codingchallange.main;

import luxoft.codingchallange.filesystem.File;
import luxoft.codingchallange.filesystem.FileSystem;
import luxoft.codingchallange.filesystem.Permissions;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        //TODO search for files
        //TODO print list of files found for previous search

        searchFilesOldWay();
        searchFilesStream();
    }


    public static void searchFilesOldWay() {
        for (File[] fileArray : FileSystem.files) {
            System.out.println("---old way ---");
            System.out.println(Arrays.toString(fileArray));

            for (File file : fileArray) {
                if (Permissions.W.equals(file.getPermission())
                        && LocalDate.of(2022, 05, 18)
                        .isBefore(ChronoLocalDate.from(file.getTimeCreated())))

                    System.out.println(file.getName());
            }
        }

        System.out.println("--- old way ended ---");
    }

    public static void searchFilesStream() {

        System.out.println("--- using stream ---");
        Arrays.stream(FileSystem.files)
                .flatMap(Arrays::stream)
                .filter(file -> Permissions.W.equals(file.getPermission())
                        && LocalDate.of(2022, 05, 18).isBefore(ChronoLocalDate.from(file.getTimeCreated()))
                ).map(File::getName)
                .filter(f-> f.equals("file40"))
                .collect(Collectors.toList())
                .forEach(System.out::println)
        ;
    }


}
