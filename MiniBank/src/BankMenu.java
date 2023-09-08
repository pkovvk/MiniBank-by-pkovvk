import java.io.IOException;
import java.util.Scanner;

public class BankMenu {
    public void getBankMenu() throws IOException {
        Listener listener = new Listener();
        Scanner in = new Scanner(System.in);
        int answer;

        System.out.println("--------------Главное меню---------------");
        System.out.println("Выберите действие: ");
        System.out.println("1: Мой баланс");
        System.out.println("2: Внести средства");
        System.out.println("3: Снять средства");
        System.out.println("4: Смотреть мои данные");
        System.out.println("5: Выйти из аккаунта");
        System.out.println("6: Закрыть приложение");
        System.out.println("-----------------------------------------");
        System.out.print("Ваш ответ: ");
        answer = in.nextInt();
        listener.reglisten(answer);
    }

    public void bankOrExit() throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println();
        System.out.println("1 - Главное меню // 0 - Закрыть программу");
        System.out.print("Ваш ответ: ");
        int answer = scan.nextInt();
        if (answer == 1) {
            getBankMenu();
        } else if (answer == 0) {
            System.out.println("До свидания!");
            System.exit(1);
        } else {
            System.out.println("Вы ввели не правильный пункт...");
            bankOrExit();
        }
    }
}
