package com.staffinghq.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class OpenApiSpecFetcher implements CommandLineRunner {

    @Value("${server.port}")
    private int serverPort;

    @Override
    public void run(String... args) throws Exception {
        try {
            HttpClient client = HttpClient.newHttpClient();

            URI docsUri = URI.create("http://localhost:" + serverPort + "/api-docs.yaml");

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(docsUri)
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            Files.writeString(Paths.get("openapi.yaml"), response.body());

            System.out.println("OpenAPI spec has been written to openapi.yaml");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
