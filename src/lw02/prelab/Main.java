package lw02.prelab;

import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        LinkedList<String[]> transactionList = new LinkedList<>();
        Scanner scanner = new Scanner(Main.class.getResourceAsStream("transaction.txt"));

        while(scanner.hasNext()){
            String name = scanner.next();
            String type = scanner.next();
            String amount = scanner.next();

            String[] transaction = {name, type, amount};
            
            transactionList.add(transaction);
        }

        scanner.close();

        LinkedList<String[]> customerList = new LinkedList<>();

        for(String[] transaction:transactionList) {
            String name = transaction[0];
            boolean found = false;

            for(String[] customerData:customerList) {
                if(customerData[0].equals(name)) {
                    found = true;
                    break;                   
                }           
            }

            if(!found) {
                String[] customerData = {name, "0"};
                customerList.add(customerData);
            }
        }

        Queue<String[]> transactionQueue = new LinkedList<>(transactionList);
        Stack<String[]> failed = new Stack<>();

        while(!transactionQueue.isEmpty()){
            String[] transactionData = transactionQueue.poll();

            String name = transactionData[0];
            String type = transactionData[1];
            int amount = Integer.parseInt(transactionData[2]);
            
            for(String[] customerData:customerList){
                if(customerData[0].equals(name)){
                    int balance = Integer.parseInt(customerData[1]);
                
                    if(type.equals("DEPOSIT")){
                        balance += amount;                            
                        customerData[1] = String.valueOf(balance);
                        } else if(type.equals("WITHDRAW")){
                            if(amount > balance){
                                failed.push(transactionData);
                            } else{
                                balance -= amount;
                                customerData[1] = String.valueOf(balance);
                            }
                        }
                        break;
                    }
                }
            }
        System.out.println("=== Final Balances ===");
        for(String[] customerData:customerList){
            System.out.println(customerData[0] + " : " + customerData[1]);
        }

        System.out.println();

        System.out.println("=== Failed Transactions ===");
        while(!failed.isEmpty()){
            String[] transactionData = failed.pop();
            System.out.println(transactionData[0] + " " + transactionData[1] + " " + transactionData[2]);
        }
    }
}