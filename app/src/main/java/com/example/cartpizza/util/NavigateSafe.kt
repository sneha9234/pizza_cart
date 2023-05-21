package com.example.cartpizza.util

import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavGraph

fun NavController.navigateSafely(directions : NavDirections){
    try {
        val destinationId  = currentDestination?.getAction(directions.actionId)?.destinationId?:-1

        currentDestination?.let { node ->
            val currentNode = when (node) {
                is NavGraph -> node
                else -> node.parent
            }
            if (destinationId != -1) {
                currentNode?.findNode(destinationId)?.let { navigate(directions) }
            }
        }
    }catch (e : Exception){
        e.printStackTrace()
    }
}
