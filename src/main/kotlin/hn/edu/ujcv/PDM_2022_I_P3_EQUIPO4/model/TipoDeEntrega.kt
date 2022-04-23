package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model

import javax.persistence.*

@Entity
@Table(name = "TipoDeEntrega")
data class TipoDeEntrega (val nombreTipoDeEntrega:String="",
                          val descripcionTipoDeEntrega:String="",
                          val estadoTipoDeEntrega:Boolean = true){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idTipoDeEntrega:Int = 0
}