package com.example.redis.Models.ServiceModel;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

public class ClientService implements Serializable {

    private static final long serialVersionUID = -1L;
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name="id")
    private long clientServiceId;

    @Column(name = "client_id")
    private long clientId;

    @Column(name = "service_id")
    private long serviceId;

    @Column(name = "active")
    private String active;

    public ClientService() {
    }

    public ClientService(long clientServiceId, long clientId, long serviceId, String active) {
        this.clientServiceId = clientServiceId;
        this.clientId = clientId;
        this.serviceId = serviceId;
        this.active = active;
    }

    public long getClientServiceId() {
        return clientServiceId;
    }

    public void setClientServiceId(long clientServiceId) {
        this.clientServiceId = clientServiceId;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public long getServiceId() {
        return serviceId;
    }

    public void setServiceId(long serviceId) {
        this.serviceId = serviceId;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }
}
