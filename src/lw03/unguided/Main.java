package lw03.unguided;

import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.List;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args){
        Scanner scanner1 = new Scanner(Main.class.getResourceAsStream("registrations.txt"));
        Scanner scanner2 = new Scanner(Main.class.getResourceAsStream("checkins.txt"));
        Set<String> participant = new LinkedHashSet<>();
        List<String> processed = new LinkedList<>();
        
        while(scanner1.hasNextLine()){
            String id = scanner1.nextLine();
            participant.add(id);
        }
        scanner1.close();

        int rejected = 0;

        System.out.println("===== Event Check-In Results =====");
        while(scanner2.hasNextLine()){
            String processid = scanner2.nextLine();
            if(!participant.contains(processid)){
                System.out.println(processid + ": Rejected (not registered)");
                rejected++;
            } else if(processed.contains(processid)){
                System.out.println(processid + ": Rejected (already checked in)");
                rejected++;
            } else{
                processed.add(processid);
                System.out.println(processid + ": Checked in");
            }
        }
        scanner2.close();

        System.out.println();
        System.out.println("===== Final Event Summary =====");
        System.out.println("Registered student: " + participant.size());
        System.out.println("Successful check-ins: " + processed.size());
        System.out.println("Absent students: " + (participant.size()-processed.size()));
        System.out.println("Rejected attempts: " + rejected);
    }
}