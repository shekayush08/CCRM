package edu.ccrm.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;


public final class RecursiveUtils {


    private RecursiveUtils() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }


    public static long calculateDirectorySize(Path path) {
        long size = 0;
        try (Stream<Path> walk = Files.walk(path)) {
            size = walk

                    .filter(Files::isRegularFile)

                    .mapToLong(p -> {
                        try {
                            return Files.size(p);
                        } catch (IOException e) {
                            System.err.println("ERROR: Could not get size for file: " + p + " - " + e.getMessage());
                            return 0L;
                        }
                    })

                    .sum();
        } catch (IOException e) {
            System.err.println("FATAL: Could not walk directory: " + path + " - " + e.getMessage());
        }
        return size;
    }
}