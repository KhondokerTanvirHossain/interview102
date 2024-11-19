package com.tanvir.spring_boot_mvc_jpa_base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    @PostMapping("/hello")
    public HelloResponse home(@RequestBody HelloRequest request) {
        log.info("Request: {}", request);
        return new HelloResponse("Hello, " + request.getName());
    }

}

@Data
@AllArgsConstructor
@NoArgsConstructor
class HelloRequest {
    String name;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class HelloResponse {
    String data;
}
