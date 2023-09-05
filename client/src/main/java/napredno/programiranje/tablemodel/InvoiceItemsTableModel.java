package napredno.programiranje.tablemodel;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.table.AbstractTableModel;

import javafx.util.Pair;
import napredno.programiranje.communication.Communication;
import napredno.programiranje.domain.InvoiceItem;

public class InvoiceItemsTableModel extends AbstractTableModel{

    List<InvoiceItem> invoiceItems;
    Pair<Object, Object> pair;
    
    public InvoiceItemsTableModel() {
        try {
            this.invoiceItems = Communication.getInstance().GetAllInvoiceItems();
        } catch (Exception ex) {
            Logger.getLogger(InvoiceItemsTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public InvoiceItemsTableModel(List<InvoiceItem> invoiceItems) {
        this.invoiceItems = invoiceItems;
    }

    public InvoiceItemsTableModel(List<InvoiceItem> invoiceItems, Pair<Object, Object> pair) {
        this.invoiceItems = invoiceItems;
        this.pair = pair;
    }
    
    
    @Override
    public int getRowCount() {
        return invoiceItems.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return invoiceItems.get(rowIndex).getProduct().getProductName();
            case 1:
                return invoiceItems.get(rowIndex).getProduct().getSellingPrice();
            case 2:
                return invoiceItems.get(rowIndex).getQuantity();
            case 3:
                return invoiceItems.get(rowIndex).getDescription();
            case 4:
                return invoiceItems.get(rowIndex).getItemPrice();
            default:
                throw new AssertionError();
        }
    }
    
    @Override
    public String getColumnName(int column) {
        String[] names = {"Proizvod", "Cena proizvoda", "Količina", "Opis", "Vrednost stavke"};
        return names[column];
    }
    
    public void add(InvoiceItem invoiceItem) {
        invoiceItems.add(invoiceItem);
        fireTableDataChanged();
    }

    public InvoiceItem get(int row) {
        return invoiceItems.get(row);
    }

    public void addInvoiceItems(List<InvoiceItem> invoiceItems) {
        this.invoiceItems = invoiceItems;
        fireTableDataChanged();
    }

    public void delete(int row) {
        invoiceItems.remove(row);
        fireTableDataChanged();
    }

}
