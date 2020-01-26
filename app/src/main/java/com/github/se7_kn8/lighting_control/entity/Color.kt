package com.github.se7_kn8.lighting_control.entity

data class Color(val r: Int, val g: Int, val b: Int) {

    constructor(hex: String) : this(
        Integer.valueOf(hex.substring(1, 3), 16),
        Integer.valueOf(hex.substring(3, 5), 16),
        Integer.valueOf(hex.substring(5, 7), 16)
    )

    fun toHexString() = String.format("#%02x%02x%02x", r, g, b)

    fun changeRed(red: Int) = Color(red, g, b)
    fun changeGreen(green: Int) = Color(r, green, b)
    fun changeBlue(blue: Int) = Color(r, g, blue)
}