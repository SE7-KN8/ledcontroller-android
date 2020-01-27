package com.github.se7_kn8.lighting_control.entity

data class Color(val r: Int, val g: Int, val b: Int) {


    constructor(hex: String) : this(
        Integer.valueOf(hex.substring(1, 3), 16),
        Integer.valueOf(hex.substring(3, 5), 16),
        Integer.valueOf(hex.substring(5, 7), 16)
    )

    fun toHexString() = String.format("#%02x%02x%02x", r, g, b)

    fun toAndroidColor() = android.graphics.Color.rgb(r, g, b)

    fun changeRed(red: Int) = Color(red, g, b)
    fun changeGreen(green: Int) = Color(r, green, b)
    fun changeBlue(blue: Int) = Color(r, g, blue)


    private fun fromHSV(h: Int, s: Int, v: Int): Color {

        val array = floatArrayOf((h.div(10).toFloat()), (s.toFloat() / 1000f), (v.toFloat() / 1000f))

        val color = android.graphics.Color.HSVToColor(array)

        return Color(android.graphics.Color.red(color), android.graphics.Color.green(color), android.graphics.Color.blue(color))
    }

    fun toHSV(): FloatArray {
        val hsv = FloatArray(3)
        android.graphics.Color.RGBToHSV(r, g, b, hsv)
        return hsv
    }

    fun getHue(): Int {
        return toHSV()[0].toInt() * 10
    }

    fun getSaturation(): Int {
        return (toHSV()[1] * 1000).toInt()
    }

    fun getColorValue(): Int {
        return (toHSV()[2] * 1000).toInt()
    }

    fun changeHue(hue: Int) = fromHSV(hue, getSaturation(), getColorValue())
    fun changeSaturation(saturation: Int) = fromHSV(getHue(), saturation, getColorValue())
    fun changeValue(value: Int) = fromHSV(getHue(), getColorValue(), value)


}