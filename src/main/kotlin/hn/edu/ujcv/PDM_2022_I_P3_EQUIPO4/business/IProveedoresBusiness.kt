package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.business

import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model.Proveedores

interface IProveedoresBusiness{

    fun getProveedores():List<Proveedores>
    fun getProveedorById(idProveedor: Int): Proveedores
    fun saveProveedor(proveedores: Proveedores): Proveedores
    fun saveProveedores(proveedores: List<Proveedores>):List<Proveedores>
    fun removeProveedor(idProveedor:Int)
    fun updateProveedor(proveedores: Proveedores): Proveedores
    fun getProveedorByNombre(nombreProveedor: String): Proveedores

}