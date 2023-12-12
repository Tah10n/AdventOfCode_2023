import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day1Part1 {
    public static void main(String[] args) {


        List<String> lines = readFile("input.txt");

        System.out.println(calculateSum(lines));

    }

    public static int calculateSum(List<String> lines) {
        List<Integer> numbers = new ArrayList<>();

        for (String line : lines) {
            char[] chars = new char[2];


            for (char c : line.toCharArray()) {
                if (Character.isDigit(c)) {
                    chars[0] = c;
                    break;
                }
            }

            for (char c : new StringBuilder(line).reverse().toString().toCharArray()) {
                if (Character.isDigit(c)) {
                    chars[1] = c;
                    break;
                }
            }

            numbers.add(Integer.parseInt(new String(chars)));

        }

        return numbers.stream().mapToInt(Integer::intValue).sum();
    }

    public static List<String> readFile(String fileName) {
        File file = new File(Day1Part1.class.getClassLoader().getResource(fileName).getFile());
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            return lines;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
