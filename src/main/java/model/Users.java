package model;

import com.sun.org.apache.xerces.internal.util.Status;

import javax.management.relation.Role;
import java.util.Date;

public class Users {
    private int id;
    private String account;
    private String password;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String email;
    private Date birthDate;
    private Role role;
    private Status status;

    public Users(int id, String username, String password, String firstname, String lastname, String address, String telephone, String email, Date birthday, model.enums.Role role, model.enums.Status status) {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Users(String account, String password) {
        this.account = account;
        this.password = password;
    }
}
