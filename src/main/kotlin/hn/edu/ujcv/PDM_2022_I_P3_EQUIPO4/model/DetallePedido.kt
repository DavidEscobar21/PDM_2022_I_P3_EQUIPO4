package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model

import javax.persistence.*

@Entity
@Table(name = "DetallePedido")
data class DetallePedido (val precio:Float = 0.0F,
                          val cantidad:Int = 0,
                          val idArticulos:Int = 0,
                          val idPedido:Int = 0){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idDetallePedido:Int = 0
}