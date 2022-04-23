package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model

import javax.persistence.*

@Entity
@Table
data class Proveedores(val nombreProveedor:String="",
                       val telefonoProveedor:String="",
                       val correoProveedor:String="",
                       val estadoProveedor:Boolean = true,
                       val documentoProveedor:String="",
                       val idTipoDeDocumento:Int = 0) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idProveedor: Int = 0
}
