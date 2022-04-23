package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.business

import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.dao.PedidoRepository
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.exceptions.BusinessException
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.exceptions.NotFoundException
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model.Pedido
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class PedidoBusiness : IPedidoBusiness{

    @Autowired
    val pedidoRepository : PedidoRepository? = null

    @kotlin.jvm.Throws(BusinessException::class)
    override fun getPedidos(): List<Pedido> {
        try {
            return pedidoRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun getPedidoById(idPedido: Int): Pedido {
        val opt: Optional<Pedido>
        try {
            opt=pedidoRepository!!.findById(idPedido)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro pedido con el Id:$idPedido")
        return opt.get()
    }

    @kotlin.jvm.Throws(BusinessException::class)
    override fun savePedido(pedido: Pedido): Pedido {
        try {

            //validaiones
            if (pedido.idCliente.toString().trim().isEmpty())
                throw BusinessException("El cliente no puede estar vacío")
            if (pedido.idRepartidor.toString().trim().isEmpty())
                throw BusinessException("El repartidor no puede estar vacío")
            if (pedido.idTipoDeEntrega.toString().trim().isEmpty())
                throw BusinessException("El tipo de entrega no puede estar vacío")
            if (pedido.idEstado.toString().trim().isEmpty())
                throw BusinessException("El estado no puede estar vacío")
            if (pedido.idTipoDePago.toString().trim().isEmpty())
                throw BusinessException("El tipo de pago no puede estar vacío")
            if (pedido.fechaPedido.toString().trim().isEmpty())
                throw BusinessException("La fecha no puede estar vacía")
            if (pedido.subTotal.toString().trim().isEmpty())
                throw BusinessException("El subTotal no puede estar vacío")
            if (pedido.subTotal <= 0.0)
                throw BusinessException("El subtotal no pueder ser menor o igual a 0")
            if (pedido.total.toString().trim().isEmpty())
                throw BusinessException("El total no puede estar vacío")
            if (pedido.total <= 0.0)
                throw BusinessException("El total no pueder ser menor o igual a 0")
            if (pedido.total < pedido.subTotal)
                throw BusinessException("El total no pueder ser menor al subtotal")
            if (pedido.impuesto.toString().trim().isEmpty())
                throw BusinessException("El impuesto no puede estar vacío")
            if (pedido.impuesto <= 0.0)
                throw BusinessException("El impuesto no pueder ser menor o igual a 0")
            if (pedido.direccionPedido.trim().isEmpty())
                throw BusinessException("La dirección no puede estar vacio.")
            if (pedido.direccionPedido.length < 3)
                throw BusinessException("La dirección no puede tener menos de 3 letras.")
            if (pedido.direccionPedido.length > 200)
                throw BusinessException("La dirección no puede tener más de 200 letras.")


            return pedidoRepository!!.save(pedido)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @kotlin.jvm.Throws(BusinessException::class)
    override fun savePedidos(pedidos: List<Pedido>): List<Pedido> {
        try {

            for (item in pedidos){
                if (item.idCliente.toString().trim().isEmpty())
                    throw BusinessException("El cliente no puede estar vacío")
                if (item.idRepartidor.toString().trim().isEmpty())
                    throw BusinessException("El repartidor no puede estar vacío")
                if (item.idTipoDeEntrega.toString().trim().isEmpty())
                    throw BusinessException("El tipo de entrega no puede estar vacío")
                if (item.idEstado.toString().trim().isEmpty())
                    throw BusinessException("El estado no puede estar vacío")
                if (item.idTipoDePago.toString().trim().isEmpty())
                    throw BusinessException("El tipo de pago no puede estar vacío")
                if (item.fechaPedido.toString().trim().isEmpty())
                    throw BusinessException("La fecha no puede estar vacía")
                if (item.subTotal.toString().trim().isEmpty())
                    throw BusinessException("El subTotal no puede estar vacío")
                if (item.subTotal <= 0.0)
                    throw BusinessException("El subtotal no pueder ser menor o igual a 0")
                if (item.total.toString().trim().isEmpty())
                    throw BusinessException("El total no puede estar vacío")
                if (item.total <= 0.0)
                    throw BusinessException("El total no pueder ser menor o igual a 0")
                if (item.total < item.subTotal)
                    throw BusinessException("El total no pueder ser menor al subtotal")
                if (item.impuesto.toString().trim().isEmpty())
                    throw BusinessException("El impuesto no puede estar vacío")
                if (item.impuesto <= 0.0)
                    throw BusinessException("El impuesto no pueder ser menor o igual a 0")
                if (item.direccionPedido.trim().isEmpty())
                    throw BusinessException("La dirección no puede estar vacio.")
                if (item.direccionPedido.length < 3)
                    throw BusinessException("La dirección no puede tener menos de 3 letras.")
                if (item.direccionPedido.length > 200)
                    throw BusinessException("La dirección no puede tener más de 200 letras.")
            }

            return pedidoRepository!!.saveAll(pedidos)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun removePedido(idPedido: Int) {
        val opt: Optional<Pedido>
        try {
            opt=pedidoRepository!!.findById(idPedido)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro pedido con el Id:$idPedido")
        else{
            try {
                pedidoRepository!!.deleteById(idPedido)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
    }

    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun updatePedido(pedido: Pedido): Pedido {
        val opt: Optional<Pedido>
        try {
            opt=pedidoRepository!!.findById(pedido.idPedido)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro pedido con el Id:${pedido.idPedido}")
        else{
            try {
                //validaciones
                if (pedido.idCliente.toString().trim().isEmpty())
                    throw BusinessException("El cliente no puede estar vacío")
                if (pedido.idRepartidor.toString().trim().isEmpty())
                    throw BusinessException("El repartidor no puede estar vacío")
                if (pedido.idTipoDeEntrega.toString().trim().isEmpty())
                    throw BusinessException("El tipo de entrega no puede estar vacío")
                if (pedido.idEstado.toString().trim().isEmpty())
                    throw BusinessException("El estado no puede estar vacío")
                if (pedido.idTipoDePago.toString().trim().isEmpty())
                    throw BusinessException("El tipo de pago no puede estar vacío")
                if (pedido.fechaPedido.toString().trim().isEmpty())
                    throw BusinessException("La fecha no puede estar vacía")
                if (pedido.subTotal.toString().trim().isEmpty())
                    throw BusinessException("El subTotal no puede estar vacío")
                if (pedido.subTotal <= 0.0)
                    throw BusinessException("El subtotal no pueder ser menor o igual a 0")
                if (pedido.total.toString().trim().isEmpty())
                    throw BusinessException("El total no puede estar vacío")
                if (pedido.total <= 0.0)
                    throw BusinessException("El total no pueder ser menor o igual a 0")
                if (pedido.total < pedido.subTotal)
                    throw BusinessException("El total no pueder ser menor al subtotal")
                if (pedido.impuesto.toString().trim().isEmpty())
                    throw BusinessException("El impuesto no puede estar vacío")
                if (pedido.impuesto <= 0.0)
                    throw BusinessException("El impuesto no pueder ser menor o igual a 0")
                if (pedido.direccionPedido.trim().isEmpty())
                    throw BusinessException("La dirección no puede estar vacio.")
                if (pedido.direccionPedido.length < 3)
                    throw BusinessException("La dirección no puede tener menos de 3 letras.")
                if (pedido.direccionPedido.length > 200)
                    throw BusinessException("La dirección no puede tener más de 200 letras.")

                val pedidoExiste = Pedido(pedido.fechaPedido,pedido.impuesto,pedido.subTotal,pedido.total,pedido.direccionPedido,
                    pedido.idCliente,pedido.idRepartidor,pedido.idTipoDePago,pedido.idTipoDeEntrega)
                pedidoRepository!!.save(pedido)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
        return opt.get()
    }

    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun getPedidoByIdCliente(idCliente: Int): List<Pedido> {
        val opt: Optional<List<Pedido>>
        try {
            opt=pedidoRepository!!.findPedidoByIdCliente(idCliente)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro pedido con el id de cliente:$idCliente")
        return opt.get()
    }

    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun getPedidoByIdEstado(idEstado: Int): List<Pedido> {
        val opt: Optional<List<Pedido>>
        try {
            opt=pedidoRepository!!.findPedidoByIdEstado(idEstado)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro pedido con el id de estado:$idEstado")
        return opt.get()
    }

    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun getPedidoByIdRepartidor(idRepartidor: Int): List<Pedido> {
        val opt: Optional<List<Pedido>>
        try {
            opt=pedidoRepository!!.findPedidoByIdRepartidor(idRepartidor)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro pedido con el id de repartidor:$idRepartidor")
        return opt.get()
    }

    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun getPedidoByIdTipoDeEntrega(idTipoDeEntrega: Int): List<Pedido> {
        val opt: Optional<List<Pedido>>
        try {
            opt=pedidoRepository!!.findPedidoByIdTipoDeEntrega(idTipoDeEntrega)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro pedido con el id de tipo de entrega:$idTipoDeEntrega")
        return opt.get()
    }

}