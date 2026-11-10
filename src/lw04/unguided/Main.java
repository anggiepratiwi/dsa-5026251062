package lw04.unguided;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(Main.class.getResourceAsStream("parcels.txt"));

        int n = scanner.nextInt();
        int[] parcels = new int[n];

        for(int i = 0; i < n; i++){
            parcels[i] = scanner.nextInt(); 
        }

        int m = scanner.nextInt();
        int[] targets = new int[m];

        for(int i = 0; i < m; i++){
            targets[i] = scanner.nextInt();
        }

        scanner.close();

        int[] afterTwoPasses = new int[n];

        bubbleSort(parcels, n - 1, afterTwoPasses);

        System.out.println("==== After 2 Passes ====");
        for(int a : afterTwoPasses){
            System.out.print(a + " ");
        }
        System.out.println();
        System.out.println();

        System.out.println("==== Fully Sorted ====");
        for(int b : parcels){
            System.out.print(b + " ");
        }
        System.out.println();
        System.out.println();

        System.out.println("==== Search Results ====");
        for(int i = 0; i < m; i++){
            int target = targets[i];
            int[] result = binarySearch(parcels, target);

            if(result[0] != -1){
                System.out.println(target + ": FOUND at index " + result[0] + " (steps: " + result[1] + ")");
            } else{
                System.out.println(target + ": NOT FOUND (steps: " + result[1] + ")");
            }
        }
    }

    public static void bubbleSort(int[] arr, int passes, int[] afterTwoPasses){
        int n = arr.length;

        for(int i = 0; i < passes; i++){
            for(int j = 0; j < n - 1 - i; j++){
                if(arr[j] < arr[j + 1]){
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if(i == 1){
                for(int k = 0; k < n; k++){
                    afterTwoPasses[k] = arr[k];
                }
            }
        }
    }

    public static int[] binarySearch(int[] arr, int target){
        int low = 0;
        int high = arr.length - 1;
        int steps = 0;

        while(low <= high){
            int mid = (low + high) / 2;
            steps++;

            if(arr[mid] == target){
                return new int[]{mid, steps};
            } else if(target > arr[mid]){
                high = mid - 1;
            } else{
                low = mid + 1;
            }
        }
        return new int[]{-1, steps};
    }
}
