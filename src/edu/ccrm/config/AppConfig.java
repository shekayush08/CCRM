package edu.ccrm.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public final class AppConfig {


    private static final AppConfig INSTANCE = new AppConfig();


    private static String dataFolderPath;
    private final String exportFolderPath;
    private final String backupFolderPath;
    private final String studentExportFileName;
    private final String courseExportFileName;
    private static String studentImportFileName;



    private AppConfig() {

        dataFolderPath = "./data";
        exportFolderPath = dataFolderPath + "/exports";
        backupFolderPath = dataFolderPath + "/backups";
        studentExportFileName = "students_export.csv";
        courseExportFileName = "courses_export.csv";
        studentImportFileName = "students_import.csv";


        try {
            Files.createDirectories(Paths.get(exportFolderPath));
            Files.createDirectories(Paths.get(backupFolderPath));
            System.out.println("INFO: Data directories initialized at: " + dataFolderPath);
        } catch (IOException e) {
            System.err.println("FATAL: Could not create necessary data directories: " + e.getMessage());
        }
    }


    public static AppConfig getInstance() {
        return INSTANCE;
    }



    public String getDataFolderPath() {
        return dataFolderPath;
    }

    public String getExportFolderPath() {
        return exportFolderPath;
    }

    public String getBackupFolderPath() {
        return backupFolderPath;
    }

    public String getStudentExportFilePath() {
        return Paths.get(exportFolderPath, studentExportFileName).toString();
    }

    public String getCourseExportFilePath() {
        return Paths.get(exportFolderPath, courseExportFileName).toString();
    }

    public static String getStudentImportFilePath() {
        return Paths.get(dataFolderPath, studentImportFileName).toString();
    }



    public void setDataFolderPath(String dataFolderPath) {
        AppConfig.dataFolderPath = dataFolderPath;
    }
}
