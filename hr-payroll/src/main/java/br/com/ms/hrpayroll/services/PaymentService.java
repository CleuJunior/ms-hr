package br.com.ms.hrpayroll.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.ms.hrpayroll.entities.Payment;
import br.com.ms.hrpayroll.entities.Worker;
import br.com.ms.hrpayroll.feignclients.WorkerFeignClient;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class PaymentService {
	private final WorkerFeignClient workerFeignClient;
	private final RestTemplate template;

	@Value("${hr-worker.host}")
	private String host;

	public PaymentService(WorkerFeignClient workerFeignClient, RestTemplate template) {
		this.workerFeignClient = workerFeignClient;
		this.template = template;
	}

	public Payment getPayment(UUID workerId, int days) {
		Map<String, String> uriVariable = new HashMap<>();
		uriVariable.put("id", workerId.toString());
				
		Worker worker = this.template.getForObject(host + "/workers/{id}", Worker.class, uriVariable);
//		Worker worker = workerFeignClient.findById(workerId).getBody();
		return new Payment(worker.getName(), worker.getDailyIncome(), days);
	}
}
