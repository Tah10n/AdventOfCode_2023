import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day6Part1 {
    public static void main(String[] args) {
        List<String> lines = readFile("Day6/resources/input.txt");
        List<Integer> times = new ArrayList<>();
        List<Integer> distances = new ArrayList<>();

        Pattern pattern = Pattern.compile("\\d+");

        String line = lines.get(0);
        System.out.println(line);
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            times.add(Integer.parseInt(matcher.group()));
        }

        line = lines.get(1);
        System.out.println(line);
        matcher = pattern.matcher(line);
        while (matcher.find()) {
            distances.add(Integer.parseInt(matcher.group()));
        }

        List<Integer> counts = new ArrayList<>();
        for(int i=0; i< times.size(); i++) {
            int time = times.get(i);
            int record = distances.get(i);
            int count = 0;

            for (int j=0; j< time;j++) {
                int v = j;
                int t = time - j;
                int s = v * t;
                if (s>record) {
                    count++;
                }

            }
            counts.add(count);
            System.out.println(count);
        }

        int result = 1;
        for (Integer count : counts) {
            result = result* count;
        }
        System.out.println(result);



    }


    public static List<String> readFile(String fileName) {
        //File file = new File(Day4Part1.class.getClassLoader().getResource(fileName).getFile());
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

            return lines;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found", e);
        } catch (IOException e) {
            throw new RuntimeException("IO error", e);
        }

    }
}
