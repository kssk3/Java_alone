package modern_Java.chap03.BufferedReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BufferedReaderMainV1 {

    public static void main(String[] args) throws IOException {
        String result1 = processFile();
        System.out.println("result1 = " + result1);

        String result2 = processFile(br -> br.readLine());
        String result3 = processFile(br -> br.readLine() + br.readLine());

        System.out.println("result2 = " + result2);
        System.out.println("result3 = " + result3);

    }

    private static String processFile() throws IOException {
        try (BufferedReader br = new BufferedReader(
                new FileReader("src/main/java/modern_Java/chap03/file.txt"))) {
            return br.readLine();
        }
    }

    private static String processFile(BufferedReaderProcessor brp) throws IOException {
        try (BufferedReader br = new BufferedReader(
                new FileReader("src/main/java/modern_Java/chap03/file.txt"))) {
            return brp.process(br);
        }
    }
}
