package utils;

import javax.swing.*;
import java.awt.*;
public class JOptionpanelUtils {

    public static void ShowMessage(String message){
        JOptionPane.showMessageDialog(null, message);
    }

    public static void ShowMessageTimed(String message, int milliseconds) {
        JOptionPane pane = new JOptionPane(
                message,
                JOptionPane.INFORMATION_MESSAGE
        );
        JDialog dialog = pane.createDialog("Alerta");

        Timer timer = new Timer(milliseconds, e -> dialog.dispose());
        timer.setRepeats(false);
        timer.start();

        dialog.setVisible(true);
    }

    public static String InputString(String message) {
        while (true) {
            String input = JOptionPane.showInputDialog(message + ": ");

            if (input == null) {
                ShowMessage("Entrada cancelada.");
                return null;
            }

            if (input.trim().isEmpty()) {
                ShowMessage("Por favor, ingrese un texto válido.");
            } else {
                return input.trim();
            }
        }
    }

    public static int InputInt(String message) {
        while (true) {
            try {
                String input = JOptionPane.showInputDialog(message + ": ");
                if (input == null) {
                    ShowMessage("Input cancelado ❌");
                    return -1;
                }
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                ShowMessage("Por favor, ingrese un número entero válido.");
            }
        }
    }

    public static double InputDouble(String message) {
        while (true) {
            try {
                String input = JOptionPane.showInputDialog(message + ": ");
                if (input == null) {
                    ShowMessage("Entrada cancelada.");
                    return -1;
                }
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                ShowMessage("Por favor, ingrese un número decimal válido.");
            }
        }
    }

    public static boolean InputBoolean(String message) {
        int option = JOptionPane.showConfirmDialog(
                null,
                message,
                "Confirmación",
                JOptionPane.YES_NO_OPTION
        );
        return option == JOptionPane.YES_OPTION;
    }

    public static int InputOption(String message, String title, String[] options) {
        return JOptionPane.showOptionDialog(
                null,
                message,
                title,
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );
    }

    public static void messagemenu(StringBuilder inventary, String title){
        JTextArea textArea = new JTextArea(inventary.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        JOptionPane.showMessageDialog(null, scrollPane, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static String InputOptionList(String message, String title, String[] options) {
        JList<String> list = new JList<>(options);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);

        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new java.awt.Dimension(250, 150));

        int result = JOptionPane.showConfirmDialog(
                null,
                new Object[]{message, scrollPane},
                title,
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION && list.getSelectedValue() != null) {
            return list.getSelectedValue();
        } else {
            ShowMessage("Selección cancelada ❌");
            return null;
        }
    }

    public static void messagemenu(String inventary, String title){
        JTextArea textArea = new JTextArea(inventary);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(600, 300)); // 👈 Ajusta tamaño de ventana

        JOptionPane.showMessageDialog(null, scrollPane, title, JOptionPane.INFORMATION_MESSAGE);
    }


    public static String ShowInfoAndInput(StringBuilder inventary, String title, String mensajeInput) {
        // Área de texto para mostrar el inventario
        JTextArea textArea = new JTextArea(inventary.toString());
        textArea.setEditable(false);
        textArea.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new java.awt.Dimension(600, 200)); // 👈 fuerza tamaño del inventario

        // Campo de entrada
        JTextField inputField = new JTextField();
        JPanel inputPanel = new JPanel(new java.awt.BorderLayout(5, 5));
        inputPanel.add(new JLabel(mensajeInput + ": "), java.awt.BorderLayout.WEST);
        inputPanel.add(inputField, java.awt.BorderLayout.CENTER);

        // Panel principal con inventario + input
        JPanel mainPanel = new JPanel(new java.awt.BorderLayout(10, 10));
        mainPanel.add(scrollPane, java.awt.BorderLayout.CENTER);
        mainPanel.add(inputPanel, java.awt.BorderLayout.SOUTH);

        // Mostrar todo en un solo JOptionPane
        int result = JOptionPane.showConfirmDialog(
                null,
                mainPanel,
                title,
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            return inputField.getText().trim();
        } else {
            ShowMessage("Selection cancelled ❌");
            return null;
        }
    }


}
