package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.web

import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.business.ICategoriaBusiness
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.exceptions.BusinessException
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.exceptions.NotFoundException
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model.Categoria
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.utils.Constants
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.utils.RestApiError
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.URL_BASE_CATEGORIA)
class CategoriaRestController {

    @Autowired
    val categoriaBusiness: ICategoriaBusiness? = null

    @GetMapping("")
    fun list(): ResponseEntity<List<Categoria>> {
        return try{
            ResponseEntity(categoriaBusiness!!.getCategorias(), HttpStatus.OK)
        }catch(e : Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/id/{id}")
    fun loadById(@PathVariable("id") idCategoria: Int): ResponseEntity<Categoria> {
        return try{
            ResponseEntity(categoriaBusiness!!.getCategoriaById(idCategoria), HttpStatus.OK)
        }catch(e : BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e : NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/nombre/{nombre}")
    fun loadByNombre(@PathVariable("nombre") nombreCategoria : String) : ResponseEntity<Categoria> {
        return try{
            ResponseEntity(categoriaBusiness!!.getCategoriaByNombre(nombreCategoria), HttpStatus.OK)
        }catch(e : BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e : NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("/addCategoria")
    fun insert(@RequestBody categoria: Categoria): ResponseEntity<Any> {
        return try{
            categoriaBusiness!!.saveCategoria(categoria)
            val responseHeader = HttpHeaders()
            responseHeader.set("location",Constants.URL_BASE_AREALABORAL + "/" + categoria.idCategoria)
            ResponseEntity(categoria,responseHeader, HttpStatus.CREATED)
        }catch(e : BusinessException){
            val apiError = RestApiError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Informacion enviada no es valida.",
                e.message.toString()
            )
            ResponseEntity(apiError, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PostMapping("/addCategorias")
    fun insert(@RequestBody categorias : List<Categoria>): ResponseEntity<Any> {
        return try{
            ResponseEntity(categoriaBusiness!!.saveCategorias(categorias), HttpStatus.CREATED)
        }catch(e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping("")
    fun update(@RequestBody categoria: Categoria) : ResponseEntity<Any> {
        return try{
            categoriaBusiness!!.updateCategoria(categoria)
            ResponseEntity(categoria, HttpStatus.OK)
        }catch(e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch(e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable("id") idCategoria: Int): ResponseEntity<Any> {
        return try{
            categoriaBusiness!!.removeCategoria(idCategoria)
            ResponseEntity(HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch(e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }

    }
}