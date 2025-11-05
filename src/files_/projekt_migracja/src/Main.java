package files_.projekt_migracja.src;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    private static final Path CURRENT_DIR = Path.of("src", "files_", "projekt_migracja");
    private static final Path OLD_DATA_PATH = CURRENT_DIR.resolve("stare_dane");
    private static final Path BACKUP_PATH = CURRENT_DIR.resolve("nowy_system", "backup");
    private static final Path RAPORTS_PATH = CURRENT_DIR.resolve("nowy_system", "raporty");

    public static void main(String[] args) {
        createDirectories();
        backupConfig();
        headerFromResources();
        UserDataProcessing();
        logAnalysis();
        maskSensitiveData();
    }

    private static void createDirectories() {
        try {
            Files.createDirectories(BACKUP_PATH);
            Files.createDirectories(RAPORTS_PATH);
            System.out.println("Folders successfully created");
        } catch (IOException e) {
            System.err.println("ERROR - Folders not created" + e.getMessage());
        }
    }

    private static void backupConfig() {
        Path source = OLD_DATA_PATH.resolve("config.ini");
        Path destination = BACKUP_PATH.resolve("config.ini.bak");

        try {
            Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Successful backup of config.ini");
        } catch (IOException e) {
            System.err.println("ERROR - Could not copy file " + e.getMessage());
        }
    }

    private static void headerFromResources() {
        String resourcePath = "/banner.txt";
        Path destination = RAPORTS_PATH.resolve("aktywni_uzytkownicy.txt");

        try (
                InputStream inputStream = Main.class.getResourceAsStream(resourcePath);
                BufferedWriter bufferedWriter = Files.newBufferedWriter(destination, StandardCharsets.UTF_8)
        ) {
            if (inputStream == null) {
                throw new IllegalStateException("ERROR - banner.txt not found");
            }
            try (
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
            ) {
                String result = reader.lines()
                        .collect(Collectors.joining("\n"));
                bufferedWriter.write(result);
                bufferedWriter.newLine();
                System.out.println("Successfully added header to file");
            }
        } catch (IOException e) {
            System.err.println("ERROR - could not write header " + e.getMessage());
        }
    }

    private static void UserDataProcessing() {
        Path source = OLD_DATA_PATH.resolve("users.csv");
        Path destination = RAPORTS_PATH.resolve("aktywni_uzytkownicy.txt");

        try (
                BufferedReader bufferedReader = Files.newBufferedReader(source, StandardCharsets.UTF_8);
                BufferedWriter bufferedWriter = Files.newBufferedWriter(destination, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
        ) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (!isLineEmptyOrStartsWithSpecialCharacter(line)) {
                    writeActiveUsers(line, bufferedWriter);
                }
            }
            System.out.println("Successfully processed user data");
        } catch (IOException e) {
            System.err.println("ERROR - Did not process user data " + e.getMessage());
        }
    }

    private static void logAnalysis() {
        Path source = OLD_DATA_PATH.resolve("system.log");
        Path destination = RAPORTS_PATH.resolve("bledy_systemowe.log");

        try (
                Stream<String> lines = Files.lines(source);
                BufferedWriter bufferedWriter = Files.newBufferedWriter(destination, StandardCharsets.UTF_8)
        ) {
            lines.filter(line -> line.contains("ERROR"))
                    .forEach(line -> writeLine(line, bufferedWriter));
            System.out.println("Successfully analyzed logs");
        } catch (IOException e) {
            System.err.println("ERROR - Log analysis failed " + e.getMessage());
        }
    }

    private static void maskSensitiveData() {
        Path source = OLD_DATA_PATH.resolve("system.log");
        Path destination = RAPORTS_PATH.resolve("system_sanitized.log");

        try (
                Stream<String> lines = Files.lines(source);
                BufferedWriter bufferedWriter = Files.newBufferedWriter(destination, StandardCharsets.UTF_8)
        ) {
            lines
                    .map(Main::maskEmailInLine)
                    .map(Main::maskIdInLine)
                    .forEach(line -> writeLine(line, bufferedWriter));
            System.out.println("Successfully masked data");
        } catch (IOException e) {
            System.err.println("ERROR - Masking data failed " + e.getMessage());
        }
    }

    private static void writeActiveUsers(String line, BufferedWriter bufferedWriter) throws IOException {
        String[] dividedLine = line.split(",");
        if (dividedLine[3].equalsIgnoreCase("active")) {
            String name = dividedLine[1];
            bufferedWriter.write(name);
            bufferedWriter.newLine();
        }
    }

    private static void writeLine(String line, BufferedWriter bufferedWriter) {
        try {
            bufferedWriter.write(line);
            bufferedWriter.newLine();
        } catch (IOException e) {
            System.err.println("ERROR - Writing not successful" + e.getMessage());
        }
    }

    private static String maskEmailInLine(String line) {
        StringBuilder stringBuilder = new StringBuilder();
        Pattern emailPattern = Pattern.compile("([\\w._%+-]+)@([\\w.-]+\\.[a-zA-Z]{2,})");
        Matcher matcher = emailPattern.matcher(line);

        while (matcher.find()) {
            String email = matcher.group(1);
            String masked = email.charAt(0) + "***@" + matcher.group(2);
            matcher.appendReplacement(stringBuilder, masked);
        }
        matcher.appendTail(stringBuilder);
        return stringBuilder.toString();
    }

    private static String maskIdInLine(String line) {
        StringBuilder stringBuilder = new StringBuilder();
        Pattern idPattern = Pattern.compile("\\b([a-zA-Z_]*id=)(\\d+)\\b");
        Matcher matcher = idPattern.matcher(line);

        while (matcher.find()) {
            String maskedLine = matcher.group(1) + "***";
            matcher.appendReplacement(stringBuilder, maskedLine);
        }
        matcher.appendTail(stringBuilder);
        return stringBuilder.toString();
    }

    private static boolean isLineEmptyOrStartsWithSpecialCharacter(String line) {
        return line.trim().isEmpty() || line.trim().startsWith("#") || line.trim().startsWith("//") || line.trim().startsWith("--");
    }
}
