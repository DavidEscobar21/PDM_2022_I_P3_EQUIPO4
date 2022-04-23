package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.dao

import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model.PedidosCancelados
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate
import java.util.*

@Repository
interface PedidosCanceladosRepository : JpaRepository<PedidosCancelados,Int>{

    fun findPedidosCanceladosByFechaCancelacion(fechaCancelacion : LocalDate): Optional<List<PedidosCancelados>>
    fun findPedidosCanceladosByIdPedido(idPedido : Int) : Optional<PedidosCancelados>

}