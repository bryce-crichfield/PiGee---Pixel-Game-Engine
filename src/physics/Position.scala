package org.apollo
package physics

import physics.Position.PROXIMITY_RANGE

case class Position(x: Double, y: Double) {

    def intX: Int = Math.round(x).toInt
    def intY: Int = Math.round(y).toInt

    def isInRangeOf(that: Position): Boolean = {
        val arg1 = Math.abs(this.x - that.x)
        val arg2 = Math.abs(this.y - that.y)
        arg1 < PROXIMITY_RANGE && arg2 < PROXIMITY_RANGE
    }

    def distanceTo(that: Position): Double = {
        val deltaX: Double = this.x - that.x
        val deltaY: Double = this.y - that.y
        Math.sqrt((deltaX*deltaX) + (deltaY*deltaY))
    }

    def applyMotion(motion: Motion): Position = {
       applyXMotion(motion).applyYMotion(motion)
    }

    def applyXMotion(motion: Motion): Position = {
        val newX = x + motion.vector.x
        Position(newX, y)
    }

    def applyYMotion(motion: Motion): Position = {
        val newY = y + motion.vector.y
        Position(x, newY)
    }

    def add(position: Position): Position = {
        val newX = x + position.x
        val newY = y + position.y
        Position(newX, newY)
    }

    def subtract(position: Position): Position = {
        val newX = x - position.x
        val newY = y - position.y
        Position(newX, newY)
    }

}
object Position {
    def apply(x: Int, y: Int): Position = Position(x.toDouble, y.toDouble)
    val PROXIMITY_RANGE: Int = 5
}
