package interfaz;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;
import users.UMain;

public class AskProductGUI extends JFrame implements ActionListener{
    
    private JTextField nameField;
    private JTextField priceField;
    private JTextField idField;
    private UMain main;
    private UserGUI useGUI;

    public AskProductGUI(UserGUI useGUI, UMain main) {
        this.main=main;
        this.useGUI=useGUI;
        // Configuración de la ventana
        setTitle("Ask Product");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(400, 250);
        setLocationRelativeTo(null);

        // Creación del panel y su layout
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;

        // Label y TextField para Name
        JLabel nameLabel = new JLabel("Nombre:");
        constraints.insets = new Insets(10, 10, 0, 10);
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(nameLabel, constraints);

        nameField = new JTextField(20);
        constraints.insets = new Insets(10, 0, 0, 10);
        constraints.gridx = 1;
        constraints.gridy = 1;
        panel.add(nameField, constraints);

        // Botones para crear el producto y volver atrás
        JButton actionsButton = new JButton("Ask");
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.insets = new Insets(20, 10, 10, 10);
        panel.add(actionsButton, constraints);

        JButton backButton = new JButton("Atrás");
        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.insets = new Insets(20, 10, 10, 10);
        panel.add(backButton, constraints);

        // Panel para contener ambos botones y unirlos sin espacio
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(actionsButton);
        buttonPanel.add(backButton);
        constraints.gridx = 0;
        constraints.gridy = 5;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(20, 10, 10, 10);
        panel.add(buttonPanel, constraints);
        
        actionsButton.addActionListener(this);
        backButton.addActionListener(this);

            // Añade el panel al JFrame
    add(panel);

    // Hace visible la ventana
    setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        
        if (command.equals("Atrás")) {
            this.dispose();
        }
        
        if (command.equals("Ask")) {
            try {
                if(main.askProduct(nameField.getText())){
                    useGUI.updateTable();
                    JOptionPane.showMessageDialog(this, "Product Asked successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                }else{                
                    JOptionPane.showMessageDialog(this, "Error", "Error", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (HeadlessException | SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } 
        }
    }
}

