package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.web

import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.business.IPedidosCanceladosBusiness
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.exceptions.BusinessException
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.exceptions.NotFoundException
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model.PedidosCancelados
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.utils.Constants
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.utils.RestApiError
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping(Constants.URL_BASE_PEDIDOSCANCELADOS)
class PedidosCanceladosRestController {

    @Autowired
    val pedidosCanceladosBusiness: IPedidosCanceladosBusiness? = null

    @GetMapping("")
    fun list(): ResponseEntity<List<PedidosCancelados>> {
        return try{
            ResponseEntity(pedidosCanceladosBusiness!!.getPedidosCancelados(), HttpStatus.OK)
        }catch(e : Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/id/{id}")
    fun loadById(@PathVariable("id") idPedidoCancelado: Int): ResponseEntity<PedidosCancelados> {
        return try{
            ResponseEntity(pedidosCanceladosBusiness!!.getPedidoCanceladoByIdPedido(idPedidoCancelado), HttpStatus.OK)
        }catch(e : BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e : NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/fechaCancelacion/{fechaCancelacion}")
    fun loadByFechaCancelacion(@PathVariable("fechaCancelacion") fechaCancelacion: LocalDate): ResponseEntity<List<PedidosCancelados>> {
        return try{
            ResponseEntity(pedidosCanceladosBusiness!!.getPedidosCanceladosByFechaCancelacion(fechaCancelacion), HttpStatus.OK)
        }catch(e : BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e : NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/idPedido/{idPedido}")
    fun loadByIdPedido(@PathVariable("idPedido") idPedido: Int): ResponseEntity<PedidosCancelados> {
        return try{
            ResponseEntity(pedidosCanceladosBusiness!!.getPedidoCanceladoByIdPedido(idPedido), HttpStatus.OK)
        }catch(e : BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e : NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }


    @PostMapping("/addPedidoCancelado")
    fun insert(@RequestBody pedidoCancelado: PedidosCancelados): ResponseEntity<Any> {
        return try{
            pedidosCanceladosBusiness!!.savePedidoCancelado(pedidoCancelado)
            val responseHeader = HttpHeaders()
            responseHeader.set("location",Constants.URL_BASE_AREALABORAL + "/" + pedidoCancelado.idPedidosCancelados)
            ResponseEntity(pedidoCancelado,responseHeader, HttpStatus.CREATED)
        }catch(e : BusinessException){
            val apiError = RestApiError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Informacion enviada no es valida.",
                e.message.toString()
            )
            ResponseEntity(apiError, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PostMapping("/addPedidosCancelados")
    fun insert(@RequestBody pedidosCancelados : List<PedidosCancelados>): ResponseEntity<Any> {
        return try{
            ResponseEntity(pedidosCanceladosBusiness!!.savePedidosCancelados(pedidosCancelados), HttpStatus.CREATED)
        }catch(e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping("")
    fun update(@RequestBody pedido: PedidosCancelados) : ResponseEntity<Any> {
        return try{
            pedidosCanceladosBusiness!!.updatePedidoCancelado(pedido)
            ResponseEntity(pedido, HttpStatus.OK)
        }catch(e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch(e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable("id") idPedidoCancelado: Int): ResponseEntity<Any> {
        return try{
            pedidosCanceladosBusiness!!.removePedidoCancelado(idPedidoCancelado)
            ResponseEntity(HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch(e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }

    }


}