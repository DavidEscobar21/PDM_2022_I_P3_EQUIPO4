package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model

import javax.persistence.*

@Entity
@Table(name = "TipoDePago")
data class TipoDePago (val nombreTipoDePago:String="",
                       val descripcionTipoDePago:String=""){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idTipoDePago:Int = 0
}