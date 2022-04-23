package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model

import javax.persistence.*

@Entity
@Table(name = "Repartidor")
data class Repartidor (val nombreRepartidor:String="",
                       val apellidoRepartidor:String="",
                       val telefonoRepartidor:String="",
                       val correoRepartidor:String="",
                       val estadoRepartidor:Boolean = true,
                       val documentoRepartidor:String="",
                       val idSexo:Int = 0,
                       val idTipoDeDocumento:Int = 0){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idRepartidor:Int = 0
}