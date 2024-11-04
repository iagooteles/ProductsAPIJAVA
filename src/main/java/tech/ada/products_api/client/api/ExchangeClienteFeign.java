package tech.ada.products_api.client.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "exchange-client", url = "https://economia.awesomeapi.com.br")
public interface ExchangeClienteFeign {

    @GetMapping("/json/last/USD-BRL")
    ResponseEntity<ExchangeResponseDTO> getExchange();
}
