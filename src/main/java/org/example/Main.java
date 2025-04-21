package org.example;

import java.util.*;

public class Main {

    public static final int MAX = 201;
    public static final int OFFSET = 100;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[][] arr = new int[MAX][MAX];

        for(int i = 0; i < 2; i++){
            int x1 = sc.nextInt();
            int y1 = sc.nextInt();
            int x2 = sc.nextInt();
            int y2 = sc.nextInt();

            if(i == 0){
                for(int x = x1 + OFFSET; x < x2 + OFFSET; x++){
                    for(int y = y1 + OFFSET; y < y2 + OFFSET; y++){
                        arr[x][y] = 1;
                    }
                }
            }
            if(i == 1){
                for(int x = x1 + OFFSET; x < x2 + OFFSET; x++){
                    for(int y = y1 + OFFSET; y < y2 + OFFSET; y++){
                        arr[x][y] = 0;
                    }
                }
            }
        }

        // 남은 잔해의 경계 찾기
        int minX = MAX;
        int maxX = -1;
        int minY = MAX;
        int maxY = -1;
        boolean found = false;

        for(int x = 0; x < MAX; x++){
            for(int y = 0; y < MAX; y++){
                if(arr[x][y] == 1){
                    minX = Math.min(minX, x);
                    maxX = Math.max(maxX, x);
                    minY = Math.min(minY, y);
                    maxY = Math.max(maxY, y);
                    found = true;
                }
            }
        }

        if(found){
            System.out.println((maxX - minX + 1) * (maxY - minY + 1));
        } else {
            System.out.println(0);
        }
    }
}
