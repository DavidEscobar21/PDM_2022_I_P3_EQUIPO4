package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.business

import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model.AreaLaboral

interface IAreaLaboralBusiness {
    fun getAreasLaboral():List<AreaLaboral>
    fun getAreaLaboralById(idAreaLaboral: Int):AreaLaboral
    fun saveAreaLaboral(areaLaboral: AreaLaboral):AreaLaboral
    fun saveAreas(areaLaboral: List<AreaLaboral>):List<AreaLaboral>
    fun removeAreaLaboral(idArea:Int)
    fun updateAreaLaboral(areaLaboral: AreaLaboral): AreaLaboral
    fun getAreaLaboralByNombre(nombreAreaLaboral: String): AreaLaboral
}