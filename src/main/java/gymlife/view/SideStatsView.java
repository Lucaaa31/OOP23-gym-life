package gymlife.view;

import gymlife.controller.api.Controller;
import gymlife.model.statistics.Counter;
import gymlife.model.statistics.StatsConstants;
import gymlife.model.statistics.StatsType;


import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import java.awt.Image;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.Serial;
import java.util.Map;
import gymlife.utility.FontLoader;
import gymlife.view.api.GamePanel;

/**
 * The StatsView class represents a JPanel that displays statistics related to
 * the gym.
 * It is a part of the GUI of the Java application.
 */
public class SideStatsView extends GamePanel {
    @Serial
    private static final long serialVersionUID = 4324743;
    private final transient Controller controller;
    private static final int BORDER_SIZE = 4;
    private static final int BOX_BORDER_5 = 5;
    private static final LineBorder BORDER = new LineBorder(Color.BLACK, BORDER_SIZE);
    private final transient DimensionGetter dimensionGetter;
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
     * @param dimensionGetter the controller object
     */
    public SideStatsView(final Controller controller, final DimensionGetter dimensionGetter) {
        this.dimensionGetter = dimensionGetter;
        this.setPreferredSize(dimensionGetter.getSideDimension());
        this.setBackground(Color.BLACK);
        this.controller = controller;
        this.setBorder(BORDER);

        this.setLayout(new GridLayout(4, 1, 10, BORDER_SIZE));

        statsPanel1.setLayout(new GridLayout(1, 3));

        statsPanel1.addMouseListener(new MouseListener() {
           @Override
           public void mouseClicked(MouseEvent e) {
           }

           @Override
           public void mousePressed(MouseEvent e) {
           }

           @Override
           public void mouseReleased(MouseEvent e) {
           }

           @Override
           public void mouseEntered(MouseEvent e) {
               buildDetailedPanel();
           }

           @Override
           public void mouseExited(MouseEvent e) {
               buildPanelStandard();
           }
        });
        buildPanelStandard();
        statsPanel3.setLayout(new GridLayout(1, 2));
        statsPanel3.add(getMoneyLabel());
        statsPanel3.add(getDaysLabel());

        statsPanel2.setBorder(BORDER);
        statsPanel3.setBorder(BORDER);
        statsPanel4.setBorder(BORDER);

        this.add(statsPanel1);
        this.add(statsPanel2);
        this.add(statsPanel3);
        this.add(statsPanel4);

    }
    /**
     * Builds the detailed panel.
     */
    private void buildDetailedPanel() {
        statsPanel1.removeAll();

        statsPanel1.add(buildLabel(StatsType.LEG_MASS, "images/icons/legs.png"));
        statsPanel1.add(buildLabel(StatsType.CHEST_MASS, "images/icons/push.png"));
        statsPanel1.add(buildLabel(StatsType.BACK_MASS, "images/icons/pull.png"));
        statsPanel1.setBorder(BORDER);

        statsPanel1.revalidate();
        statsPanel1.repaint();
    }
    /**
     * Builds the standard panel.
     */
    private void buildPanelStandard() {
        final int level = controller.getStatistics().get(StatsType.HAPPINESS).getCount();
        final String happyPath;
        if (level < StatsConstants.LEVEL_1) {
            happyPath = "images/icons/sad.png";
        } else if (level < StatsConstants.LEVEL_2) {
            happyPath = "images/icons/mid.png";
        } else {
            happyPath = "images/icons/happy.png";
        }
        statsPanel1.removeAll();
        statsPanel1.add(buildLabel(StatsType.HAPPINESS, happyPath));
        statsPanel1.add(buildLabel(StatsType.STAMINA, "images/icons/stamina.png"));
        statsPanel1.add(buildLabel(StatsType.MASS, "images/icons/mass.png"));
        statsPanel1.setBorder(BORDER);

        statsPanel1.revalidate();
        statsPanel1.repaint();
    }
    /**
     * Resizes the stats panel.
     */
    @Override
    public void resizeComponents() {
        buildPanelStandard();

        statsPanel3.removeAll();
        statsPanel3.add(getMoneyLabel());
        statsPanel3.add(getDaysLabel());
        statsPanel3.revalidate();
        statsPanel3.repaint();

    }
    /**
     * Method to return a representative name for the classes that extend this.
     *
     * @return a string representing said name.
     */
    @Override
    public String getPanelName() {
        return this.getClass().getSimpleName();
    }
    /**
     * Updates the stats panel.
     */
    public void updateStats() {
        this.resizeComponents();
    }
    /**
     * Return the money stats label.
     * @return the JLabel with the money value
     */
    private JLabel getMoneyLabel() {
        final int moneyValue = controller.getStatistics().get(StatsType.MONEY).getCount();
        final JLabel labelImage = new JLabel();

        final JLabel lablelNumber = new JLabel(String.valueOf(moneyValue),
            SwingConstants.CENTER);
        final JLabel moneyLablel = new JLabel();

        moneyLablel.setLayout(new GridLayout(2, 1));

        labelImage.setIcon(getIcon("images/icons/money.png"));
        labelImage.setHorizontalAlignment(SwingConstants.CENTER);
        labelImage.setVerticalAlignment(SwingConstants.CENTER);
        lablelNumber.setFont(FontLoader.getCustomFont(dimensionGetter.getSmallFontSize()));

        moneyLablel.add(labelImage);
        moneyLablel.add(lablelNumber);

        this.setBorder(BORDER);
        labelImage.setBorder(new MatteBorder(BOX_BORDER_5, BOX_BORDER_5, 0, BOX_BORDER_5, Color.BLACK));
        lablelNumber.setBorder(new MatteBorder(0, BOX_BORDER_5, BOX_BORDER_5, BOX_BORDER_5, Color.BLACK));
        return moneyLablel;
    }
    /**
     * Return the days stats label.
     * @return the JLabel with the days value
     */
    private JLabel getDaysLabel() {
        final Map<StatsType, Counter> statistics = controller.getStatistics();

        final JLabel labelText = new JLabel("<html><div style='text-align: center;'>DAYS<br>LEFT</div></html",
                SwingConstants.CENTER);
        final JLabel lablelNumber = new JLabel(String.valueOf(statistics.get(StatsType.DAYS).getCount()),
            SwingConstants.CENTER);
        final JLabel moneyLablel = new JLabel();

        moneyLablel.setLayout(new GridLayout(2, 1));

        labelText.setFont(FontLoader.getCustomFont(dimensionGetter.getSmallFontSize()));
        lablelNumber.setFont(FontLoader.getCustomFont(dimensionGetter.getSmallFontSize()));

        moneyLablel.add(labelText);
        moneyLablel.add(lablelNumber);
        labelText.setBorder(new MatteBorder(BOX_BORDER_5, BOX_BORDER_5, 0, BOX_BORDER_5, Color.BLACK));
        lablelNumber.setBorder(new MatteBorder(0, BOX_BORDER_5, BOX_BORDER_5, BOX_BORDER_5, Color.BLACK));
        this.setBorder(BORDER);
        return moneyLablel;
    }
    /**
     * Return the ImageIcona that is in the path.
     * @return ImageIcon
     * @param path the path of the image
     */
    private ImageIcon getIcon(final String path) {
        return new ImageIcon(new ImageIcon(ClassLoader.
                getSystemResource(path))
                .getImage()
                .getScaledInstance(dimensionGetter.getSquareStatsDimension().width,
                        dimensionGetter.getSquareStatsDimension().height,
                        Image.SCALE_SMOOTH));
    }
    private JLabel buildLabel(final StatsType statsType, final String path) {
        final int value = controller.getStatistics().get(statsType).getCount();
        final JLabel labelImage = new JLabel();
        this.setSize(dimensionGetter.getCellDimension());
        final JLabel lablelNumber = new JLabel(String.valueOf(value),
                SwingConstants.CENTER);
        final JLabel label = new JLabel();
        label.setLayout(new GridLayout(2, 1));
        label.add(labelImage);
        label.add(lablelNumber);
        FontLoader.loadFont();

        labelImage.setIcon(getIcon(path));

        this.setSize(dimensionGetter.getCellDimension());
        labelImage.setFont(FontLoader.getCustomFont(dimensionGetter.getBigFontSize()));
        lablelNumber.setFont(FontLoader.getCustomFont(dimensionGetter.getBigFontSize()));

        labelImage.setHorizontalAlignment(SwingConstants.CENTER);
        labelImage.setVerticalAlignment(SwingConstants.CENTER);

        labelImage.setBorder(new MatteBorder(3, BOX_BORDER_5, 0, 0, Color.BLACK));
        lablelNumber.setBorder(BORDER);
        return label;
    }

}
