import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SimpleTextComparer {

    // ANSI escape codes for underlining and resetting formatting
    private static final String ANSI_UNDERLINE = "\u001B[4m";
    private static final String ANSI_RESET = "\u001B[0m";

    public static void main(String[] args) throws IOException {
        // Replace these with your actual file paths
        String filePath1 = "firstFile.txt";
        String filePath2 = "secondFile.txt";

        List<String> file1Lines = readFileLines(filePath1);
        List<String> file2Lines = readFileLines(filePath2);

        compareAndPrintDifferences(file1Lines, file2Lines);
    }

    private static List<String> readFileLines(String filePath) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }

    private static void compareAndPrintDifferences(List<String> file1Lines, List<String> file2Lines) {
        int maxLines = Math.max(file1Lines.size(), file2Lines.size());

        for (int i = 0; i < maxLines; i++) {
            String line1 = i < file1Lines.size() ? file1Lines.get(i) : "";
            String line2 = i < file2Lines.size() ? file2Lines.get(i) : "";

            if (!line1.equals(line2)) {
                System.out.println("Difference found on line " + (i + 1) + ":");
                System.out.println("File 1: " + underlineDifferences(line1, line2));
                System.out.println("File 2: " + underlineDifferences(line2, line1));
                System.out.println();
            }
        }
    }

    private static String underlineDifferences(String line1, String line2) {
        StringBuilder underlined = new StringBuilder();
        int maxLength = Math.max(line1.length(), line2.length());

        for (int i = 0; i < maxLength; i++) {
            char char1 = i < line1.length() ? line1.charAt(i) : ' ';
            char char2 = i < line2.length() ? line2.charAt(i) : ' ';

            if (char1 != char2) {
                underlined.append(ANSI_UNDERLINE).append(char1).append(ANSI_RESET);
            } else {
                underlined.append(char1);
            }
        }

        return underlined.toString();
    }
}