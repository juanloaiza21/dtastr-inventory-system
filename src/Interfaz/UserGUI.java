package interfaz;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import users.UMain;

public class UserGUI extends JFrame implements ActionListener{
    private JTable table;
    private UMain main;

    public UserGUI(UMain main) {
        this.main=main;
        // Configuración de la ventana principal
        setTitle("User Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Creación de componentes
        JMenuBar menuBar = new JMenuBar();
        JMenu productsMenu = new JMenu("Products");
        JMenuItem buyProductItem = new JMenuItem("Buy Product");
        JMenuItem makeDevolutionItem = new JMenuItem("Make a Refund");
        JMenuItem askForProductItem = new JMenuItem("Ask for a Product");
        JMenuItem getProductsItem = new JMenuItem("Get Products");
        JMenu getProductsMenu = new JMenu("Get Products");
        JMenuItem getProductsByNameItem = new JMenuItem("Get Products by Name");
        JMenuItem getProductsByStockItem = new JMenuItem("Get Products by Stock");
        JMenuItem getProductsByPriceItem = new JMenuItem("Get Products by Price");
        JMenuItem getProductsLessThanPriceItem = new JMenuItem("Get Products with Price Less Than");
        JMenuItem getProductsLessThanStockItem = new JMenuItem("Get Products with Stock Less Than");
        JMenuItem signOutItem = new JMenuItem("Sign Out");

        // Agregar ActionListener a los elementos del menú
        buyProductItem.addActionListener(this);
        makeDevolutionItem.addActionListener(this);
        askForProductItem.addActionListener(this);
        getProductsItem.addActionListener(this);
        getProductsByNameItem.addActionListener(this);
        getProductsByStockItem.addActionListener(this);
        getProductsByPriceItem.addActionListener(this);
        getProductsLessThanPriceItem.addActionListener(this);
        getProductsLessThanStockItem.addActionListener(this);
        signOutItem.addActionListener(this);


        productsMenu.add(buyProductItem);
        productsMenu.add(makeDevolutionItem);
        productsMenu.add(askForProductItem);
        getProductsMenu.add(getProductsItem);
        getProductsMenu.add(getProductsByNameItem);
        getProductsMenu.add(getProductsByStockItem);
        getProductsMenu.add(getProductsByPriceItem);
        getProductsMenu.add(getProductsLessThanPriceItem);
        getProductsMenu.add(getProductsLessThanStockItem);
        menuBar.add(productsMenu);
        menuBar.add(getProductsMenu);
        menuBar.add(signOutItem);
        setJMenuBar(menuBar);

        JPanel panel = new JPanel(new BorderLayout());
        table = new JTable(new DefaultTableModel(new String[][]{
            {"Producto 1", "10.99", "100", "usuario3@example.com"},
            {"Producto 2", "19.99", "50", "usuario3@example.com"},
            {"Producto 3", "15.99", "75", "usuario3@example.com"},
        }, new Object[]{"Nombre", "Precio", "Stock", "Vendedor"}));        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        table.setEnabled(false);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        // Agregar panel a la ventana principal
        getContentPane().add(panel);

        // Mostrar ventana principal
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        // Actualizar la tabla según la opción seleccionada del menú
        if (command.equals("Buy Product")) {
            buyProduct();
        } else if (command.equals("Make a Refund")) {
            makeRefund();
        } else if (command.equals("Ask for a Product")) {
            askForProduct();
        } else if (command.equals("Get Products")) {
            //getProducts();
        } else if (command.equals("Get Products by Name")) {
            //getProductsByName();
        } else if (command.equals("Get Products by Stock")) {
            //getProductsByStock();
        } else if (command.equals("Get Products by Price")) {
            //getProductsByPrice();
        } else if (command.equals("Get Products with Price Less Than")) {
            //getProductsLessThanPrice();
        } else if (command.equals("Get Products with Stock Less Than")) {
            //getProductsLessThanStock();
        } else if (command.equals("Sign Out")) {
        //signOut();
        }
    }

    private void askForProduct() {        
        AskProductGUI buyGUI=new AskProductGUI(this, main);
    }

    private void makeRefund() {
        RefundGUI refunGUI=new RefundGUI(this, main);
        System.out.println("ref");
    }

    private void buyProduct() {
        BuyProductGUI buyGUI=new BuyProductGUI(this, main);
    }

    private void updateTable(String[][] newProductsData) {
    int rowCount = table.getRowCount();

    // Actualizar los datos de la tabla con los nuevos valores
    DefaultTableModel model = (DefaultTableModel) table.getModel();
    model.setDataVector(newProductsData, new Object[]{"Nombre", "Precio", "Stock", "Vendedor"});

    // Mostrar mensaje de éxito
    JOptionPane.showMessageDialog(this, "Products updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
    }


}