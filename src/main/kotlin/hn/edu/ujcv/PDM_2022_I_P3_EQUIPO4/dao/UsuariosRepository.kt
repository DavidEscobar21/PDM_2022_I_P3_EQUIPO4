package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.dao

import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model.Usuarios
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface UsuariosRepository : JpaRepository<Usuarios,Int> {

    fun findUsuariosByNombreUsuario(nombreUsuario : String) : Optional<Usuarios>
    fun findUsuariosByDocumentoEmpleado(documentoEmpleado: String) : Optional<Usuarios>

}