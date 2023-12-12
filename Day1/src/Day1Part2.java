import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day1Part2 {

    public static void main(String[] args) throws Exception {
        List<String> lines = readFile("input.txt");

        System.out.println(calculateSum(lines));
    }

    public static int calculateSum(List<String> lines) {


        List<Integer> numbers = new ArrayList<>();
        for (String line : lines) {
            char[] chars = new char[2];

            StringBuilder str = new StringBuilder();

            for (char c : line.toCharArray()) {
                if (str.toString().contains("one")) {
                    chars[0] = '1';
                    break;
                }
                if (str.toString().contains("two")) {
                    chars[0] = '2';
                    break;
                }
                if (str.toString().contains("three")) {
                    chars[0] = '3';
                    break;
                }
                if (str.toString().contains("four")) {
                    chars[0] = '4';
                    break;
                }
                if (str.toString().contains("five")) {
                    chars[0] = '5';
                    break;
                }
                if (str.toString().contains("six")) {
                    chars[0] = '6';
                    break;
                }
                if (str.toString().contains("seven")) {
                    chars[0] = '7';
                    break;
                }
                if (str.toString().contains("eight")) {
                    chars[0] = '8';
                    break;
                }
                if (str.toString().contains("nine")) {
                    chars[0] = '9';
                    break;
                }
                if (Character.isDigit(c)) {
                    chars[0] = c;
                    break;
                } else {
                    str.append(c);
                }
            }

            str = new StringBuilder();

            for (char c : new StringBuilder(line).reverse().toString().toCharArray()) {
                if (str.toString().contains("eno")) {
                    chars[1] = '1';
                    break;
                }
                if (str.toString().contains("owt")) {
                    chars[1] = '2';
                    break;
                }
                if (str.toString().contains("eerht")) {
                    chars[1] = '3';
                    break;
                }
                if (str.toString().contains("ruof")) {
                    chars[1] = '4';
                    break;
                }
                if (str.toString().contains("evif")) {
                    chars[1] = '5';
                    break;
                }
                if (str.toString().contains("xis")) {
                    chars[1] = '6';
                    break;
                }
                if (str.toString().contains("neves")) {
                    chars[1] = '7';
                    break;
                }
                if (str.toString().contains("thgie")) {
                    chars[1] = '8';
                    break;
                }
                if (str.toString().contains("enin")) {
                    chars[1] = '9';
                    break;
                }
                if (Character.isDigit(c)) {
                    chars[1] = c;
                    break;
                } else {
                    str.append(c);
                }
            }
            numbers.add(Integer.parseInt(new String(chars)));
        }


        return numbers.stream().mapToInt(Integer::intValue).sum();
    }

    public static List<String> readFile(String fileName) {
        File file = new File(Day1Part2.class.getClassLoader().getResource(fileName).getFile());
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
