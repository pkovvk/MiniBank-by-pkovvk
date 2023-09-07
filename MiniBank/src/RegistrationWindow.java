import java.util.Scanner;

public class RegistrationWindow {

    public void toRegWindow() {
        BankMenu bank = new BankMenu();
        ClientBase data = new ClientBase();
        Scanner scan = new Scanner(System.in);

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
        bank.getBankMenu();
    }
}