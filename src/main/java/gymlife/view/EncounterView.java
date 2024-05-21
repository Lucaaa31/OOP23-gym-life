package gymlife.view;

import gymlife.controller.api.Controller;
import gymlife.utility.FontLoader;
import gymlife.view.api.GamePanel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Locale;

public class EncounterView extends GamePanel {

    private final transient Controller controller;
    private final transient DimensionGetter dimensionGetter;
    private final JPanel infoPanel;
    private final JPanel buttonsPanel;
    private final JLabel imageLabel;
    private final JLabel descLabel;
    private final JButton acceptButton;
    private final JButton declineButton;

    public EncounterView(final Controller controller, final DimensionGetter dimensionGetter) {
        this.controller = controller;
        this.dimensionGetter = dimensionGetter;
        this.setSize(dimensionGetter.getScenarioDimension());

        final Color lightBlue = new Color(168, 228, 207);

        this.setBackground(lightBlue);
        this.setBorder(new LineBorder(lightBlue, 10));

        FontLoader.loadFont();

        final BorderLayout layout = new BorderLayout();
        layout.setHgap(10);
        this.setLayout(layout);
        final GridLayout gl = new GridLayout(2, 1);
        gl.setVgap(10);

        this.infoPanel = new JPanel(gl);
        infoPanel.setPreferredSize(dimensionGetter.getEncounterImageDimension());
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
        buttonsPanel.setPreferredSize(dimensionGetter.getEncounterButtonDimension());
        buttonsPanel.setBackground(lightBlue);

        this.acceptButton = new JButton("accept");
        this.declineButton = new JButton("decline");
        acceptButton.setFont(FontLoader.getCustomFont(dimensionGetter.getBigFontSize()));
        acceptButton.setOpaque(true);
        acceptButton.setBackground(new Color(100, 204, 46));
        acceptButton.setForeground(Color.WHITE);
        declineButton.setFont(FontLoader.getCustomFont(dimensionGetter.getBigFontSize()));
        declineButton.setOpaque(true);
        declineButton.setBackground(new Color(204, 51, 46));
        declineButton.setForeground(Color.WHITE);
        buttonsPanel.add(acceptButton);
        buttonsPanel.add(declineButton);

        acceptButton.addActionListener((event) -> {
            controller.resolveEncounter(true);
            EncounterView.super.transferFocus();
        });
        declineButton.addActionListener((event) -> {
            controller.resolveEncounter(false);
            EncounterView.super.transferFocus();
        });


        this.add(infoPanel, BorderLayout.CENTER);
        this.add(buttonsPanel, BorderLayout.EAST);


    }

    @Override
    public void resizeComponents() {
        infoPanel.setPreferredSize(dimensionGetter.getEncounterImageDimension());
        buttonsPanel.setPreferredSize(dimensionGetter.getEncounterButtonDimension());
        if(controller.getCurrentEncounter() != null) {
            imageLabel.setIcon(loadResizedImage());
            descLabel.setFont(FontLoader.getCustomFont(dimensionGetter.getBigFontSize()));
            descLabel.setText(
                    "<html><h1>RANDOM ENCOUNTER</h1><br>" + controller.getCurrentEncounter().description() + "</html>");
            acceptButton.setText("<html>ACCEPT<br>" + controller.getCurrentEncounter().acceptCase() + "</html>");
            declineButton.setText("<html>DECLINE<br>" + controller.getCurrentEncounter().denyCase() + "</html>");
        }
    }

    private ImageIcon loadResizedImage() {
        final String path = "images/randomEncounters/" +
                controller.getCurrentEncounter().name().toLowerCase(Locale.ROOT) +
                ".png";
        final Image imgToResize = new ImageIcon(ClassLoader.getSystemResource(path)).getImage();
        return new ImageIcon(imgToResize.getScaledInstance(
                dimensionGetter.getEncounterImageDimension().width,
                dimensionGetter.getEncounterImageDimension().height / 2,
                Image.SCALE_SMOOTH));
    }

    @Override
    public String getPanelName() {
        return "encounter";
    }
}
