package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.business

import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.dao.CategoriaRespository
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.exceptions.BusinessException
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.exceptions.NotFoundException
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model.Categoria
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class CategoriaBusiness : ICategoriaBusiness {

    @Autowired
    val categoriaRepository : CategoriaRespository? = null

    @kotlin.jvm.Throws(BusinessException::class)
    override fun getCategorias(): List<Categoria> {
        try {
            return categoriaRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun getCategoriaById(idCategoria: Int): Categoria {
        val opt: Optional<Categoria>
        try {
            opt=categoriaRepository!!.findById(idCategoria)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro la categoria con el Id:$idCategoria")
        return opt.get()
    }

    @kotlin.jvm.Throws(BusinessException::class)
    override fun saveCategoria(categoria: Categoria): Categoria {
        try {
            //validaciones
            if (categoria.nombreCategoria.trim().isEmpty())
                throw BusinessException("El nombre de la categoria no puede estar vacio.")
            if (categoria.nombreCategoria.length < 4)
                throw BusinessException("El nombre de la categoria no puede tener menos de 4 letras.")
            if (categoria.nombreCategoria.length > 50)
                throw BusinessException("El nombre de la categoria no puede tener más de 50 letras.")
            if (!categoria.nombreCategoria.matches(Regex("^[A-Za-záéíóú\\s]*$")))
                throw BusinessException("El nombre de la categoria no puede contener números.")
            if (categoria.descripcionCategoria.trim().isEmpty())
                throw BusinessException("La descripción de la categoria no puede estar vacio.")
            if (categoria.descripcionCategoria.length < 3)
                throw BusinessException("La descripción de la categoria no puede tener menos de 3 caracteres.")
            if (categoria.descripcionCategoria.length > 100)
                throw BusinessException("La descripción de la categoria no puede tener más de 100 caracteres.")

            return categoriaRepository!!.save(categoria)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @kotlin.jvm.Throws(BusinessException::class)
    override fun saveCategorias(categorias: List<Categoria>): List<Categoria> {
        try {
            for (item in categorias){
                //validaciones
                if (item.nombreCategoria.trim().isEmpty())
                    throw BusinessException("El nombre de la categoria no puede estar vacio.")
                if (item.nombreCategoria.length < 4)
                    throw BusinessException("El nombre de la categoria no puede tener menos de 4 letras.")
                if (item.nombreCategoria.length > 50)
                    throw BusinessException("El nombre de la categoria no puede tener más de 50 letras.")
                if (!item.nombreCategoria.matches(Regex("^[A-Za-záéíóú\\s]*$")))
                    throw BusinessException("El nombre de la categoria no puede contener números.")
                if (item.descripcionCategoria.trim().isEmpty())
                    throw BusinessException("La descripción de la categoria no puede estar vacio.")
                if (item.descripcionCategoria.length < 3)
                    throw BusinessException("La descripción de la categoria no puede tener menos de 3 caracteres.")
                if (item.descripcionCategoria.length > 100)
                    throw BusinessException("La descripción de la categoria no puede tener más de 100 caracteres.")
            }

            return categoriaRepository!!.saveAll(categorias)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun removeCategoria(idCategoria: Int) {
        val opt: Optional<Categoria>
        try {
            opt=categoriaRepository!!.findById(idCategoria)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro la categoria con el Id:$idCategoria")
        else{
            try {
                categoriaRepository!!.deleteById(idCategoria)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
    }

    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun updateCategoria(categoria: Categoria): Categoria {
        val opt: Optional<Categoria>
        try {
            opt=categoriaRepository!!.findById(categoria.idCategoria)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro la categoria con el Id:${categoria.idCategoria}")
        else{
            try {
                //validaciones
                if (categoria.nombreCategoria.trim().isEmpty())
                    throw BusinessException("El nombre de la categoria no puede estar vacio.")
                if (categoria.nombreCategoria.length < 4)
                    throw BusinessException("El nombre de la categoria no puede tener menos de 4 letras.")
                if (categoria.nombreCategoria.length > 50)
                    throw BusinessException("El nombre de la categoria no puede tener más de 50 letras.")
                if (!categoria.nombreCategoria.matches(Regex("^[A-Za-záéíóú\\s]*$")))
                    throw BusinessException("El nombre de la categoria no puede contener números.")
                if (categoria.descripcionCategoria.trim().isEmpty())
                    throw BusinessException("La descripción de la categoria no puede estar vacio.")
                if (categoria.descripcionCategoria.length < 3)
                    throw BusinessException("La descripción de la categoria no puede tener menos de 3 caracteres.")
                if (categoria.descripcionCategoria.length > 100)
                    throw BusinessException("La descripción de la categoria no puede tener más de 100 caracteres.")

                val categoriaExiste = Categoria(categoria.nombreCategoria,categoria.descripcionCategoria,categoria.estadoCategoria)
                categoriaRepository!!.save(categoria)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
        return opt.get()
    }

    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun getCategoriaByNombre(nombreCategoria: String): Categoria {
        val opt: Optional<Categoria>
        try {
            opt=categoriaRepository!!.findByNombreCategoria(nombreCategoria)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro categoria con el nombre:$nombreCategoria")
        return opt.get()
    }

}