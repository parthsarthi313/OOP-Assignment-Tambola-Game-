public class MiddleLine {
    public int conditionID = 2;

    public boolean checkCondition(Ticket t) {
        for (int j = 0; j < 5; j++) {
            if (t.ticket.get(1).get(j) != -1)
                return false;
        }
        return true;
    }
}
