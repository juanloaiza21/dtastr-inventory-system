package interfaz;

import java.awt.*;
import javax.swing.*;

public class LoginGUI extends JFrame {

    public LoginGUI() {
        // Configuración de la ventana principal
        setTitle("Inicio de sesión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        // Creación de componentes
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        JLabel correoLabel = new JLabel("Correo electrónico:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 0, 10);
        panel.add(correoLabel, constraints);

        JTextField correoInput = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 0, 0, 10);
        panel.add(correoInput, constraints);

        JLabel contrasenaLabel = new JLabel("Contraseña:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.insets = new Insets(10, 10, 10, 10);
        panel.add(contrasenaLabel, constraints);

        JPasswordField contrasenaInput = new JPasswordField(20);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.insets = new Insets(10, 0, 10, 10);
        panel.add(contrasenaInput, constraints);

        JButton loginButton = new JButton("Iniciar sesión");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.insets = new Insets(0, 10, 10, 10);
        panel.add(loginButton, constraints);

        JButton signupButton = new JButton("Registrarse");
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.insets = new Insets(0, 0, 10, 10);
        panel.add(signupButton, constraints);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(loginButton);
        buttonPanel.add(signupButton);
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(20, 10, 10, 10);
        panel.add(buttonPanel, constraints);

        // Agregar panel a la ventana principal
        getContentPane().add(panel);

        // Mostrar ventana principal
        setVisible(true);
    }

}

