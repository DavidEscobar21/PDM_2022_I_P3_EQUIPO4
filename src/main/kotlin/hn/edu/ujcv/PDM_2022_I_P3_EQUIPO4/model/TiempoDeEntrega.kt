package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model

import javax.persistence.*

@Entity
@Table(name = "TiempoDeEntrega")
data class TiempoDeEntrega (val estimadoEntrega:String="",
                            val descripcionEstimadoEntrega:String="",
                            val estadoTiempoDeEntrega:Boolean = true){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idTiempoDeEntrega:Int = 0
}