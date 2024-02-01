import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Day4Part2 {
    public static void main(String[] args) {
        List<String> lines = readFile("Day4/resources/input.txt");

        for (String line : lines) {
            System.out.println(line);
        }
        System.out.println(calculateCards(lines));


    }

    public static int calculateCards(List<String> lines) {
        int sum = 0;
        int[] repeats = new int[lines.size()];
        for (int i = 0; i < lines.size(); i++) {
            sum += 1;
            int numberMatches = 0;
            List<Integer> winNumbers = new ArrayList<>();
            List<Integer> myNumbers = new ArrayList<>();
            String line = lines.get(i);

            extractNumbers(line, winNumbers, myNumbers);

            for (Integer win : winNumbers) {
                for (Integer my : myNumbers) {
                    if (win.equals(my)) {
                        numberMatches = numberMatches + 1;
                    }
                }
            }

            if(numberMatches > 0) {
                for(int j = 1; j <= numberMatches; j++) {
                    repeats[i+j] += 1;
                }
            }

            for(int k=0; k<repeats[i]; k++) {
                sum += 1;
                for(int j = 1; j <= numberMatches; j++) {
                    repeats[i+j] += 1;
                }
            }

            System.out.println(winNumbers + " " + myNumbers + " " + sum);
            System.out.println(repeats[i]);

        }
        return sum;
    }

    private static void extractNumbers(String line, List<Integer> winNumbers, List<Integer> myNumbers) {
        String[] split = line.trim().split(":");

        String[] numbers = split[1].trim().split("\\|");


        String[] strWinNumbers = numbers[0].trim().split(" ");
        String[] strMyNumbers = numbers[1].trim().split(" ");


        for (String str : strWinNumbers) {
            if (str.length() > 0) {
                winNumbers.add(Integer.parseInt(str));
            }
        }

        for (String str : strMyNumbers) {
            if (str.length() > 0) {
                myNumbers.add(Integer.parseInt(str));
            }

        }
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
