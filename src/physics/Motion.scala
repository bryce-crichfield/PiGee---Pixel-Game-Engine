package org.apollo
package physics

import input.EntityController

case class Motion(speed: Double, vector: Vector = Vector(0,0)) {

    //TODO: def update(entityController: EntityController): Motion

    def isMoving: Boolean = {
        vector.length > 0
    }

    def multiply(multiplier: Double): Motion = {
        Motion(speed, vector.scale(multiplier))
    }

    def stop(stopX: Boolean, stopY: Boolean): Motion = {
        val newVectorX = if(stopX) 0 else vector.x
        val newVectorY = if(stopY) 0 else vector.y
        Motion(speed, Vector(newVectorX, newVectorY))
    }

    def direction: Vector = {
        vector.normalize
    }

    def add(vector: Vector): Motion = {
        val newVector = this.vector.add(vector)
        Motion(speed, newVector)
    }

    def update(entityController: EntityController): Motion = {
        var deltaX, deltaY = 0
        var updatedSpeed = speed
        if(entityController.isRequestingUp) deltaY = deltaY - 1
        if(entityController.isRequestingDown) deltaY = deltaY + 1
        if(entityController.isRequestingLeft) deltaX = deltaX - 1
        if(entityController.isRequestingRight) deltaX = deltaX + 1
        if(entityController.isRequestingSprint) updatedSpeed = speed*2
        Motion(speed, Vector(deltaX, deltaY).normalize.scale(updatedSpeed))
    }

}
