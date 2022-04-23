package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.business

import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.dao.ArticulosRepository
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.exceptions.BusinessException
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.exceptions.NotFoundException
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model.Articulos
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class ArticulosBusiness : IArticulosBusiness {

    @Autowired
    val articulosRepository : ArticulosRepository? = null

    @kotlin.jvm.Throws(BusinessException::class)
    override fun getArticulos(): List<Articulos> {
        try {
            return articulosRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun getArticuloById(idArticulo: Int): Articulos {
        val opt: Optional<Articulos>
        try {
            opt=articulosRepository!!.findById(idArticulo)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro el articulo con el Id:$idArticulo")
        return opt.get()
    }

    @kotlin.jvm.Throws(BusinessException::class)
    override fun saveArticulo(articulo: Articulos): Articulos {
        try {
            //validaciones
            if (articulo.nombreArticulo.trim().isEmpty())
                throw BusinessException("El nombre del articulo no puede estar vacio.")
            if (articulo.nombreArticulo.length < 3)
                throw BusinessException("El nombre del articulo no puede tener menos de 3 caracteres.")
            if (articulo.nombreArticulo.length > 20)
                throw BusinessException("El nombre del articulo no puede tener más de 20 caracteres.")
            if (articulo.descripcionArticulo.trim().isEmpty())
                throw BusinessException("La descripción del articulo no puede estar vacio.")
            if (articulo.descripcionArticulo.length < 3)
                throw BusinessException("La descripción del articulo no puede tener menos de 3 caracteres.")
            if (articulo.descripcionArticulo.length > 50)
                throw BusinessException("La descripción del articulo no puede tener más de 50 caracteres.")
            if (articulo.precioArticulo <= 0.0)
                throw BusinessException("El precio del articulo no puede ser menor o igual a 0.")
            if (articulo.stock <= 0)
                throw BusinessException("El stock del articulo no puede ser menor o igual 0.")
            if (articulo.idCategoria.toString().trim().isEmpty())
                throw BusinessException("La Categoria del articulo no puede estar vacio.")
            if (articulo.idProveedor.toString().trim().isEmpty())
                throw BusinessException("El proveedor del articulo no puede estar vacio.")
            if (articulo.idTiempoDeEntrega.toString().trim().isEmpty())
                throw BusinessException("El tiempo de entrega del articulo no puede estar vacio.")

            return articulosRepository!!.save(articulo)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @kotlin.jvm.Throws(BusinessException::class)
    override fun saveArticulos(articulo: List<Articulos>): List<Articulos> {
        try {

            for (item in articulo){
                //validaciones
                if (item.nombreArticulo.trim().isEmpty())
                    throw BusinessException("El nombre del articulo no puede estar vacio.")
                if (item.nombreArticulo.length < 3)
                    throw BusinessException("El nombre del articulo no puede tener menos de 3 caracteres.")
                if (item.nombreArticulo.length > 20)
                    throw BusinessException("El nombre del articulo no puede tener más de 20 caracteres.")
                if (item.descripcionArticulo.trim().isEmpty())
                    throw BusinessException("La descripción del articulo no puede estar vacio.")
                if (item.descripcionArticulo.length < 3)
                    throw BusinessException("La descripción del articulo no puede tener menos de 3 caracteres.")
                if (item.descripcionArticulo.length > 50)
                    throw BusinessException("La descripción del articulo no puede tener más de 50 caracteres.")
                if (item.precioArticulo <= 0.0)
                    throw BusinessException("El precio del articulo no puede ser menor o igual a 0.")
                if (item.stock <= 0)
                    throw BusinessException("El stock del articulo no puede ser menor o igual 0.")
                if (item.idCategoria.toString().trim().isEmpty())
                    throw BusinessException("La Categoria del articulo no puede estar vacio.")
                if (item.idProveedor.toString().trim().isEmpty())
                    throw BusinessException("El proveedor del articulo no puede estar vacio.")
                if (item.idTiempoDeEntrega.toString().trim().isEmpty())
                    throw BusinessException("El tiempo de entrega del articulo no puede estar vacio.")
            }

            return articulosRepository!!.saveAll(articulo)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun removeArticulo(idArticulo: Int) {
        val opt: Optional<Articulos>
        try {
            opt=articulosRepository!!.findById(idArticulo)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro articulo con el Id:$idArticulo")
        else{
            try {
                articulosRepository!!.deleteById(idArticulo)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
    }

    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun updateArticulo(articulo: Articulos): Articulos {
        val opt: Optional<Articulos>
        try {
            opt=articulosRepository!!.findById(articulo.idArticulos)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro articulo con el Id:${articulo.idArticulos}")
        else{
            try {
                //validaciones
                if (articulo.nombreArticulo.trim().isEmpty())
                    throw BusinessException("El nombre del articulo no puede estar vacio.")
                if (articulo.nombreArticulo.length < 3)
                    throw BusinessException("El nombre del articulo no puede tener menos de 3 caracteres.")
                if (articulo.nombreArticulo.length > 20)
                    throw BusinessException("El nombre del articulo no puede tener más de 20 caracteres.")
                if (articulo.descripcionArticulo.trim().isEmpty())
                    throw BusinessException("La descripción del articulo no puede estar vacio.")
                if (articulo.descripcionArticulo.length < 3)
                    throw BusinessException("La descripción del articulo no puede tener menos de 3 caracteres.")
                if (articulo.descripcionArticulo.length > 50)
                    throw BusinessException("La descripción del articulo no puede tener más de 50 caracteres.")
                if (articulo.precioArticulo <= 0.0)
                    throw BusinessException("El precio del articulo no puede ser menor o igual a 0.")
                if (articulo.stock <= 0)
                    throw BusinessException("El stock del articulo no puede ser menor o igual 0.")
                if (articulo.idCategoria.toString().trim().isEmpty())
                    throw BusinessException("La Categoria del articulo no puede estar vacio.")
                if (articulo.idProveedor.toString().trim().isEmpty())
                    throw BusinessException("El proveedor del articulo no puede estar vacio.")
                if (articulo.idTiempoDeEntrega.toString().trim().isEmpty())
                    throw BusinessException("El tiempo de entrega del articulo no puede estar vacio.")

                val articuloExiste = Articulos(articulo.nombreArticulo,articulo.stock,articulo.descripcionArticulo,articulo.precioArticulo,
                    articulo.estadoArticulo,articulo.idProveedor,articulo.idCategoria,articulo.idTiempoDeEntrega)
                articulosRepository!!.save(articulo)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
        return opt.get()
    }

    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun getArticuloByNombre(nombreArticulo: String): Articulos {
        val opt: Optional<Articulos>
        try {
            opt=articulosRepository!!.findByNombreArticulo(nombreArticulo)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro articulo con el nombre:$nombreArticulo")
        return opt.get()
    }

}