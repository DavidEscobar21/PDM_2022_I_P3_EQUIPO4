package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.business

import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model.TipoDeDocumento


interface ITipoDeDocumentoBusiness {
    fun getTiposDeDocumento():List<TipoDeDocumento>
    fun getTipoDeDocumentoById(idTipoDeDocumento: Int): TipoDeDocumento
    fun saveTipoDeDocumento(tipoDocumento: TipoDeDocumento): TipoDeDocumento
    fun saveTiposDeDocumento(tiposDeDocumento: List<TipoDeDocumento>):List<TipoDeDocumento>
    fun removeTipoDeDocumento(idTipoDeDocumento:Int)
    fun updateTipoDeDocumento(tipoDeDocumento: TipoDeDocumento): TipoDeDocumento
    fun getTipoDeDocumentoByNombre(nombreTipoDeDocumento: String): TipoDeDocumento
}