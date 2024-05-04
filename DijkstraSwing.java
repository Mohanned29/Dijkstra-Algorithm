import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class DijkstraSwing extends JFrame {
    private final Graph graph;
    private GraphPanel graphPanel;
    private JLabel resultLabel;

    public DijkstraSwing(Graph graph) {
        this.graph = graph;
        setTitle("Dijkstra's Algorithm Visualization");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        initComponents();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initComponents() {
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JTextField startField = new JTextField();
        JTextField finishField = new JTextField();
        JButton runButton = new JButton("Run Dijkstra");
        resultLabel = new JLabel("Result will appear here", JLabel.CENTER);

        runButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startField.setMaximumSize(new Dimension(Integer.MAX_VALUE, startField.getPreferredSize().height));
        finishField.setMaximumSize(new Dimension(Integer.MAX_VALUE, finishField.getPreferredSize().height));

        controlPanel.add(new JLabel("Start Vertex:"));
        controlPanel.add(startField);
        controlPanel.add(new JLabel("Finish Vertex:"));
        controlPanel.add(finishField);
        controlPanel.add(runButton);
        controlPanel.add(resultLabel);

        runButton.addActionListener((ActionEvent e) -> {
            try {
                int start = Integer.parseInt(startField.getText().trim());
                int finish = Integer.parseInt(finishField.getText().trim());
                List<Integer> path = graph.getShortestPath(start, finish);
                resultLabel.setText("Shortest Path: " + path.toString());
                graphPanel.repaint();
            } catch (NumberFormatException ex) {
                resultLabel.setText("Invalid input. Please enter numbers.");
            }
        });

        add(controlPanel, BorderLayout.EAST);

        graphPanel = new GraphPanel(graph);
        add(graphPanel, BorderLayout.CENTER);
    }
}
