package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.business

import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model.TiempoDeEntrega


interface ITiempoDeEntregaBusiness {
    fun getTiemposDeEntrega():List<TiempoDeEntrega>
    fun getTiempoDeEntregaById(idTiempoDeEntrega: Int): TiempoDeEntrega
    fun saveTiempoDeEntrega(tiempoDeEntrega: TiempoDeEntrega): TiempoDeEntrega
    fun saveTiempoDeEntregaList(tiempoDeEntregaList: List<TiempoDeEntrega>):List<TiempoDeEntrega>
    fun removeTiempoDeEntrega(idTiempoDeEntrega:Int)
    fun updateTiempoDeEntrega(tiempoDeEntrega: TiempoDeEntrega): TiempoDeEntrega
    fun getTiempoDeEntregaByEstadoTiempoDeEntrega(estadoTiempoDeEntrega: Boolean): List<TiempoDeEntrega>
}