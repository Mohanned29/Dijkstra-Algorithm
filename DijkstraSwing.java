import javax.swing.*;
import java.awt.*;
import java.util.List;

public class DijkstraSwing extends JFrame {
    private Graph graph;
    private GraphPanel graphPanel;
    private JLabel resultLabel;

    public DijkstraSwing(Graph graph) {
        this.graph = graph;
        initUI();
    }

    private void initUI() {
        setTitle("Dijkstra's Algorithm Visualization");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(800, 600));
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        graphPanel = new GraphPanel(graph);
        add(graphPanel, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JTextField startField = new JTextField(5);
        JTextField finishField = new JTextField(5);
        JButton runButton = new JButton("Run Dijkstra");
        resultLabel = new JLabel("Path will appear here", JLabel.CENTER);

        controlPanel.add(new JLabel("Start Vertex:"));
        controlPanel.add(startField);
        controlPanel.add(new JLabel("Finish Vertex:"));
        controlPanel.add(finishField);
        controlPanel.add(runButton);
        controlPanel.add(resultLabel);

        add(controlPanel, BorderLayout.EAST);

        runButton.addActionListener(e -> {
            try {
                int start = Integer.parseInt(startField.getText());
                int finish = Integer.parseInt(finishField.getText());
                List<Integer> path = graph.getShortestPath(start, finish);
                resultLabel.setText("Shortest Path: " + path.toString());
                graphPanel.repaint();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid integers for start and finish vertices.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        setVisible(true);
    }

}
