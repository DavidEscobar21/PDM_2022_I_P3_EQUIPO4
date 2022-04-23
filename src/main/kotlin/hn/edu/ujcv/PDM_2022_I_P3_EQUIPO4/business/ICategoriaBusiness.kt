package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.business

import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model.Categoria


interface ICategoriaBusiness {

    fun getCategorias():List<Categoria>
    fun getCategoriaById(idCategoria: Int): Categoria
    fun saveCategoria(Categoria: Categoria): Categoria
    fun saveCategorias(Categoria: List<Categoria>):List<Categoria>
    fun removeCategoria(idCategoria:Int)
    fun updateCategoria(Categoria: Categoria): Categoria
    fun getCategoriaByNombre(nombreCategoria: String): Categoria

}