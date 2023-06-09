package Interfaz;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import users.UMain;

public class AddProductGUI extends JFrame implements ActionListener{

    private JTextField nameField;
    private JTextField priceField;
    private JTextField stockField;
    private UMain main;
    private SellerGUI sellGUI;
    private JFormattedTextField spinnerTextField;

    public AddProductGUI(SellerGUI sellGUI, UMain main) {
        this.main=main;
        this.sellGUI=sellGUI;
        // Configuración de la ventana
        setTitle("Agregar Producto");
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
        constraints.gridy = 0;
        panel.add(nameLabel, constraints);

        nameField = new JTextField(20);
        constraints.insets = new Insets(10, 0, 0, 10);
        constraints.gridx = 1;
        constraints.gridy = 0;
        panel.add(nameField, constraints);

        // Label y TextField para Price
        JLabel priceLabel = new JLabel("Precio:");
        constraints.insets = new Insets(10, 10, 0, 10);
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(priceLabel, constraints);

        priceField = new JTextField();
        constraints.insets = new Insets(10, 0, 0, 10);
        constraints.gridx = 1;
        constraints.gridy = 1;
        panel.add(priceField, constraints);

        // Label y TextField para Stock
        JLabel stockLabel = new JLabel("Stock:");
        constraints.insets = new Insets(10, 10, 0, 10);
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(stockLabel, constraints);

        SpinnerModel stockModel = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1);
        JSpinner stockSpinner = new JSpinner(stockModel);
        JComponent editor = stockSpinner.getEditor();
        spinnerTextField = ((JSpinner.DefaultEditor) editor).getTextField();
        spinnerTextField.setColumns(1); 
        constraints.insets = new Insets(10, 0, 0, 10);       
        constraints.gridx = 1;
        constraints.gridy = 2;
        panel.add(stockSpinner, constraints);

        // Botones para crear el producto y volver atrás
        JButton addButton = new JButton("Agregar");
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.insets = new Insets(20, 10, 10, 10);
        panel.add(addButton, constraints);

        JButton backButton = new JButton("Atrás");
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.insets = new Insets(20, 10, 10, 10);
        panel.add(backButton, constraints);

        // Panel para contener ambos botones y unirlos sin espacio
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(backButton);
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(20, 10, 10, 10);
        panel.add(buttonPanel, constraints);

        
        addButton.addActionListener(this);
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
        
        if (command.equals("Agregar")) {
            if(main.addProduct(nameField.getText(),priceField.getText(),spinnerTextField.getText()))
            sellGUI.updateTable();
            this.dispose();
        }
    }
}