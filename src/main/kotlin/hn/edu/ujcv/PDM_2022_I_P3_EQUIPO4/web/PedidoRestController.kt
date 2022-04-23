package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.web

import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.business.IPedidoBusiness
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.exceptions.BusinessException
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.exceptions.NotFoundException
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model.Pedido
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.utils.Constants
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.utils.RestApiError
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.URL_BASE_PEDIDO)
class PedidoRestController {
    @Autowired
    val pedidoBusiness: IPedidoBusiness? = null

    @GetMapping("")
    fun list(): ResponseEntity<List<Pedido>> {
        return try{
            ResponseEntity(pedidoBusiness!!.getPedidos(), HttpStatus.OK)
        }catch(e : Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/id/{id}")
    fun loadById(@PathVariable("id") idPedido: Int): ResponseEntity<Pedido> {
        return try{
            ResponseEntity(pedidoBusiness!!.getPedidoById(idPedido), HttpStatus.OK)
        }catch(e : BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e : NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/idCliente/{idCliente}")
    fun loadByIdCliente(@PathVariable("idCliente") idCliente: Int): ResponseEntity<List<Pedido>> {
        return try{
            ResponseEntity(pedidoBusiness!!.getPedidoByIdCliente(idCliente), HttpStatus.OK)
        }catch(e : BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e : NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/idRepartidor/{idRepartidor}")
    fun loadByIdRepartidor(@PathVariable("idRepartidor") idRepartidor: Int): ResponseEntity<List<Pedido>> {
        return try{
            ResponseEntity(pedidoBusiness!!.getPedidoByIdRepartidor(idRepartidor), HttpStatus.OK)
        }catch(e : BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e : NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/idEstado/{idEstado}")
    fun loadByIdEstado(@PathVariable("idEstado") idEstado: Int): ResponseEntity<List<Pedido>> {
        return try{
            ResponseEntity(pedidoBusiness!!.getPedidoByIdRepartidor(idEstado), HttpStatus.OK)
        }catch(e : BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e : NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/idTipoDeEntrega/{idTipoDeEntrega}")
    fun loadByIdTipoDeEntrega(@PathVariable("idTipoDeEntrega") idTipoDeEntrega: Int): ResponseEntity<List<Pedido>> {
        return try{
            ResponseEntity(pedidoBusiness!!.getPedidoByIdTipoDeEntrega(idTipoDeEntrega), HttpStatus.OK)
        }catch(e : BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e : NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }


    @PostMapping("/addPedido")
    fun insert(@RequestBody pedido: Pedido): ResponseEntity<Any> {
        return try{
            pedidoBusiness!!.savePedido(pedido)
            val responseHeader = HttpHeaders()
            responseHeader.set("location",Constants.URL_BASE_AREALABORAL + "/" + pedido.idPedido)
            ResponseEntity(pedido,responseHeader, HttpStatus.CREATED)
        }catch(e : BusinessException){
            val apiError = RestApiError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Informacion enviada no es valida.",
                e.message.toString()
            )
            ResponseEntity(apiError, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PostMapping("/addPedidos")
    fun insert(@RequestBody pedidos : List<Pedido>): ResponseEntity<Any> {
        return try{
            ResponseEntity(pedidoBusiness!!.savePedidos(pedidos), HttpStatus.CREATED)
        }catch(e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping("")
    fun update(@RequestBody pedido: Pedido) : ResponseEntity<Any> {
        return try{
            pedidoBusiness!!.updatePedido(pedido)
            ResponseEntity(pedido, HttpStatus.OK)
        }catch(e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch(e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable("id") idPedido: Int): ResponseEntity<Any> {
        return try{
            pedidoBusiness!!.removePedido(idPedido)
            ResponseEntity(HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch(e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }


}