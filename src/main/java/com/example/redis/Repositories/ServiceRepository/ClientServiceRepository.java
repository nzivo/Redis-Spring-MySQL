package com.example.redis.Repositories.ServiceRepository;

import com.example.redis.Models.ServiceModel.ClientServiceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("ClientServiceRepository")
public interface ClientServiceRepository extends JpaRepository<ClientServiceModel, Long> {
}