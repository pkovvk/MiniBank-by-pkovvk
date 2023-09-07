public class Client {
    private String name;
    private String pass;
    private int bal;

    public static String currentName;
    public static String currentPass;
    public static int currentBalance;


    public Client(String username, String password, int balance) {
        this.name = username;
        this.pass = password;
        this.bal = balance;
    }

    public void setName(String username) {
        this.name = username;
    }
    public void setPass(String password) {
        this.pass = password;
    }
    public String getName() {
        return name;
    }
    public String getPass() {
        return pass;
    }


    public int getBalance() {
        return bal;
    }
    public void cashIn(int cash) {
        this.bal = currentBalance;
        currentBalance = currentBalance + cash;
    }
    public void cashOut(int cash) {
        if (Client.currentBalance < cash) {
            System.out.println("Недостатачно средств на балансе");
        } else {
            currentBalance = this.bal - cash;
        }
    }
}