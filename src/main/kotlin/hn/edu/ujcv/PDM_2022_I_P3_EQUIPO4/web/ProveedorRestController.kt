package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.web

import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.business.IProveedoresBusiness
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.exceptions.BusinessException
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.exceptions.NotFoundException
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model.Proveedores
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.utils.Constants
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.utils.RestApiError
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.URL_BASE_PROVEEDORES)
class ProveedorRestController {
    @Autowired
    val proveedorBusiness: IProveedoresBusiness? = null

    @GetMapping("")
    fun list(): ResponseEntity<List<Proveedores>> {
        return try{
            ResponseEntity(proveedorBusiness!!.getProveedores(), HttpStatus.OK)
        }catch(e : Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/id/{id}")
    fun loadById(@PathVariable("id") idProveedor: Int): ResponseEntity<Proveedores> {
        return try{
            ResponseEntity(proveedorBusiness!!.getProveedorById(idProveedor), HttpStatus.OK)
        }catch(e : BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e : NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/nombre/{nombre}")
    fun loadByNombre(@PathVariable("nombre") nombreProveedores: String) : ResponseEntity<Proveedores> {
        return try{
            ResponseEntity(proveedorBusiness!!.getProveedorByNombre(nombreProveedores), HttpStatus.OK)
        }catch(e : BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e : NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("/addProveedor")
    fun insert(@RequestBody proveedores: Proveedores): ResponseEntity<Any> {
        return try{
            proveedorBusiness!!.saveProveedor(proveedores)
            val responseHeader = HttpHeaders()
            responseHeader.set("location", Constants.URL_BASE_AREALABORAL + "/" + proveedores.idProveedor)
            ResponseEntity(proveedores,responseHeader, HttpStatus.CREATED)
        }catch(e : BusinessException){
            val apiError = RestApiError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Informacion enviada no es valida.",
                e.message.toString()
            )
            ResponseEntity(apiError, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PostMapping("/addProveedores")
    fun insert(@RequestBody proveedores: List<Proveedores>): ResponseEntity<Any> {
        return try{
            ResponseEntity(proveedorBusiness!!.saveProveedores(proveedores), HttpStatus.CREATED)
        }catch(e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping("")
    fun update(@RequestBody proveedores: Proveedores) : ResponseEntity<Any> {
        return try{
            proveedorBusiness!!.updateProveedor(proveedores)
            ResponseEntity(proveedores, HttpStatus.OK)
        }catch(e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch(e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable("id") idProveedor: Int): ResponseEntity<Any> {
        return try{
            proveedorBusiness!!.removeProveedor(idProveedor)
            ResponseEntity(HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch(e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

}