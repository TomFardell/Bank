public class Bank {

    static final int MAX_ACCOUNTS = 1000;

    private Account[] accounts;

    public Bank() {
        accounts = new Account[MAX_ACCOUNTS];
    }

    public Account getAccount(int number) {
        for (int i = 0; i < MAX_ACCOUNTS; i++) {
            if (number == accounts[i].getNumber()) {
                return accounts[i];
            }
        }

        return null;
    }
}