public class EarlyFive {
    public int conditionID = 4;

    public boolean checkCondition(Ticket t) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                if (t.ticket.get(i).get(j) == -1)
                    count++;
            }
        }
        if (count == 5)
            return true;
        return false;
    }
}
