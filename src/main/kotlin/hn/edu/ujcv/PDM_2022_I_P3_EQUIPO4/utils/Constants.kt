package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.utils

class Constants {
    companion object{
        private const val URL_API_BASE = "/api"
        private const val URL_API_VERSION = "/v1"
        private const val URL_BASE = URL_API_BASE + URL_API_VERSION
        const val URL_BASE_USUARIOS = "$URL_BASE/usuarios"
        const val URL_BASE_AREALABORAL = "$URL_BASE/arealaboral"
        const val URL_BASE_ARTICULOS = "$URL_BASE/articulos"
        const val URL_BASE_CATEGORIA = "$URL_BASE/categoria"
        const val URL_BASE_CLIENTES = "$URL_BASE/clientes"
        const val URL_BASE_DETALLEPEDIDO = "$URL_BASE/detallepedido"
        const val URL_BASE_ESTADO = "$URL_BASE/estado"
        const val URL_BASE_FACTURA = "$URL_BASE/factura"
        const val URL_BASE_PARAMETROS = "$URL_BASE/parametros"
        const val URL_BASE_PEDIDO = "$URL_BASE/pedido"
        const val URL_BASE_PEDIDOSCANCELADOS = "$URL_BASE/pedidoscancelados"
        const val URL_BASE_REPARTIDO = "$URL_BASE/repartidor"
        const val URL_BASE_SEXO = "$URL_BASE/sexo"
        const val URL_BASE_TIEMPODEENTREGA = "$URL_BASE/tiempodeentrega"
        const val URL_BASE_TIPODEDOCUMENTO = "$URL_BASE/tipodedocumento"
        const val URL_BASE_TIPODEENTREGA = "$URL_BASE/tipodeentrega"
        const val URL_BASE_TIPODEPAGO = "$URL_BASE/tipodepago"
        const val URL_BASE_PROVEEDORES = "$URL_BASE/proveedores"
    }
}