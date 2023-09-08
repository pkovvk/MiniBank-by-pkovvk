import java.io.IOException;
import java.util.Scanner;

public class Listener {
    BankMenu bank = new BankMenu();
    AuthWindow auth = new AuthWindow();
    RegistrationWindow register = new RegistrationWindow();

    public void noreglisten(int answer) throws IOException {
        currentClientCode current = new currentClientCode();
        if (answer == 1) {
            Scanner scan = new Scanner(System.in);
            System.out.println("---------------Авторизация----------------");
            if (!current.isCurrentClientEmpty()) {
                System.out.println("1: Войти в существующий аккаунт - @" + current.getSavedName());
                System.out.println("2: Войти в другой аккаунт");
                System.out.println("3: Выйти в меню");
                System.out.println("Ваш ответ: ");
                int ans = scan.nextInt();
                if (ans == 1) {
                    Client.currentName = current.getSavedName();
                    Client.currentPass = current.getSavedPass();
                    auth.toAuthWindow();
                } else if (ans == 2) {
                    Client.currentName = null;
                    Client.currentPass = null;
                    auth.toAuthWindow();
                } else if (ans == 3) {
                    Main.getMain();
                } else {
                    System.out.println("Вы выбрали неправильный пункт!");
                }
            } else {
                auth.toAuthWindow();
            }
        } else if (answer == 2) {
            System.out.println("----------------Регистрация----------------");
            register.toRegWindow();
        } else if (answer == 3) {
            current.saveClient();
            System.out.println("До свидания!");
            System.exit(1);
        } else {
            System.out.println("Неправильный пункт..");
            Main.getMain();
        }
    }

    public void reglisten(int answer) throws IOException {
        currentClientCode current = new currentClientCode();
        Client currentclient = new Client(Client.currentName, Client.currentPass, Client.currentBalance);
        Scanner scan = new Scanner(System.in);

        if (answer == 1) {
            System.out.println("------------------Баланс-----------------");
            System.out.println("Ваш баланс: " + currentclient.getBalance() + "$");
            bank.bankOrExit();
        } else if (answer == 2) {
            System.out.println("----------------Пополнение---------------");
            System.out.print("Введите желаемую сумму: ");
            int temp = scan.nextInt();
            currentclient.cashIn(temp);
            int temp2 = currentclient.getBalance() + temp;
            System.out.println("Баланс успешно пополнен на " + temp + "$");
            System.out.println("Теперь ваш баланс: " + temp2 + "$");
            bank.bankOrExit();
        } else if (answer == 3) {
            System.out.println("--------------Снятие наличных-------------");
            System.out.print("Введите сумму снятия: ");
            int temp = scan.nextInt();
            currentclient.cashOut(temp);
            int temp2 = currentclient.getBalance() - temp;
            System.out.println("Вы успешно сняли " + temp + "$");
            System.out.println("Теперь ваш баланс: " + temp2 + "$");
            bank.bankOrExit();
        } else if (answer == 4) {
            System.out.println("----------------Информация----------------");
            System.out.println("Инфрмация по вашему аккаунту: ");
            System.out.println("1. Имя: " + currentclient.getName());
            System.out.println("2. Пароль: " + Client.currentPass);
            System.out.println("Подробнее описать себя вы сможете в новых версиях приложения...");
            bank.bankOrExit();
        } else if (answer == 5) {
            Client.currentName = null;
            Client.currentPass = null;
            current.setAuth(false);
            Main.getMain();
        } else if (answer == 6) {
            current.saveClient();
            System.out.println("До свидания!");
            System.exit(1);
        } else {
            System.out.println("Вы выбрали не правильный пункт...");
            bank.getBankMenu();
        }
    }
}
