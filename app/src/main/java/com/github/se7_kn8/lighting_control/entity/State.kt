package com.github.se7_kn8.lighting_control.entity

enum class State {

    HIGH {
        override fun stateName() = "high"
    },
    LOW {
        override fun stateName() = "low"
    };

    abstract fun stateName(): String

}