package com.buchrei.mislibros;

import com.buchrei.mislibros.principal.PrincipalMenu;
import com.buchrei.mislibros.servicios.ApiLibros;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MislibrosApplication implements CommandLineRunner {

    @Autowired
    private ApiLibros apiLibros;

    public static void main(String[] args) {
        SpringApplication.run(MislibrosApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        PrincipalMenu principalMenu = new PrincipalMenu(apiLibros);
        principalMenu.mostrarMenu();
    }
}
