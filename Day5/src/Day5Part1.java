import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day5Part1 {
    public static void main(String[] args) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("Day5/resources/input.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        String line = scanner.nextLine();
        String[] split = line.split(":");
        String[] strNumbers = split[1].trim().split(" ");
        List<Long> seeds = new ArrayList<>();
        List<Long> soils = new ArrayList<>();
        List<Long> fertilizers = new ArrayList<>();
        List<Long> waters = new ArrayList<>();
        List<Long> lights = new ArrayList<>();
        List<Long> temperatures = new ArrayList<>();
        List<Long> humiditys = new ArrayList<>();
        List<Long> locations = new ArrayList<>();

        for (String str : strNumbers) {
            seeds.add(Long.parseLong(str));
        }

        while (scanner.hasNextLine()) {
            line = scanner.nextLine();

            if (line.equals("seed-to-soil map:")) {
                List<Long> destination = new ArrayList<>();
                List<Long> source = new ArrayList<>();
                List<Long> range = new ArrayList<>();
                while (scanner.hasNextLong()) {
                    destination.add(scanner.nextLong());
                    source.add(scanner.nextLong());
                    range.add(scanner.nextLong());
                }

                for(int j=0; j < seeds.size(); j++) {
                    Long seed = seeds.get(j);
                    Long correspond = correspond(source, seed, range, destination);
                    soils.add(correspond);
                    System.out.println("seed " + seed + " correspond soil " + correspond);
                }
            } else if (line.equals("soil-to-fertilizer map:")) {
                List<Long> destination = new ArrayList<>();
                List<Long> source = new ArrayList<>();
                List<Long> range = new ArrayList<>();
                while (scanner.hasNextLong()) {
                    destination.add(scanner.nextLong());
                    source.add(scanner.nextLong());
                    range.add(scanner.nextLong());
                }

                for(int j=0; j < soils.size(); j++) {
                    Long soil = soils.get(j);
                    Long correspond = correspond(source, soil, range, destination);
                    fertilizers.add(correspond);
                    System.out.println("soil " + soil + " correspond fertilixer " + correspond);
                }

            } else if (line.equals("fertilizer-to-water map:")) {
                List<Long> destination = new ArrayList<>();
                List<Long> source = new ArrayList<>();
                List<Long> range = new ArrayList<>();
                while (scanner.hasNextLong()) {
                    destination.add(scanner.nextLong());
                    source.add(scanner.nextLong());
                    range.add(scanner.nextLong());
                }

                for(int j=0; j < fertilizers.size(); j++) {
                    Long fertilizer = fertilizers.get(j);
                    Long correspond = correspond(source, fertilizer, range, destination);
                    waters.add(correspond);
                    System.out.println("fertilizer " + fertilizer + " correspond water " + correspond);
                }

            } else if (line.equals("water-to-light map:")) {
                List<Long> destination = new ArrayList<>();
                List<Long> source = new ArrayList<>();
                List<Long> range = new ArrayList<>();
                while (scanner.hasNextLong()) {
                    destination.add(scanner.nextLong());
                    source.add(scanner.nextLong());
                    range.add(scanner.nextLong());
                }

                for(int j=0; j < waters.size(); j++) {
                    Long water = waters.get(j);
                    Long correspond = correspond(source, water, range, destination);
                    lights.add(correspond);
                    System.out.println("water " + water + " correspond light " + correspond);
                }
            } else if (line.equals("light-to-temperature map:")) {
                List<Long> destination = new ArrayList<>();
                List<Long> source = new ArrayList<>();
                List<Long> range = new ArrayList<>();
                while (scanner.hasNextLong()) {
                    destination.add(scanner.nextLong());
                    source.add(scanner.nextLong());
                    range.add(scanner.nextLong());
                }

                for(int j=0; j < lights.size(); j++) {
                    Long light = lights.get(j);
                    Long correspond = correspond(source, light, range, destination);
                    temperatures.add(correspond);
                    System.out.println("light " + light + " correspond temperature " + correspond);
                }
            } else if (line.equals("temperature-to-humidity map:")) {
                List<Long> destination = new ArrayList<>();
                List<Long> source = new ArrayList<>();
                List<Long> range = new ArrayList<>();
                while (scanner.hasNextLong()) {
                    destination.add(scanner.nextLong());
                    source.add(scanner.nextLong());
                    range.add(scanner.nextLong());
                }

                for(int j=0; j < temperatures.size(); j++) {
                    Long temperature = temperatures.get(j);
                    Long correspond = correspond(source, temperature, range, destination);
                    humiditys.add(correspond);
                    System.out.println("temperature " + temperature + " correspond humidity " + correspond);
                }
            } else if (line.equals("humidity-to-location map:")) {
                List<Long> destination = new ArrayList<>();
                List<Long> source = new ArrayList<>();
                List<Long> range = new ArrayList<>();
                while (scanner.hasNextLong()) {
                    destination.add(scanner.nextLong());
                    source.add(scanner.nextLong());
                    range.add(scanner.nextLong());
                }

                for(int j=0; j < humiditys.size(); j++) {
                    Long humidity = humiditys.get(j);
                    Long correspond = correspond(source, humidity, range, destination);
                    locations.add(correspond);
                    System.out.println("humidity " + humidity + " correspond location " + correspond);
                }
            }

        }
        System.out.println("lowest location " + locations.stream().mapToLong(v -> v).min().orElseThrow());

    }

    private static Long correspond(List<Long> source, Long seed, List<Long> range, List<Long> destination) {
        for(int i = 0; i < source.size(); i++) {
            if(seed >= source.get(i) && seed < (source.get(i) + range.get(i))) {
                long diff = destination.get(i) - source.get(i);
                long result = seed + diff;
//                if(diff < 0) {
//                    result = seed - source.get(i) + destination.get(i);
//                } else {
//                    result = destination.get(i) + seed - source.get(i);
//                }

                return result;

            }
        }
        return seed;
    }


}
