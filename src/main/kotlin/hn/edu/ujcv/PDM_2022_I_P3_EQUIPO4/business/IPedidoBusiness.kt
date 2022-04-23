package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.business

import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model.Pedido


interface IPedidoBusiness {

    fun getPedidos():List<Pedido>
    fun getPedidoById(idPedido: Int): Pedido
    fun savePedido(pedido: Pedido): Pedido
    fun savePedidos(pedidos: List<Pedido>):List<Pedido>
    fun removePedido(idPedido:Int)
    fun updatePedido(pedido: Pedido): Pedido
    fun getPedidoByIdCliente(idCliente: Int): List<Pedido>
    fun getPedidoByIdRepartidor(idRepartidor: Int): List<Pedido>
    fun getPedidoByIdEstado(idEstado: Int): List<Pedido>
    fun getPedidoByIdTipoDeEntrega(idTipoDeEntrega: Int): List<Pedido>

}