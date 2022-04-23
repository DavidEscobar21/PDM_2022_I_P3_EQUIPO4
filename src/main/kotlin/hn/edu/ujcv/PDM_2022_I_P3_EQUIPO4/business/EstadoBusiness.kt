package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.business

import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.dao.EstadoRepository
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.exceptions.BusinessException
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.exceptions.NotFoundException
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model.Estado
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class EstadoBusiness : IEstadoBusiness {

    @Autowired
    val estadoRepository : EstadoRepository? = null

    @kotlin.jvm.Throws(BusinessException::class)
    override fun getEstados(): List<Estado> {
        try {
            return estadoRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun getEstadoById(idEstado: Int): Estado {
        val opt: Optional<Estado>
        try {
            opt=estadoRepository!!.findById(idEstado)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro estado con el Id:$idEstado")
        return opt.get()
    }

    @kotlin.jvm.Throws(BusinessException::class)
    override fun saveEstado(estado: Estado): Estado {
        try {

            if (estado.nombreEstado.isEmpty())
                throw BusinessException("El nombre del estado no puede estar vacio.")
            if (estado.nombreEstado.length < 4)
                throw BusinessException("El nombre del estado no puede ser tan corto.")

            return estadoRepository!!.save(estado)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @kotlin.jvm.Throws(BusinessException::class)
    override fun saveEstados(estados: List<Estado>): List<Estado> {
        try {
            for (item in estados){
                if (item.nombreEstado.isEmpty())
                    throw BusinessException("El nombre del estado no puede estar vacio.")
                if (item.nombreEstado.length < 4)
                    throw BusinessException("El nombre del estado no puede ser tan corto.")
            }
            return estadoRepository!!.saveAll(estados)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun removeEstado(idEstado: Int) {
        val opt: Optional<Estado>
        try {
            opt=estadoRepository!!.findById(idEstado)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro estado con el Id:$idEstado")
        else{
            try {
                estadoRepository!!.deleteById(idEstado)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
    }

    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun updateEstado(estado: Estado): Estado {
        val opt: Optional<Estado>
        try {
            opt=estadoRepository!!.findById(estado.idEstado)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro estado con el Id:${estado.idEstado}")
        else{
            try {
                //validaciones
                if (estado.nombreEstado.isEmpty())
                    throw BusinessException("El nombre del estado no puede estar vacio.")
                if (estado.nombreEstado.length < 4)
                    throw BusinessException("El nombre del estado no puede ser tan corto.")
                val estadoExiste = Estado(estado.nombreEstado,estado.descripcionEstado,estado.activoEstado)
                estadoRepository!!.save(estado)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
        return opt.get()
    }

    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun getEstadoByNombreEstado(nombreEstado: String): Estado {
        val opt: Optional<Estado>
        try {
            opt=estadoRepository!!.findEstadoByNombreEstado(nombreEstado)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro estado con el nombre:$nombreEstado")
        return opt.get()
    }
}