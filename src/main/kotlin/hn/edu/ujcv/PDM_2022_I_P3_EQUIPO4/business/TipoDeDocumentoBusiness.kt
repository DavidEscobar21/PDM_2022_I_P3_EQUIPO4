package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.business

import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.dao.TipoDeDocumentoRepository
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.exceptions.BusinessException
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.exceptions.NotFoundException
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model.TipoDeDocumento
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class TipoDeDocumentoBusiness : ITipoDeDocumentoBusiness{
    @Autowired
    val tipoDeDocumentoRepository : TipoDeDocumentoRepository? = null

    @kotlin.jvm.Throws(BusinessException::class)
    override fun getTiposDeDocumento(): List<TipoDeDocumento> {
        try {
            return tipoDeDocumentoRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun getTipoDeDocumentoById(idTipoDeDocumento: Int): TipoDeDocumento {
        val opt: Optional<TipoDeDocumento>
        try {
            opt=tipoDeDocumentoRepository!!.findById(idTipoDeDocumento)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro tipo de documento con el Id:$idTipoDeDocumento")
        return opt.get()
    }

    @kotlin.jvm.Throws(BusinessException::class)
    override fun saveTipoDeDocumento(tipoDeDocumento: TipoDeDocumento): TipoDeDocumento {
        try {

            if (tipoDeDocumento.nombreTipoDocumento.isEmpty())
                throw BusinessException("El nombre del tipo de documento no puede estar vacio.")
            if (tipoDeDocumento.nombreTipoDocumento.length < 4)
                throw BusinessException("El nombre del tipo de documento no puede ser tan corto.")

            return tipoDeDocumentoRepository!!.save(tipoDeDocumento)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @kotlin.jvm.Throws(BusinessException::class)
    override fun saveTiposDeDocumento(tipoDeDocumentos: List<TipoDeDocumento>): List<TipoDeDocumento> {
        try {
            for (item in tipoDeDocumentos){
                if (item.nombreTipoDocumento.isEmpty())
                    throw BusinessException("El nombre del tipo de documento no puede estar vacio. Id:${item.idTipoDeDocumento}")
                if (item.nombreTipoDocumento.length < 4)
                    throw BusinessException("El nombre del tipo de documento no puede ser tan corto. Id:${item.idTipoDeDocumento}")
            }
            return tipoDeDocumentoRepository!!.saveAll(tipoDeDocumentos)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun removeTipoDeDocumento(idTipoDeDocumento: Int) {
        val opt: Optional<TipoDeDocumento>
        try {
            opt=tipoDeDocumentoRepository!!.findById(idTipoDeDocumento)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro tipos de documento con el Id:$idTipoDeDocumento")
        else{
            try {
                tipoDeDocumentoRepository!!.deleteById(idTipoDeDocumento)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
    }

    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun updateTipoDeDocumento(tipoDeDocumento: TipoDeDocumento): TipoDeDocumento {
        val opt: Optional<TipoDeDocumento>
        try {
            opt=tipoDeDocumentoRepository!!.findById(tipoDeDocumento.idTipoDeDocumento)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro tipo de documento con el Id:${tipoDeDocumento.idTipoDeDocumento}")
        else{
            try {
                //validaciones
                if (tipoDeDocumento.nombreTipoDocumento.isEmpty())
                    throw BusinessException("El nombre del tipo de documento no puede estar vacio.")
                if (tipoDeDocumento.nombreTipoDocumento.length < 4)
                    throw BusinessException("El nombre del tipo de documento no puede ser tan corto.")
                val tipoDeDocumentoExiste = TipoDeDocumento(tipoDeDocumento.nombreTipoDocumento,tipoDeDocumento.descripcionTipoDocumento)
                tipoDeDocumentoRepository!!.save(tipoDeDocumento)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
        return opt.get()
    }

    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun getTipoDeDocumentoByNombre(nombreTipoDocumento: String): TipoDeDocumento {
        val opt: Optional<TipoDeDocumento>
        try {
            opt=tipoDeDocumentoRepository!!.findTipoDeDocumentoByNombreTipoDocumento(nombreTipoDocumento)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro tipo de documento con el nombre:$nombreTipoDocumento")
        return opt.get()
    }
}