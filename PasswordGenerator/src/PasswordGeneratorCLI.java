import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.Random;
import java.util.Scanner;
import java.awt.Toolkit;

import static java.awt.SystemColor.text;

public class PasswordGeneratorCLI {
    public static void main(String[] args) {

        // Initialize Scanner to read ser input

        Scanner scanner = new Scanner(System.in);

        // Print welcome message
        System.out.println("🔐 Password Generator🔐");

        // Step 1: Ask for password lenght
        System.out.println("Enter password lenght: ");
        int lenght = scanner.nextInt();

        // Step 2: Ask for char types (uppercase, lowercase, etc.)
        System.out.println("Include UPPERCASE letters? (y/n)");
        boolean hasUpper = scanner.next().equalsIgnoreCase("y");

        System.out.println("Include LOWERCASE letters? (y/n)");
        boolean hasLower = scanner.next().equalsIgnoreCase("y");

        System.out.println("Include DIGITS? (y/n)");
        boolean hasNumbers = scanner.next().equalsIgnoreCase("y");

        System.out.println("Include symbols? (y/n)");
        boolean hasSymbols = scanner.next().equalsIgnoreCase("y");

        // Step 3: Generate the password
        String password = generatePassword(lenght, hasUpper, hasLower, hasNumbers, hasSymbols);

        // Step 4: Display Password and Strenght
        System.out.println("\nGenerated Password: " + password);
        System.out.println("Strenght: "+checkStrenght(password));

        // Step 5: Copy to Clipboard
        copyToClipboard(password);
    }

    public static String generatePassword(int lenght, boolean hasUpper, boolean hasLower, boolean hasNumbers, boolean hasSymbols) {
        //Define character pools
        String upperChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerChars = "abcdefghijklmnopqrstuvwxyz";
        String numberChars = "0123456789";
        String symbolChars = "!@#$%^&*()_+-=<>?|:;.,{}[]";

        // Combine allowed characters based on user choice
       StringBuilder allowedChars = new StringBuilder();
       if (hasUpper) allowedChars.append(upperChars);
       if (hasLower) allowedChars.append(lowerChars);
       if (hasNumbers) allowedChars.append(numberChars);
       if (hasSymbols) allowedChars.append(symbolChars);

       // Error handling: Atleast one character type must be selected
        if(allowedChars.length() == 0){
            throw new IllegalArgumentException("Error: ou must select atleast one character type!");
        }

        // Randomly select characters from the pool
        Random random = new Random();
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < lenght; i++){
            int randomIndex = random.nextInt(allowedChars.length());
            password.append(allowedChars.charAt(randomIndex));
        }
        return password.toString();
    }
    // Check password strenght(Weak/Medium/Strong)

    public static String checkStrenght(String password) {
        // Rule 1 password is too short
        if (password.length() < 8) return "Weak (To short)";

        // Check for character diversity
        boolean hasUpper = !password.equals(password.toLowerCase());
        boolean hasLower = !password.equals(password.toUpperCase());
        boolean hasNumbers = password.matches(".*\\d.*");
        ; // contains a digit
        boolean hasSymbols = !password.matches("[A-Za-z0-9]*"); // contains a symbols

        // Score strenght (1 point for fufilled condition)
        int strenght = 0;
        if (hasUpper) strenght++;
        if (hasLower) strenght++;
        if (hasNumbers) strenght++;
        if (hasSymbols) strenght++;

        // Return rating based on score
        if (strenght < 2) return "Weak";
        else if (strenght < 4) return "Medium";
        else return "Strong";
    }

        // Copies to Clipboard
        public static void copyToClipboard(String text){
            try{
                StringSelection selection = new StringSelection(text);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(selection, null);
                System.out.println("(Password copied to clipboard!)");
            }catch (Exception e){
                System.out.println("Error copying to clipboard.");
            }

        }
    }

