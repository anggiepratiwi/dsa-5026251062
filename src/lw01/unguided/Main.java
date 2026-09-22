package lw01.unguided;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(
            Main.class.getResourceAsStream("washes.txt")
        );

        int job = scanner.nextInt();
        WashService[] wash = new WashService[job];

        for (int i = 0; i < job; i++) {

            String type = scanner.next();
            String id = scanner.next();
            int days = scanner.nextInt();
            int units = scanner.nextInt();

            if (type.equals("MOTORCYCLE")) {
                wash[i] = new MotorcycleWash(id, days, units);
            } else {
                wash[i] = new CarWash(id, days, units);
            }
        }

        for (WashService service : wash) {
            System.out.println(service.summary());
        }

        scanner.close();
    }
}