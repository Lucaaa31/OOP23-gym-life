package gymlife.view;

import gymlife.utility.DimensionGetter;
import gymlife.utility.FontLoader;
import gymlife.view.api.GamePanel;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.awt.Image;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import java.awt.event.ActionListener;
import java.io.Serial;

/**
 * Abstract class with GUI for binary choices, can be extended by a class to have custom text and button actions.
 */
public abstract class ChoicePanel extends GamePanel {

    @Serial
    private static final long serialVersionUID = 4800875570629897789L;
    private final transient DimensionGetter dimensionGetter;
    private final JPanel infoPanel;
    private final JPanel buttonsPanel;
    private final JLabel imageLabel;
    private final JLabel descLabel;
    private final JButton acceptButton;
    private final JButton declineButton;


    /**
     * Standard constructor for class EncounterView.
     *
     * @param dimensionGetter dimensionGetter inherited by the class that instantiates this.
     */
    public ChoicePanel(final DimensionGetter dimensionGetter) {
        final Border border = new LineBorder(Color.WHITE, 10);
        this.dimensionGetter = dimensionGetter;
        this.setSize(dimensionGetter.getScenarioDimension());

        final Color lightBlue = new Color(168, 228, 207);
        final Color myRed = new Color(204, 51, 46);
        final Color myGreen = new Color(100, 204, 46);

        this.setBackground(lightBlue);
        this.setBorder(new LineBorder(lightBlue, 10));



        final BorderLayout layout = new BorderLayout();
        layout.setHgap(10);
        this.setLayout(layout);
        final GridLayout gl = new GridLayout(2, 1);
        gl.setVgap(10);

        this.infoPanel = new JPanel(gl);
        infoPanel.setPreferredSize(dimensionGetter.getChoiceImageDimension());
        infoPanel.setBackground(lightBlue);

        this.imageLabel = new JLabel();
        this.descLabel = new JLabel("description.", SwingConstants.CENTER);
        descLabel.setFont(FontLoader.getCustomFont(dimensionGetter.getBigFontSize()));
        descLabel.setOpaque(true);
        descLabel.setBackground(lightBlue);
        descLabel.setBorder(new LineBorder(Color.WHITE, 10));

        infoPanel.add(imageLabel);
        infoPanel.add(descLabel);

        this.buttonsPanel = new JPanel(gl);
        buttonsPanel.setPreferredSize(dimensionGetter.getChoiceButtonDimension());
        buttonsPanel.setBackground(lightBlue);

        this.acceptButton = new JButton("accept");
        this.declineButton = new JButton("decline");
        acceptButton.setFont(FontLoader.getCustomFont(dimensionGetter.getBigFontSize()));
        acceptButton.setOpaque(true);
        acceptButton.setBackground(myGreen);
        acceptButton.setForeground(Color.WHITE);
        declineButton.setFont(FontLoader.getCustomFont(dimensionGetter.getBigFontSize()));
        declineButton.setOpaque(true);
        declineButton.setBackground(myRed);
        declineButton.setForeground(Color.WHITE);
        buttonsPanel.add(acceptButton);
        buttonsPanel.add(declineButton);


        this.add(infoPanel, BorderLayout.CENTER);
        this.add(buttonsPanel, BorderLayout.EAST);
    }

    /**
     * Loads the image from the path given, resized according to the DimensionGetter.
     * @param path String representing the path from which the image should be taken.
     * @return the ImageIcon of said path.
     */
    public ImageIcon loadResizedImage(final String path) {
        final Image imgToResize = new ImageIcon(ClassLoader.getSystemResource(path)).getImage();
        return new ImageIcon(imgToResize.getScaledInstance(
                dimensionGetter.getChoiceImageDimension().width,
                dimensionGetter.getChoiceImageDimension().height / 2,
                Image.SCALE_SMOOTH));
    }

    /**
     * Resizes components based on the size of the ScenarioPanel.
     */
    @Override
    public void resizeComponents() {
        infoPanel.setPreferredSize(dimensionGetter.getChoiceImageDimension());
        buttonsPanel.setPreferredSize(dimensionGetter.getChoiceButtonDimension());
        imageLabel.setIcon(loadResizedImage(getImagePath()));
        descLabel.setFont(FontLoader.getCustomFont(dimensionGetter.getBigFontSize()));
        descLabel.setText("<html>" + getDescription() + "</html>");
        acceptButton.setText("<html>" + getAcceptButtonText() + "</html>");
        acceptButton.setFont(FontLoader.getCustomFont(dimensionGetter.getBigFontSize()));
        declineButton.setText("<html>" + getDeclineButtonText() + "</html>");
        declineButton.setFont(FontLoader.getCustomFont(dimensionGetter.getBigFontSize()));
        declineButton.setOpaque(true);
        acceptButton.setOpaque(true);
    }

    /**
     * Method to set the action to be performed when clicking the accept button.
     * @param acceptAction actionListener to add to the button.
     */
    protected void setAcceptAction(final ActionListener acceptAction) {
        acceptButton.addActionListener(acceptAction);
        acceptButton.setOpaque(true);
    }

    /**
     * Method to set the action to be performed when clicking the decline button.
     * @param declineAction actionListener to add to the button.
     */
    protected void setDeclineAction(final ActionListener declineAction) {
        declineButton.addActionListener(declineAction);
        declineButton.setOpaque(true);
    }

    abstract String getDescription();

    abstract String getAcceptButtonText();

    abstract String getDeclineButtonText();

    abstract String getImagePath();

}
