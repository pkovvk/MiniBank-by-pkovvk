import java.io.*;

public class currentClientCode {
    static File currentClientFile = new File("currentClient");

    public void saveClient() throws IOException {
        FileWriter fw = new FileWriter(currentClientFile);
        BufferedWriter writer = new BufferedWriter(fw);

        writer.write(Client.currentName);
        writer.newLine();
        writer.write(Client.currentPass);
        writer.newLine();
        writer.write(String.valueOf(Client.currentBalance));
        writer.close();
    }

    public void updateClientFromFile() throws IOException {
        Client.currentName = getSavedName();
        Client.currentPass = getSavedPass();
    }

    public String getSavedName() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("currentClient"));
        String result = reader.readLine();
        reader.close();
        return result;
    }

    public String getSavedPass() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(currentClientFile));
        String line = reader.readLine();
        String password = null;
        int lineNumber = 0;
        while (line != null) {
            lineNumber++;
            if (lineNumber == 2) {
                password = line;
            }
            line = reader.readLine();
        }

        reader.close();
        return password;
    }

    public void deleteSavedClient() throws IOException {
        FileWriter fw = new FileWriter(currentClientFile);
        BufferedWriter writer = new BufferedWriter(fw);
        String nuller = null;

        writer.write(nuller);
        writer.close();
        fw.close();
    }

    public boolean isAuthorized() throws IOException {
        File file = new File("isAuthorized");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        reader.close();

        return Boolean.parseBoolean(line);
    }

    public boolean isSavedClientEmpty() {
        return currentClientFile.length() == 0;
    }

    public void setAuth(boolean authorized) throws IOException {
        FileWriter fw = new FileWriter("isAuthorized");
        BufferedWriter writer = new BufferedWriter(fw);

        writer.write(String.valueOf(authorized));
        writer.close();
        fw.close();
    }
}