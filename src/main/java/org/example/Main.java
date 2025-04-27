package org.example;

import java.util.Scanner;

public class Main {

    public static int[] dx = {1, -1, 0, 0};
    public static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String s  = sc.next();
        int len = s.length();

        int x = 0, y = 0;

        int dim = 3;
        for(int i = 0; i < len; i++){
            char c = s.charAt(i);

            int dir = 0;
            if(c == 'L'){
                dim = (dim - 1) % 4;
            }

            if(c == 'R'){
                dim = (dim - 1 + 4) % 4;
            }

            if(c == 'F'){
                x = x + dx[dim];
                y = y + dy[dim];
            }
        }
    }

}