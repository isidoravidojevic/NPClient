package napredno.programiranje.tablemodel;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.table.AbstractTableModel;

import napredno.programiranje.communication.Communication;
import napredno.programiranje.domain.Invoice;

public class InvoiceTableModel extends AbstractTableModel{

    List<Invoice> invoices;
    
    public InvoiceTableModel() {
        try {
            this.invoices = Communication.getInstance().GetAllInvoices();
        } catch (Exception ex) {
            Logger.getLogger(InvoiceTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public InvoiceTableModel(List<Invoice> invoices) {
        this.invoices = invoices;
    }
    
    @Override
    public int getRowCount() {
        return invoices.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return invoices.get(rowIndex).getCustomer().getCustomerName();
            case 1:
                return invoices.get(rowIndex).getIssueDate();
            case 2:
                return invoices.get(rowIndex).getPaymentDeadline();
            case 3:
                return invoices.get(rowIndex).getVAT();
            case 4:
                return invoices.get(rowIndex).getTotalValue();
            case 5:
                return invoices.get(rowIndex).isCanceled();
            default:
                throw new AssertionError();
        }
    }
    
    @Override
    public String getColumnName(int column) {
        String[] names = {"Naziv kupca", "Datum izdavanja", "Rok plaćanja", "PDV", "Ukupan iznos", "Stornirana"};
        return names[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return String.class;
            case 1:
                return LocalDate.class;
            case 2:
                return LocalDate.class;
            case 3:
                return Double.class;
            case 4:
                return Double.class;
            case 5:
                return Boolean.class;
            default:
                throw new AssertionError();
        }
    }
    
    public void add(Invoice invoice) {
        invoices.add(invoice);
        fireTableDataChanged();
    }
     
    public Invoice get(int row) {
        return invoices.get(row);
    }
    
    public void addInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
        fireTableDataChanged();
    }
    
    public void delete(int row) {
        invoices.remove(row);
        fireTableDataChanged();
    }
    
}
