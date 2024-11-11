// NetworkOptimization.java

import java.util.*;

public class NetworkOptimization {
    static class Package {
        int id;
        int weight;
        int priority;
        boolean fragile;

        Package(int id, int weight, int priority, boolean fragile) {
            this.id = id;
            this.weight = weight;
            this.priority = priority;
            this.fragile = fragile;
        }

        public int getWeight() {
            return weight;
        }

        public int getPriority() {
            return priority;
        }

        public boolean isFragile() {
            return fragile;
        }

        public int getId() {
            return id;
        }

        public static final Comparator<Package> BY_PRIORITY_THEN_FRAGILITY_DESC_THEN_WEIGHT = new Comparator<Package>() {
            @Override
            public int compare(Package package1, Package package2) {
                if(package1.getPriority() != package2.getPriority()) {
                    return package1.getPriority() - package2.getPriority();
                } else if(package1.isFragile() != package2.isFragile()) {
                    return -Boolean.compare(package1.isFragile(), package2.isFragile());
                } else if(package1.getWeight() != package2.getWeight()) {
                    return package1.getWeight() - package2.getWeight();
                } else {
                    return 0;
                }
            }
        };
    }

    static class Connection {
        String city;
        int distance;

        Connection(String city, int distance) {
            this.city = city;
            this.distance = distance;
        }
    }

    static class PathResult {
        List<String> path;
        int distance;

        PathResult(List<String> path, int distance) {
            this.path = path;
            this.distance = distance;
        }
    }

    public static List<Integer> sortPackages(List<Package> packages) {
        List<Package> sortedPackages = sortPackageLists(packages);
        List<Integer> ids = new ArrayList<>();

        for(Package pack : sortedPackages) {
            ids.add(pack.getId());
        }

        return ids;
    }

    public static List<Package> sortPackageLists(List<Package> packages) {
        int n = packages.size();
        if(n > 1) {
            List<Package> firstHalf = sortPackageLists(packages.subList(0, (int) Math.floor((double) n / 2) - 1));
            List<Package> secondHalf = sortPackageLists(packages.subList((int) Math.floor((double) n / 2), n - 1));
            return merge(firstHalf, secondHalf);
        } else {
            return packages;
        }
    }

    public static List<Package> merge(List<Package> firstList, List<Package> secondList) {
        int firstIndex = 0;
        int secondIndex = 0;
        List<Package> mergedPackages = new ArrayList<>();

        while(firstIndex < firstList.size() && secondIndex < secondList.size()) {
            if(Package.BY_PRIORITY_THEN_FRAGILITY_DESC_THEN_WEIGHT.compare(firstList.get(firstIndex), secondList.get(secondIndex)) <= 0) {
                mergedPackages.add(firstList.get(firstIndex));
                firstIndex++;
            } else {
                mergedPackages.add(secondList.get(secondIndex));
                secondIndex++;
            }
        }
        if(firstIndex == firstList.size()) {
            for(int i = secondIndex; i < secondList.size(); i++) {
                mergedPackages.add(secondList.get(i));
            }
        } else {
            for(int i = firstIndex; i < secondList.size(); i++) {
                mergedPackages.add(firstList.get(i));
            }
        }

        return mergedPackages;
    }

    public static PathResult findShortestPath(
            Map<String, List<Connection>> cities,
            String start,
            String end) {
        
        // TODO: Implement Dijkstra's algorithm
        return new PathResult(new ArrayList<>(), 0);
    }

    public static void main(String[] args) {
        // Test data setup
        Map<String, List<Connection>> cities = new HashMap<>();
        cities.put("A", Arrays.asList(
            new Connection("B", 456), new Connection("C", 231),
            new Connection("E", 912), new Connection("L", 445),
            new Connection("D",111), new Connection("F", 275)));
        cities.put("B", Arrays.asList(
            new Connection("A", 456), new Connection("C", 338),
            new Connection("G", 567), new Connection("H", 789),
            new Connection("E", 294), new Connection("D", 222)));
        cities.put("C", Arrays.asList(
            new Connection("A", 231), new Connection("B", 338),
            new Connection("G", 333), new Connection("H", 444),
            new Connection("E", 194), new Connection("D", 123)));
        cities.put("D", Arrays.asList(
            new Connection("A", 111), new Connection("B", 222),
            new Connection("C", 123), new Connection("H", 456),
            new Connection("E", 917), new Connection("K", 486)));
        cities.put("E", Arrays.asList(
            new Connection("A", 912), new Connection("B", 294),
            new Connection("C", 194), new Connection("D", 917),
            new Connection("F", 462), new Connection("G", 1087)));
        cities.put("F", Arrays.asList(
            new Connection("A", 275), new Connection("L", 283),
            new Connection("J", 195), new Connection("H", 999),
            new Connection("E", 462), new Connection("G", 107)));
        
        List<Package> packages = Arrays.asList(
            new Package(1, 487, 1, true),
            new Package(2, 123, 3, false),
            new Package(3, 345, 2, true),
            new Package(4, 234, 1, false),
            new Package(5, 456, 2, false),
            new Package(6, 67, 3, true),
            new Package(7, 432, 1, false),
            new Package(8, 289, 2, true),
            new Package(9, 178, 3, false),
            new Package(10, 397, 1, true),
            new Package(11, 245, 2, false),
            new Package(12, 156, 3, true),
            new Package(13, 478, 1, false),
            new Package(14, 321, 2, true),
            new Package(15, 198, 3, false)
        );

        // Get solutions
        List<Integer> sortedPackages = sortPackages(packages);
        PathResult pathResult = findShortestPath(cities, "A", "M");

        // Print results
        System.out.println("1. Sorted Package IDs: " + sortedPackages);
        System.out.println("2. Shortest Path: " + pathResult.path + 
                         ", Distance: " + pathResult.distance);
    }
}
