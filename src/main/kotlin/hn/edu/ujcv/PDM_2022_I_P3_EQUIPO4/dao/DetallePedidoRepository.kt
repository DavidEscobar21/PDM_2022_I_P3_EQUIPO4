package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.dao

import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model.DetallePedido
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface DetallePedidoRepository : JpaRepository<DetallePedido,Int> {

    fun findDetallePedidoByIdPedido(idPedido : Int): Optional<List<DetallePedido>>
}