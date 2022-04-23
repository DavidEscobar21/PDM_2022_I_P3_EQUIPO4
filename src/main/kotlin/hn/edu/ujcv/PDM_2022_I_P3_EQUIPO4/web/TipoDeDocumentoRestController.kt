package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.web

import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.business.ITipoDeDocumentoBusiness
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.exceptions.BusinessException
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.exceptions.NotFoundException
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model.TipoDeDocumento
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.utils.Constants
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.utils.RestApiError
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.URL_BASE_TIPODEDOCUMENTO)
class TipoDeDocumentoRestController {
    @Autowired
    val areaLaboralBusiness: ITipoDeDocumentoBusiness? = null

    @GetMapping("")
    fun list(): ResponseEntity<List<TipoDeDocumento>> {
        return try{
            ResponseEntity(areaLaboralBusiness!!.getTiposDeDocumento(), HttpStatus.OK)
        }catch(e : Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/id/{id}")
    fun loadById(@PathVariable("id") idTipoDeDocumento: Int): ResponseEntity<TipoDeDocumento> {
        return try{
            ResponseEntity(areaLaboralBusiness!!.getTipoDeDocumentoById(idTipoDeDocumento), HttpStatus.OK)
        }catch(e : BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e : NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/nombre/{nombre}")
    fun loadByNombre(@PathVariable("nombre") nombreTipoDeDocument : String) : ResponseEntity<TipoDeDocumento> {
        return try{
            ResponseEntity(areaLaboralBusiness!!.getTipoDeDocumentoByNombre(nombreTipoDeDocument), HttpStatus.OK)
        }catch(e : BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e : NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("/addTipoDeDocumento")
    fun insert(@RequestBody tipoDeDocumento: TipoDeDocumento): ResponseEntity<Any> {
        return try{
            areaLaboralBusiness!!.saveTipoDeDocumento(tipoDeDocumento)
            val responseHeader = HttpHeaders()
            responseHeader.set("location",Constants.URL_BASE_AREALABORAL + "/" + tipoDeDocumento.idTipoDeDocumento)
            ResponseEntity(tipoDeDocumento,responseHeader, HttpStatus.CREATED)
        }catch(e : BusinessException){
            val apiError = RestApiError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Informacion enviada no es valida.",
                e.message.toString()
            )
            ResponseEntity(apiError, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PostMapping("/addTiposDeDocumento")
    fun insert(@RequestBody tiposDeDocumento : List<TipoDeDocumento>): ResponseEntity<Any> {
        return try{
            ResponseEntity(areaLaboralBusiness!!.saveTiposDeDocumento(tiposDeDocumento), HttpStatus.CREATED)
        }catch(e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping("")
    fun update(@RequestBody tipoDeDocumento: TipoDeDocumento) : ResponseEntity<Any> {
        return try{
            areaLaboralBusiness!!.updateTipoDeDocumento(tipoDeDocumento)
            ResponseEntity(tipoDeDocumento, HttpStatus.OK)
        }catch(e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch(e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable("id") idTipoDeDocumento: Int): ResponseEntity<Any> {
        return try{
            areaLaboralBusiness!!.removeTipoDeDocumento(idTipoDeDocumento)
            ResponseEntity(HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch(e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }

    }
}