import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class currentClientCode {
    File currentClientFile = new File("currentClient");
    File isAuthorizedFile = new File("isAuthorized");

    public void saveClient() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(currentClientFile));
        writer.write(Client.currentName);
        writer.newLine();
        writer.write(Client.currentPass);
        writer.newLine();
        writer.write(Client.currentBalance);
        writer.close();
    }

    public void getSavedClient() throws IOException {
        FileReader fr = new FileReader(currentClientFile);
        BufferedReader reader = new BufferedReader(fr);
        List<String> list = new LinkedList<>();
        int i = 0;
        String line = reader.readLine();
        while (line != null) {
            list.add(i, line);
            i++;
            line = reader.readLine();
        }
        reader.close();

        System.out.println("Никнейм: " + list.get(0));
        System.out.println("Пароль: " + list.get(1));
        System.out.println("Баланс: " + list.get(2));
    }

    public String getSavedName() throws IOException {
        FileReader fr = new FileReader(currentClientFile);
        BufferedReader reader = new BufferedReader(fr);
        List<String> list = new LinkedList<>();
        int i = 0;
        String line = reader.readLine();
        while (line != null) {
            list.add(i, line);
            i++;
            line = reader.readLine();
        }
        reader.close();

        return list.get(0);
    }

    public String getSavedPass() throws IOException {
        FileReader fr = new FileReader(currentClientFile);
        BufferedReader reader = new BufferedReader(fr);
        List<String> list = new LinkedList<>();
        int i = 0;
        String line = reader.readLine();
        while (line != null) {
            list.add(i, line);
            i++;
            line = reader.readLine();
        }
        reader.close();

        return list.get(1);
    }

    public int getSavedBalance() throws IOException {
        FileReader fr = new FileReader(currentClientFile);
        BufferedReader reader = new BufferedReader(fr);
        List<String> list = new LinkedList<>();
        int i = 0;
        String line = reader.readLine();
        while (line != null) {
            list.add(i, line);
            i++;
            line = reader.readLine();
        }
        reader.close();

        return Integer.parseInt(list.get(2));
    }

    public boolean isAuthorized() throws IOException {
        FileReader fr = new FileReader(isAuthorizedFile);
        BufferedReader reader = new BufferedReader(fr);

        return Boolean.parseBoolean(reader.readLine());
    }

    public void deleteSavedClient() throws IOException {
        String nuller = null;
        BufferedWriter writer = new BufferedWriter(new FileWriter(currentClientFile));
        writer.write(nuller);
        writer.close();
    }

    public boolean isCurrentClientEmpty() {
        return currentClientFile.length() == 0;
    }

    public void setAuth(boolean auth) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(isAuthorizedFile));
        writer.write(String.valueOf(auth));
        writer.close();
    }
}