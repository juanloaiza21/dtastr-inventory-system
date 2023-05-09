import javax.swing.*;
import java.awt.*;

public class LoginGUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");
        frame.setSize(400, 200);
        frame.setLayout(new BorderLayout());

        // Panel para los campos de texto y etiquetas
        JPanel panel = new JPanel(new GridLayout(2, 2));
        JLabel correoLabel = new JLabel("Correo:");
        JTextField correoInput = new JTextField();
        JLabel passwordLabel = new JLabel("Contraseña:");
        JTextField passwordInput = new JTextField();
        panel.add(correoLabel);
        panel.add(correoInput);
        panel.add(passwordLabel);
        panel.add(passwordInput);

        // Panel para los botones de inicio de sesión y registro
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton loginButton = new JButton("Login");
        JButton signupButton = new JButton("Sign up");
        buttonPanel.add(loginButton);
        buttonPanel.add(signupButton);

        // Agregar paneles al marco
        frame.add(panel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Mostrar el marco
        frame.setVisible(true);
    }
}
