package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.business

import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model.Clientes

interface IClientesBusiness {

    fun getClientes():List<Clientes>
    fun getClienteById(idClientes: Int): Clientes
    fun saveCliente(cliente: Clientes): Clientes
    fun saveClientes(clientes: List<Clientes>):List<Clientes>
    fun removeCliente(idCliente:Int)
    fun updateCliente(cliente: Clientes): Clientes
    fun getClienteByNombre(nombreCliente: String): Clientes
}