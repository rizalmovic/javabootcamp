package com.mitrais;

import com.github.javafaker.Faker;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner input = new Scanner(System.in);
    private static DB database = new DB("localhost", "mysql", "root","");
    public static void main(String[] args) {
        while (true) {
            System.out.println("============================");
            System.out.println("        Football DB         ");
            System.out.println("============================");
            System.out.println("1. Show all players");
            System.out.println("2. Insert a new player");
            System.out.println("3. Edit a player");
            System.out.println("4. Delete a player");
            System.out.println("5. Generate random players");
            System.out.println("============================");
            System.out.print("> ");

            String option = input.next();

            switch (option) {
                case "1":
                    showAll();
                    break;
                case "2":
                    add();
                    break;
                case "3":
                    edit();
                    break;
                case "4":
                    delete();
                    break;
                case "5":
                    generate();
                    break;
                default:
                    System.exit(0);
                    break;
            }

            System.out.print("\n\n");
        }
    }

    private static void showAll() {
        try {
            Main.database.all().stream().forEach(x -> {
                System.out.println("ID : " + x.getId() + ", Name : " + x.getName() + ", Email : " + x.getEmail() + ", Score : " + x.getScore());
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void add() {
        System.out.println("\n\nAdd new player.");
        System.out.print("Type-in player name : ");
        String name = input.next();
        System.out.print("Type-in player email : ");
        String email = input.next();
        System.out.print("Type-in player score : ");
        Integer score = input.nextInt();

        Player player = new Player(name, email, score);

        try {
            if (database.insert(player))
                System.out.println("Player has been added.");
            else
                System.out.println("An error has been occur.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void edit() {
        System.out.println("\n\nEdit a player.");
        System.out.print("Type-in the ID of a player you want to edit : ");
        int id = input.nextInt();

        try {
            Player player = database.get(id);

            if (player != null) {
                System.out.print("Type-in player name : ");
                String name = input.next();
                System.out.print("Type-in player email : ");
                String email = input.next();
                System.out.print("Type-in player score : ");
                Integer score = input.nextInt();

                player.setName(name);
                player.setEmail(email);
                player.setScore(score);

                if (database.update(player, id) != null) {
                    System.out.println("Player has been successfully updated.");
                } else {
                    System.out.println("An error occur.");
                }
            } else {
                System.out.println("An error has occur.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void delete() {
        System.out.println("\n\nDelete a player.");
        System.out.print("Type-in the ID of a player you want to delete : ");
        int id = input.nextInt();

        try {
            Player player = database.get(id);

            if (player != null && database.delete(id)) {
                System.out.println("The player has been deleted.");
            } else {
                System.out.println("No player found with id of " + id + ".");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void generate() {
        Faker faker = new Faker();
        List<Player> players = new ArrayList<Player>();

        System.out.print("Type-in the size of the generated players : ");
        int max = input.nextInt();

        for(int i = 0; i < max; i++) {
            players.add(new Player(
                    faker.name().fullName(),
                    faker.internet().emailAddress(),
                    faker.number().numberBetween(50,80)
            ));
        }

        System.out.println("Save the players to the databases? (y/n)");
        String ans = input.next();

        if(ans.equalsIgnoreCase("y")) {
            try {
                database.insertBatch(players);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
