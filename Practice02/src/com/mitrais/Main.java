package com.mitrais;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
	    List<Integer> words = new ArrayList<Integer>();
        Integer[] numbers = {4,23,5,2,47,22,17,93,53,26, 32};
        Integer[][] flat = {{1,2},{3,5}};
        String programName = "Interview";

	    words.addAll(Arrays.asList(numbers));
        words.stream()
                .filter(x -> x % 2 > 0)
                .peek(e -> System.out.println("Before value: " + e))
                .map(x -> x + 1)
                .sorted((x, y) -> Integer.compare(y, x))
                .forEach(x -> System.out.println(x));

        Arrays.stream(flat)
                .flatMap(x -> Arrays.stream(x))
                .forEach(x -> System.out.println(x));


        try {
            Path target = Paths.get(System.getProperty("user.home")).resolve("Documents");
            Files.list(target)
                    .filter(x -> x.toString().contains(programName))
                    .limit(1)
                    .forEach(x -> {
                        try {
                            Files.lines(x).forEach(System.out::println);
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    });
            // First approach
            BufferedWriter written = Files.newBufferedWriter(target.resolve("createdByProgram.txt"), StandardOpenOption.APPEND);
            written.append("TEst ").close();

            // Second Approach
            BufferedWriter writer = new BufferedWriter(new FileWriter(target.resolve("anotherApproach.txt").toString(), true));
            writer.append("Append this text ").close();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            // Do something
        }
    }
}
