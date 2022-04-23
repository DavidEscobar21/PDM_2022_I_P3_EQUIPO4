package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.business

import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.dao.TiempoDeEntregaRepository
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.exceptions.BusinessException
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.exceptions.NotFoundException
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model.TiempoDeEntrega
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class TiempoDeEntregaBusiness : ITiempoDeEntregaBusiness{
    @Autowired
    val tiempoDeEntregaRepository : TiempoDeEntregaRepository? = null

    @kotlin.jvm.Throws(BusinessException::class)
    override fun getTiemposDeEntrega(): List<TiempoDeEntrega> {
        try {
            return tiempoDeEntregaRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun getTiempoDeEntregaById(idTiempoDeEntrega: Int): TiempoDeEntrega {
        val opt: Optional<TiempoDeEntrega>
        try {
            opt=tiempoDeEntregaRepository!!.findById(idTiempoDeEntrega)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro tiempo de entrega con el Id:$idTiempoDeEntrega")
        return opt.get()
    }

    @kotlin.jvm.Throws(BusinessException::class)
    override fun saveTiempoDeEntrega(tiempoDeEntrega: TiempoDeEntrega): TiempoDeEntrega {
        try {
            //validaciones
            if (tiempoDeEntrega.estimadoEntrega.trim().isEmpty())
                throw BusinessException("El estimado de entrega no puede estar vacio.")
            if (tiempoDeEntrega.estimadoEntrega.length < 3)
                throw BusinessException("El estimado de entrega no puede tener menos de 3 letras.")
            if (tiempoDeEntrega.estimadoEntrega.length > 50)
                throw BusinessException("El estimado de entrega no puede tener más de 50 letras.")
            if (tiempoDeEntrega.descripcionEstimadoEntrega.trim().isEmpty())
                throw BusinessException("La descripción no puede estar vacio.")
            if (tiempoDeEntrega.descripcionEstimadoEntrega.length < 3)
                throw BusinessException("La descripción no puede tener menos de 3 letras.")
            if (tiempoDeEntrega.descripcionEstimadoEntrega.length > 100)
                throw BusinessException("La descripción no puede tener más de 100 letras.")

            return tiempoDeEntregaRepository!!.save(tiempoDeEntrega)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @kotlin.jvm.Throws(BusinessException::class)
    override fun saveTiempoDeEntregaList(tiempoDeEntrega: List<TiempoDeEntrega>): List<TiempoDeEntrega> {
        try {
            for (item in tiempoDeEntrega){
                if (item.estimadoEntrega.trim().isEmpty())
                    throw BusinessException("El estimado de entrega no puede estar vacio.")
                if (item.estimadoEntrega.length < 3)
                    throw BusinessException("El estimado de entrega no puede tener menos de 3 letras.")
                if (item.estimadoEntrega.length > 50)
                    throw BusinessException("El estimado de entrega no puede tener más de 50 letras.")
                if (item.descripcionEstimadoEntrega.trim().isEmpty())
                    throw BusinessException("La descripción no puede estar vacio.")
                if (item.descripcionEstimadoEntrega.length < 3)
                    throw BusinessException("La descripción no puede tener menos de 3 letras.")
                if (item.descripcionEstimadoEntrega.length > 100)
                    throw BusinessException("La descripción no puede tener más de 100 letras.")
            }


            return tiempoDeEntregaRepository!!.saveAll(tiempoDeEntrega)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun removeTiempoDeEntrega(idTiempoDeEntrega: Int) {
        val opt: Optional<TiempoDeEntrega>
        try {
            opt=tiempoDeEntregaRepository!!.findById(idTiempoDeEntrega)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro tiempo de entrega con el Id:$idTiempoDeEntrega")
        else{
            try {
                tiempoDeEntregaRepository!!.deleteById(idTiempoDeEntrega)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
    }

    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun updateTiempoDeEntrega(tiempoDeEntrega: TiempoDeEntrega): TiempoDeEntrega {
        val opt: Optional<TiempoDeEntrega>
        try {
            opt=tiempoDeEntregaRepository!!.findById(tiempoDeEntrega.idTiempoDeEntrega)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro tiempo de entrega con el Id:${tiempoDeEntrega.idTiempoDeEntrega}")
        else{
            try {
                //validaciones
                if (tiempoDeEntrega.estimadoEntrega.trim().isEmpty())
                    throw BusinessException("El estimado de entrega no puede estar vacio.")
                if (tiempoDeEntrega.estimadoEntrega.length < 3)
                    throw BusinessException("El estimado de entrega no puede tener menos de 3 letras.")
                if (tiempoDeEntrega.estimadoEntrega.length > 50)
                    throw BusinessException("El estimado de entrega no puede tener más de 50 letras.")
                if (tiempoDeEntrega.descripcionEstimadoEntrega.trim().isEmpty())
                    throw BusinessException("La descripción no puede estar vacio.")
                if (tiempoDeEntrega.descripcionEstimadoEntrega.length < 3)
                    throw BusinessException("La descripción no puede tener menos de 3 letras.")
                if (tiempoDeEntrega.descripcionEstimadoEntrega.length > 100)
                    throw BusinessException("La descripción no puede tener más de 100 letras.")

                val entregaExiste = TiempoDeEntrega(tiempoDeEntrega.estimadoEntrega,tiempoDeEntrega.descripcionEstimadoEntrega,tiempoDeEntrega.estadoTiempoDeEntrega)
                tiempoDeEntregaRepository!!.save(tiempoDeEntrega)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
        return opt.get()
    }

    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun getTiempoDeEntregaByEstadoTiempoDeEntrega(idEstadoTiempoDeEntrega: Boolean): List<TiempoDeEntrega> {
        val opt: Optional<List<TiempoDeEntrega>>
        try {
            opt=tiempoDeEntregaRepository!!.findTiempoDeEntregaByEstadoTiempoDeEntrega(idEstadoTiempoDeEntrega)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro tiempo de entrega con el id de estado:$idEstadoTiempoDeEntrega")
        return opt.get()
    }
}