package co.edu.uniquindio.proyecto.rest;

import co.edu.uniquindio.proyecto.dto.Mensaje;
import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.servicio.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/productos")
public class ProductoRestController {

    @Autowired
    private ProductoServicio productoServicio;

    @GetMapping
    public List<Producto> listar(){
      return productoServicio.listarTodosProductos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtener(@PathVariable("id") Integer idProducto) {
        try{
            Producto  producto=  productoServicio.obtenerProducto(idProducto);
            return  ResponseEntity.status(200).body(producto);
        }catch(Exception e){
            return  ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<Mensaje> crear(@RequestBody Producto  producto)  {
        try{
             productoServicio.publicarProducto(producto);
            return  ResponseEntity.status(201).body(new Mensaje("El producto se creo correctamente"));
        }catch(Exception e){
            return  ResponseEntity.status(500).body(new Mensaje(e.getMessage()));

        }
    }

    @PutMapping
    public ResponseEntity<Mensaje> actualizar(@RequestBody Producto  producto)  {
        try{
            productoServicio.actualizarProducto(producto);
            return  ResponseEntity.status(200).body(new Mensaje("El producto se actualizo correctamente"));
        }catch(Exception e){
            return  ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Mensaje> eliminar(@PathVariable("id") Integer idProducto) {
        try{
            productoServicio.eliminarProducto(idProducto);
            return  ResponseEntity.status(200).body(new Mensaje("El producto se elimino correctamente"));
        }catch(Exception e){
            return  ResponseEntity.status(500).body(new Mensaje(e.getMessage()));

        }
    }

    @GetMapping("/categoria/{cat}")
    public ResponseEntity<?> categoria(@PathVariable("cat") String categoria) {
        try{
            List<Producto> listaProduc = productoServicio.listarProductos(Categoria.valueOf(categoria));
            return  ResponseEntity.status(200).body(listaProduc);
        }catch(Exception e){
            return  ResponseEntity.status(500).body(new Mensaje("Categoria no encontrada"));
        }
    }


    //Buscar por medio de rango de precio.
    @GetMapping("/precio/{precio1}/{precio2}")
        public ResponseEntity<?> rangoPrecio(@PathVariable("precio1") Float precio1, @PathVariable("precio2")Float precio2) {
        try{
            List<Producto> listaProduc = productoServicio.listarProductosRangoPrecio(precio1, precio2);
            return  ResponseEntity.status(200).body(listaProduc);
        }catch(Exception e){
            return  ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }


    //Buscar por medio de lugar.
    @GetMapping("/ciudad/{ciudad}")
    public ResponseEntity<?> buscarPorCiudad(@PathVariable("ciudad") String ciudad) {
        try{
            List<Producto> listaProduc = productoServicio.listarProductosCiudad(ciudad);
            return  ResponseEntity.status(200).body(listaProduc);
        }catch(Exception e){
            return  ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    //Dos funcionalidades extras que haga parte de los Productos (diferentes al CRUD).
    //Buscar productos que esten en el rango de 5 a 10 Unidades
    @GetMapping("/{unidad1}/{unidad2}")
    public ResponseEntity<?> buscarPorRangoUnidades(@PathVariable("unidad1") Integer unidad1, @PathVariable("unidad2") Integer unidad2) {
        try{
            List<Producto> listaProduc = productoServicio.listarProductosRangoUnidad(unidad1, unidad2);
            return  ResponseEntity.status(200).body(listaProduc);
        }catch(Exception e){
            return  ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    //Productos donde el descuento sea mayor a un descuento indicado
    @GetMapping("/descuento/{descuento}")
    public ResponseEntity<?> buscarPorRangoDescuento(@PathVariable("descuento") Float descuento) {
        try{
            List<Producto> listaProduc = productoServicio.listarProductosDescuento(descuento);
            return  ResponseEntity.status(200).body(listaProduc);
        }catch(Exception e){
            return  ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }



}

