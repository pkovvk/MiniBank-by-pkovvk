import java.io.IOException;
import java.util.Scanner;

public class AuthWindow {
    ClientBase data = new ClientBase();
    currentClientCode current = new currentClientCode();

    public void toAuthWindow() throws IOException {
        BankMenu bank = new BankMenu();

        if (Client.currentName == null) {
            interName();
        }
        if (Client.currentPass == null) {
            interPass();
        }

        if (data.checkName(Client.currentName)) {
            if (data.checkPass(Client.currentPass)) {
                System.out.println("Успешная авторизация!");
                current.setAuth(true);
                data.updateBalance();
                System.out.println(Client.currentBalance);
                current.saveClient(Client.currentName, Client.currentPass, Client.currentBalance);
                bank.getBankMenu();
            } else {
                System.out.println("Ошибка! Пароль введен не правильно...");
                data.passIncorrect();
            }
        } else {
            System.out.println("Ошибка! Имя пользователя не найдено...");
            data.userNotFound();
        }
    }

    public void interName() throws IOException {
        Scanner scan = new Scanner(System.in);

        System.out.print("Введите имя: ");
        Client.currentName = scan.nextLine();
        data.checkNameValidation(Client.currentName);
        if (!data.checkName(Client.currentName)) {
            System.out.println("Пользователь с таким именем не найден...");
            Client.currentName = null;
            data.userNotFound();
        }
    }

    public void interPass() throws IOException {
        Scanner scan = new Scanner(System.in);

        System.out.print("Введите пароль: ");
        Client.currentPass = scan.nextLine();
        data.checkPassValidation(Client.currentPass);
        if (!data.checkPass(Client.currentPass)) {
            System.out.println("Пароль введен не правильно...");
            Client.currentPass = null;
            data.passIncorrect();
        }
    }
}