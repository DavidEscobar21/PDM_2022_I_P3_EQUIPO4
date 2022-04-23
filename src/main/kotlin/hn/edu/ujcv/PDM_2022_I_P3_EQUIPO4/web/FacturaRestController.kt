package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.web

import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.business.IFacturaBusiness
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.exceptions.BusinessException
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.exceptions.NotFoundException
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model.Factura
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.utils.Constants
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.utils.RestApiError
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.URL_BASE_FACTURA)

class FacturaRestController {
    @Autowired
    val facturaBusiness: IFacturaBusiness? = null

    @GetMapping("")
    fun list(): ResponseEntity<List<Factura>> {
        return try{
            ResponseEntity(facturaBusiness!!.getFacturas(), HttpStatus.OK)
        }catch(e : Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/id/{id}")
    fun loadById(@PathVariable("id") idFactura: Int): ResponseEntity<Factura> {
        return try{
            ResponseEntity(facturaBusiness!!.getFacturaById(idFactura), HttpStatus.OK)
        }catch(e : BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e : NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }


    @PostMapping("/addFactura")
    fun insert(@RequestBody factura: Factura): ResponseEntity<Any> {
        return try{
            facturaBusiness!!.saveFactura(factura)
            val responseHeader = HttpHeaders()
            responseHeader.set("location",Constants.URL_BASE_AREALABORAL + "/" + factura.idFactura)
            ResponseEntity(factura,responseHeader, HttpStatus.CREATED)
        }catch(e : BusinessException){
            val apiError = RestApiError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Informacion enviada no es valida.",
                e.message.toString()
            )
            ResponseEntity(apiError, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PostMapping("/addFacturas")
    fun insert(@RequestBody facturas : List<Factura>): ResponseEntity<Any> {
        return try{
            ResponseEntity(facturaBusiness!!.saveFacturas(facturas), HttpStatus.CREATED)
        }catch(e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }


    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable("id") idFactura: Int): ResponseEntity<Any> {
        return try{
            facturaBusiness!!.removeFactura(idFactura)
            ResponseEntity(HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch(e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }

    }
}