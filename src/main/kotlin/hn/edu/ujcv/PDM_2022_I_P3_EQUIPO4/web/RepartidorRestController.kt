package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.web

import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.business.IRepartidorBusiness
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.exceptions.BusinessException
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.exceptions.NotFoundException
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model.Repartidor
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.utils.Constants
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.utils.RestApiError
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.URL_BASE_REPARTIDO)
class RepartidorRestController {

    @Autowired
    val repartidorBusiness: IRepartidorBusiness? = null

    @GetMapping("")
    fun list(): ResponseEntity<List<Repartidor>> {
        return try{
            ResponseEntity(repartidorBusiness!!.getRepartidores(), HttpStatus.OK)
        }catch(e : Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/id/{id}")
    fun loadById(@PathVariable("id") idRepartidor: Int): ResponseEntity<Repartidor> {
        return try{
            ResponseEntity(repartidorBusiness!!.getRepartidorById(idRepartidor), HttpStatus.OK)
        }catch(e : BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e : NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/nombre/{nombre}")
    fun loadByNombre(@PathVariable("nombre") nombreRepartidor : String) : ResponseEntity<Repartidor> {
        return try{
            ResponseEntity(repartidorBusiness!!.getRepartidorByNombreRepartidor(nombreRepartidor), HttpStatus.OK)
        }catch(e : BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e : NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/documento/{documento}")
    fun loadByDocumento(@PathVariable("documento") documentoRepartidor : String) : ResponseEntity<Repartidor> {
        return try{
            ResponseEntity(repartidorBusiness!!.getRepartidorByDocumentoRepartidor(documentoRepartidor), HttpStatus.OK)
        }catch(e : BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e : NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("/addRepartidor")
    fun insert(@RequestBody repartidor: Repartidor): ResponseEntity<Any> {
        return try{
            repartidorBusiness!!.saveRepartidor(repartidor)
            val responseHeader = HttpHeaders()
            responseHeader.set("location",Constants.URL_BASE_AREALABORAL + "/" + repartidor.idRepartidor)
            ResponseEntity(repartidor,responseHeader, HttpStatus.CREATED)
        }catch(e : BusinessException){
            val apiError = RestApiError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Informacion enviada no es valida.",
                e.message.toString()
            )
            ResponseEntity(apiError, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PostMapping("/addRepartidores")
    fun insert(@RequestBody repartidores : List<Repartidor>): ResponseEntity<Any> {
        return try{
            ResponseEntity(repartidorBusiness!!.saveRepartidores(repartidores), HttpStatus.CREATED)
        }catch(e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping("")
    fun update(@RequestBody repartidor: Repartidor) : ResponseEntity<Any> {
        return try{
            repartidorBusiness!!.updateRepartidor(repartidor)
            ResponseEntity(repartidor, HttpStatus.OK)
        }catch(e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch(e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable("id") idRepartidor: Int): ResponseEntity<Any> {
        return try{
            repartidorBusiness!!.removeRepartidor(idRepartidor)
            ResponseEntity(HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch(e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }

    }

}