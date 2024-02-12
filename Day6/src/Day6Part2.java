import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day6Part2 {
    public static void main(String[] args) {
        List<String> lines = readFile("Day6/resources/input.txt");
        StringBuilder strNumber = new StringBuilder();
        Long time;
        Long record;

        Pattern pattern = Pattern.compile("\\d+");

        String line = lines.get(0);
        System.out.println(line);
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            strNumber.append(matcher.group());
        }
        time = Long.parseLong(strNumber.toString());
        strNumber = new StringBuilder();
        line = lines.get(1);
        System.out.println(line);
        matcher = pattern.matcher(line);
        while (matcher.find()) {
            strNumber.append(matcher.group());
        }
        record = Long.parseLong(strNumber.toString());

        System.out.println(time);
        System.out.println(record);


            Long count = 0l;

            for (long j=0; j< time;j++) {
                long v = j;
                long t = time - j;
                long s = v * t;
                if (s>record) {
                    count++;
                }

            }
            System.out.println("result " + count);

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
