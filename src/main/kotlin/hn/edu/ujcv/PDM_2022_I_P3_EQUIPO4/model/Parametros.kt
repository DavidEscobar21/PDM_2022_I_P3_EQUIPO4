package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "Parametros")
data class Parametros (val cai:String="",
                       val fechaEmision: LocalDate?=null,
                       val fechaCaducidad: LocalDate?=null,
                       val facturaInicial:Int = 0,
                       val facturaFinal:Int = 0){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idParametros:Int = 0
}