package edu.eci.arsw.bidify.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import edu.eci.arsw.bidify.model.Producto;
import edu.eci.arsw.bidify.model.Subasta;
import edu.eci.arsw.bidify.model.Usuario;
import edu.eci.arsw.bidify.service.ProductoService;
import edu.eci.arsw.bidify.service.SubastaService;
import edu.eci.arsw.bidify.service.UsuarioService;


@Component
public class createSubastas implements CommandLineRunner{
    
    @Autowired
    ProductoService productoService;
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    SubastaService subastaService;
    
    @Override
    public void run(String... args) throws Exception {
        
        //productos
        Producto producto1 = new Producto("Jordan One", (float) 600000, "https://phantom-expansion.unidadeditorial.es/6239da431613d30a7ade440a4719e3db/crop/0x378/1074x982/resize/828/f/jpg/assets/multimedia/imagenes/2022/03/21/16478732471407.jpg");
        Producto producto2 = new Producto("Camara", (float) 800000, "https://www.workshopexperience.com/wp-content/uploads/2017/07/marcas-de-camaras-fotograficas-4.jpg");
        Producto producto3 = new Producto("Porsche 911 GT3", (float) 351500000, "https://files.porsche.com/filestore/image/multimedia/none/992-gt3-modelimage-sideshot/model/765dfc51-51bc-11eb-80d1-005056bbdc38/porsche-model.png");
        Producto producto4 = new Producto("Vinilo Michael Jackson", (float) 2000000000, "https://http2.mlstatic.com/D_NQ_NP_664991-MCO71457373138_092023-O.webp");
        Producto producto5 = new Producto("Funko pop Michael Jackson", (float) 600000, "https://http2.mlstatic.com/D_NQ_NP_631613-MCO71749069309_092023-O.webp");
        productoService.save(producto1);   
        productoService.save(producto2);  
        productoService.save(producto3);  
        productoService.save(producto4);  
        productoService.save(producto5);
        
        List<Producto> productos = new ArrayList<>(); 
        productos.add(producto5);
        productos.add(producto4);
        productos.add(producto3);

        
        Usuario usuario0 = new Usuario("Karen", "KarenLi", "karen@gmail.com", "123");
        usuarioService.registrarUsuario(usuario0);
        Producto productoprueba1 = new Producto("Plancha", (float) 600000, "https://www.hola.com/imagenes/seleccion/20201210180792/mejor-plancha-de-pelo/0-898-977/ghd-gold-planchas-a.jpg");
        productoprueba1.setUsuario(usuario0);
        productoService.save(productoprueba1);
        Producto productoprueba2 = new Producto("Samba Adidas", (float) 800000, "https://assets.adidas.com/images/h_840,f_auto,q_auto,fl_lossy,c_fill,g_auto/3bbecbdf584e40398446a8bf0117cf62_9366/Tenis_Samba_OG_Blanco_B75806_01_standard.jpg");
        productoprueba2.setUsuario(usuario0);
        productoService.save(productoprueba2);
        usuario0.addProducto(productoprueba2);
        usuario0.addProducto(productoprueba1);
        
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
        Subasta subasta1 = new Subasta(usuario2, producto5, bigDecimalValue,false, 2);
        oferentes.add(usuario1);
        oferentes.add(usuario3);
        subasta1.setOferentes(oferentes);
        
        Set<Usuario> oferentes2 = new HashSet<>();
        BigDecimal bigDecimalValue2 = new BigDecimal(Float.toString(producto3.getPrecio()));
        Subasta subasta2 = new Subasta(usuario5, producto3, bigDecimalValue2,false, 2);
        oferentes2.add(usuario4);
        oferentes2.add(usuario6);
        subasta2.setOferentes(oferentes2);
        
        subastaService.addSubasta(subasta1);
        subastaService.addSubasta(subasta2);
       
        
        
    }
}
