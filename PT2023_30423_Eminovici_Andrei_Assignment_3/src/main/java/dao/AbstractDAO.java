package dao;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import bll.ClientBLL;
import connection.ConnectionFactory;
import model.tbl_Bill;
import model.tbl_Client;
import model.tbl_Product;

import javax.swing.table.DefaultTableModel;

/**
 * Abstract class representing a generalization of the Data Access Object. Any kind of DAO class can simply inherit from this class
 * with its desired type.
 * @author Andrei Eminovici
 */
public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * <p>Creates a selection query field based on the name of the input field if
     * selectAll is false or a SELECT * FROM if true
     * </p>
     * @param field the name of the primary key field
     * @param selectAll target specific/all rows
     * @return the string of a selection query
     */
    private String createSelectQuery(String field, boolean selectAll) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        if(!selectAll) {
            sb.append(" WHERE ");
            sb.append(field);
            sb.append(" =?");
        }
        return sb.toString();
    }

    /**
     * <p>Fetches all rows of a table whose name must be the name of T's class as a list of objects
     * </p>
     * @return a list of objects, each object representing a row of the queried table given by type T's class name
     */
    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery(null, true);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * <p>Returns an object of type T if exists at specified ID in the DB or null otherwise
     * This is very similar to findById, with the difference that findById is used for fetching
     * a specific object given by ID while this method is used primarily for querying existence rather
     * than fetching
     * </p>
     * @param id the id where we want to query existence in the DB
     * @return object of type T which exists at the given ID in the DB
     */
    public T existsAtId(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery(type.getDeclaredFields()[0].getName(), false);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if(resultSet.next()) {
                return createObjects(resultSet).get(0);
            } else {
                //System.out.println("No rows found");
                return null;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * <p>Returns an object of type T if exists at specified ID in the DB or null otherwise
     * </p>
     * @param id the id where we want to query existence in the DB
     * @return object of type T which exists at the given ID in the DB
     */
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery(type.getDeclaredFields()[0].getName(), false);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * <p>Converts a resultSet query output into a List of objects
     * </p>
     * @param resultSet output of a sql query execution
     * @return A list of objects representing the conversion of the resultSet into a workable Java data structure
     */
    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                if(type.equals(tbl_Bill.class)) { // Record Bill class
                    ctor.setAccessible(true);
                    String[] fieldNames = new String[type.getDeclaredFields().length];
                    Object[] values =  new Object[type.getDeclaredFields().length];
                    int count = 0;
                    for(Field field : type.getDeclaredFields()) {
                        fieldNames[count] = field.getName();
                        // Perform check for wrapper classes. They need to be converted to primitive for cast to succeed
                        Class<?> fieldType = field.getType();
                        if (fieldType.isPrimitive()) {
                            if (fieldType == int.class) {
                                values[count] = resultSet.getInt(fieldNames[count]);
                            } else if (fieldType == long.class) {
                                values[count] = resultSet.getLong(fieldNames[count]);
                            } else if (fieldType == double.class) {
                                values[count] = resultSet.getDouble(fieldNames[count]);
                            } // Add more primitive types as needed
                        } else {
                            values[count] = fieldType.cast(resultSet.getObject(fieldNames[count]));
                        }
                        ++count;
                    }
                    T instance = (T) ctor.newInstance(values);
                    list.add(instance);
                } else { // Non-record classes
                    ctor.setAccessible(true);
                    T instance = (T) ctor.newInstance();
                    for (Field field : type.getDeclaredFields()) {
                        String fieldName = field.getName();
                        Object value = resultSet.getObject(fieldName);
                        if (fieldName.equals("client_id_order")) {
                            ClientDAO clientDao = new ClientDAO();
                            value = clientDao.findById((Integer) value);
                        } else if (fieldName.equals("prod_id_order")) {
                            ProductDAO productDao = new ProductDAO();
                            value = productDao.findById((Integer) value);
                        }
                        PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                        Method method = propertyDescriptor.getWriteMethod();
                        method.invoke(instance, value);
                    }
                    list.add(instance);
                }
            }
        } catch (InstantiationException | IllegalArgumentException | IllegalAccessException | SecurityException |
                 InvocationTargetException | SQLException | IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * <p>Generalized insert into a table from DB method.
     * </p>
     * @param t the object to be inserted into the DB
     * @return the ID of the inserted item, 0 if failed
     */
    public int insert(T t) throws SQLException, IllegalAccessException {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement insertStatement = null;
        int insertedId = 0;

        StringBuilder insertStatementString = new StringBuilder("INSERT INTO " + type.getSimpleName() + " VALUES(");

        // Fetch all fields of given 't' object
        Field[] fields = type.getDeclaredFields();

        // Obtain field values and append them to SQL query string

        int count = 0;
        for(Field field : fields) {
            field.setAccessible(true);
            Object value = field.get(t);
            field.setAccessible(false);
            // If we have a string, we must surround it with '' to respect SQL syntax
            if(field.getType().equals(String.class)) {
                insertStatementString.append("'");
            }
            // Handle particular case of Client and Product objects which cannot be directly given, only id must be extracted from them
            if(value.getClass().equals(tbl_Client.class)) {
                insertStatementString.append(((tbl_Client) value).getClient_id());
            } else if(value.getClass().equals(tbl_Product.class)) {
                insertStatementString.append(((tbl_Product) value).getProd_id());
            } else {
                insertStatementString.append(value);
            }
            if(field.getType().equals(String.class)) {
                insertStatementString.append("'");
            }
            if(count == fields.length - 1) {
                insertStatementString.append(")");
            } else {
                insertStatementString.append(",");
            }
            ++count;
        }
        // Prepare DB query execution
        insertStatement = dbConnection.prepareStatement(insertStatementString.toString(), Statement.RETURN_GENERATED_KEYS);
        //System.out.println(insertStatementString);
        insertStatement.executeUpdate();

        ResultSet rs = insertStatement.getGeneratedKeys();
        if (rs.next()) {
            insertedId = rs.getInt(1);
        }
        ConnectionFactory.close(insertStatement);
        ConnectionFactory.close(dbConnection);
        return insertedId;
    }

    /**
     * <p>Generalized update row of a table from DB method.
     * </p>
     * @param t the object which will override the old object at id
     * @param id the target ID of the object to be updated with the new t object
     */
    public void update(T t, int id) throws SQLException, IllegalAccessException {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;

        StringBuilder updateStatementString = new StringBuilder("UPDATE " + type.getSimpleName() + " SET ");

        // Fetch all fields of given 't' object
        Field[] fields = type.getDeclaredFields();

        // Obtain field values and append them to SQL query string

        int count = 0;
        String idFieldName = ""; // It is assumed id field is always the first one in the DB structure
        for(Field field : fields) {
            field.setAccessible(true);
            String fieldName = field.getName();
            if(count == 0) {
                idFieldName = fieldName; // Remember it for later
            }
            Object value = field.get(t);
            field.setAccessible(false);
            // Append column name to the update query string
            updateStatementString.append(fieldName);
            updateStatementString.append(" = ");
            // If we have a string, we must surround it with '' to respect SQL syntax
            if(field.getType().equals(String.class)) {
                updateStatementString.append("'");
            }
            // Handle particular case of Client and Product objects which cannot be directly given, only id must be extracted from them
            if(value.getClass().equals(tbl_Client.class)) {
                updateStatementString.append(((tbl_Client) value).getClient_id());
            } else if(value.getClass().equals(tbl_Product.class)) {
                updateStatementString.append(((tbl_Product) value).getProd_id());
            } else {
                updateStatementString.append(value);
            }
            if(field.getType().equals(String.class)) {
                updateStatementString.append("'");
            }
            if(count != fields.length - 1) {
                updateStatementString.append(",");
            }
            ++count;
        }
        // Append WHERE <condition> part
        updateStatementString.append(" WHERE ");
        updateStatementString.append(idFieldName);
        updateStatementString.append(" = ");
        updateStatementString.append(id);

        // Prepare DB query execution
        updateStatement = dbConnection.prepareStatement(updateStatementString.toString());
        updateStatement.executeUpdate();

        // Close connections
        ConnectionFactory.close(updateStatement);
        ConnectionFactory.close(dbConnection);
    }

    /**
     * <p>Generalized delete row of a table from DB method.
     * </p>
     * @param id the target ID of the object to be deleted
     */
    public void remove(int id) throws SQLException {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement removeStatement = null;

        StringBuilder removeStatementString = new StringBuilder("DELETE FROM " + type.getSimpleName() + " WHERE ");

        // Fetch id field of given 't' object(it is assumed id always is first field in DB)
        Field[] fields = type.getDeclaredFields();

        // Obtain field name and append it to the SQL query string
        String idFieldName = fields[0].getName();

        removeStatementString.append(idFieldName);
        removeStatementString.append(" = ");
        removeStatementString.append(id);

        // Prepare DB query execution
        removeStatement = dbConnection.prepareStatement(removeStatementString.toString());
        removeStatement.executeUpdate();

        ConnectionFactory.close(removeStatement);
        ConnectionFactory.close(dbConnection);
    }

    /**
     * <p>Generalized method for getting the table model of a certain table from a DB.
     * Used for 1:1 display of a SQL table into a JTable.
     * </p>
     * @return the table model representing the table present in the DB
     */
    public DefaultTableModel getTableModel() throws IllegalAccessException {
        Field[] fields = type.getDeclaredFields();
        String[] columnNames = new String[fields.length];

        int count = 0;
        for (Field field : fields) {
            columnNames[count] = field.getName();
            // Strip away bill_ from column names for better readability
            if(columnNames[count].contains("bill_")) {
                columnNames[count] = columnNames[count].replace("bill_", "");
            }
            ++count;
        }

        List<T> genericList = (List<T>) findAll();

        Object[][] tableData = new Object[genericList.size()][fields.length];

        count = 0;
        for(T t : genericList) {
            int count2 = 0;
            // Obtain all field values for each object of the list
            for(Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(t);
                if(value.getClass().equals(tbl_Client.class)) {
                    value = ((tbl_Client) value).getClient_id();
                } else if(value.getClass().equals(tbl_Product.class)) {
                    value = ((tbl_Product) value).getProd_id();
                }
                tableData[count][count2] = value;
                ++count2;
            }
            ++count;
        }
        return new DefaultTableModel(tableData, columnNames);
    }
}
