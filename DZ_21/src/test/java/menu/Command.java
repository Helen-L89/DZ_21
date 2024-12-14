package menu;
import animals.Animal;
public enum Command {

        ADD, LIST, UPDATE, FILTER, EXIT;

        public static Command fromString(String input) {
            try {
                return Command.valueOf(input.trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                return null;
            }
        }
    }

