package org.apollo
package physics

case class Vector(x: Double, y: Double) {

    def length: Double = {
        Math.sqrt((x*x) + (y*y))
    }

    def normalize: Vector = {
        val newX = if(x == 0) 0 else x/length
        val newY = if(y == 0) 0 else y/length
        Vector(newX, newY)
    }

    def scale(scalar: Double): Vector = {
        Vector(x*scalar, y*scalar)
    }

    def add(vector: Vector): Vector = {
        val newX = x + vector.x
        val newY = y + vector.y
        Vector(newX, newY)
    }

}
object Vector {

    def directionBetweenPositions(start: Position, end: Position): Vector = {
        val x = start.x - end.x
        val y = start.x - end.y
        Vector(x, y).normalize
    }

    def dot(v1: Vector, v2: Vector): Double = {
        (v1.x * v2.x) + (v1.y * v2.y)
    }

}
