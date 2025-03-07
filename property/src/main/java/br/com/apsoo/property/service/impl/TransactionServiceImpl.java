package br.com.apsoo.property.service.impl;

import br.com.apsoo.property.dto.CreateTransactionDTO;
import br.com.apsoo.property.dto.TransactionDTO;
import br.com.apsoo.property.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final WebClient.Builder wBuilder;
    private final DiscoveryClient discoveryClient;


    @Autowired
    TransactionServiceImpl(WebClient.Builder wBuilder, DiscoveryClient discoveryClient) {
        this.wBuilder = wBuilder;
        this.discoveryClient = discoveryClient;
    }

    public WebClient client() {
        String serviceUrl = getServiceUrl("payment");
        return wBuilder.baseUrl(serviceUrl).build();
    }

    @Override
    public TransactionDTO create(CreateTransactionDTO transactionDTO) {
        return client()
                .post()
                .uri("/transaction")
                .header("Content-Type", "application/json")
                .bodyValue(transactionDTO)
                .retrieve()
                .bodyToMono(TransactionDTO.class)
                .block();
    }

    public String getServiceUrl(String serviceName) {
        List<ServiceInstance> instances = discoveryClient.getInstances(serviceName);

        instances.forEach(i -> System.out.println(i.getUri().toString()));

        if (instances == null || instances.isEmpty()) {
            throw new RuntimeException("Serviço não encontrado no Eureka: " + serviceName);
        }

        // Retorna uma instância aleatória para distribuição de carga manual
        ServiceInstance instance = instances.getFirst();
        return instance.getUri().toString();
    }


}
