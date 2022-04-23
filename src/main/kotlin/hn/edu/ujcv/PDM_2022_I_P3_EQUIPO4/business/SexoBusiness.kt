package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.business

import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.dao.SexoRepository
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.exceptions.BusinessException
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.exceptions.NotFoundException
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model.Sexo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class SexoBusiness : ISexoBusiness {
    @Autowired
    val sexoRepository : SexoRepository? = null

    @kotlin.jvm.Throws(BusinessException::class)
    override fun getSexoList(): List<Sexo> {
        try {
            return sexoRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun getSexoById(idSexo: Int): Sexo {
        val opt: Optional<Sexo>
        try {
            opt=sexoRepository!!.findById(idSexo)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro sexo con el Id:$idSexo")
        return opt.get()
    }

    @kotlin.jvm.Throws(BusinessException::class)
    override fun saveSexo(sexo: Sexo): Sexo {
        try {

            if (sexo.nombreSexo.isEmpty())
                throw BusinessException("El nombre del sexo no puede estar vacio.")
            if (sexo.nombreSexo.length < 2)
                throw BusinessException("El nombre del sexo no puede ser tan corto.")

            return sexoRepository!!.save(sexo)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @kotlin.jvm.Throws(BusinessException::class)
    override fun saveSexoList(sexoList: List<Sexo>): List<Sexo> {
        try {
            for (item in sexoList){
                if (item.nombreSexo.isEmpty())
                    throw BusinessException("El nombre del sexo no puede estar vacio. Id:${item.idSexo}")
                if (item.nombreSexo.length < 2)
                    throw BusinessException("El nombre del sexo no puede ser tan corto. Id:${item.idSexo}")
            }
            return sexoRepository!!.saveAll(sexoList)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun removeSexo(idSexo: Int) {
        val opt: Optional<Sexo>
        try {
            opt=sexoRepository!!.findById(idSexo)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro sexo con el Id:$idSexo")
        else{
            try {
                sexoRepository!!.deleteById(idSexo)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
    }

    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun updateSexo(sexo: Sexo): Sexo {
        val opt: Optional<Sexo>
        try {
            opt=sexoRepository!!.findById(sexo.idSexo)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro sexo con el Id:${sexo.idSexo}")
        else{
            try {
                //validaciones
                if (sexo.nombreSexo.isEmpty())
                    throw BusinessException("El nombre del sexo no puede estar vacio.")
                if (sexo.nombreSexo.length < 2)
                    throw BusinessException("El nombre del sexo no puede ser tan corto.")
                val sexoExiste = Sexo(sexo.nombreSexo)
                sexoRepository!!.save(sexo)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
        return opt.get()
    }

    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun getSexoByNombre(nombreSexo: String): Sexo {
        val opt: Optional<Sexo>
        try {
            opt=sexoRepository!!.findSexoByNombreSexo(nombreSexo)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro sexo con el nombre:$nombreSexo")
        return opt.get()
    }
}