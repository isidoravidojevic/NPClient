package napredno.programiranje.communication;

import java.net.Socket;
import java.util.List;

import javafx.util.Pair;
import napredno.programiranje.domain.City;
import napredno.programiranje.domain.Customer;
import napredno.programiranje.domain.Invoice;
import napredno.programiranje.domain.InvoiceItem;
import napredno.programiranje.domain.InvoiceReceptionType;
import napredno.programiranje.domain.Producer;
import napredno.programiranje.domain.Product;
import napredno.programiranje.domain.User;

public class Communication {

	Socket socket;
    Sender sender;
    Receiver receiver;
    private static Communication instance;

    private Communication() throws Exception {
        socket = new Socket("localhost", 9000);
        sender = new Sender(socket);
        receiver = new Receiver(socket);
    }

    public static Communication getInstance() throws Exception {
        if (instance == null) {
            instance = new Communication();
        }
        return instance;
    }

    public User login(String username, String password) throws Exception {
        User user = new User(null, null, username, password);
        Request request = new Request(Operation.LOGIN, user);
        sender.send(request);
        System.out.println("Korisnik je poslat.");
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (User) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public String logout() throws Exception {
        Request request = new Request(Operation.LOGOUT, null);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            socket.close();
            instance = null;
            return (String) response.getResult();
        } else {
            throw response.getException();
        }
    }
    
    public Response addProduct(Product product) throws Exception {
        Request request = new Request(Operation.ADD_PRODUCT, product);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return response;
        } else {
            throw response.getException();
        }
    }

    public List<Product> getAllProducts() throws Exception {
        Request request = new Request(Operation.GET_ALL_PRODUCTS, null);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (List) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public int deleteProduct(Product product) throws Exception {
        Request request = new Request(Operation.DELETE_PRODUCT, product);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (int)response.getResult();
        } else {
            throw response.getException();
        }
    }
    
    
    public int editProduct(Product product) throws Exception {
        Request request = new Request(Operation.EDIT_PRODUCT, product);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (int) response.getResult();
        } else {
            throw response.getException();
        }
    }
    
    public Response addCustomer(Customer customer) throws Exception {
        Request request = new Request(Operation.ADD_CUSTOMER, customer);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return response;
        } else {
            throw response.getException();
        }
    }

    public List<Customer> getAllCustomers() throws Exception {
        Request request = new Request(Operation.GET_ALL_CUSTOMERS, null);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (List<Customer>) response.getResult();
        } else {
            throw response.getException();
        }
    }
    
    public int deleteCustomer(Customer customer) throws Exception {
        Request request = new Request(Operation.DELETE_CUSTOMER, customer);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (int)response.getResult();
        } else {
            throw response.getException();
        }
    }
    
    public int editCustomer(Customer customer) throws Exception {
        Request request = new Request(Operation.EDIT_CUSTOMER, customer);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (int) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public long addInvoice(Invoice invoice) throws Exception {
        Request request = new Request(Operation.ADD_INVOICE, invoice);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (long)response.getResult();
        } else {
            throw response.getException();
        }
    }
    
    public int cancelInvoice(Invoice invoice) throws Exception {
        Request request = new Request(Operation.CANCEL_INVOICE, invoice);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (int) response.getResult();
        } else {
            throw response.getException();
        }
    }
    
    public List<Invoice> GetAllInvoices() throws Exception{
        Request request=new Request(Operation.GET_ALL_INVOICES, null);
        sender.send(request);
        Response response=(Response)receiver.receive();
        if(response.getException()==null){
            return (List<Invoice>)response.getResult();
        }else{
            throw response.getException();
        }
    }
        
    public List<Invoice> GetAllInvoicesParameter(Pair pair) throws Exception{
        Request request=new Request(Operation.GET_ALL_INVOICES_PARAMETER, pair);
        sender.send(request);
        Response response=(Response)receiver.receive();
        if(response.getException()==null){
            return (List<Invoice>)response.getResult();
        }else{
            throw response.getException();
        }
    }
    
    public Response addInvoiceItem(InvoiceItem invoiceItem) throws Exception {
        Request request = new Request(Operation.ADD_INVOICE_ITEM, invoiceItem);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return response;
        } else {
            throw response.getException();
        }
    }
    
    public List<InvoiceItem> GetAllInvoiceItemsParameter(Pair pair) throws Exception{
        Request request=new Request(Operation.GET_ALL_INVOICE_ITEMS_PARAMETER, pair);
        sender.send(request);
        
        Response response=(Response)receiver.receive();
        if(response.getException()==null){
            return (List<InvoiceItem>)response.getResult();
        }else{
            throw response.getException();
        }
    }
    
    public List<InvoiceItem> GetAllInvoiceItems() throws Exception{
        Request request=new Request(Operation.GET_ALL_INVOICE_ITEMS, null);
        sender.send(request);
        
        Response response=(Response)receiver.receive();
        if(response.getException()==null){
            return (List<InvoiceItem>)response.getResult();
        }else{
            throw response.getException();
        }
    }  
    
    public Response addInvoiceReceptionType(InvoiceReceptionType invoiceReceptionType) throws Exception {
        Request request = new Request(Operation.ADD_INVOICE_RECEPTION_TYPE, invoiceReceptionType);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return response;
        } else {
            throw response.getException();
        }
    }
    
    public List<City> getAllCities() throws Exception {
        Request request = new Request(Operation.GET_ALL_CITIES, null);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (List<City>) response.getResult();
        } else {
            throw response.getException();
        }
    }
    
    public List<Producer> getAllProducers() throws Exception {
        Request request = new Request(Operation.GET_ALL_PRODUCERS, null);
        sender.send(request);
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (List<Producer>) response.getResult();
        } else {
            throw response.getException();
        }
    }
}
