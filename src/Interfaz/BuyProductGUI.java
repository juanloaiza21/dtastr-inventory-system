package Interfaz;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import users.UMain;

public class BuyProductGUI extends JFrame implements ActionListener{
    
    private JTextField nameField;
    private JTextField priceField;
    private JTextField idField;
    private UMain main;
    private UserGUI useGUI;
    private JSpinner quantSpinner;
    private JFormattedTextField spinnerTextField;
    private JButton actionsButton;
    private SpinnerNumberModel quantModel;

    public BuyProductGUI(UserGUI useGUI, UMain main) {
        this.main=main;
        this.useGUI=useGUI;
        // Configuración de la ventana
        setTitle("Comprar Producto");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setSize(400, 250);
        setLocationRelativeTo(null);

        // Creación del panel y su layout
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;

        // Label y TextField para Name
        JLabel idLabel = new JLabel("ID:");
        constraints.insets = new Insets(10, 10, 0, 10);
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(idLabel, constraints);

        idField = new JTextField(20);
        constraints.insets = new Insets(10, 0, 0, 10);
        constraints.gridx = 1;
        constraints.gridy = 0;
        idField.setEditable(false);
        panel.add(idField, constraints);

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

        // Label y TextField para Price
        JLabel priceLabel = new JLabel("Precio:");
        constraints.insets = new Insets(10, 10, 0, 10);
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(priceLabel, constraints);

        priceField = new JTextField();
        constraints.insets = new Insets(10, 0, 0, 10);
        constraints.gridx = 1;
        constraints.gridy = 2;
        priceField.setEditable(false);
        panel.add(priceField, constraints);

        // Label y TextField para Stock
        JLabel quantLabel = new JLabel("Quantity:");
        constraints.insets = new Insets(10, 10, 0, 10);
        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(quantLabel, constraints);

        quantModel = new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1);
         quantSpinner = new JSpinner(quantModel);
        JComponent editor = quantSpinner.getEditor();
         spinnerTextField = ((JSpinner.DefaultEditor) editor).getTextField();
        spinnerTextField.setColumns(1); 
        constraints.insets = new Insets(10, 0, 0, 10);       
        constraints.gridx = 1;
        constraints.gridy = 3;
        spinnerTextField.setEditable(false);
        quantSpinner.setEnabled(false);
        
        spinnerTextField.setText("1");
        panel.add(quantSpinner, constraints);

        // Botones para crear el producto y volver atrás
        actionsButton = new JButton("Buscar");
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
        String[][] s=null;
        
        if (command.equals("Atrás")) {
            this.dispose();
        } 
         if (command.equals("Buscar")) {
            s = main.getProductsByName(nameField.getText());
            if(s.length==0){
                JOptionPane.showMessageDialog(this, "Item not Found", "Error", JOptionPane.INFORMATION_MESSAGE);
            }else{
                nameField.setEditable(false);
                idField.setText(s[0][0]);
                priceField.setText(s[0][2]);
                quantModel.setMaximum(Integer.valueOf(s[0][3]));
                spinnerTextField.setEditable(true);
                quantSpinner.setEnabled(true);

                actionsButton.setActionCommand("Calcular precio");
                actionsButton.setText("Calcular precio");
            }
        }  
        if (command.equals("Calcular precio")) {
            s = main.getProductsByName(nameField.getText());
            priceField.setText(Double.toString(Double.valueOf(s[0][2])*Integer.valueOf(spinnerTextField.getText())));
            
            spinnerTextField.setEditable(false);
            quantSpinner.setEnabled(false);
            actionsButton.setActionCommand("Buy");
            actionsButton.setText("Buy");
        }

        if (command.equals("Buy")) {
            if(main.buyProduct(Integer.valueOf(idField.getText()),Integer.valueOf(spinnerTextField.getText()))){
                useGUI.updateTable();
                JOptionPane.showMessageDialog(this, "Product updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
            }else{                
                JOptionPane.showMessageDialog(this, "Error", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
