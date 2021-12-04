package co.edu.uniquindio.proyecto.servicio;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import co.edu.uniquindio.proyecto.repositorios.CompraRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompraServicioImpl implements CompraServicio{

    @Autowired
    private CompraRepo compraRepo;

    @Override
    public List<Compra> listarComprasUsuario(String codigoUsuario) {
        return compraRepo.listaCompraUsuario(codigoUsuario);
    }
}
