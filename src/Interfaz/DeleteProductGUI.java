package interfaz;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import users.UMain;

public class DeleteProductGUI extends JFrame implements ActionListener {
    
    private JTextField nameField;
    private JTextField priceField;
    private JTextField idField;
    private UMain main;
    private SellerGUI sellGUI;
    private JSpinner stockSpinner;
    private JFormattedTextField spinnerTextField;
    private JButton actionsButton;

    public DeleteProductGUI(SellerGUI sellGUI, UMain main) {
        this.main=main;
        this.sellGUI=sellGUI;
        // Configuración de la ventana
        setTitle("Elminar Producto");
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
        JLabel stockLabel = new JLabel("Stock:");
        constraints.insets = new Insets(10, 10, 0, 10);
        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(stockLabel, constraints);

        SpinnerModel stockModel = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1);
        stockSpinner = new JSpinner(stockModel);
        JComponent editor = stockSpinner.getEditor();
        spinnerTextField = ((JSpinner.DefaultEditor) editor).getTextField();
        spinnerTextField.setColumns(1); 
        constraints.insets = new Insets(10, 0, 0, 10);       
        constraints.gridx = 1;
        constraints.gridy = 3;
        stockSpinner.setEnabled(false);
        spinnerTextField.setEditable(false);
        panel.add(stockSpinner, constraints);

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
        
        if (command.equals("Atrás")) {
            this.dispose();
        } 
         if (command.equals("Buscar")) {
            String[][] s= main.getProductsByName(nameField.getText());
            if(s.length==0){
                JOptionPane.showMessageDialog(this, "Item not Found", "Error", JOptionPane.INFORMATION_MESSAGE);
            }else{
                nameField.setEditable(false);
                idField.setText(s[0][0]);
                priceField.setText(s[0][2]);
                spinnerTextField.setText(s[0][3]);
                actionsButton.setActionCommand("Eliminar");
                actionsButton.setText("Eliminar");
            }
        }  
        if (command.equals("Eliminar")) {
            if(main.deleteProduct(Integer.valueOf(idField.getText()))){

                JOptionPane.showMessageDialog(this, "Product deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                sellGUI.updateTable();
                this.dispose();
            }else{                
                JOptionPane.showMessageDialog(this, "Error", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}