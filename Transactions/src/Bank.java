import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Bank {

    private static final ConcurrentMap<String, Account> accounts = new ConcurrentHashMap<>();
    private final Random random = new Random();

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount) throws InterruptedException {
        Thread.sleep(1000);
        return random.nextBoolean();
    }

    public void addAccountToBank(Account account) {
        accounts.put(account.getAccNumber(), account);
    }

    public void transfer(String fromAccountNum, String toAccountNum, long amount) {
        Account fromAccount = accounts.get(fromAccountNum);
        Account toAccount = accounts.get(toAccountNum);
        int fromNum = Integer.parseInt(fromAccountNum);
        int toNum = Integer.parseInt(toAccountNum);


        if (fromNum > toNum) {
            synchronized (toAccount) {
                synchronized (fromAccount) {
                    transfer(amount, fromAccount, toAccount);
                }
            }
        } else {
            synchronized (fromAccount) {
                synchronized (toAccount) {
                    transfer(amount, fromAccount, toAccount);

                }
            }
        }
    }

    private void transfer(long amount, Account fromAccount, Account toAccount) {
        if (isEnoughMoney(fromAccount.getMoney(), amount)) {
            long verificationLimit = 50000;
            boolean check = false;
            if (amount > verificationLimit) {
                try {
                    {
                        check = isFraud(fromAccount.getAccNumber(), toAccount.getAccNumber(), amount);
                    }
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
                if (check) {

                    fromAccount.blockedAccount();
                    toAccount.blockedAccount();
                } else {

                    toAccount.setMoney(toAccount.getMoney() + amount);
                    fromAccount.setMoney(fromAccount.getMoney() - amount);
                }

            } else {

                toAccount.setMoney(toAccount.getMoney() + amount);
                fromAccount.setMoney(fromAccount.getMoney() - amount);

            }
        }
    }


    public boolean isEnoughMoney(long fromAccountMoney, long amount) {
        return fromAccountMoney >= amount;
    }

    public long getBalance(String accountNum) {

        System.out.println("Сумма на счету: № " + accountNum + " - " + accounts.get(accountNum).getMoney() + " руб." + "|статус болкировки: " + accounts.get(accountNum).getStatus());
        return accounts.get(accountNum).getMoney();
    }

}