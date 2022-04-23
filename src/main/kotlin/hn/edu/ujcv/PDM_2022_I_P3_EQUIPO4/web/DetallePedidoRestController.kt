package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.web

import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.business.IDetallePedidoBusiness
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.exceptions.BusinessException
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.exceptions.NotFoundException
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model.DetallePedido
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.utils.Constants
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.utils.RestApiError
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.URL_BASE_DETALLEPEDIDO)
class DetallePedidoRestController {
    
    @Autowired
    val detallePedidosBusiness: IDetallePedidoBusiness? = null

    @GetMapping("")
    fun list(): ResponseEntity<List<DetallePedido>> {
        return try{
            ResponseEntity(detallePedidosBusiness!!.getDetallesPedido(), HttpStatus.OK)
        }catch(e : Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/id/{id}")
    fun loadById(@PathVariable("id") idDetallePedido: Int): ResponseEntity<DetallePedido> {
        return try{
            ResponseEntity(detallePedidosBusiness!!.getDetallePedidoById(idDetallePedido), HttpStatus.OK)
        }catch(e : BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e : NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/idPedido/{idPedido}")
    fun loadByIdPedido(@PathVariable("idPedido") idPedido: Int): ResponseEntity<List<DetallePedido>> {
        return try{
            ResponseEntity(detallePedidosBusiness!!.getDetallePedidoByIdPedidos(idPedido), HttpStatus.OK)
        }catch(e : BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e : NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("/addDetallePedido")
    fun insert(@RequestBody detallePedido: DetallePedido): ResponseEntity<Any> {
        return try{
            detallePedidosBusiness!!.saveDetallePedido(detallePedido)
            val responseHeader = HttpHeaders()
            responseHeader.set("location",Constants.URL_BASE_AREALABORAL + "/" + detallePedido.idDetallePedido)
            ResponseEntity(detallePedido,responseHeader, HttpStatus.CREATED)
        }catch(e : BusinessException){
            val apiError = RestApiError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Informacion enviada no es valida.",
                e.message.toString()
            )
            ResponseEntity(apiError, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PostMapping("/addDetallesPedido")
    fun insert(@RequestBody detallesPedido : List<DetallePedido>): ResponseEntity<Any> {
        return try{
            ResponseEntity(detallePedidosBusiness!!.saveDetallesPedido(detallesPedido), HttpStatus.CREATED)
        }catch(e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }


    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable("id") idDetallePedido: Int): ResponseEntity<Any> {
        return try{
            detallePedidosBusiness!!.removeDetallePedido(idDetallePedido)
            ResponseEntity(HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch(e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }

    }
}