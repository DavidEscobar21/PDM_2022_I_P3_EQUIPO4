package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.web

import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.business.IAreaLaboralBusiness
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.exceptions.BusinessException
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.exceptions.NotFoundException
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.model.AreaLaboral
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.utils.Constants
import hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.utils.RestApiError
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(Constants.URL_BASE_AREALABORAL)
class AreaLaboralRestController {
    @Autowired
    val areaLaboralBusiness: IAreaLaboralBusiness? = null

    @GetMapping("")
    fun list():ResponseEntity<List<AreaLaboral>>{
        return try{
            ResponseEntity(areaLaboralBusiness!!.getAreasLaboral(), HttpStatus.OK)
        }catch(e : Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/id/{id}")
    fun loadById(@PathVariable("id") idAreaLaboral: Int):ResponseEntity<AreaLaboral>{
        return try{
            ResponseEntity(areaLaboralBusiness!!.getAreaLaboralById(idAreaLaboral), HttpStatus.OK)
        }catch(e : BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e : NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/nombre/{nombre}")
    fun loadByNombre(@PathVariable("nombre") nombreAreaLaboral : String) : ResponseEntity<AreaLaboral>{
        return try{
            ResponseEntity(areaLaboralBusiness!!.getAreaLaboralByNombre(nombreAreaLaboral), HttpStatus.OK)
        }catch(e : BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (e : NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping("/addAreaLaboral")
    fun insert(@RequestBody areaLaboral: AreaLaboral):ResponseEntity<Any>{
        return try{
            areaLaboralBusiness!!.saveAreaLaboral(areaLaboral)
            val responseHeader = HttpHeaders()
            responseHeader.set("location",Constants.URL_BASE_AREALABORAL + "/" + areaLaboral.idAreaLaboral)
            ResponseEntity(areaLaboral,responseHeader,HttpStatus.CREATED)
        }catch(e : BusinessException){
            val apiError = RestApiError(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Informacion enviada no es valida.",
                e.message.toString()
            )
            ResponseEntity(apiError,HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PostMapping("/addAreas")
    fun insert(@RequestBody areasLaborales : List<AreaLaboral>):ResponseEntity<Any>{
        return try{
            ResponseEntity(areaLaboralBusiness!!.saveAreas(areasLaborales), HttpStatus.CREATED)
        }catch(e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping("")
    fun update(@RequestBody areaLaboral: AreaLaboral) : ResponseEntity<Any>{
        return try{
            areaLaboralBusiness!!.updateAreaLaboral(areaLaboral)
            ResponseEntity(areaLaboral,HttpStatus.OK)
        }catch(e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch(e:NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable("id") idAreaLaboral: Int):ResponseEntity<Any>{
        return try{
            areaLaboralBusiness!!.removeAreaLaboral(idAreaLaboral)
            ResponseEntity(HttpStatus.OK)
        }catch (e:BusinessException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch(e:NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }

    }
}