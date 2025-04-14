package com.api.v2.common

import com.api.v2.cards.exceptions.NonExistentCardException
import com.api.v2.customers.exceptions.NonExistentCustomerException
import com.api.v2.doctors.exceptions.DuplicatedMedicalLicenseNumberException
import com.api.v2.doctors.exceptions.ImmutableDoctorException
import com.api.v2.doctors.exceptions.NonExistentDoctorException
import com.api.v2.medical_appointments.exceptions.ImmutableMedicalAppointmentException
import com.api.v2.medical_appointments.exceptions.InaccessibleMedicalAppointmentException
import com.api.v2.medical_appointments.exceptions.NonExistentMedicalAppointmentException
import com.api.v2.medical_slots.exceptions.ImmutableMedicalSlotException
import com.api.v2.medical_slots.exceptions.InaccessibleMedicalSlotException
import com.api.v2.medical_slots.exceptions.NonExistentMedicalSlotException
import com.api.v2.people.exceptions.DuplicatedEmailException
import com.api.v2.people.exceptions.DuplicatedSsnException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler


@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(DuplicatedSsnException::class)
    fun handleException(ex: DuplicatedSsnException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.message)
    }

    @ExceptionHandler(DuplicatedEmailException::class)
    fun handleException(ex: DuplicatedEmailException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.message)
    }

    @ExceptionHandler(DuplicatedMedicalLicenseNumberException::class)
    fun handleException(ex: DuplicatedMedicalLicenseNumberException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.message)
    }

    @ExceptionHandler(ImmutableDoctorException::class)
    fun handleException(ex: ImmutableDoctorException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.message)
    }

    @ExceptionHandler(InaccessibleMedicalAppointmentException::class)
    fun handleException(ex: InaccessibleMedicalAppointmentException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.message)
    }

    @ExceptionHandler(ImmutableMedicalAppointmentException::class)
    fun handleException(ex: ImmutableMedicalAppointmentException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.message)
    }

    @ExceptionHandler(InaccessibleMedicalSlotException::class)
    fun handleException(ex: InaccessibleMedicalSlotException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.message)
    }

    @ExceptionHandler(ImmutableMedicalSlotException::class)
    fun handleException(ex: ImmutableMedicalSlotException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.message)
    }

    @ExceptionHandler(PastBookingDateException::class)
    fun handleException(ex: PastBookingDateException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.message)
    }

    @ExceptionHandler(NonExistentStateException::class)
    fun handleException(ex: NonExistentStateException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.message)
    }

    @ExceptionHandler(UnavailableBookingDateTimeException::class)
    fun handleException(ex: UnavailableBookingDateTimeException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.message)
    }

    @ExceptionHandler(NonExistentMedicalAppointmentException::class)
    fun handleException(ex: NonExistentMedicalAppointmentException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.message)
    }

    @ExceptionHandler(NonExistentCustomerException::class)
    fun handleException(ex: NonExistentCustomerException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.message)
    }

    @ExceptionHandler(NonExistentDoctorException::class)
    fun handleException(ex: NonExistentDoctorException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.message)
    }

    @ExceptionHandler(NonExistentMedicalSlotException::class)
    fun handleException(ex: NonExistentMedicalSlotException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.message)
    }

    @ExceptionHandler(NonExistentCardException::class)
    fun handleException(ex: NonExistentCardException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.message)
    }
}