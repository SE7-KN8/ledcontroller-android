package com.github.se7_kn8.lighting_control.repo

import com.github.se7_kn8.lighting_control.DefaultCallback
import com.github.se7_kn8.lighting_control.ErrorHandler
import com.github.se7_kn8.lighting_control.entity.State
import com.github.se7_kn8.lighting_control.service.ControlService


class GpioControlRepository(private val service: ControlService) {

    fun writeGPIO(pin: Int, state: State, errorHandler: ErrorHandler) {
        service.writeGPIOState(pin, state.stateName()).enqueue(DefaultCallback<Unit>(errorHandler))
    }

}