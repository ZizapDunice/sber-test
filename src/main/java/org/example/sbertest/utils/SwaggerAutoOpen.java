package org.example.sbertest.utils;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class SwaggerAutoOpen {

    @PostConstruct
    public void openSwaggerUI() {
        System.out.println("Попытка открыть Swagger UI...");
        final String url = "http://localhost:8888/swagger-ui/index.html";
        try {
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
            } else if (os.contains("mac")) {
                Runtime.getRuntime().exec("open " + url);
            } else if (os.contains("nix") || os.contains("nux")) {
                Runtime.getRuntime().exec("xdg-open " + url);
            } else {
                System.out.println("ОС не поддерживается: " + os);
            }
            System.out.println("Swagger UI открыт!");
        } catch (IOException e) {
            System.out.println("Ошибка при открытии Swagger UI: " + e.getMessage());
        }
    }
}
