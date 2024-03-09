
import java.util.Random;

public class RandomPasswordGenerator {

    private static final String LOWERCASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBERS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()-_=+[]{}|;:,.<>?/~`";

    private final Random random;
    private final int length;
    private final boolean useLowercase;
    private final boolean useUppercase;
    private final boolean useNumbers;
    private final boolean useSpecialCharacters;

    public RandomPasswordGenerator(int length, boolean useLowercase, boolean useUppercase, boolean useNumbers, boolean useSpecialCharacters) {
        this.random = new Random();
        this.length = length;
        this.useLowercase = useLowercase;
        this.useUppercase = useUppercase;
        this.useNumbers = useNumbers;
        this.useSpecialCharacters = useSpecialCharacters;
    }

    public String generatePassword() {
        StringBuilder password = new StringBuilder();

        if (useLowercase) {
            password.append(getRandomString(LOWERCASE_LETTERS, 1));
        }

        if (useUppercase) {
            password.append(getRandomString(UPPERCASE_LETTERS, 1));
        }

        if (useNumbers) {
            password.append(getRandomString(NUMBERS, 1));
        }

        if (useSpecialCharacters) {
            password.append(getRandomString(SPECIAL_CHARACTERS, 1));
        }

        for (int i = password.length(); i < length; i++) {
            password.append(getRandomString(getAllowedCharacters(), 1));
        }

        return shuffle(password.toString());
    }

    private String getRandomString(String characters, int length) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            sb.append(characters.charAt(randomIndex));
        }

        return sb.toString();
    }

    private String getAllowedCharacters() {
        StringBuilder sb = new StringBuilder();

        if (useLowercase) {
            sb.append(LOWERCASE_LETTERS);
        }

        if (useUppercase) {
            sb.append(UPPERCASE_LETTERS);
        }

        if (useNumbers) {
            sb.append(NUMBERS);
        }

        if (useSpecialCharacters) {
            sb.append(SPECIAL_CHARACTERS);
        }

        return sb.toString();
    }

    private String shuffle(String input) {
        char[] array = input.toCharArray();
        for (int i = array.length - 1; i > 0; i--) {
            int randomIndex = random.nextInt(i + 1);
            char temp = array[randomIndex];
            array[randomIndex] = array[i];
            array[i] = temp;
        }
        return new String(array);
    }

    public static void main(String[] args) {
        RandomPasswordGenerator generator = new RandomPasswordGenerator(12, true, true, true, true);
        String password = generator.generatePassword();
        System.out.println("Generated password: " + password);
    }
}