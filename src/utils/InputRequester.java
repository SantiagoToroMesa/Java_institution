package utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Optional;

import javax.swing.JOptionPane;

public class InputRequester {
    private final static String DEFAULT_INVALID_INPUT_MESSAGE = "Entrada inválida. Inténtalo de nuevo";
    private final static String DEFAULT_INVALID_INPUT_PANE_TITLE = "Entrada inválida";

    // ------------------ STRING ------------------
    public static String requestString(String prompt) {
        return requestString(prompt, DEFAULT_INVALID_INPUT_MESSAGE, false);
    }

    public static String requestString(String prompt, boolean allowEmpty) {
        return requestString(prompt, DEFAULT_INVALID_INPUT_MESSAGE, allowEmpty);
    }

    public static String requestString(String prompt, String invalidInputMessage, boolean allowEmpty) {
        while (true) {
            String inputString = JOptionPane.showInputDialog(null, prompt);
            String trimmedInput = inputString == null ? "" : inputString.trim();
            if (allowEmpty)
                return trimmedInput;

            if (!trimmedInput.isEmpty())
                return trimmedInput;

            JOptionPane.showMessageDialog(null, invalidInputMessage, DEFAULT_INVALID_INPUT_PANE_TITLE,
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    // ------------------ DATE ------------------
    public static Date requestDate(String message) {
        while (true) {
            try {
                String input = JOptionPane.showInputDialog(null, message);
                if (input == null) { // Si el usuario presiona cancelar
                    return null;
                }

                LocalDate localDate = LocalDate.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(null, "Formato inválido. Usa yyyy-MM-dd.");
            }
        }
    }

    public static Optional<LocalDate> requestLocalDate(String prompt, boolean allowEmpty) {
        return requestLocalDate(prompt, "Formato de fecha inválido. Usa yyyy-MM-dd", allowEmpty);
    }

    public static Optional<LocalDate> requestLocalDate(String prompt, String invalidInputMessage) {
        return requestLocalDate(prompt, invalidInputMessage, false);
    }

    public static Optional<LocalDate> requestLocalDate(String prompt, String invalidInputMessage, boolean allowEmpty) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        while (true) {
            String inputLocalDate = requestString(prompt + "\nFormato: yyyy-MM-dd", allowEmpty);
            if (allowEmpty && inputLocalDate.isEmpty())
                return Optional.empty();

            try {
                return Optional.of(LocalDate.parse(inputLocalDate, formatter));
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(null, invalidInputMessage, DEFAULT_INVALID_INPUT_PANE_TITLE,
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    // ------------------ LOCALDATETIME ------------------
    public static Optional<LocalDateTime> requestLocalDateTime(String prompt, String invalidInputMessage) {
        return requestLocalDateTime(prompt, invalidInputMessage, false);
    }

    public static Optional<LocalDateTime> requestLocalDateTime(String prompt, boolean allowEmpty) {
        return requestLocalDateTime(prompt, "Formato de fecha y hora inválido. Usa yyyy-MM-ddTHH:mm:ss", allowEmpty);
    }

    public static Optional<LocalDateTime> requestLocalDateTime(String prompt) {
        return requestLocalDateTime(prompt, "Formato de fecha y hora inválido. Usa yyyy-MM-ddTHH:mm:ss", false);
    }

    public static Optional<LocalDateTime> requestLocalDateTime(String prompt, String invalidInputMessage,
                                                               boolean allowEmpty) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        while (true) {
            String inputLocalDateTime = requestString(prompt + "\nFormato: yyyy-MM-ddTHH:mm:ss", allowEmpty);
            if (allowEmpty && inputLocalDateTime.isEmpty())
                return Optional.empty();

            try {
                return Optional.of(LocalDateTime.parse(inputLocalDateTime, formatter));
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(null, invalidInputMessage, DEFAULT_INVALID_INPUT_PANE_TITLE,
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    // ------------------ LOCALTIME ------------------
    public static Optional<LocalTime> requestLocalTime(String prompt, String invalidInputMessage) {
        return requestLocalTime(prompt, invalidInputMessage, false);
    }

    public static Optional<LocalTime> requestLocalTime(String prompt, boolean allowEmpty) {
        return requestLocalTime(prompt, "Formato de hora inválido. Usa HH:mm o HH:mm:ss", allowEmpty);
    }

    public static Optional<LocalTime> requestLocalTime(String prompt) {
        return requestLocalTime(prompt, "Formato de hora inválido. Usa HH:mm o HH:mm:ss", false);
    }

    public static Optional<LocalTime> requestLocalTime(String prompt, String invalidInputMessage, boolean allowEmpty) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm[:ss]");
        while (true) {
            String inputLocalTime = requestString(prompt + "\nFormato: HH:mm o HH:mm:ss", allowEmpty);
            if (allowEmpty && inputLocalTime.isEmpty())
                return Optional.empty();

            try {
                return Optional.of(LocalTime.parse(inputLocalTime, formatter));
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(null, invalidInputMessage, DEFAULT_INVALID_INPUT_PANE_TITLE,
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    // ------------------ INTEGER ------------------
    public static int requestInteger(String prompt, String invalidInputMessage, boolean allowEmpty, int defaultValue) {
        while (true) {
            String inputInteger = requestString(prompt, allowEmpty);
            if (allowEmpty && (inputInteger == null || inputInteger.isEmpty())) {
                return defaultValue;
            }

            try {
                return Integer.parseInt(inputInteger);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, invalidInputMessage, "Invalid Input",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    public static int requestInteger(String prompt, String invalidInputMessage, int defaultValue) {
        return requestInteger(prompt, invalidInputMessage, false, defaultValue);
    }

    public static int requestInteger(String prompt, int defaultValue) {
        return requestInteger(prompt, "La entrada no es un entero. Inténtalo de nuevo", false, defaultValue);
    }

    public static int requestInteger(String prompt) {
        return requestInteger(prompt, "La entrada no es un entero. Inténtalo de nuevo", false, 0);
    }

    // ------------------ DOUBLE ------------------
    public static Optional<Double> requestDouble(String prompt, String invalidInputMessage) {
        return requestDouble(prompt, invalidInputMessage, false);
    }

    public static Optional<Double> requestDouble(String prompt, boolean allowEmpty) {
        return requestDouble(prompt, "La entrada no es un número válido. Inténtalo de nuevo", allowEmpty);
    }

    public static Optional<Double> requestDouble(String prompt) {
        return requestDouble(prompt, "La entrada no es un número válido. Inténtalo de nuevo", false);
    }

    public static Optional<Double> requestDouble(String prompt, String invalidInputMessage, boolean allowEmpty) {
        while (true) {
            String inputDouble = requestString(prompt, allowEmpty);
            if (allowEmpty && inputDouble.isEmpty())
                return Optional.empty();

            try {
                return Optional.of(Double.parseDouble(inputDouble));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, invalidInputMessage, DEFAULT_INVALID_INPUT_PANE_TITLE,
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
