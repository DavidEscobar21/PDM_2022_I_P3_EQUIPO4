package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.web

import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.business.ITiempoDeEntregaBusiness
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.exceptions.BusinessException
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.exceptions.NotFoundException
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model.TiempoDeEntrega
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.utils.Constants
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.utils.RestApiError
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.URL_BASE_TIEMPODEENTREGA)
class TiempoDeEntregaRestController {
    @Autowired
    val tiempoDeEntregaBusiness: ITiempoDeEntregaBusiness? = null

    @GetMapping("")
    fun list(): ResponseEntity<List<TiempoDeEntrega>> {
        return try{
            ResponseEntity(tiempoDeEntregaBusiness!!.getTiemposDeEntrega(), HttpStatus.OK)
        }catch(e : Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/id/{id}")
    fun loadById(@PathVariable("id") idTiempoDeEntrega: Int): ResponseEntity<TiempoDeEntrega> {
        return try{
            ResponseEntity(tiempoDeEntregaBusiness!!.getTiempoDeEntregaById(idTiempoDeEntrega), HttpStatus.OK)
        }catch(e : BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e : NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/idEstado/{idEstado}")
    fun loadByIdEstado(@PathVariable("idEstado") estado : Boolean) : ResponseEntity<List<TiempoDeEntrega>> {
        return try{
            ResponseEntity(tiempoDeEntregaBusiness!!.getTiempoDeEntregaByEstadoTiempoDeEntrega(estado), HttpStatus.OK)
        }catch(e : BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e : NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("/addTiempoDeEntrega")
    fun insert(@RequestBody tiempoDeEntrega: TiempoDeEntrega): ResponseEntity<Any> {
        return try{
            tiempoDeEntregaBusiness!!.saveTiempoDeEntrega(tiempoDeEntrega)
            val responseHeader = HttpHeaders()
            responseHeader.set("location",Constants.URL_BASE_AREALABORAL + "/" + tiempoDeEntrega.idTiempoDeEntrega)
            ResponseEntity(tiempoDeEntrega,responseHeader, HttpStatus.CREATED)
        }catch(e : BusinessException){
            val apiError = RestApiError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Informacion enviada no es valida.",
                e.message.toString()
            )
            ResponseEntity(apiError, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PostMapping("/addTiemposDeEntrega")
    fun insert(@RequestBody tiemposDeEntrega : List<TiempoDeEntrega>): ResponseEntity<Any> {
        return try{
            ResponseEntity(tiempoDeEntregaBusiness!!.saveTiempoDeEntregaList(tiemposDeEntrega), HttpStatus.CREATED)
        }catch(e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping("")
    fun update(@RequestBody tiempoDeEntrega: TiempoDeEntrega) : ResponseEntity<Any> {
        return try{
            tiempoDeEntregaBusiness!!.updateTiempoDeEntrega(tiempoDeEntrega)
            ResponseEntity(tiempoDeEntrega, HttpStatus.OK)
        }catch(e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch(e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable("id") idTiempoDeEntrega: Int): ResponseEntity<Any> {
        return try{
            tiempoDeEntregaBusiness!!.removeTiempoDeEntrega(idTiempoDeEntrega)
            ResponseEntity(HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch(e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }

    }
}