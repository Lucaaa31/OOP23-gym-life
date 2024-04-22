package gymlife.view;

import javax.swing.JButton;
import javax.swing.JPanel;

import gymlife.controller.api.Controller;
import gymlife.utility.BenchDifficulty;

public class DifficultyView extends JPanel{
    private Controller controller;
    private JButton easyButton;
    private JButton normalButton;
    private JButton hardButton;

    
    public DifficultyView(final Controller controller) {
        this.controller = controller;
        this.easyButton = new JButton("Easy");
        this.normalButton = new JButton("Normal");
        this.hardButton = new JButton("Hard");

        this.add(easyButton);
        this.add(normalButton);
        this.add(hardButton);

        easyButton.addActionListener(e -> {
            controller.setDifficulty(BenchDifficulty.EASY);
        });

        normalButton.addActionListener(e -> {
            controller.setDifficulty(BenchDifficulty.MEDIUM);
        });

        hardButton.addActionListener(e -> {
            controller.setDifficulty(BenchDifficulty.HARD);
        });
    }



}
