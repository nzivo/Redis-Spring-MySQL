package com.example.redis.Repositories.ServiceRepository;

import com.example.redis.Models.ServiceModel.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository("ServiceRepository")
public interface ServiceRepository extends JpaRepository<Service, Long> {
    Service findByServiceId(@Param("serviceId") long serviceId);
//    List<ClientServiceModel> findByClientId(@Param("clientId") long clientId);
}