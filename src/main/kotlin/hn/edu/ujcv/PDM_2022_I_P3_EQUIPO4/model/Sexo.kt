package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model

import javax.persistence.*

@Entity
@Table(name = "Sexo")
data class Sexo (val nombreSexo:String=""){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idSexo:Int = 0
}