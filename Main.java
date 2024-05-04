import javax.swing.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Graph graph = new Graph();

            graph.addVertex(1, Arrays.asList(new Vertex(2, 1), new Vertex(3, 4)));
            graph.addVertex(2, Arrays.asList(new Vertex(1, 1), new Vertex(4, 2), new Vertex(5, 3)));
            graph.addVertex(3, Arrays.asList(new Vertex(1, 4), new Vertex(5, 1)));
            graph.addVertex(4, Arrays.asList(new Vertex(2, 2), new Vertex(6, 4)));
            graph.addVertex(5, Arrays.asList(new Vertex(2, 3), new Vertex(3, 1), new Vertex(6, 2)));
            graph.addVertex(6, Arrays.asList(new Vertex(4, 4), new Vertex(5, 2)));
            new DijkstraSwing(graph);
        });
    }
}
