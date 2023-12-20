package com.example.nutritrack10.data.remote.response

data class PredictionResponse(
    val predictions: List<PredictionItem>
)

data class PredictionItem(
    val score: List<Float>,
    val label: List<String>
)