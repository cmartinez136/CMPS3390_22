package a2.cmartinez136;
import a1.cmartinez136.*;

import java.util.Random;
import java.util.Scanner;


public class Main{

    public static void main(String[] args) throws InterruptedException {
        Scanner scan = new Scanner(System.in);
        Random ran = new Random();

        System.out.print("Do you want a [S]ingle Sort, [D]ual sort, or [Q]uad sort?");
        char selection = scan.next().charAt(0);

        System.out.print("How many items do you want to sort?");
        int count = scan.nextInt();

        Item[] items = new Item[count];

        for(int i=0; i<count; i++){
            int t = ran.nextInt(4);

            switch (t) {
                case 0 -> items[i] = a1.cmartinez136.Main.genFood();
                case 1 -> items[i] = a1.cmartinez136.Main.genTool();
                case 2 -> items[i] = a1.cmartinez136.Main.genWep();
                case 3 -> items[i] = a1.cmartinez136.Main.genMat();
            }
        }
        switch (selection) {
            case 's', 'S' -> SingleSort(items);
            case 'd', 'D' -> DualSort(items);
            case 'q', 'Q' -> QuadSort(items);
        }
    }

    public static void QuadSort(Item[] items) throws InterruptedException {
        int mid = Math.round(items.length / 2f);
        ThreadSort t1 = new ThreadSort(items, 0, mid);
        ThreadSort t2 = new ThreadSort(items, mid, items.length);
        ThreadSort t3 = new ThreadSort(items, 0, mid);
        ThreadSort t4 = new ThreadSort(items, mid, items.length);

        long startTime = System.nanoTime();
        t1.start();
        t2.start();
        t3.start();
        t4.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();

        MergeSort m1 = new MergeSort(t1.gettItems(), t2.gettItems());
        m1.start();
        m1.join();
        MergeSort m2 = new MergeSort(t3.gettItems(), t4.gettItems());
        m2.start();
        m2.join();

        MergeSort m3 = new MergeSort(m1.getSortedItems(), m2.getSortedItems());
        long endTime = System.nanoTime();
        long duration = (endTime - startTime)/1000000;

        for(Item i : m2.getSortedItems()){
            System.out.println(i);
        }
        System.out.println("Quad sort took: " + duration);
    }

    public static void DualSort(Item[] items) throws InterruptedException {
        int mid = Math.round(items.length / 2f);
        ThreadSort t1 = new ThreadSort(items, 0, mid);
        ThreadSort t2 = new ThreadSort(items, mid, items.length);

        long startTime = System.nanoTime();
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        MergeSort m1 = new MergeSort(t1.gettItems(), t2.gettItems());
        m1.start();
        m1.join();
        long endTime = System.nanoTime();
        long duration = (endTime - startTime)/1000000;

        for(Item i : m1.getSortedItems()){
            System.out.println(i);
        }
        System.out.println("Dual sort took: " + duration);
    }


    private static void SingleSort(Item[] items){
        //Sort before print
        ThreadSort single = new ThreadSort(items, 0, items.length);
        long startTime = System.nanoTime();
        single.start();
        try {
            single.join();
            long endTime = System.nanoTime();
            long duration = (endTime - startTime)/1000000;
            Item[] sortedItems = single.gettItems();
            for(Item i : sortedItems){
                System.out.println(i);
            }
            System.out.println("Was sorted in: " + duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
