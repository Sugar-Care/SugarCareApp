package com.rayhdf.sugarcareapp.data.model

data class PredictRequest(
    val age: Int,
    val bloodGlucoseLevels: Double = 1.0,
    val bloodPressure: Double = 1.0,
    val weightGainDuringPregnancy: Double = 1.0,
    val waistCircumference: Double = 1.0,
    val bmi: Double = 1.0,
    val insulinLevels: Double = 1.0,
    val cholesterolLevels: Double = 1.0,
    val digestiveEnzymeLevels: Double = 1.0,
    val pulmonaryFunction: Double = 1.0
)