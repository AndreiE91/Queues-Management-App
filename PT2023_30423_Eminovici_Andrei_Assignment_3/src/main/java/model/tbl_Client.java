package model;

/**
 * The 1:1 class representing the tbl_Client present into the DB.
 * @author Andrei Eminovici
 */
public class tbl_Client {
    /**
     * The ID of the client in the DB
     */
    private int client_id;
    /**
     * The first name of the client
     */
    private String first_name;
    /**
     * The last name of the client
     */
    private String last_name;
    /**
     * The address of the client
     */
    private String address;
    /**
     * The email of the client
     */
    private String email;
    /**
     * The age of the client
     */
    private int age;


    public tbl_Client(int client_id, int age, String first_name, String last_name, String address, String email) {
        this.client_id = client_id;
        this.age = age;
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.email = email;
    }

    public tbl_Client() {

    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Client [id=" + client_id + ", first_name=" + first_name + ", last_name=" + last_name + ", address=" + address + ", email=" + email + ", age=" + age
                + "]";
    }
}
