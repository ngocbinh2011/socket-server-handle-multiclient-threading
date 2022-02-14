package util;

import java.util.Scanner;

public class Scannable {
    private static Scanner scanner;

    public synchronized static Scanner getScanner(){
        if(scanner == null){
            scanner = new Scanner(System.in);
        }
        return scanner;
    }
}
