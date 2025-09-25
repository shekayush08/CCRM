package edu.ccrm.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

public class BackupService {


    public BackupService() {

    }


    public void createBackup(String sourceDir, String backupRootDir) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        String timestamp = LocalDateTime.now().format(formatter);
        String backupFolderName = "backup-" + timestamp;

        Path sourcePath = Paths.get(sourceDir);
        Path destinationPath = Paths.get(backupRootDir, backupFolderName);

        System.out.println("INFO: Starting backup process...");
        System.out.println("  > Source: " + sourcePath.toAbsolutePath());
        System.out.println("  > Destination: " + destinationPath.toAbsolutePath());

        try {

            Files.createDirectories(destinationPath);


            try (Stream<Path> stream = Files.walk(sourcePath)) {
                stream.forEach(sourceFile -> {
                    try {

                        Path targetFile = destinationPath.resolve(sourcePath.relativize(sourceFile));

                        Files.copy(sourceFile, targetFile, StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException e) {
                        System.err.println("ERROR: Could not copy file: " + sourceFile + " - " + e.getMessage());
                    }
                });
            }
            System.out.println("SUCCESS: Backup created successfully at " + destinationPath);
        } catch (IOException e) {
            System.err.println("FATAL: Failed to create backup directory or walk the source tree: " + e.getMessage());
        }
    }
}