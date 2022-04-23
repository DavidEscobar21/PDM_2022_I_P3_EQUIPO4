package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model

import javax.persistence.*


@Entity
@Table(name = "Clientes")
data class Clientes (val nombreCliente:String="",
                     val apellidoCliente:String="",
                     val telefonoCliente:String="",
                     val direccionCliente:String="",
                     val correoCliente:String="",
                     val estadoCliente:Boolean = true,
                     val contrasenaCliente:String = "",
                     val documentoCliente:String="",
                     val idTipoDeDocumento:Int = 0){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idCliente:Int = 0
}