package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.dao

import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model.Repartidor
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface RepartidorRepository : JpaRepository<Repartidor,Int> {

    fun findRepartidorByNombreRepartidor(nombreRepartidor: String) : Optional<Repartidor>
    fun findRepartidorByDocumentoRepartidor(documentoRepartidor: String) : Optional<Repartidor>


}