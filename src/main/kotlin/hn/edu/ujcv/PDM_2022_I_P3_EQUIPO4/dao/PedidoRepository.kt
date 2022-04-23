package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.dao

import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model.Pedido
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model.TipoDeEntrega
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface PedidoRepository : JpaRepository<Pedido,Int> {

    fun findPedidoByIdCliente(idCliente : Int) : Optional<List<Pedido>>
    fun findPedidoByIdRepartidor(idRepartidor : Int) : Optional<List<Pedido>>
    fun findPedidoByIdEstado(idEstado : Int) : Optional<List<Pedido>>
    fun findPedidoByIdTipoDeEntrega(idTipoDeEntrega: Int): Optional<List<Pedido>>

}