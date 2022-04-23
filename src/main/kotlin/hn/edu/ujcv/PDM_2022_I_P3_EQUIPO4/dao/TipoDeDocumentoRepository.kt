package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.dao

import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model.TipoDeDocumento
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface TipoDeDocumentoRepository : JpaRepository<TipoDeDocumento,Int>{

    fun findTipoDeDocumentoByNombreTipoDocumento(nombreTipoDeDocumento: String) : Optional<TipoDeDocumento>

}