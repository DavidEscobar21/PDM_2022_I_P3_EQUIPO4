package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model

import javax.persistence.*

@Entity
@Table(name = "Estado")
data class Estado (val nombreEstado:String="",
                   val descripcionEstado:String="",
                   val activoEstado:Boolean = true){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idEstado:Int = 0
}