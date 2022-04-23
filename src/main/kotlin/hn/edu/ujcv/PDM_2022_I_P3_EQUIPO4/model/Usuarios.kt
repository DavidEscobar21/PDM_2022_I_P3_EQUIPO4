package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "Usuarios")
data class Usuarios (val nombreEmpleado:String="",
                     val apellidoEmpleado:String="",
                     val nombreUsuario:String="",
                     val contrasena:String="",
                     val numeroDeIntentos:Int = 0,
                     val estadoUsuario:Boolean = true,
                     val admin:Boolean = false,
                     val documentoEmpleado:String="",
                     val idSexo:Int = 0,
                     val idTipoDeDocumento:Int = 0,
                     val FechaDeCambio:LocalDate?=null,
                     val idAreaLaboral:Int = 0){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idUsuario:Int = 0
}