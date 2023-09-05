package napredno.programiranje.tablemodel;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.table.AbstractTableModel;

import napredno.programiranje.communication.Communication;
import napredno.programiranje.domain.Customer;

public class CustomersTableModel extends AbstractTableModel {

    List<Customer> customers;

    public CustomersTableModel() {
        try {
            this.customers = Communication.getInstance().getAllCustomers();
        } catch (Exception ex) {
            Logger.getLogger(CustomersTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public CustomersTableModel(List<Customer> customers) {
        this.customers = customers;
    }

    @Override
    public int getRowCount() {
        return customers.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return customers.get(rowIndex).getCustomerName();
            case 1:
                return customers.get(rowIndex).getAddress();
            case 2:
                return customers.get(rowIndex).getVATnumber();
            case 3:
                return customers.get(rowIndex).getCompanyNumber();
            case 4:
            	return customers.get(rowIndex).getCity().getCityName();
            default:
                throw new AssertionError();
        }
    }

    @Override
    public String getColumnName(int column) {
        String[] names = {"Naziv", "Adresa", "PIB", "Matični broj", "Grad"};
        return names[column];
    }

    public void add(Customer customer) {
        customers.add(customer);
        fireTableDataChanged();
    }

    public Customer get(int row) {
        return customers.get(row);
    }

    public void addCustomers(List<Customer> customers) {
        this.customers = customers;
        fireTableDataChanged();
    }

    public void delete(int row) {
        customers.remove(row);
        fireTableDataChanged();
    }

}