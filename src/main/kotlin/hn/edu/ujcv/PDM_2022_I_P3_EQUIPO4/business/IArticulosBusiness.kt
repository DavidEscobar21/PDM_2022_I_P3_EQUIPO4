package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.business

import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model.Articulos

interface IArticulosBusiness {
    fun getArticulos():List<Articulos>
    fun getArticuloById(idArticulo: Int): Articulos
    fun saveArticulo(articulo: Articulos): Articulos
    fun saveArticulos(articulo: List<Articulos>):List<Articulos>
    fun removeArticulo(idArticulo:Int)
    fun updateArticulo(articulo: Articulos): Articulos
    fun getArticuloByNombre(articulo: String): Articulos
}