package br.com.ms.hrworker.resources;


import br.com.ms.hrworker.dto.WorkResponse;
import br.com.ms.hrworker.seriveces.WorkerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RefreshScope
@RestController
@RequestMapping(value = "/workers")
public class WorkerResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(WorkerResource.class);
    private final WorkerService service;

    public WorkerResource(WorkerService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<WorkResponse>> findAll() {
        List<WorkResponse> response = this.service.findAll();
        LOGGER.info("Worker List");
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<WorkResponse> findById(@PathVariable Long id) {
        WorkResponse response = this.service.findById(id);
        LOGGER.info("Worker Found");
        return ResponseEntity.ok(response);
    }

}
