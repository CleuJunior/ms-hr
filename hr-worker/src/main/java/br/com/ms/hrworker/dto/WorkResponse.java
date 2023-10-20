package br.com.ms.hrworker.dto;

import br.com.ms.hrworker.entities.Worker;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter @Setter
public class WorkResponse {
    private UUID id;
    private String name;
    private Double dailyIncome;

    public WorkResponse(Worker worker) {
        this(worker.getId(), worker.getName(), worker.getDailyIncome());
    }
}
