package com.mitrais;

import java.util.concurrent.ForkJoinPool;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();

        // Recursive Action
//        String words = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
//        CustomRecursiveAction custom = new CustomRecursive(words);
//
        // Recursive Task
        int[] numbers = Stream.iterate(1, x -> x + 1).limit(21).mapToInt(x -> x).toArray();
        CustomRecursiveTask custom = new CustomRecursiveTask(numbers);

        System.out.println(pool.invoke(custom));
    }
}
