package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.business

import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.dao.AreaLaboralRepository
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.exceptions.BusinessException
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.exceptions.NotFoundException
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model.AreaLaboral
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class AreaLaboralBusiness : IAreaLaboralBusiness {

    @Autowired
    val areaLaboralRepository : AreaLaboralRepository? = null

    @kotlin.jvm.Throws(BusinessException::class)
    override fun getAreasLaboral(): List<AreaLaboral> {
        try {
            return areaLaboralRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @kotlin.jvm.Throws(BusinessException::class,NotFoundException::class)
    override fun getAreaLaboralById(idAreaLaboral: Int): AreaLaboral {
        val opt:Optional<AreaLaboral>
        try {
            opt=areaLaboralRepository!!.findById(idAreaLaboral)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro area laboral con el Id:$idAreaLaboral")
        return opt.get()
    }

    @kotlin.jvm.Throws(BusinessException::class)
    override fun saveAreaLaboral(areaLaboral: AreaLaboral): AreaLaboral {
        try {

            if (areaLaboral.nombreAreaLaboral.trim().isEmpty())
                throw BusinessException("El nombre del area laboral no puede estar vacio.")
            if (areaLaboral.nombreAreaLaboral.length < 3)
                throw BusinessException("El nombre del area laboral no puede tener menos de 3 letras.")
            if (areaLaboral.nombreAreaLaboral.length > 25)
                throw BusinessException("El nombre del area laboral no puede tener más de 25 letras.")
            if (areaLaboral.descripcionAreaLaboral.trim().isEmpty())
                throw BusinessException("La descripción del area laboral no puede estar vacio.")
            if (areaLaboral.descripcionAreaLaboral.length < 3)
                throw BusinessException("La descripción del area laboral no puede tener menos de 3 letras.")
            if (areaLaboral.descripcionAreaLaboral.length > 50)
                throw BusinessException("La descripción del area laboral no puede tener más de 50 letras.")

            return areaLaboralRepository!!.save(areaLaboral)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @kotlin.jvm.Throws(BusinessException::class)
    override fun saveAreas(areaLaboral: List<AreaLaboral>): List<AreaLaboral> {
        try {
            for (item in areaLaboral){
                if (item.nombreAreaLaboral.trim().isEmpty())
                    throw BusinessException("El nombre del area laboral no puede estar vacio.")
                if (item.nombreAreaLaboral.length < 3)
                    throw BusinessException("El nombre del area laboral no puede tener menos de 3 letras.")
                if (item.nombreAreaLaboral.length > 25)
                    throw BusinessException("El nombre del area laboral no puede tener más de 25 letras.")
                if (item.descripcionAreaLaboral.trim().isEmpty())
                    throw BusinessException("La descripción del area laboral no puede estar vacio.")
                if (item.descripcionAreaLaboral.length < 3)
                    throw BusinessException("La descripción del area laboral no puede tener menos de 3 letras.")
                if (item.descripcionAreaLaboral.length > 50)
                    throw BusinessException("La descripción del area laboral no puede tener más de 50 letras.")
            }
            return areaLaboralRepository!!.saveAll(areaLaboral)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @kotlin.jvm.Throws(BusinessException::class,NotFoundException::class)
    override fun removeAreaLaboral(idArea: Int) {
        val opt:Optional<AreaLaboral>
        try {
            opt=areaLaboralRepository!!.findById(idArea)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro area laboral con el Id:$idArea")
        else{
            try {
                areaLaboralRepository!!.deleteById(idArea)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
    }

    @kotlin.jvm.Throws(BusinessException::class,NotFoundException::class)
    override fun updateAreaLaboral(areaLaboral: AreaLaboral): AreaLaboral {
        val opt:Optional<AreaLaboral>
        try {
            opt=areaLaboralRepository!!.findById(areaLaboral.idAreaLaboral)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro area laboral con el Id:${areaLaboral.idAreaLaboral}")
        else{
            try {
                if (areaLaboral.nombreAreaLaboral.trim().isEmpty())
                    throw BusinessException("El nombre del area laboral no puede estar vacio.")
                if (areaLaboral.nombreAreaLaboral.length < 3)
                    throw BusinessException("El nombre del area laboral no puede tener menos de 3 letras.")
                if (areaLaboral.nombreAreaLaboral.length > 25)
                    throw BusinessException("El nombre del area laboral no puede tener más de 25 letras.")
                if (areaLaboral.descripcionAreaLaboral.trim().isEmpty())
                    throw BusinessException("La descripción del area laboral no puede estar vacio.")
                if (areaLaboral.descripcionAreaLaboral.length < 3)
                    throw BusinessException("La descripción del area laboral no puede tener menos de 3 letras.")
                if (areaLaboral.descripcionAreaLaboral.length > 50)
                    throw BusinessException("La descripción del area laboral no puede tener más de 50 letras.")

                val areaExiste = AreaLaboral(areaLaboral.nombreAreaLaboral,areaLaboral.descripcionAreaLaboral,areaLaboral.estadoAreaLaboral)
                areaLaboralRepository!!.save(areaLaboral)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
        return opt.get()
    }

    @kotlin.jvm.Throws(BusinessException::class,NotFoundException::class)
    override fun getAreaLaboralByNombre(NombreAreaLaboral: String): AreaLaboral {
        val opt:Optional<AreaLaboral>
        try {
            opt=areaLaboralRepository!!.findByNombreAreaLaboral(NombreAreaLaboral)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro area laboral con el nombre:$NombreAreaLaboral")
        return opt.get()
    }

}