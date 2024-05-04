import java.util.*;

public class Graph {
    private final Map<Integer, List<Vertex>> vertices;
    private List<Integer> lastComputedPath;

    public Graph() {
        this.vertices = new HashMap<>();
        this.lastComputedPath = new ArrayList<>();
    }

    public void addVertex(int number, List<Vertex> edges) {
        vertices.put(number, edges);
    }

    public List<Integer> getShortestPath(int start, int finish) {
        final Map<Integer, Integer> distances = new HashMap<>();
        final Map<Integer, Vertex> previous = new HashMap<>();
        PriorityQueue<Vertex> nodes = new PriorityQueue<>();

        vertices.keySet().forEach(vertex -> {
            if (vertex == start) {
                distances.put(vertex, 0);
                nodes.add(new Vertex(vertex, 0));
            } else {
                distances.put(vertex, Integer.MAX_VALUE);
                nodes.add(new Vertex(vertex, Integer.MAX_VALUE));
            }
            previous.put(vertex, null);
        });

        while (!nodes.isEmpty()) {
            Vertex smallest = nodes.poll();
            if (smallest.id == finish) {
                lastComputedPath = new ArrayList<>();
                for (Integer at = finish; at != null; at = previous.get(at) == null ? null : previous.get(at).id) {
                    lastComputedPath.add(at);
                }
                Collections.reverse(lastComputedPath);
                return lastComputedPath;
            }

            for (Vertex neighbor : vertices.get(smallest.id)) {
                int alt = distances.get(smallest.id) + neighbor.distance;
                if (alt < distances.get(neighbor.id)) {
                    distances.put(neighbor.id, alt);
                    previous.put(neighbor.id, smallest);
                    nodes.add(new Vertex(neighbor.id, distances.get(neighbor.id)));
                }
            }
        }
        lastComputedPath.clear();
        return lastComputedPath;
    }

    public List<Integer> getLastComputedPath() {
        return lastComputedPath;
    }
}
