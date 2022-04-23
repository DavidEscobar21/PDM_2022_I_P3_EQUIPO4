package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.business

import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.dao.TipoDeEntregaRepository
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.exceptions.BusinessException
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.exceptions.NotFoundException
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model.TipoDeEntrega
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class TipoDeEntregaBusiness : ITipoDeEntregaBusiness{
    @Autowired
    val tipoDeEntregaRepository : TipoDeEntregaRepository? = null

    @kotlin.jvm.Throws(BusinessException::class)
    override fun getTiposDeEntrega(): List<TipoDeEntrega> {
        try {
            return tipoDeEntregaRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun getTipoDeEntregaById(idTipoDeEntrega: Int): TipoDeEntrega {
        val opt: Optional<TipoDeEntrega>
        try {
            opt=tipoDeEntregaRepository!!.findById(idTipoDeEntrega)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro tipo de entrega con el Id:$idTipoDeEntrega")
        return opt.get()
    }

    @kotlin.jvm.Throws(BusinessException::class)
    override fun saveTipoDeEntrega(tipoDeEntrega: TipoDeEntrega): TipoDeEntrega {
        try {

            if (tipoDeEntrega.nombreTipoDeEntrega.trim().isEmpty())
                throw BusinessException("El tipo de entrega no puede estar vacio.")
            if (tipoDeEntrega.nombreTipoDeEntrega.length < 3)
                throw BusinessException("El tipo de entrega no puede tener menos de 3 letras.")
            if (tipoDeEntrega.nombreTipoDeEntrega.length > 50)
                throw BusinessException("El tipo de entrega no puede tener más de 50 letras.")
            if (!tipoDeEntrega.nombreTipoDeEntrega.matches(Regex("^[A-Za-záéíóú\\s]*$")))
                throw BusinessException("El tipo de entrega no puede contener números.")
            if (tipoDeEntrega.descripcionTipoDeEntrega.trim().isEmpty())
                throw BusinessException("La descripción no puede estar vacio.")
            if (tipoDeEntrega.descripcionTipoDeEntrega.length < 3)
                throw BusinessException("La descripción no puede tener menos de 3 letras.")
            if (tipoDeEntrega.descripcionTipoDeEntrega.length > 100)
                throw BusinessException("La descripción no puede tener más de 100 letras.")

            return tipoDeEntregaRepository!!.save(tipoDeEntrega)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @kotlin.jvm.Throws(BusinessException::class)
    override fun saveTiposDeEntrega(tiposDeEntrega: List<TipoDeEntrega>): List<TipoDeEntrega> {
        try {
            for (item in tiposDeEntrega){
                if (item.nombreTipoDeEntrega.trim().isEmpty())
                    throw BusinessException("El tipo de entrega no puede estar vacio.")
                if (item.nombreTipoDeEntrega.length < 3)
                    throw BusinessException("El tipo de entrega no puede tener menos de 3 letras.")
                if (item.nombreTipoDeEntrega.length > 50)
                    throw BusinessException("El tipo de entrega no puede tener más de 50 letras.")
                if (!item.nombreTipoDeEntrega.matches(Regex("^[A-Za-záéíóú\\s]*$")))
                    throw BusinessException("El tipo de entrega no puede contener números.")
                if (item.descripcionTipoDeEntrega.trim().isEmpty())
                    throw BusinessException("La descripción no puede estar vacio.")
                if (item.descripcionTipoDeEntrega.length < 3)
                    throw BusinessException("La descripción no puede tener menos de 3 letras.")
                if (item.descripcionTipoDeEntrega.length > 100)
                    throw BusinessException("La descripción no puede tener más de 100 letras.")
            }
            return tipoDeEntregaRepository!!.saveAll(tiposDeEntrega)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun removeTipoDeEntrega(idTipoDeEntrega: Int) {
        val opt: Optional<TipoDeEntrega>
        try {
            opt=tipoDeEntregaRepository!!.findById(idTipoDeEntrega)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro tipo de entrega con el Id:$idTipoDeEntrega")
        else{
            try {
                tipoDeEntregaRepository!!.deleteById(idTipoDeEntrega)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
    }

    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun updateTipoDeEntrega(tipoDeEntrega: TipoDeEntrega): TipoDeEntrega {
        val opt: Optional<TipoDeEntrega>
        try {
            opt=tipoDeEntregaRepository!!.findById(tipoDeEntrega.idTipoDeEntrega)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro tipo de entrega con el Id:${tipoDeEntrega.idTipoDeEntrega}")
        else{
            try {
                //validaciones
                if (tipoDeEntrega.nombreTipoDeEntrega.trim().isEmpty())
                    throw BusinessException("El tipo de entrega no puede estar vacio.")
                if (tipoDeEntrega.nombreTipoDeEntrega.length < 3)
                    throw BusinessException("El tipo de entrega no puede tener menos de 3 letras.")
                if (tipoDeEntrega.nombreTipoDeEntrega.length > 50)
                    throw BusinessException("El tipo de entrega no puede tener más de 50 letras.")
                if (!tipoDeEntrega.nombreTipoDeEntrega.matches(Regex("^[A-Za-záéíóú\\s]*$")))
                    throw BusinessException("El tipo de entrega no puede contener números.")
                if (tipoDeEntrega.descripcionTipoDeEntrega.trim().isEmpty())
                    throw BusinessException("La descripción no puede estar vacio.")
                if (tipoDeEntrega.descripcionTipoDeEntrega.length < 3)
                    throw BusinessException("La descripción no puede tener menos de 3 letras.")
                if (tipoDeEntrega.descripcionTipoDeEntrega.length > 100)
                    throw BusinessException("La descripción no puede tener más de 100 letras.")

                val tipoDeEntregaExiste = TipoDeEntrega(tipoDeEntrega.nombreTipoDeEntrega,tipoDeEntrega.descripcionTipoDeEntrega,tipoDeEntrega.estadoTipoDeEntrega)
                tipoDeEntregaRepository!!.save(tipoDeEntrega)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
        return opt.get()
    }

    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun getTipoDeEntregaByNombreTipoDeEntrega(nombreTipoEntrega: String): TipoDeEntrega {
        val opt: Optional<TipoDeEntrega>
        try {
            opt=tipoDeEntregaRepository!!.findTipoDeEntregaByNombreTipoDeEntrega(nombreTipoEntrega)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro tipo de entrega con el nombre:$nombreTipoEntrega")
        return opt.get()
    }
}