import java.util.*;

class Node implements Comparable<Node> {

    String city;
    int distance;

    Node(String city, int distance) {
        this.city = city;
        this.distance = distance;
    }

    public int compareTo(Node other) {
        return this.distance - other.distance;
    }
}

public class DijkstraAlgorithm {

    static Map<String, List<Node>> graph = new HashMap<>();

    static {
        graph.put("Edinburgh", Arrays.asList(
                new Node("Perth", 43),
                new Node("Dundee", 66)
        ));

        graph.put("Perth", Arrays.asList(
                new Node("Dundee", 22)
        ));

        graph.put("Dundee", new ArrayList<>());
    }

    public static void main(String[] args) {

        Map<String, Integer> dist = new HashMap<>();
        PriorityQueue<Node> pq = new PriorityQueue<>();

        for (String city : graph.keySet()) {
            dist.put(city, Integer.MAX_VALUE);
        }

        dist.put("Edinburgh", 0);
        pq.add(new Node("Edinburgh", 0));

        while (!pq.isEmpty()) {

            Node current = pq.poll();

            for (Node neighbor : graph.get(current.city)) {

                int newDist = dist.get(current.city) + neighbor.distance;

                if (newDist < dist.get(neighbor.city)) {

                    dist.put(neighbor.city, newDist);
                    pq.add(new Node(neighbor.city, newDist));
                }
            }
        }

        System.out.println("Shortest distance: " + dist.get("Dundee"));
    }
}