package edu.ieti.bidify.util;

import edu.ieti.bidify.model.*;
import edu.ieti.bidify.service.*;
import edu.ieti.bidify.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Component
/*
    Esta clase se ejecuta una única vez con el objetivo de cargar datos al mongoDB
 */
public class createSubastas implements CommandLineRunner {
    /*
    @Autowired
    ProductoService productoService;
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    SubastaService subastaService;

     */
    @Override
    public void run(String... args) throws Exception {
        /*
        //productos
        ProductoDto producto1 = new ProductoDto("Jordan One", (float) 600000, "https://phantom-expansion.unidadeditorial.es/6239da431613d30a7ade440a4719e3db/crop/0x378/1074x982/resize/828/f/jpg/assets/multimedia/imagenes/2022/03/21/16478732471407.jpg");
        ProductoDto producto2 = new ProductoDto("Camara", (float) 800000, "https://www.workshopexperience.com/wp-content/uploads/2017/07/marcas-de-camaras-fotograficas-4.jpg");
        ProductoDto producto3 = new ProductoDto("Porsche 911 GT3", (float) 351500000, "https://files.porsche.com/filestore/image/multimedia/none/992-gt3-modelimage-sideshot/model/765dfc51-51bc-11eb-80d1-005056bbdc38/porsche-model.png");
        ProductoDto producto4 = new ProductoDto("Vinilo Michael Jackson", (float) 2000000000, "https://http2.mlstatic.com/D_NQ_NP_664991-MCO71457373138_092023-O.webp");
        ProductoDto producto5 = new ProductoDto("Funko pop Michael Jackson", (float) 600000, "https://http2.mlstatic.com/D_NQ_NP_631613-MCO71749069309_092023-O.webp");
        productoService.save(producto1);
        productoService.save(producto2);
        productoService.save(producto3);
        productoService.save(producto4);
        productoService.save(producto5);

        List<ProductoDto> productos = new ArrayList<>();
        productos.add(producto5);
        productos.add(producto4);
        productos.add(producto3);


        Usuario usuario1 = new Usuario("migue", "Miguel Angel", "miguel@gmail.com", "123");
        Usuario usuario2 = new Usuario("jaider", "Jaider Gonzalez", "jaider@gmail.com", "123");
        Usuario usuario3 = new Usuario("santi", "Santiago Gonzalez", "santiago@gmail.com", "123");
        Usuario usuario4 = new Usuario("camilo", "camilo Angel", "camilo@gmail.com", "123");
        Usuario usuario5 = new Usuario("sebastian", "sebastian Gonzalez", "sebastian@gmail.com", "123");
        Usuario usuario6 = new Usuario("andrea", "andrea Gonzalez", "andrea@gmail.com", "123");

        usuarioService.registrarUsuario(usuario1);
        usuarioService.registrarUsuario(usuario2);
        usuarioService.registrarUsuario(usuario3);
        usuarioService.registrarUsuario(usuario4);
        usuarioService.registrarUsuario(usuario5);
        usuarioService.registrarUsuario(usuario6);

        Set<Usuario> oferentes = new HashSet<>();
        BigDecimal bigDecimalValue = new BigDecimal(Float.toString(producto5.getPrecio()));
        Subasta subasta1 = new Subasta(usuario2, productoService.getByNombre(producto5.getNombre()).get(), bigDecimalValue,true, 2);
        oferentes.add(usuario1);
        oferentes.add(usuario3);
        subasta1.setOferentes(oferentes);

        Set<Usuario> oferentes2 = new HashSet<>();
        BigDecimal bigDecimalValue2 = new BigDecimal(Float.toString(producto3.getPrecio()));
        Subasta subasta2 = new Subasta(usuario5, productoService.getByNombre(producto3.getNombre()).get(), bigDecimalValue2,true, 2);
        oferentes2.add(usuario4);
        oferentes2.add(usuario6);
        subasta2.setOferentes(oferentes2);

        subastaService.addSubasta(subasta1);
        subastaService.addSubasta(subasta2);

         */
    }
}
