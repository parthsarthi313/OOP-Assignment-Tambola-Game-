import java.util.*;
import java.lang.Math;

public class Ticket {
    public ArrayList<ArrayList<Integer>> ticket = new ArrayList<ArrayList<Integer>>();
    private HashSet<Integer> uniqueNumbers = new HashSet<Integer>();

    Ticket() {
        ticket.add(getRow());
        ticket.add(getRow());
        ticket.add(getRow());
    }

    public ArrayList<Integer> getRow() {
        ArrayList<Integer> row = new ArrayList<Integer>();
        for (int i = 0; i < 5; i++) {
            int temp = getRandomNumber(1, 91);
            while (uniqueNumbers.contains(temp))
                temp = getRandomNumber(1, 91);
            uniqueNumbers.add(temp);
            row.add(temp);
        }
        return row;
    }

    public int getRandomNumber(int a, int b) {
        return (int) (Math.random() * (b - a) + a);
    }
}