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
        }
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
        // TODO: Implement package sorting
        // Sort by: 1) priority (1->3)
        //         2) fragile before non-fragile
        //         3) weight (lightest first)
        return new ArrayList<>();
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
            new Connection("E", 912)));
        cities.put("B", Arrays.asList(
            new Connection("A", 456), new Connection("C", 338)));
        cities.put("C", Arrays.asList(
            new Connection("A", 231), new Connection("B", 338),
            new Connection("D", 445)));
        cities.put("D", Arrays.asList(
            new Connection("C", 445), new Connection("E", 234),
            new Connection("F", 567)));
        cities.put("E", Arrays.asList(
            new Connection("A", 912), new Connection("D", 234),
            new Connection("F", 345)));
        cities.put("F", Arrays.asList(
            new Connection("D", 567), new Connection("E", 345)));
        
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
