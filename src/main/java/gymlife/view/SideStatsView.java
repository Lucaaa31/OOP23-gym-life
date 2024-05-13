package gymlife.view;

import gymlife.controller.api.Controller;
import gymlife.model.statistics.Counter;
import gymlife.model.statistics.StatsType;


import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.Serial;
import java.util.Map;
import  gymlife.utility.FontLoader;

/**
 * The StatsView class represents a JPanel that displays statistics related to
 * the gym.
 * It is a part of the GUI of the Java application.
 */
public class SideStatsView extends JPanel {
    @Serial
    private static final long serialVersionUID = 4324743;
    private final transient Controller controller;
    private static final float FONT_SIZE = 50f;
    private static final int BORDER_SIZE = 4;
    private static final LineBorder BORDER = new LineBorder(Color.BLACK, BORDER_SIZE);
    private final DimensionGetter dimensionGetter;
    private final JPanel statsPanel1 = new JPanel();
    private final JPanel statsPanel2 = new JPanel();
    private final JPanel statsPanel3 = new JPanel();
    private final JPanel statsPanel4 = new JPanel();
    /**
     * Starts the main view of the application.
     * Sets the size, layout, and default close operation of the frame.
     * Sets the size, layout, and visibility of the character view panel.
     * Adds the character view panel to the main frame and makes it visible.
     * 
     * @param controller the controller object
     */
    public SideStatsView(final Controller controller, DimensionGetter dimensionGetter) {
        this.dimensionGetter = dimensionGetter;
        this.setPreferredSize(dimensionGetter.getSideDimension());
        this.setBackground(Color.BLACK);
        this.controller = controller;
        this.setBorder(BORDER);

        this.setLayout(new GridLayout(4, 1, 10, BORDER_SIZE));

        statsPanel1.setLayout(new GridLayout(1, 3));
        statsPanel1.add(getHappinessLabel());
        statsPanel1.add(getStaminaLabel());
        statsPanel1.add(getMassLabel());

        statsPanel2.setLayout(new GridLayout(1, 3));
        statsPanel2.add(getHappinessLabel());
        statsPanel2.add(getStaminaLabel());
        statsPanel2.add(getMassLabel());

        statsPanel3.setLayout(new GridLayout(2, 1));
        statsPanel3.add(getMoneyLabel());
        statsPanel3.add(getDaysLabel());
        statsPanel1.setBorder(BORDER);

        statsPanel4.setLayout(new GridLayout(2, 1));
        statsPanel4.add(getMoneyLabel());
        statsPanel4.add(getDaysLabel());
        statsPanel4.setBorder(BORDER);


        statsPanel1.setBorder(BORDER);
        statsPanel2.setBorder(BORDER);
        statsPanel3.setBorder(BORDER);
        statsPanel4.setBorder(BORDER);

        statsPanel1.setBackground(Color.blue);
        statsPanel2.setBackground(Color.red);
        statsPanel3.setBackground(Color.green);
        statsPanel4.setBackground(Color.yellow);

        this.add(statsPanel1);
        this.add(statsPanel2);
        this.add(statsPanel3);
        this.add(statsPanel4);

    }
    public void resizeStats() {
        statsPanel1.removeAll();
        statsPanel1.add(getHappinessLabel());
        statsPanel1.add(getStaminaLabel());
        statsPanel1.add(getMassLabel());
        statsPanel1.revalidate();
        statsPanel1.repaint();

        statsPanel2.removeAll();
        statsPanel2.add(getHappinessLabel());
        statsPanel2.add(getStaminaLabel());
        statsPanel2.add(getMassLabel());

        statsPanel3.removeAll();
        statsPanel3.add(getMoneyLabel());
        statsPanel3.add(getDaysLabel());
        statsPanel3.revalidate();
        statsPanel3.repaint();

        statsPanel4.removeAll();
        statsPanel4.add(getMoneyLabel());
        statsPanel4.add(getDaysLabel());
        statsPanel4.setBorder(BORDER);
    }
    private JLabel getHappinessLabel() {
        final Map<StatsType, Counter> statistics = controller.getStatistics();
        final JLabel labelText = new JLabel("H", SwingConstants.CENTER);
        final JLabel lablelNumber = new JLabel(String.valueOf(statistics.get(StatsType.HAPPINESS).getCount()), 
            SwingConstants.CENTER);
        final JLabel happinessLabel = new JLabel();

        happinessLabel.setLayout(new BorderLayout());
        happinessLabel.add(labelText, BorderLayout.NORTH);
        happinessLabel.add(lablelNumber, BorderLayout.CENTER);
        FontLoader.loadFont();

        labelText.setFont(FontLoader.getCustomFont(dimensionGetter.getBigFontSize()));
        lablelNumber.setFont(FontLoader.getCustomFont(dimensionGetter.getBigFontSize()));

        labelText.setBorder(BORDER);
        lablelNumber.setBorder(BORDER);

        labelText.setBackground(Color.BLUE);
        return happinessLabel;
    }
    private JLabel getStaminaLabel() {
        final Map<StatsType, Counter> statistics = controller.getStatistics();

        final JLabel labelText = new JLabel("S", SwingConstants.CENTER);
        final JLabel lablelNumber = new JLabel(String.valueOf(statistics.get(StatsType.STAMINA).getCount()), 
            SwingConstants.CENTER);
        final JLabel staminaLabel = new JLabel();

        staminaLabel.setLayout(new BorderLayout());
        staminaLabel.add(labelText, BorderLayout.NORTH);
        staminaLabel.add(lablelNumber, BorderLayout.CENTER);
        FontLoader.loadFont();

        labelText.setFont(FontLoader.getCustomFont(dimensionGetter.getBigFontSize()));
        lablelNumber.setFont(FontLoader.getCustomFont(dimensionGetter.getBigFontSize()));

        labelText.setHorizontalTextPosition(SwingConstants.CENTER);
        lablelNumber.setHorizontalTextPosition(SwingConstants.CENTER);

        labelText.setBorder(BORDER);
        lablelNumber.setBorder(BORDER);

        staminaLabel.setBackground(Color.RED);

        return staminaLabel;
    }
    private JLabel getMassLabel() {
        final Map<StatsType, Counter> statistics = controller.getStatistics();

        final JLabel labelText = new JLabel("M", SwingConstants.CENTER);
        final JLabel lablelNumber = new JLabel(String.valueOf(statistics.get(StatsType.MASS).getCount()), 
            SwingConstants.CENTER);
        final JLabel massLabel = new JLabel();

        massLabel.setLayout(new BorderLayout());
        massLabel.add(labelText, BorderLayout.NORTH);
        massLabel.add(lablelNumber, BorderLayout.CENTER);
        FontLoader.loadFont();

        labelText.setFont(FontLoader.getCustomFont(dimensionGetter.getBigFontSize()));
        lablelNumber.setFont(FontLoader.getCustomFont(dimensionGetter.getBigFontSize()));

        labelText.setHorizontalTextPosition(SwingConstants.CENTER);
        lablelNumber.setHorizontalTextPosition(SwingConstants.CENTER);

        labelText.setBorder(BORDER);
        lablelNumber.setBorder(BORDER);


        return massLabel;
    }
    private JLabel getMoneyLabel() {
        final Map<StatsType, Counter> statistics = controller.getStatistics();

        final JLabel labelText = new JLabel("MONEY", SwingConstants.CENTER);
        final JLabel lablelNumber = new JLabel(String.valueOf(statistics.get(StatsType.MONEY).getCount()), 
            SwingConstants.CENTER);
        final JLabel moneyLablel = new JLabel();

        moneyLablel.setLayout(new GridLayout(1, 2));

        labelText.setFont(FontLoader.getCustomFont(dimensionGetter.getSmallFontSize()));
        lablelNumber.setFont(FontLoader.getCustomFont(dimensionGetter.getSmallFontSize()));

        moneyLablel.add(labelText);
        moneyLablel.add(lablelNumber);

        labelText.setBorder(BORDER);
        lablelNumber.setBorder(BORDER);

        return moneyLablel;
    }
    private JLabel getDaysLabel() {
        final Map<StatsType, Counter> statistics = controller.getStatistics();

        final JLabel labelText = new JLabel("DAYS", SwingConstants.CENTER);
        final JLabel lablelNumber = new JLabel(String.valueOf(statistics.get(StatsType.DAYS).getCount()), 
            SwingConstants.CENTER);
        final JLabel moneyLablel = new JLabel();

        moneyLablel.setLayout(new GridLayout(1, 2));

        labelText.setFont(FontLoader.getCustomFont(dimensionGetter.getSmallFontSize()));
        lablelNumber.setFont(FontLoader.getCustomFont(dimensionGetter.getSmallFontSize()));

        moneyLablel.add(labelText);
        moneyLablel.add(lablelNumber);

        labelText.setBorder(BORDER);
        lablelNumber.setBorder(BORDER);

        return moneyLablel;
    }

}
