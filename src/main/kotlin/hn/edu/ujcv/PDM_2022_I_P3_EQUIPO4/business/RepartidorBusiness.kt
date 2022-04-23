package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.business

import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.dao.RepartidorRepository
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.exceptions.BusinessException
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.exceptions.NotFoundException
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model.Repartidor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class RepartidorBusiness : IRepartidorBusiness{

    @Autowired
    val repartidorBusiness : RepartidorRepository? = null

    @kotlin.jvm.Throws(BusinessException::class)
    override fun getRepartidores(): List<Repartidor> {
        try {
            return repartidorBusiness!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun getRepartidorById(idRepartidor: Int): Repartidor {
        val opt: Optional<Repartidor>
        try {
            opt=repartidorBusiness!!.findById(idRepartidor)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro repartidor con el Id:$idRepartidor")
        return opt.get()
    }

    @kotlin.jvm.Throws(BusinessException::class)
    override fun saveRepartidor(repartidor: Repartidor): Repartidor {
        try {

            if (repartidor.nombreRepartidor.trim().isEmpty())
                throw BusinessException("El nombre del repartidor no puede estar vacio.")
            if (repartidor.nombreRepartidor.length < 3)
                throw BusinessException("El nombre del repartidor no puede tener menos de 3 letras.")
            if (repartidor.nombreRepartidor.length > 20)
                throw BusinessException("El nombre del repartidor no puede tener más de 20 letras.")
            if (!repartidor.nombreRepartidor.matches(Regex("^[A-Z]{1}[a-záéíóú]*$")))
                throw BusinessException("El nombre debe de comenzar con mayúscula y solo puede tener letras.")
            if (repartidor.apellidoRepartidor.trim().isEmpty())
                throw BusinessException("El apellido del repartidor no puede estar vacio.")
            if (repartidor.apellidoRepartidor.length < 2)
                throw BusinessException("El apellido del repartidor no puede tener menos de 2 letras.")
            if (repartidor.apellidoRepartidor.length > 20)
                throw BusinessException("El apellido del repartidor no puede tener más de 20 letras.")
            if (!repartidor.apellidoRepartidor.matches(Regex("^[A-Z]{1}[a-záéíóú]*$")))
                throw BusinessException("El apellido debe de comenzar con mayúscula y solo puede tener letras.")
            if (repartidor.telefonoRepartidor.trim().isEmpty())
                throw BusinessException("El telefono del repartidor no puede estar vacio.")
            if (repartidor.telefonoRepartidor.length != 8)
                throw BusinessException("El telefono debe de contener 8 dígitos.")
            if (!repartidor.telefonoRepartidor.matches(Regex("^[23789]\\d{7,10}$")))
                throw BusinessException("Ese no es un numero de telefono valido.")
            if (repartidor.correoRepartidor.trim().isEmpty())
                throw BusinessException("El correo del repartidor no puede estar vacio.")
            if (!repartidor.correoRepartidor.matches(Regex("^(?=.{1,64}@)[A-Za-z0-9_-]+(.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})\$")))
                throw BusinessException("El correo del repartidor es invalido.")
            if (repartidor.idSexo.toString().trim().isEmpty())
                throw BusinessException("El sexo del repartidor no puede estar vacio.")
            if (repartidor.idTipoDeDocumento.toString().trim().isEmpty())
                throw BusinessException("El Tipo de documento del repartidor no puede estar vacio.")
            if (repartidor.documentoRepartidor.trim().isEmpty())
                throw BusinessException("El documento del repartidor no puede estar vacio.")
            if (repartidor.documentoRepartidor.length < 6)
                throw BusinessException("El numero de documento del repartidor no puede tener menos de 6 caracteres.")
            if (!repartidor.documentoRepartidor.matches(Regex("^[A-Z0-9]*$")))
                throw BusinessException("El documento solo permite letras mayúsculas y números.")

            return repartidorBusiness!!.save(repartidor)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @kotlin.jvm.Throws(BusinessException::class)
    override fun saveRepartidores(repartidores: List<Repartidor>): List<Repartidor> {
        try {
            for (item in repartidores){
                if (item.nombreRepartidor.trim().isEmpty())
                    throw BusinessException("El nombre del repartidor no puede estar vacio.")
                if (item.nombreRepartidor.length < 3)
                    throw BusinessException("El nombre del repartidor no puede tener menos de 3 letras.")
                if (item.nombreRepartidor.length > 20)
                    throw BusinessException("El nombre del repartidor no puede tener más de 20 letras.")
                if (!item.nombreRepartidor.matches(Regex("^[A-Z]{1}[a-záéíóú]*$")))
                    throw BusinessException("El nombre debe de comenzar con mayúscula y solo puede tener letras.")
                if (item.apellidoRepartidor.trim().isEmpty())
                    throw BusinessException("El apellido del repartidor no puede estar vacio.")
                if (item.apellidoRepartidor.length < 2)
                    throw BusinessException("El apellido del repartidor no puede tener menos de 2 letras.")
                if (item.apellidoRepartidor.length > 20)
                    throw BusinessException("El apellido del repartidor no puede tener más de 20 letras.")
                if (!item.apellidoRepartidor.matches(Regex("^[A-Z]{1}[a-záéíóú]*$")))
                    throw BusinessException("El apellido debe de comenzar con mayúscula y solo puede tener letras.")
                if (item.telefonoRepartidor.trim().isEmpty())
                    throw BusinessException("El telefono del repartidor no puede estar vacio.")
                if (item.telefonoRepartidor.length != 8)
                    throw BusinessException("El telefono debe de contener 8 dígitos.")
                if (!item.telefonoRepartidor.matches(Regex("^[23789]\\d{7,10}$")))
                    throw BusinessException("Ese no es un numero de telefono valido.")
                if (item.correoRepartidor.trim().isEmpty())
                    throw BusinessException("El correo del repartidor no puede estar vacio.")
                if (!item.correoRepartidor.matches(Regex("^(?=.{1,64}@)[A-Za-z0-9_-]+(.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})\$")))
                    throw BusinessException("El correo del repartidor es invalido.")
                if (item.idSexo.toString().trim().isEmpty())
                    throw BusinessException("El sexo del repartidor no puede estar vacio.")
                if (item.idTipoDeDocumento.toString().trim().isEmpty())
                    throw BusinessException("El Tipo de documento del repartidor no puede estar vacio.")
                if (item.documentoRepartidor.trim().isEmpty())
                    throw BusinessException("El documento del repartidor no puede estar vacio.")
                if (item.documentoRepartidor.length < 6)
                    throw BusinessException("El numero de documento del repartidor no puede tener menos de 6 caracteres.")
                if (!item.documentoRepartidor.matches(Regex("^[A-Z0-9]*$")))
                    throw BusinessException("El documento solo permite letras mayúsculas y números.")
            }

            return repartidorBusiness!!.saveAll(repartidores)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun removeRepartidor(idRepartidor: Int) {
        val opt: Optional<Repartidor>
        try {
            opt=repartidorBusiness!!.findById(idRepartidor)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro repartidor con el Id:$idRepartidor")
        else{
            try {
                repartidorBusiness!!.deleteById(idRepartidor)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
    }

    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun updateRepartidor(repartidor: Repartidor): Repartidor {
        val opt: Optional<Repartidor>
        try {
            opt=repartidorBusiness!!.findById(repartidor.idRepartidor)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro repartidor con el Id:${repartidor.idRepartidor}")
        else{
            try {
                //validaciones
                if (repartidor.nombreRepartidor.trim().isEmpty())
                    throw BusinessException("El nombre del repartidor no puede estar vacio.")
                if (repartidor.nombreRepartidor.length < 3)
                    throw BusinessException("El nombre del repartidor no puede tener menos de 3 letras.")
                if (repartidor.nombreRepartidor.length > 20)
                    throw BusinessException("El nombre del repartidor no puede tener más de 20 letras.")
                if (!repartidor.nombreRepartidor.matches(Regex("^[A-Z]{1}[a-záéíóú]*$")))
                    throw BusinessException("El nombre debe de comenzar con mayúscula y solo puede tener letras.")
                if (repartidor.apellidoRepartidor.trim().isEmpty())
                    throw BusinessException("El apellido del repartidor no puede estar vacio.")
                if (repartidor.apellidoRepartidor.length < 2)
                    throw BusinessException("El apellido del repartidor no puede tener menos de 2 letras.")
                if (repartidor.apellidoRepartidor.length > 20)
                    throw BusinessException("El apellido del repartidor no puede tener más de 20 letras.")
                if (!repartidor.apellidoRepartidor.matches(Regex("^[A-Z]{1}[a-záéíóú]*$")))
                    throw BusinessException("El apellido debe de comenzar con mayúscula y solo puede tener letras.")
                if (repartidor.telefonoRepartidor.trim().isEmpty())
                    throw BusinessException("El telefono del repartidor no puede estar vacio.")
                if (repartidor.telefonoRepartidor.length != 8)
                    throw BusinessException("El telefono debe de contener 8 dígitos.")
                if (!repartidor.telefonoRepartidor.matches(Regex("^[23789]\\d{7,10}$")))
                    throw BusinessException("Ese no es un numero de telefono valido.")
                if (repartidor.correoRepartidor.trim().isEmpty())
                    throw BusinessException("El correo del repartidor no puede estar vacio.")
                if (!repartidor.correoRepartidor.matches(Regex("^(?=.{1,64}@)[A-Za-z0-9_-]+(.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})\$")))
                    throw BusinessException("El correo del repartidor es invalido.")
                if (repartidor.idSexo.toString().trim().isEmpty())
                    throw BusinessException("El sexo del repartidor no puede estar vacio.")
                if (repartidor.idTipoDeDocumento.toString().trim().isEmpty())
                    throw BusinessException("El Tipo de documento del repartidor no puede estar vacio.")
                if (repartidor.documentoRepartidor.trim().isEmpty())
                    throw BusinessException("El documento del repartidor no puede estar vacio.")
                if (repartidor.documentoRepartidor.length < 6)
                    throw BusinessException("El numero de documento del repartidor no puede tener menos de 6 caracteres.")
                if (!repartidor.documentoRepartidor.matches(Regex("^[A-Z0-9]*$")))
                    throw BusinessException("El documento solo permite letras mayúsculas y números.")

                val repartidorExiste = Repartidor(repartidor.nombreRepartidor,repartidor.apellidoRepartidor,repartidor.telefonoRepartidor,
                    repartidor.correoRepartidor,repartidor.estadoRepartidor,repartidor.documentoRepartidor,repartidor.idSexo,repartidor.idTipoDeDocumento)
                repartidorBusiness!!.save(repartidor)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
        return opt.get()
    }

    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun getRepartidorByNombreRepartidor(nombreRepartidor: String): Repartidor {
        val opt: Optional<Repartidor>
        try {
            opt=repartidorBusiness!!.findRepartidorByNombreRepartidor(nombreRepartidor)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro repartidor con el nombre:$nombreRepartidor")
        return opt.get()
    }

    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun getRepartidorByDocumentoRepartidor(documentoRepartidor: String): Repartidor {
        val opt: Optional<Repartidor>
        try {
            opt=repartidorBusiness!!.findRepartidorByDocumentoRepartidor(documentoRepartidor)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro repartidor con el documento personal:$documentoRepartidor")
        return opt.get()
    }

}