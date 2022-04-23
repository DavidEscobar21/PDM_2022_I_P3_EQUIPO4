package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.business

import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model.TipoDePago


interface ITipoDePagoBusiness {

    fun getTiposDePago():List<TipoDePago>
    fun getTipoDePagoById(idTipoDePago: Int): TipoDePago
    fun saveTipoDePago(tipoDepago: TipoDePago): TipoDePago
    fun saveTiposDePago(tiposDePago: List<TipoDePago>):List<TipoDePago>
    fun removeTipoDePago(idTipoDePago:Int)
    fun updateTipoDePago(tipoDePago: TipoDePago): TipoDePago
    fun getTipoDePagoByNombre(nombreTipoDePago: String): TipoDePago
}