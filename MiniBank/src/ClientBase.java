import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ClientBase {
    File dataBaseFile = new File("ClientBaseFile");

    int temp = 0;

    public boolean checkName(String username) throws IOException {
        FileReader fr = new FileReader(dataBaseFile);
        BufferedReader reader = new BufferedReader(fr);

        List<String> list = new LinkedList<>();
        String line = reader.readLine();
        while (line != null) {
            String[] splitter = line.split(",");
            list.add(splitter[0]);
            line = reader.readLine();
        }
        while (temp <= list.size() - 1) {
            if (list.get(temp).equals(username)) {
                reader.close();
                return true;
            } else {
                temp++;
            }
        }
        reader.close();
        return false;
    }

    public boolean checkPass(String password) throws IOException {
        FileReader fr = new FileReader(dataBaseFile);
        BufferedReader reader = new BufferedReader(fr);

        List<String> list = new LinkedList<>();
        String line = reader.readLine();
        while (line != null) {
            String[] splitter = line.split(",");
            list.add(splitter[1]);
            line = reader.readLine();
        }
        reader.close();
        return list.get(temp).equals(password);
    }

    public void updateBalance() throws IOException {
        FileReader fr = new FileReader(dataBaseFile);
        BufferedReader reader = new BufferedReader(fr);

        List<String> list = new LinkedList<>();
        String line = reader.readLine();
        int lineNumber = 0;
        while (line != null) {
            lineNumber++;
            String[] splitter = line.split(",");
            list.add(splitter[2]);
            line = reader.readLine();
        }

        reader.close();
        Client.currentBalance = Integer.parseInt(list.get(temp));
    }

    public void userNotFound() throws IOException {
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

    public void passIncorrect() throws IOException {
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
            Client.currentName = null;
            Client.currentPass = null;
            System.out.println("----------------Регистрация----------------");
            reg.toRegWindow();
        } else if (answer == 3) {
            Client.currentName = null;
            Client.currentPass = null;
            Main.getMain();
        } else {
            System.out.println("Неправильный пункт...");
            passIncorrect();
        }
    }

    public boolean checkNameValidation(String username) throws IOException {
        if (username.isEmpty()) {
            System.out.println("Ошибка! Вы ввели пустое имя...");
            return false;
        } else if (username.length() < 3) {
            System.out.println("Ошибка! В имени должно быть как минимум 3 символа...");
            return false;
        } else {
            return true;
        }
    }

    public boolean checkPassValidation(String password) throws IOException {
        if (password.isEmpty()) {
            System.out.println("Ошибка! Пароль не должен быть пустым...");
            return false;
        } else if (password.length() < 8) {
            System.out.println("Ошибка! Пароль должен быть минимум из 8 символов!");
            return false;
        } else {
            return true;
        }
    }

    public void checkRegNameValidation(String username) throws IOException {
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

    public void checkRegPassValidation(String password) throws IOException {
        RegistrationWindow reg = new RegistrationWindow();
        if (password.isEmpty()) {
            System.out.println("Ошибка! Пароль не должен быть пустым...");
            Client.currentPass = null;
            reg.toRegWindow();
        } else if (password.length() < 8) {
            System.out.println("Ошибка! Пароль должен минимум из 8 символов!");
            Client.currentPass = null;
            reg.toRegWindow();
        }
    }


    public void saveNewClientToBase(String username, String password, int balance) throws IOException {
        FileWriter fw = new FileWriter(dataBaseFile, true);
        BufferedWriter writer = new BufferedWriter(fw);

        writer.newLine();
        writer.write(username + "," + password + "," + balance);
        writer.close();
    }

    public void deleteClientFromBase() throws IOException {
        int lineNumber = 0;
        FileReader fr = new FileReader(dataBaseFile);
        BufferedReader reader = new BufferedReader(fr);
        FileWriter fw = new FileWriter(dataBaseFile);
        BufferedWriter writer = new BufferedWriter(fw);

        List<String> list = new LinkedList<>();
        List<String> list2 = new LinkedList<>();
        String line = reader.readLine();
        String line2 = reader.readLine();
        while (line != null) {
            String[] splitter = line.split(",");
            list.add(splitter[0]);
            line = reader.readLine();
        }
        while (line2 != null) {
            list2.add(line2);
            line2 = reader.readLine();
        }

        while (lineNumber <= list.size() - 1) {
            if (list.get(lineNumber).equals(Client.currentName)) {
                lineNumber++;
            } else {
                writer.write(list2.get(lineNumber));
            }
        }
        writer.close();
        reader.close();
    }

    public void changeNewPass() throws IOException {
        Scanner scan = new Scanner(System.in);

        System.out.print("Введите актуальный пароль: ");
        String temppass = scan.nextLine();
        if (temppass.equals(Client.currentPass)) {
            System.out.print("Введите новый пароль: ");
            temppass = scan.nextLine();
            if (!checkPassValidation(temppass)) {
                changeNewPass();
            } else {
                Client.currentPass = temppass;
                updateClientOnBase();
                System.out.println("Успешно!");
            }
        }
    }

    public void updateClientOnBase() throws IOException {
        int lineNumber = 0;
        FileReader fr = new FileReader(dataBaseFile);
        BufferedReader reader = new BufferedReader(fr);
        FileWriter fw = new FileWriter(dataBaseFile);
        BufferedWriter writer = new BufferedWriter(fw);

        List<String> list = new LinkedList<>();
        List<String> list2 = new LinkedList<>();
        String line = reader.readLine();
        String line2 = reader.readLine();
        while (line != null) {
            String[] splitter = line.split(",");
            list.add(splitter[0]);
            line = reader.readLine();
        }
        while (line2 != null) {
            list2.add(line2);
            line2 = reader.readLine();
        }

        while (lineNumber <= list.size() - 1) {
            if (list.get(lineNumber).equals(Client.currentName)) {
                writer.write(Client.currentName + "," + Client.currentPass + "," + Client.currentBalance);
            } else {
                writer.write(list2.get(lineNumber));
            }
        }
        writer.close();
        reader.close();
    }

    public String secureGetPass(boolean access) {
        if (access) {
            return Client.currentPass;
        } else {
            return String.valueOf("*".repeat(Math.max(0, Client.currentPass.length() + 1)));
        }
    }

    public boolean interName() throws IOException {
        Scanner scan = new Scanner(System.in);

        System.out.print("Введите имя: ");
        Client.currentName = scan.nextLine();
        if (!checkNameValidation(Client.currentPass)) {
            interName();
        }
        if (!checkName(Client.currentName)) {
            System.out.println("Пользователь с таким именем не найден...");
            Client.currentName = null;
            return false;
        } else {
            return true;
        }
    }

    public boolean interPass() throws IOException {
        Scanner scan = new Scanner(System.in);

        System.out.print("Введите пароль: ");
        Client.currentPass = scan.nextLine();
        if (!checkPassValidation(Client.currentPass)) {
            interPass();
        }
        if (!checkPass(Client.currentPass)) {
            System.out.println("Пароль введен не правильно...");
            Client.currentPass = null;
            return false;
        } else {
            return true;
        }
    }
}