package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.business

import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.dao.PedidosCanceladosRepository
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.exceptions.BusinessException
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.exceptions.NotFoundException
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model.PedidosCancelados
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.*

@Service
class PedidosCanceladosBusiness : IPedidosCanceladosBusiness {

    @Autowired
    val pedidosCanceladosRepository : PedidosCanceladosRepository? = null

    @kotlin.jvm.Throws(BusinessException::class)
    override fun getPedidosCancelados(): List<PedidosCancelados> {
        try {
            return pedidosCanceladosRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun getPedidoCanceladoById(idPedidoCancelado: Int): PedidosCancelados {
        val opt: Optional<PedidosCancelados>
        try {
            opt=pedidosCanceladosRepository!!.findById(idPedidoCancelado)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro pedido cancelado con el Id:$idPedidoCancelado")
        return opt.get()
    }

    @kotlin.jvm.Throws(BusinessException::class)
    override fun savePedidoCancelado(pedidoCancelado: PedidosCancelados): PedidosCancelados {
        try {

            //validaciones
            if (pedidoCancelado.fechaCancelacion.toString().trim().isEmpty())
                throw BusinessException("La Fecha no puede estar vacia.")
            if (pedidoCancelado.descripcionCancelacion.trim().isEmpty())
                throw BusinessException("La descripcion no puede estar vacia.")
            if (pedidoCancelado.descripcionCancelacion.length < 5)
                throw BusinessException("La descripccion no puede tener menos de 5 caracteres")
            if (pedidoCancelado.descripcionCancelacion.length > 100)
                throw BusinessException("La descripccion no puede tener más de 100 caracteres")
            if (pedidoCancelado.idPedido.toString().trim().isEmpty())
                throw BusinessException("El pedido no puede estar vacia.")



            return pedidosCanceladosRepository!!.save(pedidoCancelado)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @kotlin.jvm.Throws(BusinessException::class)
    override fun savePedidosCancelados(pedidosCancelados: List<PedidosCancelados>): List<PedidosCancelados> {
        try {
            for (item in pedidosCancelados){
                if (item.fechaCancelacion.toString().trim().isEmpty())
                    throw BusinessException("La Fecha no puede estar vacia.")
                if (item.descripcionCancelacion.trim().isEmpty())
                    throw BusinessException("La descripcion no puede estar vacia.")
                if (item.descripcionCancelacion.length < 5)
                    throw BusinessException("La descripccion no puede tener menos de 5 caracteres")
                if (item.descripcionCancelacion.length > 100)
                    throw BusinessException("La descripccion no puede tener más de 100 caracteres")
                if (item.idPedido.toString().trim().isEmpty())
                    throw BusinessException("El pedido no puede estar vacia.")
            }
            return pedidosCanceladosRepository!!.saveAll(pedidosCancelados)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun removePedidoCancelado(idPedidoCancelado: Int) {
        val opt: Optional<PedidosCancelados>
        try {
            opt=pedidosCanceladosRepository!!.findById(idPedidoCancelado)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro pedido cancelado con el Id:$idPedidoCancelado")
        else{
            try {
                pedidosCanceladosRepository!!.deleteById(idPedidoCancelado)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
    }

    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun updatePedidoCancelado(pedidoCancelado: PedidosCancelados): PedidosCancelados {
        val opt: Optional<PedidosCancelados>
        try {
            opt=pedidosCanceladosRepository!!.findById(pedidoCancelado.idPedidosCancelados)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro pedido con el Id:${pedidoCancelado.idPedidosCancelados}")
        else{
            try {
                //validaciones
                if (pedidoCancelado.fechaCancelacion.toString().trim().isEmpty())
                    throw BusinessException("La Fecha no puede estar vacia.")
                if (pedidoCancelado.descripcionCancelacion.trim().isEmpty())
                    throw BusinessException("La descripcion no puede estar vacia.")
                if (pedidoCancelado.descripcionCancelacion.length < 5)
                    throw BusinessException("La descripccion no puede tener menos de 5 caracteres")
                if (pedidoCancelado.descripcionCancelacion.length > 100)
                    throw BusinessException("La descripccion no puede tener más de 100 caracteres")
                if (pedidoCancelado.idPedido.toString().trim().isEmpty())
                    throw BusinessException("El pedido no puede estar vacia.")

                val pedidoExiste = PedidosCancelados(pedidoCancelado.fechaCancelacion,pedidoCancelado.descripcionCancelacion,pedidoCancelado.idPedido)
                pedidosCanceladosRepository!!.save(pedidoCancelado)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
        return opt.get()
    }

    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun getPedidosCanceladosByFechaCancelacion(fechaCancelacion: LocalDate): List<PedidosCancelados> {
        val opt: Optional<List<PedidosCancelados>>
        try {
            opt=pedidosCanceladosRepository!!.findPedidosCanceladosByFechaCancelacion(fechaCancelacion)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontraron pedidos realizados en :$fechaCancelacion")
        return opt.get()
    }

    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun getPedidoCanceladoByIdPedido(idPedido: Int): PedidosCancelados {
        val opt: Optional<PedidosCancelados>
        try {
            opt=pedidosCanceladosRepository!!.findPedidosCanceladosByIdPedido(idPedido)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontraron pedidos realizados con el id de pedido:$idPedido")
        return opt.get()
    }


}