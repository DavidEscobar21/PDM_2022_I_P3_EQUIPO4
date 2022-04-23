package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model

import javax.persistence.*

@Entity
@Table(name = "Articulos")
data class Articulos (val nombreArticulo:String="",
                      val stock:Int = 0,
                      val descripcionArticulo:String="",
                      val precioArticulo:Float = 0.0F,
                      val estadoArticulo:Boolean = true,
                      val idProveedor:Int = 0,
                      val idCategoria:Int = 0,
                      val idTiempoDeEntrega:Int = 0){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idArticulos:Int = 0
}