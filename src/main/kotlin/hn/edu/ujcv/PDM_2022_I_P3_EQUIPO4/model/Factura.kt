package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "Factura")
data class Factura (val fechaFactura: LocalDate?=null,
                    val numeroFactura:String="",
                    val totalFactura:Float = 0.0F,
                    val idPedido:Int = 0,
                    val idParametros:Int = 0){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idFactura:Int = 0
}