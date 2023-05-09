package interfaz;

import java.awt.*;
import javax.swing.*;

public class SellerGUI extends JFrame {
    
    public SellerGUI() {
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
        setJMenuBar(menuBar);

        JPanel panel = new JPanel(new BorderLayout());
        JTable table = new JTable(new Object[][]{
            {"Producto 1", 10.99, 100, "usuario3@example.com"},
            {"Producto 2", 19.99, 50, "usuario3@example.com"},
            {"Producto 3", 15.99, 75, "usuario3@example.com"},
        }, new Object[]{"Nombre", "Precio", "Stock", "Vendedor"});
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        table.setEnabled(false);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        // Agregar panel a la ventana principal
        getContentPane().add(panel);

        // Mostrar ventana principal
        setVisible(true);
    }
}

