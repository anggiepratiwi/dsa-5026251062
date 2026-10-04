package lw03.prelab;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.Map;

public class Main {
    public static void main(String[] args){
        Scanner scanner1 = new Scanner(Main.class.getResourceAsStream("playlist.txt"));
        List<String> playlist = new ArrayList<>();

        while(scanner1.hasNextLine()){
            String operation = scanner1.next();

            if(operation.equals("ADD")){
                String title = scanner1.nextLine();
                playlist.add(title);
            } else if(operation.equals("REMOVE")){
                String title = scanner1.nextLine();
                playlist.remove(title);
            } else if(operation.equals("INSERT")){
                int index = scanner1.nextInt();
                String title = scanner1.nextLine();
                playlist.add(index, title);
            }
        }

        System.out.println("===== Problem 1 =====");
        System.out.println("Total songs: " + playlist.size());
        int number1 = 1;
        for(String song : playlist){
            System.out.println(number1 + ": " + song);
            number1++;
        }
        System.out.println();

        scanner1.close();

        Scanner scanner2 = new Scanner(Main.class.getResourceAsStream("participants.txt"));
        Set<String> participant = new LinkedHashSet<>();
        int duplicate = 0;

        while(scanner2.hasNextLine()){
            String name = scanner2.next();

            if(!participant.contains(name)){
                participant.add(name);
            } else{
                duplicate++;
            }
        }

        System.out.println("===== Problem 2 =====");
        System.out.println("Unique participants: " + participant.size());
        
        int number2 = 1;
        for(String name : participant){
            System.out.println(number2 + ". " + name);
            number2++;
        }
        System.out.println("Duplicate registrations: " + duplicate);
        System.out.println();

        scanner2.close();

        Scanner scanner3 = new Scanner(Main.class.getResourceAsStream("inventory.txt"));
        Map<String, Integer> inventory = new LinkedHashMap<>();
        int failed = 0;

        while(scanner3.hasNextLine()){
            String type = scanner3.next();
            String product = scanner3.next();
            int quantity = scanner3.nextInt();

            if(type.equals("ADD")){
                if(inventory.containsKey(product)){
                    inventory.put(product, inventory.get(product) + quantity);
                } else {
                    inventory.put(product, quantity);
                }
            } else if(type.equals("SELL")){
                if(inventory.containsKey(product) && inventory.get(product) >= quantity){
                    inventory.put(product, inventory.get(product) - quantity);
                } else{
                    failed++;
                }
            }
        }
        System.out.println("===== Problem 3 =====");
            for(String p : inventory.keySet()){
                System.out.println(p + ": " + inventory.get(p));
            }
            System.out.println("Failed sales: " + failed);

            scanner3.close();
    }
}