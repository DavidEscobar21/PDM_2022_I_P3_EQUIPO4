package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model

import javax.persistence.*

@Entity
@Table(name = "Categoria")
data class Categoria (val nombreCategoria:String="",
                      val descripcionCategoria:String="",
                      val estadoCategoria:Boolean = true){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idCategoria:Int = 0
}