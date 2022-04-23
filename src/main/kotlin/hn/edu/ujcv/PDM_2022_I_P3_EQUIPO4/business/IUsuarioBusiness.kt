package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.business

import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model.Usuarios


interface IUsuarioBusiness {

    fun getUsuarios():List<Usuarios>
    fun getUsuarioById(idUsuario: Int): Usuarios
    fun saveUsuario(usuario: Usuarios): Usuarios
    fun saveUsuarios(usuarios: List<Usuarios>):List<Usuarios>
    fun removeUsuario(idUsuario:Int)
    fun updateUsuario(usuario: Usuarios): Usuarios
    fun getUsuarioByDocumentoEmpleado(documentoEmpleado: String): Usuarios
    fun getUsuarioByNombreUsuario(nombreUsuario : String) : Usuarios
}