public class Main21 {

    public static void main(String[] args) throws InterruptedException {
        Bank bank = new Bank();
        Account firstClient = new Account("1", 100000);
        Account secondClient = new Account("2", 100000);
        Account thirdClient = new Account("3", 100000);
        Account fourthClient = new Account("4", 100000);

        bank.addAccountToBank(firstClient);
        bank.addAccountToBank(secondClient);
        bank.addAccountToBank(thirdClient);
        bank.addAccountToBank(fourthClient);


        Thread threadOne =
                new Thread(
                        () -> {
                            bank.transfer(firstClient.getAccNumber(), secondClient.getAccNumber(), 51000);
                            bank.transfer(secondClient.getAccNumber(), firstClient.getAccNumber(), 6000);
                            bank.transfer(secondClient.getAccNumber(), firstClient.getAccNumber(), 1300);
                            bank.transfer(fourthClient.getAccNumber(), thirdClient.getAccNumber(), 6500);
                            bank.transfer(thirdClient.getAccNumber(), fourthClient.getAccNumber(), 8150);
                            bank.transfer(fourthClient.getAccNumber(), thirdClient.getAccNumber(), 58000);
                            bank.transfer(thirdClient.getAccNumber(), fourthClient.getAccNumber(), 11250);
                            System.out.println(Thread.currentThread().getName());
                        });

        Thread threadTwo =
                new Thread(
                        () -> {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            bank.transfer(fourthClient.getAccNumber(), thirdClient.getAccNumber(), 58700);
                            bank.transfer(thirdClient.getAccNumber(), fourthClient.getAccNumber(), 11250);
                            System.out.println(Thread.currentThread().getName());
                        });

        threadOne.start();
        threadTwo.start();


        threadOne.join();
        threadTwo.join();

        bank.getBalance(firstClient.getAccNumber());
        bank.getBalance(secondClient.getAccNumber());
        bank.getBalance(thirdClient.getAccNumber());
        bank.getBalance(fourthClient.getAccNumber());
        firstClient.getStatus();
        secondClient.getStatus();


    }
}


