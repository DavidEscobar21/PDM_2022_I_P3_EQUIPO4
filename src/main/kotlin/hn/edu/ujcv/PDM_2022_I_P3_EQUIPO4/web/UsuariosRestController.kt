package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.web

import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.business.IUsuarioBusiness
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.exceptions.BusinessException
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.exceptions.NotFoundException
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model.Usuarios
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.utils.Constants
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.utils.RestApiError
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(Constants.URL_BASE_USUARIOS)
class UsuariosRestController {
    @Autowired
    val usuariosBusiness: IUsuarioBusiness? = null

    @GetMapping("")
    fun list(): ResponseEntity<List<Usuarios>> {
        return try{
            ResponseEntity(usuariosBusiness!!.getUsuarios(), HttpStatus.OK)
        }catch(e : Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/id/{id}")
    fun loadById(@PathVariable("id") idUsuario: Int): ResponseEntity<Usuarios> {
        return try{
            ResponseEntity(usuariosBusiness!!.getUsuarioById(idUsuario), HttpStatus.OK)
        }catch(e : BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e : NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/nombreUsuario/{nombreUsuario}")
    fun loadByNombre(@PathVariable("nombreUsuario") nombreDeUsuario : String) : ResponseEntity<Usuarios> {
        return try{
            ResponseEntity(usuariosBusiness!!.getUsuarioByNombreUsuario(nombreDeUsuario), HttpStatus.OK)
        }catch(e : BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e : NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/documento/{documento}")
    fun loadByDocumento(@PathVariable("documento") documento : String) : ResponseEntity<Usuarios> {
        return try{
            ResponseEntity(usuariosBusiness!!.getUsuarioByDocumentoEmpleado(documento), HttpStatus.OK)
        }catch(e : BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e : NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("/addUsuario")
    fun insert(@RequestBody usuario: Usuarios): ResponseEntity<Any> {
        return try{
            usuariosBusiness!!.saveUsuario(usuario)
            val responseHeader = HttpHeaders()
            responseHeader.set("location",Constants.URL_BASE_AREALABORAL + "/" + usuario.idUsuario)
            ResponseEntity(usuario,responseHeader, HttpStatus.CREATED)
        }catch(e : BusinessException){
            val apiError = RestApiError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Informacion enviada no es valida.",
                e.message.toString()
            )
            ResponseEntity(apiError, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PostMapping("/addUsuarios")
    fun insert(@RequestBody usuarios : List<Usuarios>): ResponseEntity<Any> {
        return try{
            ResponseEntity(usuariosBusiness!!.saveUsuarios(usuarios), HttpStatus.CREATED)
        }catch(e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping("")
    fun update(@RequestBody usuario: Usuarios) : ResponseEntity<Any> {
        return try{
            usuariosBusiness!!.updateUsuario(usuario)
            ResponseEntity(usuario, HttpStatus.OK)
        }catch(e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch(e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable("id") idUsuario: Int): ResponseEntity<Any> {
        return try{
            usuariosBusiness!!.removeUsuario(idUsuario)
            ResponseEntity(HttpStatus.OK)
        }catch (e: BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch(e: NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }

    }
}