package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.business

import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model.PedidosCancelados
import java.time.LocalDate


interface IPedidosCanceladosBusiness {
    fun getPedidosCancelados():List<PedidosCancelados>
    fun getPedidoCanceladoById(idPedidoCancelado: Int): PedidosCancelados
    fun savePedidoCancelado(pedidoCancelado: PedidosCancelados): PedidosCancelados
    fun savePedidosCancelados(pedidosCancelados: List<PedidosCancelados>):List<PedidosCancelados>
    fun removePedidoCancelado(idPedidoCancelado: Int)
    fun updatePedidoCancelado(pedidoCancelado: PedidosCancelados): PedidosCancelados
    fun getPedidosCanceladosByFechaCancelacion(fechaCancelacion: LocalDate): List<PedidosCancelados>
    fun getPedidoCanceladoByIdPedido(idPedido : Int) : PedidosCancelados
}