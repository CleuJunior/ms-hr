package br.com.ms.hrworker.seriveces;

import br.com.ms.hrworker.dto.WorkResponse;
import br.com.ms.hrworker.entities.Worker;
import br.com.ms.hrworker.repositories.WorkerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class WorkerService {
    private final WorkerRepository repository;

    public WorkerService(WorkerRepository repository) {
        this.repository = repository;
    }

    public List<WorkResponse> findAll() {
        return this.repository.findAll()
                .stream()
                .map(WorkResponse::new)

                .collect(Collectors.toList());
    }
    public WorkResponse findById(UUID id) {
        Worker response = this.repository.findById(id).get();
        return new WorkResponse(response);
    }
}
