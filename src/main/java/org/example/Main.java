package org.example;
import java.util.*;

public class Main {

    public static final int MAX = 20_000;
//    public static int start = 10_000;
    public static int start = 50;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        char[] color = new char[101];
        for(int i = 0; i < color.length; i++){
            color[i] = 'N';
        }

        for(int i = 0; i < n; i++){
            int x = sc.nextInt();
            char c = sc.next().charAt(0);

            if(c == 'R'){
                for(int j = start; j < start + x; j++){
                    if(color[j] == 'N' || color[j] == 'W'){
                        color[j] = 'B';
                    }
                }
                start += x;
            }

            if(c == 'L'){
                for(int j = start - 1; j >= start - x; j--){
                    if(color[j] == 'N' || color[j] == 'B'){
                        color[j] = 'W';
                    }
                }
                start -= x;
            }
        }

        int wCount = 0, bCount = 0;
        for(char c : color){
            if(c == 'W'){wCount++;}
            else if(c == 'B'){bCount++;}
        }

        System.out.println(wCount + " " + bCount);
    }
}