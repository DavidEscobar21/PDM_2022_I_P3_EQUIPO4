package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.business

import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model.Parametros

interface IParametrosBusiness {

    fun getParametros():List<Parametros>
    fun getParametroById(idParametro: Int): Parametros
    fun saveParametro(parametro: Parametros): Parametros
    fun saveParametros(parametros: List<Parametros>):List<Parametros>
    fun removeParametro(idParametro:Int)
    fun updateParametro(parametro: Parametros): Parametros
    fun getParametroByCai(cai: String): Parametros
}