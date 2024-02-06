package bll;

import bll.validators.*;
import dao.*;
import model.exception.InvalidInstanceException;
import model.tbl_Client;

import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * Business layer for the client table interactions
 * @author Andrei Eminovici
 */
public class ClientBLL {
    /**
     * Create a reference to the Client Data Access Object
     */
    ClientDAO clientDao = new ClientDAO();
    /**
     * Create a list to be populated with appropriate validators for a client
     */
    private List<Validator<tbl_Client>> validators;

    public ClientBLL() {
        validators = new ArrayList<Validator<tbl_Client>>();
        validators.add(new EmailValidator());
        validators.add(new ClientAgeValidator());
    }

    public tbl_Client findClientById(int id) {
        tbl_Client st = clientDao.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The client with id =" + id + " was not found!");
        }
        return st;
    }

    public boolean existsAtId(int id) {
        tbl_Client st = clientDao.existsAtId(id);
        if (st == null) {
            return false;
        }
        return true;
    }

    public tbl_Client getRandomExistingClient() {
        Random random = new Random();
        ArrayList<tbl_Client> clients = (ArrayList<tbl_Client>) clientDao.findAll();
        int randomIndex = random.nextInt(clients.size());
        return clients.get(randomIndex);
    }

    public int insertClient(tbl_Client client) throws SQLException, IllegalAccessException {
        for (Validator<tbl_Client> v : validators) {
            v.validate(client);
        }
        return clientDao.insert(client);
    }

    public void updateClient(tbl_Client client, int id) throws SQLException, IllegalAccessException, InvalidInstanceException {
        if(clientDao.findById(id) == null) {
            throw new InvalidInstanceException("The client ID is never in use. Please choose a valid ID");
        }
        for (Validator<tbl_Client> v : validators) {
            v.validate(client);
        }
        clientDao.update(client, id);
    }

    public void removeClient(int id) throws SQLException, InvalidInstanceException {
        if(clientDao.findById(id) == null) {
            throw new InvalidInstanceException("The client ID is already in use. Please choose a valid ID");
        }
        clientDao.remove(id);
    }

    public List<tbl_Client> getAllClients() {
        return clientDao.findAll();
    }

    public DefaultTableModel getTableModel() throws IllegalAccessException {
        return clientDao.getTableModel();
    }
}
