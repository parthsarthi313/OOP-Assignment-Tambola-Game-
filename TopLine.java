public class TopLine {
    public int conditionID = 1;

    public boolean checkCondition(Ticket t) {
        for (int j = 0; j < 5; j++) {
            if (t.ticket.get(0).get(j) != -1)
                return false;
        }
        return true;
    }
}
