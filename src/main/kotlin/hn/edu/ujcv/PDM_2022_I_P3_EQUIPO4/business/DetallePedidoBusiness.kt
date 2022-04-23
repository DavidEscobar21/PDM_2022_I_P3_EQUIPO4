package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.business

import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.dao.DetallePedidoRepository
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.exceptions.BusinessException
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.exceptions.NotFoundException
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model.Clientes
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model.DetallePedido
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class DetallePedidoBusiness : IDetallePedidoBusiness {

    @Autowired
    val detallePedidoRepository : DetallePedidoRepository? = null

    @kotlin.jvm.Throws(BusinessException::class)
    override fun getDetallesPedido(): List<DetallePedido> {
        try {
            return detallePedidoRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun getDetallePedidoById(idDetallePedido: Int): DetallePedido {
        val opt: Optional<DetallePedido>
        try {
            opt=detallePedidoRepository!!.findById(idDetallePedido)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro el detalle de pedido con el Id:$idDetallePedido")
        return opt.get()
    }

    @kotlin.jvm.Throws(BusinessException::class)
    override fun saveDetallePedido(detallePedido: DetallePedido): DetallePedido {
        try {
            //validaciones
            if (detallePedido.idPedido.toString().trim().isEmpty())
                throw BusinessException("El pedido del detalle pedido no puede estar vacío.")
            if (detallePedido.idArticulos.toString().trim().isEmpty())
                throw BusinessException("El artículo del detalle pedido no puede estar vacío.")
            if (detallePedido.precio <= 0.0)
                throw BusinessException("El precio del detalle pedido no puede ser menor o igual a 0.")
            if (detallePedido.cantidad <= 0)
                throw BusinessException("La cantidad del detalle pedido no puede ser menor o igual a 0.")



            return detallePedidoRepository!!.save(detallePedido)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @kotlin.jvm.Throws(BusinessException::class)
    override fun saveDetallesPedido(detallesPedido: List<DetallePedido>): List<DetallePedido> {
        try {
            for (item in detallesPedido){
                //validaciones
                if (item.idPedido.toString().trim().isEmpty())
                    throw BusinessException("El pedido del detalle pedido no puede estar vacío.")
                if (item.idArticulos.toString().trim().isEmpty())
                    throw BusinessException("El artículo del detalle pedido no puede estar vacío.")
                if (item.precio <= 0.0)
                    throw BusinessException("El precio del detalle pedido no puede ser menor o igual a 0.")
                if (item.cantidad <= 0)
                    throw BusinessException("La cantidad del detalle pedido no puede ser menor o igual a 0.")
            }
            return detallePedidoRepository!!.saveAll(detallesPedido)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }


    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun getDetallePedidoByIdPedidos(idPedido: Int): List<DetallePedido> {
        val opt: Optional<List<DetallePedido>>
        try {
            opt=detallePedidoRepository!!.findDetallePedidoByIdPedido(idPedido)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontraron detalles de pedido con el id de pedido:$idPedido")
        return opt.get()
    }

    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun removeDetallePedido(idDetallePedido: Int) {
        val opt: Optional<DetallePedido>
        try {
            opt=detallePedidoRepository!!.findById(idDetallePedido)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro el detalle pedido con el Id:$idDetallePedido")
        else{
            try {
                detallePedidoRepository!!.deleteById(idDetallePedido)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
    }

}