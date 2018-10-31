package com.atosDev.reader.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.atosDev.reader.entity.Queue;

@Repository
public interface QueueRepo extends JpaRepository<Queue, Integer> {

}
