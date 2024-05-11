package gymlife.application;

import gymlife.controller.ControllerImpl;
import gymlife.model.Minigame.MinigameManager;
import gymlife.utility.GameDifficulty;
import gymlife.utility.MinigameType;
import gymlife.view.minigame.MinigameViewImpl;
import javax.swing.*;
import java.awt.*;

public final class GameLauncher {

    private GameLauncher() {
    }

    public static void main(final String[] args) {

        ControllerImpl controller = new ControllerImpl(GameDifficulty.EASY);
        MinigameManager minigameManager = new MinigameManager();

        controller.setMinigameManager(minigameManager);
        minigameManager.setCurrentMinigame(MinigameType.BENCH_PRESS);


        JFrame mainFrame = new JFrame("Gym Life");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        JPanel mainPanel = new JPanel();

        mainFrame.setLayout(new FlowLayout());

        mainFrame.setSize(screenSize.width/2, screenSize.height/2);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel.add(new MinigameViewImpl(controller));

       // TimerView timerView = new TimerView(controller);

       // mainPanel.add(timerView);

       // timerView.setPreferredSize(new Dimension(200, 100));

        //timerView.setVisible(true);



        //mainPanel.setPreferredSize(new Dimension(screenSize.width/2, screenSize.height/2));

        mainFrame.add(mainPanel);
        mainPanel.setVisible(true);
        mainFrame.setVisible(true);
    }

}