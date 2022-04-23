package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.dao

import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model.Factura
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface FacturaRepository : JpaRepository<Factura,Int>{

    fun findFacturaByIdPedido(idPedido : Int): Optional<Factura>

}