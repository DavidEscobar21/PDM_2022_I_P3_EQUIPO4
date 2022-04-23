package hn.edu.ujcv.PDM_2022_I_P3_EQUIPO4.utils

import org.springframework.http.HttpStatus

class RestApiError(val httpStatus: HttpStatus, val errorMessage:String, val errorDetails:String) {
}