import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day3Part2 {
    public static void main(String[] args) {
        char[][] chars = readFile("input.txt");

        System.out.println(calculateSum(chars));
    }

    public static int calculateSum(char[][] chars) {

        StringBuilder number;

        int count = 0;


        List<Integer> gears = new ArrayList<>();


        for (int j = 0; j < chars[0].length; j++) {
            for (int i = 0; i < chars.length; i++) {

                if (chars[i][j] == '*') {
                    count += 1;

                    List<String> numbers = new ArrayList<>();

                    boolean toBreak = false;
                    for (int l = -1; l <= 1; l++) {
                        if (toBreak)
                            break;
                        if (l + j < 0 || l + j >= chars[0].length)
                            continue;


                        for (int k = -1; k <= 1; k++) {

                            if (i + k < 0 || i + k >= chars.length)
                                continue;

                            number = new StringBuilder();

                            if (Character.isDigit(chars[i + k][j + l])) {
                                if (Character.isDigit(chars[i + k - 1][j + l])) {
                                    if (Character.isDigit(chars[i + k - 2][j + l])) {
                                        number.append(chars[i + k - 2][j + l]);

                                    }
                                    number.append(chars[i + k - 1][j + l]);
                                }
                                number.append(chars[i + k][j + l]);

                                if (Character.isDigit(chars[i + k + 1][j + l])) {
                                    number.append(chars[i + k + 1][j + l]);
                                    if (Character.isDigit(chars[i + k + 2][j + l])) {
                                        number.append(chars[i + k + 2][j + l]);

                                    } else {
                                        numbers.add(number.toString());
                                        k = k + 2;
                                        continue;
                                        //
                                    }
                                } else {
                                    numbers.add(number.toString());
                                    k = k + 1;
                                    continue;
                                }
                                k = k + number.length();
                                numbers.add(number.toString());
                            }

                        }
                    }
                    //System.out.println("No:" + count);
                    int multiply = 1;
                    for (String num : numbers) {
                        //System.out.println("number:" + num);
                        multiply *= Integer.parseInt(num);
                    }
                    //System.out.println("multiply:" + multiply);
                    if (numbers.size() == 2) {
                        gears.add(multiply);

                    }

                }
            }
        }

        //System.out.println("All count:" + count);

        return gears.stream().mapToInt(Integer::intValue).sum();
    }

    public static char[][] readFile(String fileName) {
        File file = new File(Day3Part1.class.getClassLoader().getResource(fileName).getFile());
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

            char[][] chars = new char[lines.get(0).length()][lines.size()];

            for (int j = 0; j < chars[0].length; j++) {
                char[] str = lines.get(j).toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    chars[i][j] = str[i];
                }
            }
            return chars;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
