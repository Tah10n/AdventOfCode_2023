import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day5Part2 {
    static List<List<Long>> seeds = new ArrayList<>();
    static List<List<Long>> seedToSoilmap = new ArrayList<>();
    static List<List<Long>> soilToFert = new ArrayList<>();
    static List<List<Long>> fertToWater = new ArrayList<>();
    static List<List<Long>> waterToLight = new ArrayList<>();
    static List<List<Long>> lightToTemp = new ArrayList<>();
    static List<List<Long>> tempToHumidity = new ArrayList<>();
    static List<List<Long>> humidityToLocation = new ArrayList<>();

    static List<List> maps = new ArrayList<>();

    static {
        maps.add(seedToSoilmap);
        maps.add(soilToFert);
        maps.add(fertToWater);
        maps.add(waterToLight);
        maps.add(lightToTemp);
        maps.add(tempToHumidity);
        maps.add(humidityToLocation);
    }

    public static void main(String[] args) throws FileNotFoundException {


        File file = new File("Day5/resources/input.txt");


        fillSeeds(new Scanner(file));
        fillMaps(new Scanner(file));

//        printSeeds();
//        printMaps();

        //System.out.println(maxLocation);
        //locationToSeed(location);
        //minLocation = fillSeeds(strNumbers, start, minLocation);
        //System.out.println(correspondBackward(50l,seedToSoilmap));
        //System.out.println(seedToLocation(13l));
        //System.out.println(locationToSeed(35l));

        Long lowestLocation = findLowestLocation();
        System.out.println("min location " + lowestLocation);

    }

    private static Long findLowestLocation() {
        Long maxLocation = getMaxLocation();

        for(long i = 0; i < maxLocation; i++) {
            Long seed = locationToSeed(i);

            if(inSeedRange(seed)) {
                return i;

            }
        }
        return maxLocation;
    }

    private static boolean inSeedRange(Long seed) {
        for (List<Long> list : seeds) {
            Long start = list.get(0);
            Long range = list.get(1);

            if(seed >= start && seed < (start + range)) {
                return true;
            }
        }
        return false;
    }

    private static Long getMaxLocation() {
        Long maxLocation = Long.MIN_VALUE;
        for(List<Long> list : humidityToLocation) {
            Long destination = list.get(0);
            Long source = list.get(1);
            Long range = list.get(2);
            if(maxLocation < destination + range) {
                maxLocation = destination + range;
            }
        }
        return maxLocation;
    }


    private static Long seedToLocation(Long seed) {

        for (List<List<Long>> map : maps) {
            seed = correspond(seed, map);
        }
        return seed;
    }

    private static Long locationToSeed(Long location) {

        for(int i = maps.size()-1; i>=0 ; i--) {
            location = correspondBackward(location, maps.get(i));
        }
        return location;
    }

    private static void printSeeds() {
        for (List<Long> list : seeds) {
            for (Long seed : list) {
                System.out.print(seed + " ");
            }
            System.out.println();
        }
    }

    private static void fillSeeds(Scanner scanner) {
        String line = scanner.nextLine();
        String[] split = line.split(":");
        String[] strNumbers = split[1].trim().split(" ");

        for (int i = 0; i < strNumbers.length; i = i + 2) {

            seeds.add(List.of(Long.parseLong(strNumbers[i]), Long.parseLong(strNumbers[i + 1])));

        }

    }

    private static void printMaps() {

        for (List<List<Long>> map : maps) {
            for (List<Long> list : map) {
                for (Long nmb : list) {
                    System.out.print(nmb + " ");
                }
                System.out.println();
            }

        }
    }

    private static void fillMaps(Scanner scanner) {
        String line;
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();

            if (line.equals("seed-to-soil map:")) {
                while (scanner.hasNextLong()) {
                    long destination = scanner.nextLong();
                    long source = scanner.nextLong();
                    long range = scanner.nextLong();
                    //System.out.println(destination + " " + source + " " + range);
                    seedToSoilmap.add(List.of(destination, source, range));


                }

            } else if (line.equals("soil-to-fertilizer map:")) {
                while (scanner.hasNextLong()) {
                    long destination = scanner.nextLong();
                    long source = scanner.nextLong();
                    long range = scanner.nextLong();
                    //System.out.println(destination + " " + source + " " + range);
                    soilToFert.add(List.of(destination, source, range));
                }
            } else if (line.equals("fertilizer-to-water map:")) {
                while (scanner.hasNextLong()) {
                    long destination = scanner.nextLong();
                    long source = scanner.nextLong();
                    long range = scanner.nextLong();
                    //System.out.println(destination + " " + source + " " + range);
                    fertToWater.add(List.of(destination, source, range));
                }
            } else if (line.equals("water-to-light map:")) {
                while (scanner.hasNextLong()) {
                    long destination = scanner.nextLong();
                    long source = scanner.nextLong();
                    long range = scanner.nextLong();
                    //System.out.println(destination + " " + source + " " + range);
                    waterToLight.add(List.of(destination, source, range));
                }
            } else if (line.equals("light-to-temperature map:")) {
                while (scanner.hasNextLong()) {
                    long destination = scanner.nextLong();
                    long source = scanner.nextLong();
                    long range = scanner.nextLong();
                    //System.out.println(destination + " " + source + " " + range);
                    lightToTemp.add(List.of(destination, source, range));
                }
            } else if (line.equals("temperature-to-humidity map:")) {
                while (scanner.hasNextLong()) {
                    long destination = scanner.nextLong();
                    long source = scanner.nextLong();
                    long range = scanner.nextLong();
                    //System.out.println(destination + " " + source + " " + range);
                    tempToHumidity.add(List.of(destination, source, range));
                }
            } else if (line.equals("humidity-to-location map:")) {
                while (scanner.hasNextLong()) {
                    long destination = scanner.nextLong();
                    long source = scanner.nextLong();
                    long range = scanner.nextLong();
                    //System.out.println(destination + " " + source + " " + range);
                    humidityToLocation.add(List.of(destination, source, range));
                }
            }
        }
    }


    private static Long correspond(Long input, List<List<Long>> map) {
        for (List<Long> list : map) {
            Long destination = list.get(0);
            Long source = list.get(1);
            Long range = list.get(2);
            if (input >= source && input < (source + range)) {
                long diff = destination - source;
                long result = input + diff;

                return result;

            }
        }
        return input;
    }

    private static Long correspondBackward(Long input, List<List<Long>> map) {
        for (List<Long> list : map) {
            Long destination = list.get(0);
            Long source = list.get(1);
            Long range = list.get(2);
            if (input >= destination && input < (destination + range)) {
                long diff = source - destination;
                long result = input + diff;

                return result;
            }
        }
        return input;
    }


}
