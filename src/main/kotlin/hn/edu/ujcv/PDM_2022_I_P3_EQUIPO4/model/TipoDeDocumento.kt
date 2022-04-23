package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model

import javax.persistence.*

@Entity
@Table(name = "TipoDeDocumento")
data class TipoDeDocumento (val nombreTipoDocumento:String="",
                            val descripcionTipoDocumento:String=""){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idTipoDeDocumento:Int = 0
}