import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Listener listener = new Listener();
        currentClientCode current = new currentClientCode();
        BankMenu bank = new BankMenu();

        if (current.isAuthorized()) {
            bank.getBankMenu();
        } else {
            System.out.println("------------Mini Bank by pkovvk----------");
            System.out.println("Выберите действие: ");
            System.out.println("1: Войти в аккаунт");
            System.out.println("2: Зарегестрироваться");
            System.out.println("3: Закрыть приложение");
            System.out.println("-----------------------------------------");
            listener.noreglisten(listener.yourAnswer());
        }
    }

    public static void getMain() throws IOException {
        String[] s = null;
        main(s);
    }
}