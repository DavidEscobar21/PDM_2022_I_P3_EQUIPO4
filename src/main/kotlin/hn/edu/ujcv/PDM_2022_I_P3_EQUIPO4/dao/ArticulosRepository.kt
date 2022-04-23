package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.dao

import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model.Articulos
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface ArticulosRepository : JpaRepository<Articulos,Int> {

    fun findByNombreArticulo(nombreArticulos: String) : Optional<Articulos>
}