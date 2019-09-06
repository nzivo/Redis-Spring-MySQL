package com.example.redis.Repositories.ClientRepository;

import com.example.redis.Models.ClientModel.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("ClientRepository")
public interface ClientRepository extends JpaRepository<Client, Long> {
}