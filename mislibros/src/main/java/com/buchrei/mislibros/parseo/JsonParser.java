package com.buchrei.mislibros.parseo;

import com.buchrei.mislibros.modelos.Autor;
import com.buchrei.mislibros.modelos.Libro;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JsonParser {

    public List<Libro> parsearLibrosDesdeJson(String datosJson) {
        List<Libro> libros = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(datosJson);
            JSONArray booksArray = jsonObject.getJSONArray("results");

            for (int i = 0; i < booksArray.length(); i++) {
                JSONObject libroJson = booksArray.getJSONObject(i);
                Libro libro = new Libro();
                libro.setTitulo(libroJson.getString("title"));
                libro.setAutores(parsearAutoresDesdeJson(libroJson.getJSONArray("authors")));
                libro.setIdioma(libroJson.optString("languages")); // Usamos optString para evitar excepciones si no hay idioma
                libro.setGeneros(parsearGenerosDesdeJson(libroJson.getJSONArray("subjects")));
                libros.add(libro);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            // Manejo de la excepción según tu lógica de la aplicación
        }

        return libros;
    }

    private List<String> parsearAutoresDesdeJson(JSONArray authorsArray) throws JSONException {
        List<String> autores = new ArrayList<>();
        for (int j = 0; j < authorsArray.length(); j++) {
            JSONObject autorJson = authorsArray.getJSONObject(j);
            autores.add(autorJson.getString("name"));
        }
        return autores;
    }

    private List<String> parsearGenerosDesdeJson(JSONArray subjectsArray) throws JSONException {
        List<String> generos = new ArrayList<>();
        for (int k = 0; k < subjectsArray.length(); k++) {
            generos.add(subjectsArray.getString(k));
        }
        return generos;
    }

    public List<Autor> parsearAutoresDesdeJson(String datosJson) {
        List<Autor> autores = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(datosJson);
            JSONArray authorsArray = jsonObject.getJSONArray("results");

            for (int i = 0; i < authorsArray.length(); i++) {
                JSONObject autorJson = authorsArray.getJSONObject(i);
                Autor autor = new Autor();
                autor.setNombre(autorJson.getString("name"));
                autor.setFechaNacimiento(autorJson.optString("birth_year"));
                autor.setFechaMuerte(autorJson.optString("death_year"));
                autores.add(autor);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            // Manejo de la excepción según tu lógica de la aplicación
        }

        return autores;
    }
}
