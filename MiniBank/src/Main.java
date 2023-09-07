import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int answer;
        Listener listener = new Listener();
        Scanner in = new Scanner(System.in);

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

    public static void getMain() {
        main(null);
    }
}