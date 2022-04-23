package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model

import javax.persistence.*

@Entity
@Table(name = "AreaLaboral")
data class AreaLaboral (val nombreAreaLaboral:String="",
                        val descripcionAreaLaboral:String="",
                        val estadoAreaLaboral:Boolean = true){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idAreaLaboral:Int = 0
}