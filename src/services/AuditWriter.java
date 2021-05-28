package services;

import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AuditWriter {
    private static AuditWriter instance = null;

    private final String csvDirPath = "D:\\Facultate\\Anul II\\Semestrul 2\\Programare avansata pe obiecte\\Laborator\\ELearningPlatform\\res\\csv\\";

    private AuditWriter() {}

    public static AuditWriter getInstance() {
        if (AuditWriter.instance == null) {
            AuditWriter.instance = new AuditWriter();
        }
        return AuditWriter.instance;
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
