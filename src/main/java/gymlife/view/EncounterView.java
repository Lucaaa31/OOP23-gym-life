package gymlife.view;

import gymlife.controller.api.Controller;
import gymlife.view.api.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

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
        this.setLayout(new BorderLayout());

        this.infoPanel = new JPanel(new GridLayout(2, 1));
        infoPanel.setPreferredSize(dimensionGetter.getEncounterImageDimension());
        infoPanel.setBackground(new Color(168, 228, 207));

        this.imageLabel = new JLabel();
        this.descLabel = new JLabel("description.");

        infoPanel.add(imageLabel);
        infoPanel.add(descLabel);

        this.buttonsPanel = new JPanel(new GridLayout(2, 1));
        buttonsPanel.setPreferredSize(dimensionGetter.getEncounterButtonDimension());

        this.acceptButton = new JButton("accept");
        this.declineButton = new JButton("decline");
        buttonsPanel.add(acceptButton);
        buttonsPanel.add(declineButton);


        this.add(infoPanel, BorderLayout.CENTER);
        this.add(buttonsPanel, BorderLayout.EAST);


    }

    @Override
    public void resizeComponents() {
        infoPanel.setPreferredSize(dimensionGetter.getEncounterImageDimension());
        buttonsPanel.setPreferredSize(dimensionGetter.getEncounterButtonDimension());

    }

    @Override
    public String getPanelName() {
        return "encounter";
    }
}
