import java.util.*;
import javax.swing.*;

public class GameGUI {
    Moderator moderator;
    ArrayList<Player> players;
    GameData gameData;

    GameGUI(GameData gameData, Moderator moderator, ArrayList<Player> players) {
        this.gameData = gameData;
        this.moderator = moderator;
        this.players = players;

        JFrame mainGameFrame = new JFrame("Housie");
        mainGameFrame.setSize(800, 800);
        mainGameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainGameFrame.setLayout(new BoxLayout(mainGameFrame.getContentPane(), BoxLayout.Y_AXIS));
        mainGameFrame.add(gameData.label);

        String s = "Selected Condition(s): ";
        if (gameData.seletedConditions.contains(0))
            s += "FullHouse ";
        if (gameData.seletedConditions.contains(1))
            s += "TopLine ";
        if (gameData.seletedConditions.contains(2))
            s += "MiddleLine ";
        if (gameData.seletedConditions.contains(3))
            s += "BottomLine ";
        if (gameData.seletedConditions.contains(4))
            s += "EarlyFive ";
        JLabel selectedCond = new JLabel(s);
        mainGameFrame.add(selectedCond);
        for (int i = 0; i < gameData.numberOfPlayers; i++) {
            mainGameFrame.add(players.get(i).lblPlayer);
            mainGameFrame.add(players.get(i).getPlayerTicketPanel());
        }
        mainGameFrame.setVisible(true);
    }
}
