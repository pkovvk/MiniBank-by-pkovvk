import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ClientBase {
    Client client1 = new Client("bob", "1111", 0);
    Client client2 = new Client("david", "1234", 100);
    Client client3 = new Client("alice", "2020", 120);

    int i;


    public boolean checkName(String username) {
        List<String> clientNames = new LinkedList<>();
        clientNames.add(0, client1.getName());
        clientNames.add(1, client2.getName());
        clientNames.add(2, client3.getName());

        while (i <= clientNames.size()-1) {
            if (clientNames.get(i).equals(username)) {
                return true;
            } else if (i == clientNames.size()-1) {
                break;
            } else {
                i++;
            }
        }
        return false;
    }

    public boolean checkPass(String password) {
        List<String> clientPass = new LinkedList<>();
        clientPass.add(0, client1.getPass());
        clientPass.add(1, client2.getPass());
        clientPass.add(2, client3.getPass());

        return clientPass.get(i).equals(password);
    }

    public void updateBalance(){
        List<Integer> clientBalance = new LinkedList<>();
        clientBalance.add(0, client1.getBalance());
        clientBalance.add(1, client2.getBalance());
        clientBalance.add(2, client3.getBalance());

        Client.currentBalance = clientBalance.get(i);
    }

    public void userNotFound(){
        RegistrationWindow reg = new RegistrationWindow();
        AuthWindow auth = new AuthWindow();
        Scanner scan = new Scanner(System.in);
        System.out.println("1: Попробовать еще раз");
        System.out.println("2: Пройти регистрацию");
        System.out.println("3: Выйти в меню");
        System.out.print("Ваш ответ: ");
        int answer = scan.nextInt();
        if (answer == 1) {
            auth.toAuthWindow();
        } else if (answer == 2) {
            System.out.println("----------------Регистрация----------------");
            reg.toRegWindow();
        } else if (answer == 3) {
            Main.getMain();
        } else {
            System.out.println("Неправильный пункт...");
            userNotFound();
        }
    }

    public void passIncorrect() {
        RegistrationWindow reg = new RegistrationWindow();
        AuthWindow auth = new AuthWindow();
        Scanner scan = new Scanner(System.in);
        System.out.println("1: Попробовать еще раз");
        System.out.println("2: Пройти регистрацию");
        System.out.println("3: Выйти в меню");
        System.out.print("Ваш ответ: ");
        int answer = scan.nextInt();
        if (answer == 1) {
            auth.toAuthWindow();
        } else if (answer == 2) {
            System.out.println("----------------Регистрация----------------");
            reg.toRegWindow();
        } else if (answer == 3) {
            Main.getMain();
        } else {
            System.out.println("Неправильный пункт...");
            passIncorrect();
        }
    }

    public void checkNameValidation(String username) {
        AuthWindow auth = new AuthWindow();
        if (username.isEmpty()) {
            System.out.println("Ошибка! Вы ввели пустое имя...");
            Client.currentName = null;
            auth.toAuthWindow();
        } else if (username.length() < 3) {
            System.out.println("Ошибка! В имени должно быть как минимум 3 символа...");
            Client.currentName = null;
            auth.toAuthWindow();
        }
    }

    public void checkPassValidation(String password) {
        AuthWindow auth = new AuthWindow();
        if (password.isEmpty()) {
            System.out.println("Ошибка! Пароль не должен быть пустым...");
            Client.currentPass = null;
            auth.toAuthWindow();
        } else if (password.length() != 4) {
            System.out.println("Ошибка! Пароль должен состоять из 4 символов!");
            Client.currentPass = null;
            auth.toAuthWindow();
        }
    }

    public void checkRegNameValidation(String username) {
        RegistrationWindow reg = new RegistrationWindow();
        if (username.isEmpty()) {
            System.out.println("Ошибка! Вы ввели пустое имя...");
            Client.currentName = null;
            reg.toRegWindow();
        } else if (username.length() < 3) {
            System.out.println("Ошибка! В имени должно быть как минимум 3 символа...");
            Client.currentName = null;
            reg.toRegWindow();
        }
    }

    public void checkRegPassValidation(String password) {
        RegistrationWindow reg = new RegistrationWindow();
        if (password.isEmpty()) {
            System.out.println("Ошибка! Пароль не должен быть пустым...");
            Client.currentPass = null;
            reg.toRegWindow();
        } else if (password.length() != 4) {
            System.out.println("Ошибка! Пароль должен состоять из 4 символов!");
            Client.currentPass = null;
            reg.toRegWindow();
        }
    }
}