import java.io.IOException;
import java.util.Scanner;

public class RegistrationWindow {

    public void toRegWindow() throws IOException {
        BankMenu bank = new BankMenu();
        ClientBase data = new ClientBase();
        Scanner scan = new Scanner(System.in);
        currentClientCode current = new currentClientCode();

        if (Client.currentName == null) {
            System.out.print("Введите имя: ");
            Client.currentName = scan.nextLine();
            data.checkRegNameValidation(Client.currentName);
            if (data.checkName(Client.currentName)) {
                System.out.println("Такой пользователь уже есть! Пожалуйста, выберите другое имя!");
                Client.currentName = null;
                toRegWindow();
            }
        }
        if (Client.currentPass == null) {
            System.out.print("Введите пароль: ");
            Client.currentPass = scan.nextLine();
            data.checkRegPassValidation(Client.currentPass);
        }
        System.out.println("Успешная регистрация!");
        data.saveNewClientToBase(Client.currentName, Client.currentPass, Client.currentBalance);
        current.setAuth(true);
        bank.getBankMenu();
    }
}