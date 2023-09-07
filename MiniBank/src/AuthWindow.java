import java.util.Scanner;

public class AuthWindow {
    ClientBase data = new ClientBase();

    public void toAuthWindow() {
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
                data.updateBalance();
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

    public void interName() {
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

    public void interPass() {
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