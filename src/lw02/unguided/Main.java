package lw02.unguided;

import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main {
    public static void main(String[] agrs){
        Scanner scanner = new Scanner(Main.class.getResourceAsStream("orders.txt"));
        LinkedList<String[]> orders = new LinkedList<>();
        LinkedList<String[]> foodStock = new LinkedList<>();
        LinkedList<String[]> drinkStock = new LinkedList<>();

        while(scanner.hasNextLine()){
            String name = scanner.next();
            String food = scanner.next();
            String drink = scanner.next();
            String table = scanner.next();

            String[] order = {name, food, drink, table};
            orders.add(order);
        }

        scanner.close();

        String[] bakso = {"Bakso", "2"};
        String[] sate = {"Sate", "1"};
        String[] soto = {"Soto", "2"};

        foodStock.add(bakso);
        foodStock.add(sate);
        foodStock.add(soto);

        String[] esTeh = {"EsTeh", "4"};
        String[] esJeruk = {"EsJeruk", "2"};

        drinkStock.add(esTeh);
        drinkStock.add(esJeruk);

        Queue<String[]> queue = new LinkedList<>(orders);
        LinkedList<String[]> success = new LinkedList<>();
        Stack<String[]> failed = new Stack<>();

        while(!queue.isEmpty()){
            String[] data = queue.poll();

            String food = data[1];
            String drink = data[2];

            boolean foodAvailable = true;
            boolean drinkAvailable = true;

            if (!food.equals("-")) {
                for (String[] fs : foodStock) {
                    if (fs[0].equals(food)) {
                        int currentFoodStock = Integer.parseInt(fs[1]);
                        if (currentFoodStock == 0) {
                            foodAvailable = false;
                        }
                    }
                }
            }

            if (!drink.equals("-")) {
                for (String[] ds : drinkStock) {
                    if (ds[0].equals(drink)) {
                        int currentDrinkStock = Integer.parseInt(ds[1]);
                        if (currentDrinkStock == 0) {
                            drinkAvailable = false;
                        }
                    }
                }
            }

            if (foodAvailable && drinkAvailable) {
                if (!food.equals("-")) {
                    for (String[] fs : foodStock) {
                        if (fs[0].equals(food)) {
                            int currentFoodStock = Integer.parseInt(fs[1]);
                            currentFoodStock -= 1;
                            fs[1] = String.valueOf(currentFoodStock);
                        }
                    }
                }

                if (!drink.equals("-")) {
                    for (String[] ds : drinkStock) {
                        if (ds[0].equals(drink)) {
                            int currentDrinkStock = Integer.parseInt(ds[1]);
                            currentDrinkStock -= 1;
                            ds[1] = String.valueOf(currentDrinkStock);
                        }
                    }
                }
                success.add(data);
            } else {
                failed.push(data);
            }
        }

        System.out.println("=== Successfully Processed Orders ===");
        for(String[] s : success){
            System.out.println(s[0] + " " + s[1] + " " + s[2] + " " + s[3]);
        }

        System.out.println();
        System.out.println("=== Remaining Food Stock ===");
        for (String[] sf : foodStock){
            System.out.println(sf[0] + " : " + sf[1]);
        }

        System.out.println();
        System.out.println("=== Remaining Drink Stock ===");
        for (String[] df : drinkStock){
            System.out.println(df[0] + " : " + df[1]);
        }

        System.out.println();
        System.out.println("=== Failed Orders ===");
        while(!failed.isEmpty()){
            String[] fail = failed.pop();
            System.out.println(fail[0] + " " + fail[1] + " " + fail[2] + " " + fail[3]);
        }
    }
}
