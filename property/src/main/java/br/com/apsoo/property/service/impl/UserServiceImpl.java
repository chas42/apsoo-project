package br.com.apsoo.property.service.impl;

import br.com.apsoo.property.dto.UserDTO;
import br.com.apsoo.property.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    private final WebClient.Builder wBuilder;
    private final DiscoveryClient discoveryClient;


    @Autowired
    UserServiceImpl( WebClient.Builder wBuilder,DiscoveryClient discoveryClient){
        this.wBuilder = wBuilder;
        this.discoveryClient = discoveryClient;
    }

    public WebClient client() {
        String serviceUrl = getServiceUrl("user");
        return wBuilder.baseUrl(serviceUrl).build();
    }

    @Override
    public UserDTO getById(Long id) {

       return client().get()
                .uri("/user/{id}", id)
                .retrieve()
                .bodyToMono(UserDTO.class)
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
