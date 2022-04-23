package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.business

import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model.Estado


interface IEstadoBusiness {

    fun getEstados():List<Estado>
    fun getEstadoById(idEstado: Int): Estado
    fun saveEstado(estado: Estado): Estado
    fun saveEstados(estados: List<Estado>):List<Estado>
    fun removeEstado(idEstado:Int)
    fun updateEstado(estado: Estado): Estado
    fun getEstadoByNombreEstado(nombreEstado: String): Estado
}