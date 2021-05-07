package services;

import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CSVWriter {
    private static CSVWriter instance = null;

    private final String csvDirPath = "D:\\Facultate\\Anul II\\Semestrul 2\\Programare avansata pe obiecte\\Laborator\\ELearningPlatform\\res\\csv\\";

    private CSVWriter() {}

    public static CSVWriter getInstance() {
        if (CSVWriter.instance == null) {
            CSVWriter.instance = new CSVWriter();
        }
        return CSVWriter.instance;
    }

    public void writeToAudit(String action) {
        try {
            FileWriter auditWriter = new FileWriter(csvDirPath + "audit.csv", true);
            auditWriter.append(action);
            auditWriter.append(",");
            auditWriter.append(new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date()));
            auditWriter.append("\n");
            auditWriter.flush();
            auditWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
