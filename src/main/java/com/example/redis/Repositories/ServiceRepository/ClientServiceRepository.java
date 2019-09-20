package com.example.redis.Repositories.ServiceRepository;

import com.example.redis.Models.ServiceModel.ClientServiceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("ClientServiceRepository")
public interface ClientServiceRepository extends JpaRepository<ClientServiceModel, Long> {
    List<ClientServiceModel> findByClientId(@Param("clientId") long clientId);
}