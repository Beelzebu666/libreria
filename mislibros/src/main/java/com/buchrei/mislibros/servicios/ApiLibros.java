package com.buchrei.mislibros.servicios;

import com.buchrei.mislibros.modelos.Autor;
import com.buchrei.mislibros.modelos.Libro;
import com.buchrei.mislibros.parseo.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class ApiLibros {

    private final HttpClient httpClient;
    private final JsonParser jsonParser;

    @Autowired
    public ApiLibros(JsonParser jsonParser) {
        this.httpClient = HttpClient.newHttpClient();
        this.jsonParser = jsonParser;
    }

    

    private String enviarSolicitudHttp(String url) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error al obtener datos de la URL: " + url, e);
        }
        return response.body();
    }
}
