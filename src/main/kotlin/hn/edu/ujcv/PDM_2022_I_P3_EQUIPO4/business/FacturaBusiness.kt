package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.business

import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.dao.FacturaRepository
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.exceptions.BusinessException
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.exceptions.NotFoundException
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model.Factura
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class FacturaBusiness : IFacturaBusiness {

    @Autowired
    val facturaRepository : FacturaRepository? = null

    @kotlin.jvm.Throws(BusinessException::class)
    override fun getFacturas(): List<Factura> {
        try {
            return facturaRepository!!.findAll()
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun getFacturaById(idFactura: Int): Factura {
        val opt: Optional<Factura>
        try {
            opt=facturaRepository!!.findById(idFactura)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro factura con el Id:$idFactura")
        return opt.get()
    }

    @kotlin.jvm.Throws(BusinessException::class)
    override fun saveFactura(factura: Factura): Factura {
        try {
            //validaciones
            if (factura.fechaFactura.toString().trim().isEmpty())
                throw BusinessException("La fecha no puede estar vacía.")
            if (factura.idPedido.toString().trim().isEmpty())
                throw BusinessException("El pedido no puede estar vacío.")
            if (factura.idParametros.toString().trim().isEmpty())
                throw BusinessException("El parametro no puede estar vacío.")
            if (factura.totalFactura <= 0.0)
                throw BusinessException("El total de la factura no puede ser menor o igual a 0.")
            if (factura.numeroFactura.trim().isEmpty())
                throw BusinessException("El número de factura no puede estar vacío.")

            return facturaRepository!!.save(factura)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @kotlin.jvm.Throws(BusinessException::class)
    override fun saveFacturas(facturas: List<Factura>): List<Factura> {
        try {
            for (item in facturas){
                if (item.totalFactura <= 0.0)
                    throw BusinessException("El total de la factura no puede ser menor o igual a 0. Id: ${item.totalFactura}")
            }
            return facturaRepository!!.saveAll(facturas)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
    }

    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun removeFactura(idFactura: Int) {
        val opt: Optional<Factura>
        try {
            opt=facturaRepository!!.findById(idFactura)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro factura con el Id:$idFactura")
        else{
            try {
                facturaRepository!!.deleteById(idFactura)
            }catch (e:Exception){
                throw BusinessException(e.message)
            }
        }
    }


    @kotlin.jvm.Throws(BusinessException::class, NotFoundException::class)
    override fun getFacturaByIdPedido(idPedido: Int): Factura {
        val opt: Optional<Factura>
        try {
            opt=facturaRepository!!.findFacturaByIdPedido(idPedido)
        }catch (e:Exception){
            throw BusinessException(e.message)
        }
        if(!opt.isPresent)
            throw NotFoundException("No se encontro factura con el id de pedido:$idPedido")
        return opt.get()
    }

}