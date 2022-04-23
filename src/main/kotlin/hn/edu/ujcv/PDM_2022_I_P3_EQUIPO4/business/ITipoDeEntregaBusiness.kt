package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.business

import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model.TipoDeEntrega

interface ITipoDeEntregaBusiness {
    fun getTiposDeEntrega():List<TipoDeEntrega>
    fun getTipoDeEntregaById(idTipoDeEntrega: Int): TipoDeEntrega
    fun saveTipoDeEntrega(tipoDeEntrega: TipoDeEntrega): TipoDeEntrega
    fun saveTiposDeEntrega(tiposDeEntrega: List<TipoDeEntrega>):List<TipoDeEntrega>
    fun removeTipoDeEntrega(idTipoDeEntrega:Int)
    fun updateTipoDeEntrega(tipoDeEntrega: TipoDeEntrega): TipoDeEntrega
    fun getTipoDeEntregaByNombreTipoDeEntrega(nombreTipoDeEntrega: String): TipoDeEntrega
}