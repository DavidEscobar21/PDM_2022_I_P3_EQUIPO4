package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.web

import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.business.ITipoDePagoBusiness
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.exceptions.BusinessException
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.exceptions.NotFoundException
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model.TipoDePago
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.utils.Constants
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.utils.RestApiError
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.URL_BASE_TIPODEPAGO)
class TipoDePagoRestController {
    @Autowired
    val tipoDePagoBusiness: ITipoDePagoBusiness? = null

    @GetMapping("")
    fun list(): ResponseEntity<List<TipoDePago>> {
        return try{
            ResponseEntity(tipoDePagoBusiness!!.getTiposDePago(), HttpStatus.OK)
        }catch(e : Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/id/{id}")
    fun loadById(@PathVariable("id") idTipoDePago: Int): ResponseEntity<TipoDePago> {
        return try{
            ResponseEntity(tipoDePagoBusiness!!.getTipoDePagoById(idTipoDePago), HttpStatus.OK)
        }catch(e : BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e : NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/nombre/{nombre}")
    fun loadByNombre(@PathVariable("nombre") nombreTipoDePago : String) : ResponseEntity<TipoDePago> {
        return try{
            ResponseEntity(tipoDePagoBusiness!!.getTipoDePagoByNombre(nombreTipoDePago), HttpStatus.OK)
        }catch(e : BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e : NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("/addTipoDePago")
    fun insert(@RequestBody tipoDePago: TipoDePago): ResponseEntity<Any> {
        return try{
            tipoDePagoBusiness!!.saveTipoDePago(tipoDePago)
            val responseHeader = HttpHeaders()
            responseHeader.set("location", Constants.URL_BASE_AREALABORAL + "/" + tipoDePago.idTipoDePago)
            ResponseEntity(tipoDePago,responseHeader, HttpStatus.CREATED)
        }catch(e : BusinessException){
            val apiError = RestApiError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Informacion enviada no es valida.",
                e.message.toString()
            )
            ResponseEntity(apiError, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PostMapping("/addTiposDePago")
    fun insert(@RequestBody tiposDePago : List<TipoDePago>): ResponseEntity<Any> {
        return try{
            ResponseEntity(tipoDePagoBusiness!!.saveTiposDePago(tiposDePago), HttpStatus.CREATED)
        }catch(e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping("")
    fun update(@RequestBody tipoDePago: TipoDePago) : ResponseEntity<Any> {
        return try{
            tipoDePagoBusiness!!.updateTipoDePago(tipoDePago)
            ResponseEntity(tipoDePago, HttpStatus.OK)
        }catch(e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch(e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable("id") idTipoDePago: Int): ResponseEntity<Any> {
        return try{
            tipoDePagoBusiness!!.removeTipoDePago(idTipoDePago)
            ResponseEntity(HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch(e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }

    }
}