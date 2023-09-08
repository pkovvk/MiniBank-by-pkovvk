import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        int answer;
        Listener listener = new Listener();
        currentClientCode current = new currentClientCode();
        BankMenu bank = new BankMenu();
        Scanner in = new Scanner(System.in);

        if (current.isAuthorized()) {
            Client.currentName = current.getSavedName();
            Client.currentPass = current.getSavedPass();
            Client.currentBalance = current.getSavedBalance();
            bank.getBankMenu();
        } else {
            System.out.println("------------Mini Bank by pkovvk----------");
            System.out.println("Выберите действие: ");
            System.out.println("1: Войти в аккаунт");
            System.out.println("2: Зарегестрироваться");
            System.out.println("3: Закрыть приложение");
            System.out.println("-----------------------------------------");
            System.out.print("Ваш ответ: ");
            answer = in.nextInt();
            listener.noreglisten(answer);
        }
    }

    public static void getMain() throws IOException {
        main(null);
    }
}