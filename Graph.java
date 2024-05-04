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

        for (Integer vertex : vertices.keySet()) {
            if (vertex == start) {
                distances.put(vertex, 0);
                nodes.add(new Vertex(vertex, 0));
            } else {
                distances.put(vertex, Integer.MAX_VALUE);
                nodes.add(new Vertex(vertex, Integer.MAX_VALUE));
            }
            previous.put(vertex, null);
        }

        while (!nodes.isEmpty()) {
            Vertex smallest = nodes.poll();
            if (smallest.id == finish) {
                final List<Integer> path = new ArrayList<>();
                Integer current = finish;
                while (current != null) {
                    path.add(current);
                    current = previous.get(current) != null ? previous.get(current).id : null;
                }
                Collections.reverse(path);
                lastComputedPath = path;
                return path;
            }

            for (Vertex neighbor : vertices.get(smallest.id)) {
                int alt = distances.get(smallest.id) + neighbor.distance;
                if (alt < distances.get(neighbor.id)) {
                    distances.put(neighbor.id, alt);
                    previous.put(neighbor.id, smallest);

                    for (Vertex n : nodes) {
                        if (n.id == neighbor.id) {
                            nodes.remove(n);
                            break;
                        }
                    }
                    nodes.add(new Vertex(neighbor.id, alt));
                }
            }
        }

        lastComputedPath = new ArrayList<>();
        return lastComputedPath;
    }

    public List<Integer> getLastComputedPath() {
        return lastComputedPath;
    }
}
