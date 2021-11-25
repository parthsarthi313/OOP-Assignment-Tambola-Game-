public class BottomLine {
    public int conditionID = 3;

    public boolean checkCondition(Ticket t) {
        for (int j = 0; j < 5; j++) {
            if (t.ticket.get(2).get(j) != -1)
                return false;
        }
        return true;
    }
}
