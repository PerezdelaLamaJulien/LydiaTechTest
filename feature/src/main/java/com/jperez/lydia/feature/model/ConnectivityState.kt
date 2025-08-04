package com.jperez.lydia.feature.model

sealed class ConnectivityState {
    object Available : ConnectivityState()
    object Unavailable : ConnectivityState()
}