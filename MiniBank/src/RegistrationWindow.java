import java.io.IOException;
import java.util.Scanner;

public class RegistrationWindow {
    currentClientCode current = new currentClientCode();
    BankMenu bank = new BankMenu();
    ClientBase data = new ClientBase();

    public void toRegWindow() throws IOException {
        Scanner scan = new Scanner(System.in);

        if (Client.currentName == null) {
            System.out.print("Введите имя: ");
            Client.currentName = scan.nextLine();
            data.checkRegNameValidation(Client.currentName);
            if (data.checkName(Client.currentName)) {
                data.userAlreadyUsed();
            }
        }
        if (Client.currentPass == null) {
            System.out.print("Введите пароль: ");
            Client.currentPass = scan.nextLine();
            data.checkRegPassValidation(Client.currentPass);
        }
        System.out.println("Успешная регистрация!");
        current.setAuth(true);
        Client.currentBalance = 0;
        current.saveClient(Client.currentName, Client.currentPass, Client.currentBalance);
        bank.getBankMenu();
    }
}