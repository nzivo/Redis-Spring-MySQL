package com.example.redis.Models.ClientModel;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "Client")
@Data
@Table(name = "client")
public class Client implements Serializable {
    private static final long serialVersionUID = -1L;
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    private long clientId;

    @Column(name="country_id")
    private int country_id;

    @Column(name="client_code")
    private int client_code;

    @Column(name="client_name")
    private String client_name;

    @Column(name="description")
    private String description;

    @Column(name="email")
    private String email;

    @Column(name="phone")
    private int phone;

    @Column(name="msisdn")
    private long msisdn;

    @Column(name="address")
    private String address;

    @Column(name="active")
    private int active;

    public Client() {
    }

    public Client(int country_id, int client_code, String client_name, String description, String email, int phone, long msisdn, String address, int active) {
        this.country_id = country_id;
        this.client_code = client_code;
        this.client_name = client_name;
        this.description = description;
        this.email = email;
        this.phone = phone;
        this.msisdn = msisdn;
        this.address = address;
        this.active = active;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public int getCountry_id() {
        return country_id;
    }

    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }

    public int getClient_code() {
        return client_code;
    }

    public void setClient_code(int client_code) {
        this.client_code = client_code;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public long getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(int msisdn) {
        this.msisdn = msisdn;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    @Override
    public String toString(){
        return "User{" +
                "id=" + clientId +
                ",username = '" + client_name + '\'' +
                ",address = '" + address + '\'' +
                '}';
    }
}
