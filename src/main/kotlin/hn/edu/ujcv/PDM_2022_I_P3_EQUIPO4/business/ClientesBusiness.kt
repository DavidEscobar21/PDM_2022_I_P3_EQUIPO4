package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.business

import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.dao.ClientesRepository
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.exceptions.BusinessException
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.exceptions.NotFoundException
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model.Clientes
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class ClientesBusiness : IClientesBusiness{

    @Autowired
    val clientesRepository : ClientesRepository? = null

    @kotlin.jvm.Throws(BusinessException::class)
    override fun getClientes(): List<Clientes> {
        try {
            return clientesRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun getClienteById(idCliente: Int): Clientes {
        val opt: Optional<Clientes>
        try {
            opt=clientesRepository!!.findById(idCliente)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro el cliente con el Id:$idCliente")
        return opt.get()
    }

    @kotlin.jvm.Throws(BusinessException::class)
    override fun saveCliente(cliente: Clientes): Clientes {
        try {
            //validaciones
            if (cliente.nombreCliente.trim().isEmpty())
                throw BusinessException("El nombre del cliente no puede estar vacio.")
            if (cliente.nombreCliente.length < 3)
                throw BusinessException("El nombre del cliente no puede tener menos de 3 letras.")
            if (cliente.nombreCliente.length > 20)
                throw BusinessException("El nombre del cliente no puede tener más de 20 letras.")
            if (!cliente.nombreCliente.matches(Regex("^[A-Z]{1}[a-záéíóú]*$")))
                throw BusinessException("El nombre debe de comenzar con mayúscula y solo puede tener letras.")
            if (cliente.apellidoCliente.trim().isEmpty())
                throw BusinessException("El apellido del cliente no puede estar vacio.")
            if (cliente.apellidoCliente.length < 2)
                throw BusinessException("El apellido del cliente no puede tener menos de 2 letras.")
            if (cliente.apellidoCliente.length > 20)
                throw BusinessException("El apellido del cliente no puede tener más de 20 letras.")
            if (!cliente.apellidoCliente.matches(Regex("^[A-Z]{1}[a-záéíóú]*$")))
                throw BusinessException("El apellido debe de comenzar con mayúscula y solo puede tener letras.")
            if (cliente.telefonoCliente.trim().isEmpty())
                throw BusinessException("El telefono del cliente no puede estar vacio.")
            if (cliente.telefonoCliente.length != 8)
                throw BusinessException("El telefono debe de contener 8 digitos.")
            if (!cliente.telefonoCliente.matches(Regex("^[23789]\\d{7,10}$")))
                throw BusinessException("Ese no es un numero de telefono valido.")
            if (cliente.direccionCliente.trim().isEmpty())
                throw BusinessException("La dirección del cliente no puede estar vacio.")
            if (cliente.direccionCliente.length < 3)
                throw BusinessException("La dirección del cliente no puede tener menos de 3 letras.")
            if (cliente.direccionCliente.length > 150)
                throw BusinessException("La dirección del cliente no puede tener más de 150 letras.")
            if (cliente.correoCliente.trim().isEmpty())
                throw BusinessException("El correo del cliente no puede estar vacio.")
            if (!cliente.correoCliente.matches(Regex("^(?=.{1,64}@)[A-Za-z0-9_-]+(.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})\$")))
                throw BusinessException("El correo del cliente es invalido.")
            if (cliente.contrasenaCliente.trim().isEmpty())
                throw BusinessException("La contraseña del cliente no puede estar vacia.")
            if (cliente.contrasenaCliente.length < 8)
                throw BusinessException("La contraseña del cliente no puede tener menos de 8 caracteres.")
            if (!cliente.contrasenaCliente.matches(Regex("^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z]).*\$")))
                throw BusinessException("La contraseña tiene que contener al menos un número, una minúscula y una mayúscula.")
            if (cliente.idTipoDeDocumento.toString().trim().isEmpty())
                throw BusinessException("El Tipo de documento del cliente no puede estar vacio.")
            if (cliente.documentoCliente.trim().isEmpty())
                throw BusinessException("El documento del cliente no puede estar vacio.")
            if (cliente.documentoCliente.length < 6)
                throw BusinessException("El numero de documento del cliente no puede tener menos de 6 caracteres.")
            if (!cliente.documentoCliente.matches(Regex("^[A-Z0-9]*$")))
                throw BusinessException("El documento solo permite letras mayúsculas y números.")

            return clientesRepository!!.save(cliente)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @kotlin.jvm.Throws(BusinessException::class)
    override fun saveClientes(clientes: List<Clientes>): List<Clientes> {
        try {
            for (item in clientes){
                if (item.nombreCliente.trim().isEmpty())
                    throw BusinessException("El nombre del cliente no puede estar vacio.")
                if (item.nombreCliente.length < 3)
                    throw BusinessException("El nombre del cliente no puede tener menos de 3 letras.")
                if (item.nombreCliente.length > 20)
                    throw BusinessException("El nombre del cliente no puede tener más de 20 letras.")
                if (!item.nombreCliente.matches(Regex("^[A-Z]{1}[a-záéíóú]*$")))
                    throw BusinessException("El nombre debe de comenzar con mayúscula y solo puede tener letras.")
                if (item.apellidoCliente.trim().isEmpty())
                    throw BusinessException("El apellido del cliente no puede estar vacio.")
                if (item.apellidoCliente.length < 2)
                    throw BusinessException("El apellido del cliente no puede tener menos de 2 letras.")
                if (item.apellidoCliente.length > 20)
                    throw BusinessException("El apellido del cliente no puede tener más de 20 letras.")
                if (!item.apellidoCliente.matches(Regex("^[A-Z]{1}[a-záéíóú]*$")))
                    throw BusinessException("El apellido debe de comenzar con mayúscula y solo puede tener letras.")
                if (item.telefonoCliente.trim().isEmpty())
                    throw BusinessException("El telefono del cliente no puede estar vacio.")
                if (item.telefonoCliente.length != 8)
                    throw BusinessException("El telefono debe de contener 8 digitos.")
                if (!item.telefonoCliente.matches(Regex("^[23789]\\d{7,10}$")))
                    throw BusinessException("Ese no es un numero de telefono valido.")
                if (item.direccionCliente.trim().isEmpty())
                    throw BusinessException("La dirección del cliente no puede estar vacio.")
                if (item.direccionCliente.length < 3)
                    throw BusinessException("La dirección del cliente no puede tener menos de 3 letras.")
                if (item.direccionCliente.length > 150)
                    throw BusinessException("La dirección del cliente no puede tener más de 150 letras.")
                if (item.correoCliente.trim().isEmpty())
                    throw BusinessException("El correo del cliente no puede estar vacio.")
                if (!item.correoCliente.matches(Regex("^(?=.{1,64}@)[A-Za-z0-9_-]+(.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})\$")))
                    throw BusinessException("El correo del cliente es invalido.")
                if (item.contrasenaCliente.trim().isEmpty())
                    throw BusinessException("La contraseña del cliente no puede estar vacia.")
                if (item.contrasenaCliente.length < 8)
                    throw BusinessException("La contraseña del cliente no puede tener menos de 8 caracteres.")
                if (!item.contrasenaCliente.matches(Regex("^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z]).*\$")))
                    throw BusinessException("La contraseña tiene que contener al menos un número, una minúscula y una mayúscula.")
                if (item.idTipoDeDocumento.toString().trim().isEmpty())
                    throw BusinessException("El Tipo de documento del cliente no puede estar vacio.")
                if (item.documentoCliente.trim().isEmpty())
                    throw BusinessException("El documento del cliente no puede estar vacio.")
                if (item.documentoCliente.length < 6)
                    throw BusinessException("El numero de documento del cliente no puede tener menos de 6 caracteres.")
                if (!item.documentoCliente.matches(Regex("^[A-Z0-9]*$")))
                    throw BusinessException("El documento solo permite letras mayúsculas y números.")
            }

            return clientesRepository!!.saveAll(clientes)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun removeCliente(idCliente: Int) {
        val opt: Optional<Clientes>
        try {
            opt=clientesRepository!!.findById(idCliente)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro el cliente con el Id:$idCliente")
        else{
            try {
                clientesRepository!!.deleteById(idCliente)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
    }

    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun updateCliente(cliente: Clientes): Clientes {
        val opt: Optional<Clientes>
        try {
            opt=clientesRepository!!.findById(cliente.idCliente)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro el cliente con el Id:${cliente.idCliente}")
        else{
            try {
                //validaciones
                if (cliente.nombreCliente.trim().isEmpty())
                    throw BusinessException("El nombre del cliente no puede estar vacio.")
                if (cliente.nombreCliente.length < 3)
                    throw BusinessException("El nombre del cliente no puede tener menos de 3 letras.")
                if (cliente.nombreCliente.length > 20)
                    throw BusinessException("El nombre del cliente no puede tener más de 20 letras.")
                if (!cliente.nombreCliente.matches(Regex("^[A-Z]{1}[a-záéíóú]*$")))
                    throw BusinessException("El nombre debe de comenzar con mayúscula y solo puede tener letras.")
                if (cliente.apellidoCliente.trim().isEmpty())
                    throw BusinessException("El apellido del cliente no puede estar vacio.")
                if (cliente.apellidoCliente.length < 2)
                    throw BusinessException("El apellido del cliente no puede tener menos de 2 letras.")
                if (cliente.apellidoCliente.length > 20)
                    throw BusinessException("El apellido del cliente no puede tener más de 20 letras.")
                if (!cliente.apellidoCliente.matches(Regex("^[A-Z]{1}[a-záéíóú]*$")))
                    throw BusinessException("El apellido debe de comenzar con mayúscula y solo puede tener letras.")
                if (cliente.telefonoCliente.trim().isEmpty())
                    throw BusinessException("El telefono del cliente no puede estar vacio.")
                if (cliente.telefonoCliente.length != 8)
                    throw BusinessException("El telefono debe de contener 8 digitos.")
                if (!cliente.telefonoCliente.matches(Regex("^[23789]\\d{7,10}$")))
                    throw BusinessException("Ese no es un numero de telefono valido.")
                if (cliente.direccionCliente.trim().isEmpty())
                    throw BusinessException("La dirección del cliente no puede estar vacio.")
                if (cliente.direccionCliente.length < 3)
                    throw BusinessException("La dirección del cliente no puede tener menos de 3 letras.")
                if (cliente.direccionCliente.length > 150)
                    throw BusinessException("La dirección del cliente no puede tener más de 150 letras.")
                if (cliente.correoCliente.trim().isEmpty())
                    throw BusinessException("El correo del cliente no puede estar vacio.")
                if (!cliente.correoCliente.matches(Regex("^(?=.{1,64}@)[A-Za-z0-9_-]+(.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})\$")))
                    throw BusinessException("El correo del cliente es invalido.")
                if (cliente.contrasenaCliente.trim().isEmpty())
                    throw BusinessException("La contraseña del cliente no puede estar vacia.")
                if (cliente.contrasenaCliente.length < 8)
                    throw BusinessException("La contraseña del cliente no puede tener menos de 8 caracteres.")
                if (!cliente.contrasenaCliente.matches(Regex("^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z]).*\$")))
                    throw BusinessException("La contraseña tiene que contener al menos un número, una minúscula y una mayúscula.")
                if (cliente.idTipoDeDocumento.toString().trim().isEmpty())
                    throw BusinessException("El Tipo de documento del cliente no puede estar vacio.")
                if (cliente.documentoCliente.trim().isEmpty())
                    throw BusinessException("El documento del cliente no puede estar vacio.")
                if (cliente.documentoCliente.length < 6)
                    throw BusinessException("El numero de documento del cliente no puede tener menos de 6 caracteres.")
                if (!cliente.documentoCliente.matches(Regex("^[A-Z0-9]*$")))
                    throw BusinessException("El documento solo permite letras mayúsculas y números.")

                val clienteExiste = Clientes(cliente.nombreCliente,cliente.apellidoCliente,cliente.telefonoCliente,cliente.direccionCliente,cliente.correoCliente,
                    cliente.estadoCliente,cliente.contrasenaCliente,cliente.documentoCliente,cliente.idTipoDeDocumento)
                clientesRepository!!.save(cliente)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
        return opt.get()
    }

    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun getClienteByNombre(nombreCliente: String): Clientes {
        val opt: Optional<Clientes>
        try {
            opt=clientesRepository!!.findByNombreCliente(nombreCliente)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro un cliente con el nombre:$nombreCliente")
        return opt.get()
    }


}