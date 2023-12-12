import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Day2Part2 {
    public static void main(String[] args) {
        List<String> lines = readFile("input.txt");

        System.out.println(calculateSum(lines));
    }

    public static int calculateSum(List<String> lines) {

        List<Integer> numbers = new ArrayList<>();

        for (int i = 0; i < lines.size(); i++) {
            String[] game = lines.get(i).split(":");

            String[] cubes = game[1].split("[,;]");
            int maxRed = 0;
            int maxGreen = 0;
            int maxBlue = 0;
            for (String cube : cubes) {
                String[] values = cube.trim().split(" ");

                if (values[1].equals("red") && (Integer.parseInt(values[0]) > maxRed)) {
                    maxRed = Integer.parseInt(values[0]);
                }

                if (values[1].equals("green") && (Integer.parseInt(values[0]) > maxGreen)) {
                    maxGreen = Integer.parseInt(values[0]);
                }

                if (values[1].equals("blue") && (Integer.parseInt(values[0]) > maxBlue)) {
                    maxBlue = Integer.parseInt(values[0]);
                }

            }
            numbers.add(maxRed * maxGreen * maxBlue);

        }

        return numbers.stream().mapToInt(Integer::intValue).sum();
    }

    public static List<String> readFile(String fileName) {
        File file = new File(Day2Part2.class.getClassLoader().getResource(fileName).getFile());
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
