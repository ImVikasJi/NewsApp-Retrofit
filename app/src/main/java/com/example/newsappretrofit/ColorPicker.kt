package com.example.newsappretrofit

object ColorPicker {
    val colors = arrayOf(
        "#ffcdd2",
        "#ffecb3",
        "#e1bee7",
        "#c5cae9",
        "#bbdefb",
        "#b2ebf2",
        "#c8e6c9",
        "#f0f4c3",
        "#ffccbc"
    )
    var colorIndex = 1
    fun getColor() : String{
        return colors[colorIndex++ % colors.size]
    }
}