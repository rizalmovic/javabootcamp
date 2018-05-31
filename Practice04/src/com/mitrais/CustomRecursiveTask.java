package com.mitrais;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class CustomRecursiveTask extends RecursiveTask {
    public static final int THRESHOLD = 5;
    private int[] numbers;
    private int start, end;

    public CustomRecursiveTask(int[] numbers) {
        this(numbers, 0, numbers.length);
    }

    public CustomRecursiveTask(int[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int length  = end - start;
        int max = 0;

        if(length > THRESHOLD) {
            return ForkJoinTask
                    .invokeAll(createSubTask())
                    .stream()
                    .mapToInt(ForkJoinTask::join)
                    .sum();
        } else {
            return processing(max);
        }
    }

    private Integer processing(int max) {
        for(int x = start; x < end; x++) {
            max = numbers[x];
        }
        return max;
    }

    private List<CustomRecursiveTask> createSubTask() {
        List<CustomRecursiveTask> subtask = new ArrayList<>();

        int split = length / 2;
        CustomRecursiveTask left = new CustomRecursiveTask(numbers, start, start + split);
        CustomRecursiveTask right = new CustomRecursiveTask(numbers, start + split, end);
        subtask.add(left);
        subtask.add(right);

        return subtask;
    }
}
