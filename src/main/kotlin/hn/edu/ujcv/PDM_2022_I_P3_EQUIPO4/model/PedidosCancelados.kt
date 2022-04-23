package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "PedidosCancelados")
data class PedidosCancelados (val fechaCancelacion: LocalDate?=null,
                              val descripcionCancelacion:String="",
                              val idPedido:Int = 0){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idPedidosCancelados:Int = 0
}