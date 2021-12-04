package co.edu.uniquindio.proyecto.servicio;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.DetalleCompra;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import co.edu.uniquindio.proyecto.repositorios.DetalleCompraRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleComServicioImpl implements DetalleCompraServicio{

    @Autowired
    private DetalleCompraRepo detalleCompraRepo;

    @Override
    public List<DetalleCompra> listarDetallesCompra(Integer codigoCompra) {
        return detalleCompraRepo.listarDetalleCompra(codigoCompra);
    }
}
