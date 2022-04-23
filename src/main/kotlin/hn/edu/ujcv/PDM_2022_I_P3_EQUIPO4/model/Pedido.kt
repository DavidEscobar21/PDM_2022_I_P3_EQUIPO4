package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "Pedido")
class Pedido (val fechaPedido: LocalDate?=null,
              val impuesto:Float = 0.0F,
              val subTotal:Float = 0.0F,
              val total:Float = 0.0F,
              val direccionPedido:String="",
              val idCliente:Int = 0,
              val idRepartidor:Int = 0,
              val idTipoDePago:Int = 0,
              val idTipoDeEntrega:Int = 0,
              val idEstado:Int = 0){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idPedido:Int = 0
}