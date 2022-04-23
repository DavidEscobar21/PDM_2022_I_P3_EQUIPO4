package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.business

import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model.Sexo


interface ISexoBusiness {
    fun getSexoList():List<Sexo>
    fun getSexoById(idSexo: Int): Sexo
    fun saveSexo(sexo: Sexo): Sexo
    fun saveSexoList(sexoList: List<Sexo>):List<Sexo>
    fun removeSexo(idSexo:Int)
    fun updateSexo(sexo: Sexo): Sexo
    fun getSexoByNombre(nombreSexo: String): Sexo
}