package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.business

import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.dao.UsuariosRepository
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.exceptions.BusinessException
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.exceptions.NotFoundException
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model.Usuarios
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class UsuariosBusiness : IUsuarioBusiness {
    @Autowired
    val usuariosRepository : UsuariosRepository? = null

    @kotlin.jvm.Throws(BusinessException::class)
    override fun getUsuarios(): List<Usuarios> {
        try {
            return usuariosRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun getUsuarioById(idUsuario: Int): Usuarios {
        val opt: Optional<Usuarios>
        try {
            opt=usuariosRepository!!.findById(idUsuario)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro el usuario con el Id:$idUsuario")
        return opt.get()
    }

    @kotlin.jvm.Throws(BusinessException::class)
    override fun saveUsuario(usuario: Usuarios): Usuarios {
        try {

            if (usuario.nombreEmpleado.trim().isEmpty())
                throw BusinessException("El nombre del empleado no puede estar vacio.")
            if (usuario.nombreEmpleado.length < 3)
                throw BusinessException("El nombre del empleado no puede tener menos de 3 letras.")
            if (usuario.nombreEmpleado.length > 20)
                throw BusinessException("El nombre del empleado no puede tener más de 20 letras.")
            if (!usuario.nombreEmpleado.matches(Regex("^[A-Z]{1}[a-záéíóú]*$")))
                throw BusinessException("El nombre debe de comenzar con mayúscula y solo puede tener letras.")
            if (usuario.apellidoEmpleado.trim().isEmpty())
                throw BusinessException("El apellido del empleado no puede estar vacio.")
            if (usuario.apellidoEmpleado.length < 2)
                throw BusinessException("El apellido del empleado no puede tener menos de 2 letras.")
            if (usuario.apellidoEmpleado.length > 20)
                throw BusinessException("El apellido del empleado no puede tener más de 20 letras.")
            if (!usuario.apellidoEmpleado.matches(Regex("^[A-Z]{1}[a-záéíóú]*$")))
                throw BusinessException("El apellido debe de comenzar con mayúscula y solo puede tener letras.")
            if (usuario.nombreUsuario.trim().isEmpty())
                throw BusinessException("El nombre de usuario no puede estar vacio.")
            if (usuario.nombreUsuario.length < 3)
                throw BusinessException("El nombre de usuario no puede tener menos de 3 letras.")
            if (usuario.contrasena.trim().isEmpty())
                throw BusinessException("La contraseña de usuario no puede estar vacia.")
            if (usuario.contrasena.length < 8)
                throw BusinessException("La contraseña del empleado no puede tener menos de 8 caracteres.")
            if (!usuario.contrasena.matches(Regex("^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z]).*\$")))
                throw BusinessException("La contraseña tiene que contener al menos un número, una minúscula y una mayúscula.")
            if (usuario.idAreaLaboral.toString().trim().isEmpty())
                throw BusinessException("El area laboral del emplaedo no puede estar vacia.")
            if (usuario.idSexo.toString().trim().isEmpty())
                throw BusinessException("El sexo del emplaedo no puede estar vacio.")
            if (usuario.idTipoDeDocumento.toString().trim().isEmpty())
                throw BusinessException("El tipo de documento del emplaedo no puede estar vacio.")
            if (usuario.documentoEmpleado.trim().isEmpty())
                throw BusinessException("El documento del empleado no puede estar vacio.")
            if (usuario.documentoEmpleado.length < 6)
                throw BusinessException("El numero de documento del empleado no puede tener menos de 6 caracteres.")
            if (!usuario.documentoEmpleado.matches(Regex("^[A-Z0-9]*$")))
                throw BusinessException("El documento solo permite letras mayúsculas y números.")

            return usuariosRepository!!.save(usuario)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @kotlin.jvm.Throws(BusinessException::class)
    override fun saveUsuarios(usuarios: List<Usuarios>): List<Usuarios> {
        try {
            for (item in usuarios){
            if (item.nombreEmpleado.trim().isEmpty())
                throw BusinessException("El nombre del empleado no puede estar vacio.")
            if (item.nombreEmpleado.length < 3)
                throw BusinessException("El nombre del empleado no puede tener menos de 3 letras.")
            if (item.nombreEmpleado.length > 20)
                throw BusinessException("El nombre del empleado no puede tener más de 20 letras.")
            if (!item.nombreEmpleado.matches(Regex("^[A-Z]{1}[a-záéíóú]*$")))
                throw BusinessException("El nombre debe de comenzar con mayúscula y solo puede tener letras.")
            if (item.apellidoEmpleado.trim().isEmpty())
                throw BusinessException("El apellido del empleado no puede estar vacio.")
            if (item.apellidoEmpleado.length < 2)
                throw BusinessException("El apellido del empleado no puede tener menos de 2 letras.")
            if (item.apellidoEmpleado.length > 20)
                throw BusinessException("El apellido del empleado no puede tener más de 20 letras.")
            if (!item.apellidoEmpleado.matches(Regex("^[A-Z]{1}[a-záéíóú]*$")))
                throw BusinessException("El apellido debe de comenzar con mayúscula y solo puede tener letras.")
            if (item.nombreUsuario.trim().isEmpty())
                throw BusinessException("El nombre de usuario no puede estar vacio.")
            if (item.nombreUsuario.length < 3)
                throw BusinessException("El nombre de usuario no puede tener menos de 3 letras.")
            if (item.contrasena.trim().isEmpty())
                throw BusinessException("La contraseña de usuario no puede estar vacia.")
            if (item.contrasena.length < 8)
                throw BusinessException("La contraseña del empleado no puede tener menos de 8 caracteres.")
            if (!item.contrasena.matches(Regex("^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z]).*\$")))
                throw BusinessException("La contraseña tiene que contener al menos un número, una minúscula y una mayúscula.")
            if (item.idAreaLaboral.toString().trim().isEmpty())
                throw BusinessException("El area laboral del emplaedo no puede estar vacia.")
            if (item.idSexo.toString().trim().isEmpty())
                throw BusinessException("El sexo del emplaedo no puede estar vacio.")
            if (item.idTipoDeDocumento.toString().trim().isEmpty())
                throw BusinessException("El tipo de documento del emplaedo no puede estar vacio.")
            if (item.documentoEmpleado.trim().isEmpty())
                throw BusinessException("El documento del empleado no puede estar vacio.")
            if (item.documentoEmpleado.length < 6)
                throw BusinessException("El numero de documento del empleado no puede tener menos de 6 caracteres.")
            if (!item.documentoEmpleado.matches(Regex("^[A-Z0-9]*$")))
                throw BusinessException("El documento solo permite letras mayúsculas y números.")
            }
            return usuariosRepository!!.saveAll(usuarios)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun removeUsuario(idUsuario: Int) {
        val opt: Optional<Usuarios>
        try {
            opt=usuariosRepository!!.findById(idUsuario)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro el usuario con el Id:$idUsuario")
        else{
            try {
                usuariosRepository!!.deleteById(idUsuario)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
    }

    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun updateUsuario(usuario: Usuarios): Usuarios {
        val opt: Optional<Usuarios>
        try {
            opt=usuariosRepository!!.findById(usuario.idUsuario)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro el usuario con el Id:${usuario.idUsuario}")
        else{
            try {
                //validaciones
                if (usuario.nombreEmpleado.trim().isEmpty())
                    throw BusinessException("El nombre del empleado no puede estar vacio.")
                if (usuario.nombreEmpleado.length < 3)
                    throw BusinessException("El nombre del empleado no puede tener menos de 3 letras.")
                if (usuario.nombreEmpleado.length > 20)
                    throw BusinessException("El nombre del empleado no puede tener más de 20 letras.")
                if (!usuario.nombreEmpleado.matches(Regex("^[A-Z]{1}[a-záéíóú]*$")))
                    throw BusinessException("El nombre debe de comenzar con mayúscula y solo puede tener letras.")
                if (usuario.apellidoEmpleado.trim().isEmpty())
                    throw BusinessException("El apellido del empleado no puede estar vacio.")
                if (usuario.apellidoEmpleado.length < 2)
                    throw BusinessException("El apellido del empleado no puede tener menos de 2 letras.")
                if (usuario.apellidoEmpleado.length > 20)
                    throw BusinessException("El apellido del empleado no puede tener más de 20 letras.")
                if (!usuario.apellidoEmpleado.matches(Regex("^[A-Z]{1}[a-záéíóú]*$")))
                    throw BusinessException("El apellido debe de comenzar con mayúscula y solo puede tener letras.")
                if (usuario.nombreUsuario.trim().isEmpty())
                    throw BusinessException("El nombre de usuario no puede estar vacio.")
                if (usuario.nombreUsuario.length < 3)
                    throw BusinessException("El nombre de usuario no puede tener menos de 3 letras.")
                if (usuario.contrasena.trim().isEmpty())
                    throw BusinessException("La contraseña de usuario no puede estar vacia.")
                if (usuario.contrasena.length < 8)
                    throw BusinessException("La contraseña del empleado no puede tener menos de 8 caracteres.")
                if (!usuario.contrasena.matches(Regex("^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z]).*\$")))
                    throw BusinessException("La contraseña tiene que contener al menos un número, una minúscula y una mayúscula.")
                if (usuario.idAreaLaboral.toString().trim().isEmpty())
                    throw BusinessException("El area laboral del emplaedo no puede estar vacia.")
                if (usuario.idSexo.toString().trim().isEmpty())
                    throw BusinessException("El sexo del emplaedo no puede estar vacio.")
                if (usuario.idTipoDeDocumento.toString().trim().isEmpty())
                    throw BusinessException("El tipo de documento del emplaedo no puede estar vacio.")
                if (usuario.documentoEmpleado.trim().isEmpty())
                    throw BusinessException("El documento del empleado no puede estar vacio.")
                if (usuario.documentoEmpleado.length < 6)
                    throw BusinessException("El numero de documento del empleado no puede tener menos de 6 caracteres.")
                if (!usuario.documentoEmpleado.matches(Regex("^[A-Z0-9]*$")))
                    throw BusinessException("El documento solo permite letras mayúsculas y números.")

                val clienteExiste = Usuarios(usuario.nombreEmpleado,usuario.apellidoEmpleado,usuario.nombreUsuario,usuario.contrasena,usuario.numeroDeIntentos,
                    usuario.estadoUsuario,usuario.admin,usuario.documentoEmpleado,usuario.idSexo,usuario.idTipoDeDocumento,usuario.FechaDeCambio,usuario.idAreaLaboral)
                usuariosRepository!!.save(usuario)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
        return opt.get()
    }

    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun getUsuarioByNombreUsuario(nombreUsuario: String): Usuarios {
        val opt: Optional<Usuarios>
        try {
            opt=usuariosRepository!!.findUsuariosByNombreUsuario(nombreUsuario)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro un usuario con el nombre de usuario:$nombreUsuario")
        return opt.get()
    }

    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun getUsuarioByDocumentoEmpleado(documentoEmpleado: String): Usuarios {
        val opt: Optional<Usuarios>
        try {
            opt=usuariosRepository!!.findUsuariosByDocumentoEmpleado(documentoEmpleado)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro un usuario con el documento:$documentoEmpleado")
        return opt.get()
    }
}