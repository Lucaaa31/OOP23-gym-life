package gymlife.view;

import gymlife.controller.api.Controller;
import gymlife.view.api.GamePanel;

import javax.swing.*;
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
        this.setBackground(Color.BLACK);

        final BorderLayout layout = new BorderLayout();
        layout.setHgap(20);
        this.setLayout(layout);
        final GridLayout gl = new GridLayout(2, 1);
        gl.setVgap(20);

        this.infoPanel = new JPanel(gl);
        infoPanel.setPreferredSize(dimensionGetter.getEncounterImageDimension());
        infoPanel.setBackground(Color.BLACK);

        this.imageLabel = new JLabel();
        this.descLabel = new JLabel("description.", SwingConstants.CENTER);
        descLabel.setOpaque(true);
        descLabel.setBackground(new Color(168, 228, 207));

        infoPanel.add(imageLabel);
        infoPanel.add(descLabel);

        this.buttonsPanel = new JPanel(gl);
        buttonsPanel.setPreferredSize(dimensionGetter.getEncounterButtonDimension());
        buttonsPanel.setBackground(Color.BLACK);

        this.acceptButton = new JButton("accept");
        this.declineButton = new JButton("decline");
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
            descLabel.setText(controller.getCurrentEncounter().description());
            acceptButton.setText("Accept\n" + controller.getCurrentEncounter().acceptCase());
            declineButton.setText("Decline\n" + controller.getCurrentEncounter().denyCase());
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
