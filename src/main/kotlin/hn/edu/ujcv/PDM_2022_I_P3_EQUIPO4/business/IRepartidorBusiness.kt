package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.business

import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model.Repartidor
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model.TipoDeDocumento


interface IRepartidorBusiness {

    fun getRepartidores():List<Repartidor>
    fun getRepartidorById(idRepartidor: Int): Repartidor
    fun saveRepartidor(repartidor: Repartidor): Repartidor
    fun saveRepartidores(repartidores: List<Repartidor>):List<Repartidor>
    fun removeRepartidor(idRepartidor:Int)
    fun updateRepartidor(repartidor: Repartidor): Repartidor
    fun getRepartidorByNombreRepartidor(nombreRepartidor: String): Repartidor
    fun getRepartidorByDocumentoRepartidor(documentoRepartidor: String) : Repartidor

}