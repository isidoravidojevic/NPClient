package napredno.programiranje.tablemodel;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.table.AbstractTableModel;

import com.google.gson.Gson;

import napredno.programiranje.communication.Communication;
import napredno.programiranje.domain.Customer;

public class CustomersTableModel extends AbstractTableModel {

	List<Customer> customers;

	public CustomersTableModel() {
		try {
			File temp = Paths.get(".").toAbsolutePath().normalize().toFile();
			File file = new File(temp+"/outputCustomers.json");
			if (file.exists()) {
				if (file.delete()) {
					System.out.println("Prethodni fajl je obrisan.");
				} else {
					System.out.println("Nije moguće obrisati prethodni fajl.");
				}
			}
			this.customers = Communication.getInstance().getAllCustomers();
			Gson gson = new Gson();
			String customersJson = gson.toJson(this.customers);
			FileWriter fw = new FileWriter(temp+"/outputCustomers.json");
			fw.write(customersJson);
			fw.close();
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
		String[] names = { "Naziv", "Adresa", "PIB", "Matični broj", "Grad" };
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