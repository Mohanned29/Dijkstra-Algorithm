import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Graph graph = new Graph();
            new DijkstraSwing(graph);
        });
    }
}
