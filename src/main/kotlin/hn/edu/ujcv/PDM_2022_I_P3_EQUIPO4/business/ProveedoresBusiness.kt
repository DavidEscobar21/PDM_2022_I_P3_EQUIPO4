package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.business

import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.dao.ProveedoresRepository
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.exceptions.BusinessException
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.exceptions.NotFoundException
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model.Proveedores
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProveedoresBusiness:IProveedoresBusiness {

    @Autowired
    val proveedoresRepository: ProveedoresRepository? = null

    @kotlin.jvm.Throws(BusinessException::class)
    override fun getProveedores(): List<Proveedores> {
        try {
            return proveedoresRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun getProveedorById(idProveedor: Int): Proveedores {
        val opt: Optional<Proveedores>
        try {
            opt=proveedoresRepository!!.findById(idProveedor)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro el Proveedor con el Id:$idProveedor")
        return opt.get()
    }

    @kotlin.jvm.Throws(BusinessException::class)
    override fun saveProveedor(proveedores: Proveedores): Proveedores {
        try {
            //validaciones
            if (proveedores.nombreProveedor.trim().isEmpty())
                throw BusinessException("El nombre del proveedor no puede estar vacio.")
            if (proveedores.nombreProveedor.length < 3)
                throw BusinessException("El nombre del proveedor no puede tener menos de 3 caracteres.")
            if (proveedores.nombreProveedor.length > 30)
                throw BusinessException("El nombre del proveedor no puede tener más de 30 caracteres.")
            if (proveedores.telefonoProveedor.trim().isEmpty())
                throw BusinessException("El telefono del proveedor no puede estar vacio.")
            if (proveedores.telefonoProveedor.length != 8)
                throw BusinessException("El telefono debe de contener 8 digitos.")
            if (!proveedores.telefonoProveedor.matches(Regex("^[23789]\\d{7,10}$")))
                throw BusinessException("Ese no es un numero de telefono valido.")
            if (proveedores.correoProveedor.trim().isEmpty())
                throw BusinessException("El correo del proveedor no puede estar vacio.")
            if (!proveedores.correoProveedor.matches(Regex("^(?=.{1,64}@)[A-Za-z0-9_-]+(.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})\$")))
                throw BusinessException("El correo del proveedor es invalido.")
            if (proveedores.idTipoDeDocumento.toString().trim().isEmpty())
                throw BusinessException("El Tipo de documento del proveedor no puede estar vacio.")
            if (proveedores.documentoProveedor.trim().isEmpty())
                throw BusinessException("El documento del proveedor no puede estar vacio.")
            if (proveedores.documentoProveedor.length < 6)
                throw BusinessException("El numero de documento del proveedor no puede tener menos de 6 caracteres.")
            if (!proveedores.documentoProveedor.matches(Regex("^[A-Z0-9]*$")))
                throw BusinessException("El documento solo permite letras mayúsculas y números.")

            return proveedoresRepository!!.save(proveedores)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @kotlin.jvm.Throws(BusinessException::class)
    override fun saveProveedores(proveedores: List<Proveedores>): List<Proveedores> {
        try {
            for (item in proveedores){
                if (item.nombreProveedor.trim().isEmpty())
                    throw BusinessException("El nombre del proveedor no puede estar vacio.")
                if (item.nombreProveedor.length < 3)
                    throw BusinessException("El nombre del proveedor no puede tener menos de 3 caracteres.")
                if (item.nombreProveedor.length > 30)
                    throw BusinessException("El nombre del proveedor no puede tener más de 30 caracteres.")
                if (item.telefonoProveedor.trim().isEmpty())
                    throw BusinessException("El telefono del proveedor no puede estar vacio.")
                if (item.telefonoProveedor.length != 8)
                    throw BusinessException("El telefono debe de contener 8 digitos.")
                if (!item.telefonoProveedor.matches(Regex("^[23789]\\d{7,10}$")))
                    throw BusinessException("Ese no es un numero de telefono valido.")
                if (item.correoProveedor.trim().isEmpty())
                    throw BusinessException("El correo del proveedor no puede estar vacio.")
                if (!item.correoProveedor.matches(Regex("^(?=.{1,64}@)[A-Za-z0-9_-]+(.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})\$")))
                    throw BusinessException("El correo del proveedor es invalido.")
                if (item.idTipoDeDocumento.toString().trim().isEmpty())
                    throw BusinessException("El Tipo de documento del proveedor no puede estar vacio.")
                if (item.documentoProveedor.trim().isEmpty())
                    throw BusinessException("El documento del proveedor no puede estar vacio.")
                if (item.documentoProveedor.length < 6)
                    throw BusinessException("El numero de documento del proveedor no puede tener menos de 6 caracteres.")
                if (!item.documentoProveedor.matches(Regex("^[A-Z0-9]*$")))
                    throw BusinessException("El documento solo permite letras mayúsculas y números.")
            }

            return proveedoresRepository!!.saveAll(proveedores)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun removeProveedor(idProveedor: Int) {
        val opt: Optional<Proveedores>
        try {
            opt=proveedoresRepository!!.findById(idProveedor)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro el cliente con el Id:$idProveedor")
        else{
            try {
                proveedoresRepository!!.deleteById(idProveedor)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
    }

    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun updateProveedor(proveedores: Proveedores): Proveedores {
        val opt: Optional<Proveedores>
        try {
            opt=proveedoresRepository!!.findById(proveedores.idProveedor)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro el proveedor con el Id:${proveedores.idProveedor}")
        else{
            try {
                //validaciones
                if (proveedores.nombreProveedor.trim().isEmpty())
                    throw BusinessException("El nombre del proveedor no puede estar vacio.")
                if (proveedores.nombreProveedor.length < 3)
                    throw BusinessException("El nombre del proveedor no puede tener menos de 3 caracteres.")
                if (proveedores.nombreProveedor.length > 30)
                    throw BusinessException("El nombre del proveedor no puede tener más de 30 caracteres.")
                if (proveedores.telefonoProveedor.trim().isEmpty())
                    throw BusinessException("El telefono del proveedor no puede estar vacio.")
                if (proveedores.telefonoProveedor.length != 8)
                    throw BusinessException("El telefono debe de contener 8 digitos.")
                if (!proveedores.telefonoProveedor.matches(Regex("^[23789]\\d{7,10}$")))
                    throw BusinessException("Ese no es un numero de telefono valido.")
                if (proveedores.correoProveedor.trim().isEmpty())
                    throw BusinessException("El correo del proveedor no puede estar vacio.")
                if (!proveedores.correoProveedor.matches(Regex("^(?=.{1,64}@)[A-Za-z0-9_-]+(.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})\$")))
                    throw BusinessException("El correo del proveedor es invalido.")
                if (proveedores.idTipoDeDocumento.toString().trim().isEmpty())
                    throw BusinessException("El Tipo de documento del proveedor no puede estar vacio.")
                if (proveedores.documentoProveedor.trim().isEmpty())
                    throw BusinessException("El documento del proveedor no puede estar vacio.")
                if (proveedores.documentoProveedor.length < 6)
                    throw BusinessException("El numero de documento del proveedor no puede tener menos de 6 caracteres.")
                if (!proveedores.documentoProveedor.matches(Regex("^[A-Z0-9]*$")))
                    throw BusinessException("El documento solo permite letras mayúsculas y números.")

                val proveedorExiste = Proveedores(proveedores.nombreProveedor,proveedores.telefonoProveedor,proveedores.correoProveedor,
                    proveedores.estadoProveedor,proveedores.documentoProveedor,proveedores.idTipoDeDocumento)
                proveedoresRepository!!.save(proveedores)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
        return opt.get()
    }

    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun getProveedorByNombre(nombreProveedor: String): Proveedores {
        val opt: Optional<Proveedores>
        try {
            opt=proveedoresRepository!!.findByNombreProveedor(nombreProveedor)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro un proveedor con el nombre:$nombreProveedor")
        return opt.get()
    }
}