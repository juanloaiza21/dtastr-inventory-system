package Interfaz;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import users.UMain;

public class SignUpGUI extends JFrame  implements ActionListener {
    private UMain main; 
    private LoginGUI lGUI; 
    private JTextField idInput,nameInput,mailInput,cellphoneInput;
    private JPasswordField passwordInput;
    private ButtonGroup typeGroup;

    public SignUpGUI(LoginGUI lGUI, UMain main) {
        this.lGUI=lGUI;
        this.main=main;
        // Configuración de la ventana principal
        setTitle("Registro de usuario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        // Creación de componentes
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        JLabel idLabel = new JLabel("ID:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 10, 0, 10);
        panel.add(idLabel, constraints);

        idInput = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 0, 0, 10);
        panel.add(idInput, constraints);

        JLabel nameLabel = new JLabel("Nombre:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.insets = new Insets(10, 10, 0, 10);
        panel.add(nameLabel, constraints);

        nameInput = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.insets = new Insets(10, 0, 0, 10);
        panel.add(nameInput, constraints);

        JLabel mailLabel = new JLabel("Correo electrónico:");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.insets = new Insets(10, 10, 0, 10);
        panel.add(mailLabel, constraints);

        mailInput = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.insets = new Insets(10, 0, 0, 10);
        panel.add(mailInput, constraints);

        JLabel cellphoneLabel = new JLabel("Celular:");
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.insets = new Insets(10, 10, 0, 10);
        panel.add(cellphoneLabel, constraints);

        cellphoneInput = new JTextField(20);
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.insets = new Insets(10, 0, 0, 10);
        panel.add(cellphoneInput, constraints);

        JLabel passwordLabel = new JLabel("Contraseña:");
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.insets = new Insets(10, 10, 0, 10);
        panel.add(passwordLabel, constraints);

        passwordInput = new JPasswordField(20);
        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.insets = new Insets(10, 0, 0, 10);
        panel.add(passwordInput, constraints);

        JLabel typeLabel = new JLabel("Tipo de usuario:");
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.insets = new Insets(10, 10, 0, 10);
        panel.add(typeLabel, constraints);

        JRadioButton buyerRadioButton = new JRadioButton("Comprador");
        JRadioButton sellerRadioButton = new JRadioButton("Vendedor");
        typeGroup = new ButtonGroup();
        buyerRadioButton.setActionCommand("USER");
        sellerRadioButton.setActionCommand("SELLER");
        buyerRadioButton.setSelected(true);
        typeGroup.add(buyerRadioButton);
        typeGroup.add(sellerRadioButton);

        JPanel typePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        typePanel.add(buyerRadioButton);
        typePanel.add(sellerRadioButton);
        constraints.gridx = 1;
        constraints.gridy = 5;
        constraints.insets = new Insets(10, 0, 0, 10);
        panel.add(typePanel, constraints);
        
        JButton createButton = new JButton("Crear");
        constraints.gridx = 0;
        constraints.gridy = 6;
        constraints.insets = new Insets(20, 10, 10, 0);
        panel.add(createButton, constraints);

        JButton backButton = new JButton("Atrás");
        constraints.gridx = 1;
        constraints.gridy = 6;
        constraints.insets = new Insets(20, 5, 10, 10);
        panel.add(backButton, constraints);

        // Panel para contener ambos botones y unirlos sin espacio
        JPanel buttonsPanel = new JPanel(new BorderLayout());
        buttonsPanel.add(createButton, BorderLayout.WEST);
        buttonsPanel.add(backButton, BorderLayout.CENTER);
        buttonsPanel.setBorder(new EmptyBorder(0, 0, 0, 0)); // Establece la separación entre botones
        constraints.gridx = 0;
        constraints.gridy = 7;
        constraints.gridwidth = 2;
        constraints.insets = new Insets(20, 10, 10, 10);
        panel.add(buttonsPanel, constraints);

        createButton.addActionListener(this);
        backButton.addActionListener(this);
    
        // Configuración del panel principal
        setContentPane(panel);
        pack();
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        
        if (command.equals("Atrás")) {
            lGUI.setVisible(true);
            this.dispose();
        }
        if (command.equals("Crear")) {
            if(main.createUser(idInput.getText(),nameInput.getText(),mailInput.getText(),cellphoneInput.getText(),typeGroup.getSelection().getActionCommand(),new String(passwordInput.getPassword() ))){
                JOptionPane.showMessageDialog(this, "Usuario creado exitosamente", "Success", JOptionPane.INFORMATION_MESSAGE);                
                lGUI.setVisible(true);
                this.dispose();
            }else{
                JOptionPane.showMessageDialog(this, "Error: verifique la informacion e intente de nuevo", "Error", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
    
        
