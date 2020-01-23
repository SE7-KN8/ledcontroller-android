package com.github.se7_kn8.lighting_control.entity

data class Color(var r: Int, var g: Int, var b: Int) {


    fun toHexString(): String {
        return String.format("#%02x%02x%02x", r, g, b);
    }

    constructor(hex: String) : this(
        Integer.valueOf(hex.substring(1, 3), 16),
        Integer.valueOf(hex.substring(3, 5), 16),
        Integer.valueOf(hex.substring(5, 7), 16)
    )
}