import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GraphPanel extends JPanel {
    private Graph graph;

    public GraphPanel(Graph graph) {
        this.graph = graph;
        this.setPreferredSize(new Dimension(600, 600));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGraph(g);
    }

    private void drawGraph(Graphics g) {
        int radius = 20;
        int[][] positions = {
            {150, 100},
            {450, 100},
            {600, 300},
            {450, 500},
            {150, 500},
            {50, 300}
        };

        // Draw vertices
        g.setColor(Color.BLUE);
        for (int i = 0; i < positions.length; i++) {
            g.fillOval(positions[i][0] - radius, positions[i][1] - radius, 2 * radius, 2 * radius);
            g.setColor(Color.WHITE);
            g.drawString(Integer.toString(i + 1), positions[i][0] - 5, positions[i][1] + 5);
            g.setColor(Color.BLUE);
        }

        // Draw edges
        g.setColor(Color.GRAY);
        drawEdge(g, positions, 1, 2);
        drawEdge(g, positions, 2, 3);
        drawEdge(g, positions, 3, 4);
        drawEdge(g, positions, 4, 5);
        drawEdge(g, positions, 5, 6);
        drawEdge(g, positions, 6, 1);
        drawEdge(g, positions, 1, 4);
        drawEdge(g, positions, 2, 5);
        drawEdge(g, positions, 3, 6);

        List<Integer> path = graph.getLastComputedPath();
        if (path != null && !path.isEmpty()) {
            g.setColor(Color.RED);
            for (int i = 0; i < path.size() - 1; i++) {
                int from = path.get(i);
                int to = path.get(i + 1);
                drawEdge(g, positions, from, to);
            }
        }
    }

    private void drawEdge(Graphics g, int[][] positions, int from, int to) {
        g.drawLine(positions[from - 1][0], positions[from - 1][1], positions[to - 1][0], positions[to - 1][1]);
    }
}
