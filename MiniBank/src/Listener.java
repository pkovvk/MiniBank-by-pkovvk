import java.io.IOException;
import java.util.Scanner;

public class Listener {
    BankMenu bank = new BankMenu();
    AuthWindow auth = new AuthWindow();
    RegistrationWindow register = new RegistrationWindow();
    currentClientCode current = new currentClientCode();
    ClientBase data = new ClientBase();
    boolean access = false;

    public void noreglisten(int answer) throws IOException {
        if (answer == 1) {
            Scanner scan = new Scanner(System.in);
            System.out.println("---------------Авторизация----------------");
            if (!current.isSavedClientEmpty()) {
                System.out.println("1: Войти в существующий аккаунт - @" + current.getSavedName());
                System.out.println("2: Войти в другой аккаунт");
                System.out.println("3: Выйти в меню");
                System.out.print("Ваш ответ: ");
                int ans = scan.nextInt();
                if (ans == 1) {
                    if (data.checkName(current.getSavedName())) {
                        if (data.checkPass(current.getSavedPass())) {
                            current.updateClientFromFile();
                            auth.toAuthWindow();
                        }
                    } else {
                        System.out.println("Ошибка! Пожалуйста, войдите в систему повторно!");
                        auth.toAuthWindow();
                    }
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
            System.out.println("До свидания!");
            System.exit(1);
        } else {
            System.out.println("Неправильный пункт..");
            noreglisten(yourAnswer());
        }
    }

    public void reglisten(int answer) throws IOException {
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
            System.out.println("----------------Настройки----------------");
            System.out.println("Выберите действие: ");
            System.out.println("1: Информация по аккаунту");
            System.out.println("2: Выйти из аккаунта");
            System.out.println("3: Удалить аккаунт");
            System.out.println("4: Выйти в меню");
            System.out.println("-----------------------------------------");
            settingsListen(yourAnswer());
        } else if (answer == 5) {
            System.out.println("До свидания, " + Client.currentName + ".");
            current.saveClient();
            data.updateClientOnBase();
            System.exit(1);
        } else {
            System.out.println("Вы выбрали не правильный пункт...");
            bank.getBankMenu();
        }
    }

    public void settingsListen(int answer) throws IOException {
        if (answer == 1) {
            System.out.println("----------------Информация----------------");
            System.out.println("1: Логин - @" + Client.currentName);
            System.out.println("2: Пароль - " + data.secureGetPass(access));
            System.out.println();
            System.out.println("Выберите действие:");
            if (!access) {
                System.out.println("1: Получить полную информацию");
            } else {
                System.out.println("1: Спрятать информацию (Функция временно недоступна)");
            }
            System.out.println("2: Изменить пароль");
            System.out.println("3: Вернуться в настройки");
            System.out.println("-----------------------------------------");
            int tempans = yourAnswer();
            if (tempans == 1) {
                Scanner scan = new Scanner(System.in);
                System.out.print("Введите пароль: ");
                String temppass = scan.nextLine();
                if (temppass.equals(Client.currentPass)) {
                    System.out.println("Успешно!");
                    access = true;
                    settingsListen(1);
                }
            } else if (tempans == 2) {
                data.changeNewPass();

            } else if (tempans == 3) {
                reglisten(4);
            }
        } else if (answer == 2) {
            Scanner scan = new Scanner(System.in);
            System.out.println("Вы уверены?(1-Да/2-Нет): ");
            int tempans = scan.nextInt();
            if (tempans == 1) {
                current.saveClient();
                current.setAuth(false);
                data.updateClientOnBase();
                Client.currentName = null;
                Client.currentPass = null;
                Main.getMain();
            } else if (tempans == 2) {
                System.out.println("Возвращаем вас в настройки");
                reglisten(4);
            } else {
                System.out.println("Неправильный пункт...");
                settingsListen(2);
            }
        } else if (answer == 3) {
            Scanner scan = new Scanner(System.in);
            System.out.println("ВНИМАНИЕ!!! Действие необратимо. Если вы удалите аккаунт его невозможно будет восстановить.");
            System.out.println("Вы уверены?(1-Да/2-Нет): ");
            int tempans = scan.nextInt();
            if (tempans == 1) {
                data.deleteClientFromBase();
                Scanner scan2 = new Scanner(System.in);
                System.out.println("Ваш аккаунт успешно удален...");
                System.out.println();
                System.out.println("1 - Главное меню // 0 - Закрыть программу");
                int temp2 = scan2.nextInt();
                if (temp2 == 1) {
                    Main.getMain();
                } else if (temp2 == 0) {
                    System.out.println("До свидания, " + Client.currentName + ".");
                    current.saveClient();
                    data.updateClientOnBase();
                    System.exit(1);
                }
            } else if (tempans == 2) {
                System.out.println("Возвращаем вас в настройки");
                reglisten(4);
            } else {
                System.out.println("Неправильный пункт...");
                settingsListen(3);
            }
        } else if (answer == 4) {
            bank.getBankMenu();
        } else {
            System.out.println("Неправильный пункт...");
            reglisten(4);
        }
    }


    public int yourAnswer() {
        Scanner in = new Scanner(System.in);
        System.out.print("Ваш ответ: ");
        return in.nextInt();
    }
}