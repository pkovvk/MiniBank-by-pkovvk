import java.io.IOException;

public class AuthWindow {
    ClientBase data = new ClientBase();
    currentClientCode current = new currentClientCode();
    BankMenu bank = new BankMenu();

    public void toAuthWindow() throws IOException {

        if (Client.currentName == null) {
            if (!data.interName()) {
                toAuthWindow();
            }
        }
        if (Client.currentPass == null) {
            if (!data.interPass()) {
                toAuthWindow();
            }
        }

        if (data.checkName(Client.currentName)) {
            if (data.checkPass(Client.currentPass)) {
                System.out.println("Успешная авторизация!");
                current.setAuth(true);
                data.updateBalance();
                System.out.println(Client.currentBalance);
                current.saveClient();
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
}