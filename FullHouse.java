public class FullHouse implements WinningCondition {
    public int conditionID = 0;

    public boolean checkCondition(Ticket t) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                if (t.ticket.get(i).get(j) != -1)
                    return false;
            }
        }
        return true;
    }
}