import java.util.*;
import javax.swing.JLabel;

public class GameData {
    public int announcedNumber = 0;
    public int conditionCount = 0;
    public int checkCount = 0;
    public int numberOfPlayers = 0;
    public boolean noAnnouncedFlag = false;
    HashSet<Integer> calledOutNumbers = new HashSet<>();
    HashSet<Integer> seletedConditions = new HashSet<>();
    ArrayList<Boolean> cp = new ArrayList<>();
    public Object lock1 = new Object();
    public Object lock2 = new Object();
    JLabel label = new JLabel("Game Starts");
}
