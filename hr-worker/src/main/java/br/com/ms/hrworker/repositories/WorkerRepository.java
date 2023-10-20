package br.com.ms.hrworker.repositories;

import br.com.ms.hrworker.entities.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, UUID> {
    @Override
    Optional<Worker> findById(UUID id);
}
