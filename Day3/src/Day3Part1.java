import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Day3Part1 {
    public static void main(String[] args) {
        char[][] chars  = readFile("input.txt");

        System.out.println(calculateSum(chars));
    }

    public static int calculateSum(char[][] chars) {
        int sum = 0;
        StringBuilder number;

        for (int j = 0; j < chars[0].length; j++) {
            for (int i = 0; i < chars.length; i++) {

                if (Character.isDigit(chars[i][j])) {
                    number = new StringBuilder();
                    number.append(chars[i][j]);
                    if ((i + 1 < chars.length) && Character.isDigit(chars[i + 1][j])) {
                        number.append(chars[i + 1][j]);

                        if ((i + 2 < chars.length) && Character.isDigit(chars[i + 2][j])) {
                            number.append(chars[i + 2][j]);
                        }
                    }

                    // System.out.println(number);

                    boolean toBreak = false;
                    for (int l = -1; l <= 1; l++) {
                        if (toBreak)
                            break;
                        if (l + j < 0)
                            continue;
                        if (l + j >= chars[0].length)
                            continue;

                        for (int k = -1; k <= number.length(); k++) {

                            if (i + k < 0)
                                continue;
                            if (i + k >= chars.length)
                                continue;

                            if (!Character.isDigit(chars[i + k][j + l]) && chars[i + k][j + l] != '.') {
                                sum += Integer.parseInt(number.toString());
                                // System.out.println("sum = " + sum + "; new num = " + number);

                                toBreak = true;
                                break;
                            }
                        }
                    }

                    i = i + number.length();
                }
            }
        }

        return sum;
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
