package com.example.redis.Models.ServiceModel;

import com.example.redis.Models.ClientModel.Client;
import com.example.redis.Models.CountryModel.Country;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "client_service")
public class ClientServiceModel implements Serializable {

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", insertable = false, updatable = false)
    @Fetch(FetchMode.JOIN)
    private Client client;


    public ClientServiceModel() {
    }

    public ClientServiceModel(long clientServiceId, long clientId, long serviceId, String active) {
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
