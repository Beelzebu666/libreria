package com.buchrei.mislibros.servicios;

import com.buchrei.mislibros.modelos.Libro;
import com.buchrei.mislibros.repositorio.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicioLibros {

    private final LibroRepository libroRepository;

    @Autowired
    public ServicioLibros(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public void guardarLibro(String titulo, String autor, String genero) {
        Libro libro = new Libro(titulo, autor, genero);
        libroRepository.save(libro);
        System.out.println("Libro guardado correctamente: " + libro);
    }
}
