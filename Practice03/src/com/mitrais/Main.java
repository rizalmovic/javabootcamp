package com.mitrais;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static Scanner input = new Scanner(System.in);

    public static int[] bubleSort(int[] list) {
        int temp;
        int i = 0;

        while(i < list.length - 1) {
            if (list[i] > list[i + 1]) {
                temp = list[i];
                list[i] = list[i + 1];
                list[i + 1] = temp;
                Main.bubleSort(list);
            } else {
                i++;
            }
        }

        return list;
    }

    public static int[] quickSort(int[] list)
    {
        return list;
    }

    public static void main(String[] args) {
        System.out.print("Type in array members (separated with comma) :");
        String[] inputs = input.next().replace(" ", "").split(",");
        int[] numbers = new int[inputs.length];
        int[] bubbleSort;
        int[] quickSort;

        for(int i = 0; i < inputs.length; i++) {
            numbers[i] = Integer.parseInt(inputs[i]);
        }

        ForkJoinPool commonPool = ForkJoinPool.commonPool();

//        bubbleSort = Main.bubleSort(numbers);
//        System.out.println("BUBBLE SORT : ");
//        Arrays.stream(bubbleSort).forEach(System.out::println);

        quickSort = Main.quickSort(numbers);
        System.out.println("QUICK SORT : ");
        Arrays.stream(quickSort).forEach(System.out::println);
    }
}