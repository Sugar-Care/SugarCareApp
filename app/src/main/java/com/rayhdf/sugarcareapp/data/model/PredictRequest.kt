package com.rayhdf.sugarcareapp.data.model

data class PredictRequest(
    val age: Int,
    val bloodGlucoseLevels: Int,
    val bloodPressure: Int,
    val weightGainDuringPregnancy: Int,
    val waistCircumference: Int,
    val bmi: Int,
    val insulinLevels: Int,
    val cholesterolLevels: Int,
    val digestiveEnzymeLevels: Int,
    val pulmonaryFunction: Int
)
