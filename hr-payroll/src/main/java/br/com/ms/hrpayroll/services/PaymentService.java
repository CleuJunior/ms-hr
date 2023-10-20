package br.com.ms.hrpayroll.services;

import br.com.ms.hrpayroll.entities.Payment;
import br.com.ms.hrpayroll.entities.Worker;
import br.com.ms.hrpayroll.feignclients.WorkerFeignClient;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentService {
    private final WorkerFeignClient workerFeignClient;

    public PaymentService(WorkerFeignClient workerFeignClient) {
        this.workerFeignClient = workerFeignClient;
    }

    public Payment getPayment(UUID workerId, int days) {
		Worker worker = workerFeignClient.findById(workerId).getBody();
        return new Payment(worker.getName(), worker.getDailyIncome(), days);
    }
}
