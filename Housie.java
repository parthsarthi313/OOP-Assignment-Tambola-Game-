import java.util.*;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Housie {
    ArrayList<Player> players;
    Moderator moderator;

    public void startGame(int playerCount) {
        GameData game = new GameData();
        game.numberOfPlayers = playerCount;
        players = new ArrayList<>();
        for (int i = 0; i < game.numberOfPlayers; i++) {
            Player temp = new Player(i, game);
            players.add(temp);
            game.cp.add(false);
        }
        Moderator moderator = new Moderator(game);
        moderator.start();
        for (int i = 0; i < game.numberOfPlayers; i++) {
            players.get(i).start();
        }
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GameGUI(game, moderator, players);
            }
        });
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the Number of players: "));
        Housie h = new Housie();
        h.startGame(n);
        sc.close();
    }
}
