package napredno.programiranje.tablemodel;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.table.AbstractTableModel;

import napredno.programiranje.communication.Communication;
import napredno.programiranje.domain.Product;

public class ProductsTableModel extends AbstractTableModel{

    List<Product> products;
    
    public ProductsTableModel() {
        try {
            this.products = Communication.getInstance().getAllProducts();
        } catch (Exception ex) {
            Logger.getLogger(CustomersTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ProductsTableModel(List<Product> products) {
        this.products = products;
    }
    
    @Override
    public int getRowCount() {
        return products.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return products.get(rowIndex).getProductName();
            case 1:
                return products.get(rowIndex).getQuantity();
            case 2:
                return products.get(rowIndex).getMeasurementUnit();
            case 3:
                return products.get(rowIndex).getPurchasePrice();
            case 4:
                return products.get(rowIndex).getSellingPrice();
            case 5:
            	return products.get(rowIndex).getProducer().getProducerName();
            default:
                throw new AssertionError();
        }
    }
    
    @Override
    public String getColumnName(int column) {
        String[] names = {"Naziv", "Količina", "Merna jedinica", "Nabavna cena", "Prodajna cena", "Proizvodjac"};
        return names[column];
    }
    
     public void add(Product product) {
        products.add(product);
        fireTableDataChanged();
    }
     
    public Product get(int row) {
        return products.get(row);
    }
    
    public void addProducts(List<Product> products) {
        this.products = products;
        fireTableDataChanged();
    }
    
    public void delete(int row) {
        products.remove(row);
        fireTableDataChanged();
    }
}

