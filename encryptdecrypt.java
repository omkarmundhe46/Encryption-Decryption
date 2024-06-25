import java.io.*;
import java.util.Scanner;

public class encrydepcry {
    private static String encrypt(String text, int shift) {
        StringBuilder encrypted = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                encrypted.append((char) ((c - base + shift) % 26 + base));
            } else {
                encrypted.append(c);
            }
        }
        return encrypted.toString();
    }

    private static String decrypt(String text, int shift) {
        return encrypt(text, 26 - shift);
    }

    private static void processFile(String inputFilePath, String outputFilePath, int shift, boolean isEncryption) {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String processedLine = isEncryption ? encrypt(line, shift) : decrypt(line, shift);
                writer.write(processedLine);
                writer.newLine();
            }

            System.out.println("File processing complete. Output saved to " + outputFilePath);

        } catch (IOException e) {
            System.err.println("Error processing the file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Would you like to encrypt or decrypt a file? (E/D)");
        String choice = scanner.nextLine().trim().toUpperCase();

        boolean isEncryption = choice.equals("E");

        System.out.println("Enter the file path:");
        String inputFilePath = scanner.nextLine().trim();

        System.out.println("Enter the output file path:");
        String outputFilePath = scanner.nextLine().trim();

        System.out.println("Enter the shift amount (for Caesar Cipher):");
        int shift = Integer.parseInt(scanner.nextLine().trim());

        processFile(inputFilePath, outputFilePath, shift, isEncryption);

        scanner.close();
    }
}
