package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.business

import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model.Factura


interface IFacturaBusiness {

    fun getFacturas():List<Factura>
    fun getFacturaById(idFactura: Int): Factura
    fun saveFactura(factura: Factura): Factura
    fun saveFacturas(facturas: List<Factura>):List<Factura>
    fun getFacturaByIdPedido(idPedido: Int): Factura
    fun removeFactura(idFactura: Int)

}