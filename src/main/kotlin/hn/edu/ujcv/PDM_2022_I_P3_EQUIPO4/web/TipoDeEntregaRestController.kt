package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.web

import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.business.ITipoDeEntregaBusiness
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.exceptions.BusinessException
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.exceptions.NotFoundException
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model.TipoDeEntrega
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.utils.Constants
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.utils.RestApiError
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.URL_BASE_TIPODEENTREGA)
class TipoDeEntregaRestController {
    @Autowired
    val areaLaboralBusiness: ITipoDeEntregaBusiness? = null

    @GetMapping("")
    fun list(): ResponseEntity<List<TipoDeEntrega>> {
        return try{
            ResponseEntity(areaLaboralBusiness!!.getTiposDeEntrega(), HttpStatus.OK)
        }catch(e : Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/id/{id}")
    fun loadById(@PathVariable("id") idTipoDeEntrega: Int): ResponseEntity<TipoDeEntrega> {
        return try{
            ResponseEntity(areaLaboralBusiness!!.getTipoDeEntregaById(idTipoDeEntrega), HttpStatus.OK)
        }catch(e : BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e : NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/nombre/{nombre}")
    fun loadByNombre(@PathVariable("nombre") nombreTipoDeEntrega : String) : ResponseEntity<TipoDeEntrega> {
        return try{
            ResponseEntity(areaLaboralBusiness!!.getTipoDeEntregaByNombreTipoDeEntrega(nombreTipoDeEntrega), HttpStatus.OK)
        }catch(e : BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e : NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("/addTipoDeEntrega")
    fun insert(@RequestBody tipoDeEntrega: TipoDeEntrega): ResponseEntity<Any> {
        return try{
            areaLaboralBusiness!!.saveTipoDeEntrega(tipoDeEntrega)
            val responseHeader = HttpHeaders()
            responseHeader.set("location", Constants.URL_BASE_AREALABORAL + "/" + tipoDeEntrega.idTipoDeEntrega)
            ResponseEntity(tipoDeEntrega,responseHeader, HttpStatus.CREATED)
        }catch(e : BusinessException){
            val apiError = RestApiError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Informacion enviada no es valida.",
                e.message.toString()
            )
            ResponseEntity(apiError, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PostMapping("/addTiposDeEntrega")
    fun insert(@RequestBody tiposDeEntrega : List<TipoDeEntrega>): ResponseEntity<Any> {
        return try{
            ResponseEntity(areaLaboralBusiness!!.saveTiposDeEntrega(tiposDeEntrega), HttpStatus.CREATED)
        }catch(e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping("")
    fun update(@RequestBody tipoDeEntrega: TipoDeEntrega) : ResponseEntity<Any> {
        return try{
            areaLaboralBusiness!!.updateTipoDeEntrega(tipoDeEntrega)
            ResponseEntity(tipoDeEntrega, HttpStatus.OK)
        }catch(e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch(e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable("id") idTipoDeEntrega: Int): ResponseEntity<Any> {
        return try{
            areaLaboralBusiness!!.removeTipoDeEntrega(idTipoDeEntrega)
            ResponseEntity(HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch(e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }

    }
}