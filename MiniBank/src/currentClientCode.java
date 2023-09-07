import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class currentClientCode {
    File currentClientFile = new File("currentClient");
    BufferedReader reader = null;

    public void writeClient(String username) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(currentClientFile);

        pw.println(Client.currentName);
        pw.println(Client.currentBalance);
        pw.close();
    }

    public String getName() throws IOException {
        reader = new BufferedReader(new FileReader("currentClient"));
        String nameLine = reader.readLine();
        reader.close();

        return nameLine;
    }

    public int getBalance() throws IOException {
        reader = new BufferedReader(new FileReader("currentClient"));
        int balance = 0;
        String line;
        int lineNumber = 0;
        while ((line = reader.readLine()) != null) {
            lineNumber++;
            if (lineNumber == 2) {
                balance = Integer.parseInt(line);
                break;
            }
        reader.close();
        }
        return balance;
    }

    public void deleteClient() throws IOException {
        String replace = null;
        String newLine = "";

        try (BufferedReader br = Files.newBufferedReader(Paths.get("currentClient"));) {
            br.lines()
                    .skip(1) // пропускаем первую строку (обычно это заголовок)
                    .forEach(line -> { // заменяем все вхождения строки ‘replacement’ на ‘newLine’
                        line = line.replaceAll(replace, newLine);
                        System.out.println(line);
                    });
        }
    }
}