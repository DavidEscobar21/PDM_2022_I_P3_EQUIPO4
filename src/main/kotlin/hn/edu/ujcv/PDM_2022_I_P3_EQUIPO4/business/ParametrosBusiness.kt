package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.business

import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.dao.ParametrosRepository
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.exceptions.BusinessException
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.exceptions.NotFoundException
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model.Parametros
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class ParametrosBusiness : IParametrosBusiness {
    @Autowired
    val parametrosRepository : ParametrosRepository? = null

    @kotlin.jvm.Throws(BusinessException::class)
    override fun getParametros(): List<Parametros> {
        try {
            return parametrosRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun getParametroById(idParametro: Int): Parametros {
        val opt: Optional<Parametros>
        try {
            opt=parametrosRepository!!.findById(idParametro)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro parametro con el Id:$idParametro")
        return opt.get()
    }

    @kotlin.jvm.Throws(BusinessException::class)
    override fun saveParametro(parametro: Parametros): Parametros {
        try {
            //validaciones
            if (parametro.cai.isEmpty())
                throw BusinessException("El CAI no puede ir vacio.")
            if (!parametro.cai.matches(Regex("^[A-Z,0-9]{6}[-]{1}[A-Z,0-9]{6}[-]{1}[A-Z,0-9]{6}[-]{1}[A-Z,0-9]{6}[-]{1}[A-Z,0-9]{6}-[A-Z,0-9]{2}\$")))
                throw BusinessException("El formato del CAI es invalido")
            if (parametro.fechaCaducidad!!.isBefore(parametro.fechaEmision))
                throw BusinessException("La fecha de caducidad del parametro no puede ser menor a la fecha de emision.")
            if (parametro.facturaInicial <= 0)
                throw BusinessException("La factura inicial no puede ser menor o igual 0.")
            if (parametro.facturaFinal <= 0)
                throw BusinessException("La factura final no puede ser menor o igual 0.")
            if (parametro.facturaFinal <= parametro.facturaInicial)
                throw BusinessException("El valor de factura final debe ser mayor a factura inicial")

            return parametrosRepository!!.save(parametro)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @kotlin.jvm.Throws(BusinessException::class)
    override fun saveParametros(parametros: List<Parametros>): List<Parametros> {
        try {
            for (item in parametros){
                if (item.cai.isEmpty())
                    throw BusinessException("El CAI no puede ir vacio. ID: ${item.idParametros}")
                if (!item.cai.matches(Regex("^[A-Z,0-9]{6}[-]{1}[A-Z,0-9]{6}[-]{1}[A-Z,0-9]{6}[-]{1}[A-Z,0-9]{6}[-]{1}[A-Z,0-9]{6}-[A-Z,0-9]{2}\$")))
                    throw BusinessException("El formato del CAI es invalido. ID: ${item.idParametros}")
                if (item.fechaCaducidad!!.isBefore(item.fechaEmision))
                    throw BusinessException("La fecha de caducidad del parametro no puede ser menor a la fecha de emision. ID: ${item.idParametros}")
                if (item.facturaInicial < 0)
                    throw BusinessException("La factura inicial no puee ser menor a 0.ID: ${item.idParametros}")
                if (item.facturaFinal < 0)
                    throw BusinessException("La factura final no puede ser menor a 0.ID: ${item.idParametros}")
                if (item.facturaFinal <= item.facturaInicial)
                    throw BusinessException("El valor de factura final debe ser mayor a factura inicial.ID: ${item.idParametros}")
            }
            return parametrosRepository!!.saveAll(parametros)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun removeParametro(idParametro: Int) {
        val opt: Optional<Parametros>
        try {
            opt=parametrosRepository!!.findById(idParametro)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro parametro con el Id:$idParametro")
        else{
            try {
                parametrosRepository!!.deleteById(idParametro)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
    }

    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun updateParametro(parametro: Parametros): Parametros {
        val opt: Optional<Parametros>
        try {
            opt=parametrosRepository!!.findById(parametro.idParametros)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro area laboral con el Id:${parametro.idParametros}")
        else{
            try {
                //validaciones
                if (parametro.cai.isEmpty())
                    throw BusinessException("El CAI no puede ir vacio.")
                if (!parametro.cai.matches(Regex("^[A-Z,0-9]{6}[-]{1}[A-Z,0-9]{6}[-]{1}[A-Z,0-9]{6}[-]{1}[A-Z,0-9]{6}[-]{1}[A-Z,0-9]{6}-[A-Z,0-9]{2}\$")))
                    throw BusinessException("El formato del CAI es invalido")
                if (parametro.fechaCaducidad!!.isBefore(parametro.fechaEmision))
                    throw BusinessException("La fecha de caducidad del parametro no puede ser menor a la fecha de emision.")
                if (parametro.facturaInicial < 0)
                    throw BusinessException("La factura inicial no puee ser menor a 0.")
                if (parametro.facturaFinal < 0)
                    throw BusinessException("La factura final no puede ser menor a 0.")
                if (parametro.facturaFinal <= parametro.facturaInicial)
                    throw BusinessException("El valor de factura final debe ser mayor a factura inicial")
                parametrosRepository!!.save(parametro)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
        return opt.get()
    }

    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun getParametroByCai(cai: String): Parametros {
        val opt: Optional<Parametros>
        try {
            opt=parametrosRepository!!.findParametrosByCai(cai)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro parametro con el cai:$cai")
        return opt.get()
    }
}