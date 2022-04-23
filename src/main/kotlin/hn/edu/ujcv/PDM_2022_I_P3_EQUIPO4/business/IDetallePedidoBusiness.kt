package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.business

import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model.DetallePedido

interface IDetallePedidoBusiness {

    fun getDetallesPedido():List<DetallePedido>
    fun getDetallePedidoById(idDetallePedido: Int): DetallePedido
    fun saveDetallePedido(detallePedido: DetallePedido): DetallePedido
    fun saveDetallesPedido(detallesPedido: List<DetallePedido>):List<DetallePedido>
    fun getDetallePedidoByIdPedidos(idPedido: Int): List<DetallePedido>
    fun removeDetallePedido(idDetallePedido: Int)

}