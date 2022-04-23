package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.business

import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.dao.TipoDePagoRepository
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.exceptions.BusinessException
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.exceptions.NotFoundException
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model.TipoDePago
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class TipoDePagoBusiness : ITipoDePagoBusiness{
    @Autowired
    val tipoDePagoRepository : TipoDePagoRepository? = null

    @kotlin.jvm.Throws(BusinessException::class)
    override fun getTiposDePago(): List<TipoDePago> {
        try {
            return tipoDePagoRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun getTipoDePagoById(idTipoDePago: Int): TipoDePago {
        val opt: Optional<TipoDePago>
        try {
            opt=tipoDePagoRepository!!.findById(idTipoDePago)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro tipo de pago con el Id:$idTipoDePago")
        return opt.get()
    }

    @kotlin.jvm.Throws(BusinessException::class)
    override fun saveTipoDePago(tipoDePago: TipoDePago): TipoDePago {
        try {

            if (tipoDePago.nombreTipoDePago.isEmpty())
                throw BusinessException("El nombre del tipo de pago no puede estar vacio.")
            if (tipoDePago.nombreTipoDePago.length < 4)
                throw BusinessException("El nombre del tipo de pago no puede ser tan corto.")
            if (tipoDePago.descripcionTipoDePago.length < 4)
                throw BusinessException("La descripcion del tipo de pago no puede ser tan corta.")

            return tipoDePagoRepository!!.save(tipoDePago)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @kotlin.jvm.Throws(BusinessException::class)
    override fun saveTiposDePago(tiposDePago: List<TipoDePago>): List<TipoDePago> {
        try {
            for (item in tiposDePago){
                if (item.nombreTipoDePago.isEmpty())
                    throw BusinessException("El nombre del tipo de pago no puede estar vacio.")
                if (item.nombreTipoDePago.length < 4)
                    throw BusinessException("El nombre del tipo de pago no puede ser tan corto.")
                if (item.descripcionTipoDePago.length < 4)
                    throw BusinessException("La descripcion del tipo de pago no puede ser tan corta.")
            }

            return tipoDePagoRepository!!.saveAll(tiposDePago)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun removeTipoDePago(idTipoDePago: Int) {
        val opt: Optional<TipoDePago>
        try {
            opt=tipoDePagoRepository!!.findById(idTipoDePago)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro tipo de pago con el Id:$idTipoDePago")
        else{
            try {
                tipoDePagoRepository!!.deleteById(idTipoDePago)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
    }

    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun updateTipoDePago(tipoDePago: TipoDePago): TipoDePago {
        val opt: Optional<TipoDePago>
        try {
            opt=tipoDePagoRepository!!.findById(tipoDePago.idTipoDePago)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro tipo de documento con el Id:${tipoDePago.idTipoDePago}")
        else{
            try {
                //validaciones
                if (tipoDePago.nombreTipoDePago.isEmpty())
                    throw BusinessException("El nombre del tipo de pago no puede estar vacio.")
                if (tipoDePago.nombreTipoDePago.length < 4)
                    throw BusinessException("El nombre del tipo de pago no puede ser tan corto.")
                if (tipoDePago.descripcionTipoDePago.length < 4)
                    throw BusinessException("La descripcion del tipo de pago no puede ser tan corta.")
                val tipodePagoExistent = TipoDePago(tipoDePago.nombreTipoDePago,tipoDePago.descripcionTipoDePago)
                tipoDePagoRepository!!.save(tipoDePago)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
        return opt.get()
    }

    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun getTipoDePagoByNombre(nombreTipoDePago: String): TipoDePago {
        val opt: Optional<TipoDePago>
        try {
            opt=tipoDePagoRepository!!.findTipoDePagoByNombreTipoDePago(nombreTipoDePago)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro tipo de pago con el nombre:$nombreTipoDePago")
        return opt.get()
    }
}