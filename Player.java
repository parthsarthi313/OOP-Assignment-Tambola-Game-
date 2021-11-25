import java.util.*;
import java.awt.*;
import javax.swing.*;

public class Player extends Thread {
    public Ticket tic;
    public int playerID;
    private GameData gameData;
    private JPanel playerTicketPanel;
    private JButton[] btnOnTicket;
    JLabel lblPlayer;

    Player(int playerID, GameData gameData) {
        this.gameData = gameData;
        this.playerID = playerID;
        tic = new Ticket();
        System.out.println("Player " + playerID + "'s ticket");
        System.out.println(tic.ticket);
        lblPlayer = new JLabel("Player " + playerID, JLabel.CENTER);
        playerTicketPanel = new JPanel();
        playerTicketPanel.setLayout(new GridLayout(3, 5));
        btnOnTicket = new JButton[15];

        for (int i = 0; i < 15; i++) {
            btnOnTicket[i] = new JButton(String.valueOf(tic.ticket.get((int) Math.floor(i / 5)).get(i % 5)));
            btnOnTicket[i].setEnabled(false);
            playerTicketPanel.add(btnOnTicket[i]);
        }
        playerTicketPanel.setBounds(0, playerID * 50, 1000, 50);
    }

    public boolean checkNumber(int number) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                if (tic.ticket.get(i).get(j) == number) {
                    tic.ticket.get(i).set(j, -1);
                    System.out.println(number + " found in Player " + playerID + "'s ticket");
                    this.btnOnTicket[5 * i + j].setBackground(Color.GREEN);
                    return true;
                }
            }
        }
        return false;
    }

    public void completeConditions(ArrayList<Integer> conditions) {
        for (int i = 0; i < conditions.size(); i++) {
            if (gameData.seletedConditions.contains(conditions.get(i))) {
                gameData.conditionCount--;
                gameData.seletedConditions.remove(conditions.get(i));
                String s = "";
                if (conditions.get(i) == 0)
                    s = "FullHouse";
                if (conditions.get(i) == 1)
                    s = "TopLine";
                if (conditions.get(i) == 2)
                    s = "MiddleLine";
                if (conditions.get(i) == 3)
                    s = "BottomLine";
                if (conditions.get(i) == 4)
                    s = "EarlyFive";
                System.out.println("Player " + playerID + " Has Completed " + s);
                gameData.label.setText("Player " + playerID + " Has Completed " + s);
                lblPlayer.setText(lblPlayer.getText() + " " + s);
            }
        }
    }

    public ArrayList<Integer> checkWinningConditions() {
        ArrayList<Integer> temp = new ArrayList<Integer>();
        FullHouse a = new FullHouse();
        TopLine b = new TopLine();
        MiddleLine c = new MiddleLine();
        BottomLine d = new BottomLine();
        EarlyFive e = new EarlyFive();
        if (a.checkCondition(tic))
            temp.add(0);
        if (b.checkCondition(tic))
            temp.add(1);
        if (c.checkCondition(tic))
            temp.add(2);
        if (d.checkCondition(tic))
            temp.add(3);
        if (e.checkCondition(tic))
            temp.add(4);
        return temp;
    }

    public void run() {
        synchronized (gameData.lock1) {
            while (gameData.conditionCount > 0) {
                while (!gameData.noAnnouncedFlag || gameData.cp.get(playerID)) {
                    try {
                        gameData.lock1.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (gameData.conditionCount > 0) {
                    if (checkNumber(gameData.announcedNumber)) {
                        completeConditions(checkWinningConditions());
                    }
                    try {
                        sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                gameData.cp.set(playerID, true);
                gameData.checkCount++;
                gameData.lock1.notifyAll();
            }
        }
    }

    public JPanel getPlayerTicketPanel() {
        return playerTicketPanel;
    }

}
