package gymlife.view.stats;

import gymlife.controller.api.Controller;
import gymlife.model.statistics.Counter;
import gymlife.model.statistics.StatsType;
import gymlife.utility.Constants;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
//import gymlife.utility.ScenariosType;
//import java.util.HashMap;
//import java.util.Map;

/**
 * The StatsView class represents a JPanel that displays statistics related to
 * the gym.
 * It is a part of the GUI of the Java application.
 */
public class SideStatsView extends JPanel {
    public static final long serialVersionUID = 4328743;
    private final transient Controller controller;
    private final transient Map<StatsType, Counter> statistics;

    /**
     * Starts the main view of the application.
     * Sets the size, layout, and default close operation of the frame.
     * Sets the size, layout, and visibility of the character view panel.
     * Adds the character view panel to the main frame and makes it visible.
     */
    public SideStatsView(final Controller controller) {
        this.controller = controller;
        this.setPreferredSize(new Dimension(Constants.SIDE_WIDTH, Constants.HEIGHT));
        this.setBackground(Color.GRAY);

        statistics = controller.getStatistics();

        JPanel statsPanel1 = new JPanel();
        JPanel statsPanel2 = new JPanel();

        GridLayout g = new GridLayout(2, 1, 10, 10);
        this.setLayout(g);
        statsPanel1.setPreferredSize(new Dimension(Constants.SIDE_WIDTH, Constants.HEIGHT/2));
        statsPanel1.setBackground(Color.cyan);
        this.add(statsPanel1);
        statsPanel2.setPreferredSize(new Dimension(Constants.SIDE_WIDTH, Constants.HEIGHT/2));
        statsPanel1.setLayout(new BorderLayout());
        statsPanel1.add(getDaysLabel(), BorderLayout.NORTH);
        statsPanel1.setBackground(Color.GREEN);
        this.add(statsPanel2);
    }

    private JLabel getDaysLabel() {
        final JLabel daysLabel = new JLabel("Days: " + statistics.get(StatsType.DAYS).getCount());
        daysLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        return daysLabel;
    }
}
