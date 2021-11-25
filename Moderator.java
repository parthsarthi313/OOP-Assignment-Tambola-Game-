import java.util.*;

import java.lang.Math;

public class Moderator extends Thread {
    private GameData gameData;

    Moderator(GameData gameData) {
        this.gameData = gameData;
        gameData.conditionCount = getRandomNumber(1, 5);
        gameData.seletedConditions = selectRandomConditions();
        if (gameData.seletedConditions.contains(0))
            System.out.println("FullHouse was selected by Moderator");
        if (gameData.seletedConditions.contains(1))
            System.out.println("TopLine was selected by Moderator");
        if (gameData.seletedConditions.contains(2))
            System.out.println("MiddleLine was selected by Moderator");
        if (gameData.seletedConditions.contains(3))
            System.out.println("BottomLine was selected by Moderator");
        if (gameData.seletedConditions.contains(4))
            System.out.println("EarlyFive was selected by Moderator");
    }

    public HashSet<Integer> selectRandomConditions() {
        HashSet<Integer> temp = new HashSet<>();
        for (int i = 0; i < gameData.conditionCount; i++) {
            int num = getRandomNumber(0, 5);
            while (temp.contains(num))
                num = getRandomNumber(0, 5);
            temp.add(num);
        }
        return temp;
    }

    public int callRandomNumber() {
        int num = getRandomNumber(0, 91);
        while (gameData.calledOutNumbers.contains(num))
            num = getRandomNumber(0, 91);
        gameData.calledOutNumbers.add(num);
        gameData.label.setText("Moderator called out " + num);
        System.out.println("Moderator called out " + num);
        return num;
    }

    public void run() {
        synchronized (gameData.lock1) {
            while (gameData.conditionCount > 0) {
                gameData.noAnnouncedFlag = false;
                for (int i = 0; i < gameData.numberOfPlayers; i++) {
                    gameData.cp.set(i, false);
                }
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                gameData.checkCount = 0;
                gameData.announcedNumber = callRandomNumber();
                gameData.noAnnouncedFlag = true;
                gameData.lock1.notifyAll();
                while (gameData.checkCount != gameData.numberOfPlayers) {
                    try {
                        gameData.lock1.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("Game Ends");
            gameData.label.setText("Game Ends");
        }
    }

    public int getRandomNumber(int a, int b) {
        return (int) (Math.random() * (b - a) + a);
    }
}
