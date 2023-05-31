package interfaz;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import users.UMain;
import java.awt.event.*;

public class SellerGUI extends JFrame implements ActionListener{
    private JTable table;
    private UMain main;

    public SellerGUI(UMain main) {
        this.main=main;
        // Configuración de la ventana principal
        setTitle("Seller Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        // Creación de componentes
        JMenuBar menuBar = new JMenuBar();
        JMenu productsMenu = new JMenu("Products");
        JMenu getAllMenu = new JMenu("Get All Products");
        JMenuItem addProductItem = new JMenuItem("Add Product");
        JMenuItem updateProductItem = new JMenuItem("Update Product");
        JMenuItem deleteProductItem = new JMenuItem("Delete Product");
        JMenuItem getAllProductsItem = new JMenuItem("Get All Products");
        JMenuItem getAllAskedProductsItem = new JMenuItem("Get All Products that Had Been Asked");
        JMenu getProductsMenu = new JMenu("Get Products");
        JMenuItem getProductsByNameItem = new JMenuItem("Get Products by Name");
        JMenuItem getProductsByStockItem = new JMenuItem("Get Products by Stock");
        JMenuItem getProductsByPriceItem = new JMenuItem("Get Products by Price");
        JMenuItem getProductsLessThanPriceItem = new JMenuItem("Get Products with Price Less Than");
        JMenuItem getProductsLessThanStockItem = new JMenuItem("Get Products with Stock Less Than");
        JMenuItem signOutItem = new JMenuItem("Sign Out");

        // Agregar ActionListener a los elementos del menú
        addProductItem.addActionListener(this);
        updateProductItem.addActionListener(this);
        deleteProductItem.addActionListener(this);
        getAllProductsItem.addActionListener(this);
        getAllAskedProductsItem.addActionListener(this);
        getProductsByNameItem.addActionListener(this);
        getProductsByStockItem.addActionListener(this);
        getProductsByPriceItem.addActionListener(this);
        getProductsLessThanPriceItem.addActionListener(this);
        getProductsLessThanStockItem.addActionListener(this);
        signOutItem.addActionListener(this);


        productsMenu.add(addProductItem);
        productsMenu.add(updateProductItem);
        productsMenu.add(deleteProductItem);
        getAllMenu.add(getAllProductsItem);
        getAllMenu.add(getAllAskedProductsItem);
        getProductsMenu.add(getProductsByNameItem);
        getProductsMenu.add(getProductsByStockItem);
        getProductsMenu.add(getProductsByPriceItem);
        getProductsMenu.add(getProductsLessThanPriceItem);
        getProductsMenu.add(getProductsLessThanStockItem);
        menuBar.add(productsMenu);        
        menuBar.add(getAllMenu);
        menuBar.add(getProductsMenu);
        menuBar.add(signOutItem);
        this.setJMenuBar(menuBar);

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

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        // Actualizar la tabla según la opción seleccionada del menú
        if (command.equals("Add Product")) {
        addProduct();
        } else if (command.equals("Update Product")) {
        updateProduct();
        } else if (command.equals("Delete Product")) {
        deleteProduct();
        } else if (command.equals("Get All Products")) {
        getAllProducts();
        } else if (command.equals("Get All Products that Had Been Asked")) {
        //getAllAskedProducts();
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

    private void deleteProduct() {
        DeleteProductGUI delGUI=new DeleteProductGUI(this, main);
    }

    private void updateProduct() {
         UpdateProductGUI upGUI = new UpdateProductGUI(this, main);
    }

    private void addProduct(){
        AddProductGUI addP= new AddProductGUI(this, main);
    }

    private void updateTable(String[][] newProductsData) {

        // Obtener el modelo de la tabla actual
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setDataVector(newProductsData, new Object[]{"Nombre", "Precio", "Stock", "Vendedor"});

        // Mostrar mensaje de éxito
        JOptionPane.showMessageDialog(this, "Products updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    private void getAllProducts(){
        updateTable(new String[][]{
            {"Producto 4", "10.99", "100", "usuario3@example.com"},
            {"Producto 5", "19.99", "50", "usuario3@example.com"},
            {"Producto 6", "15.99", "75", "usuario3@example.com"},
        });
    }
}
