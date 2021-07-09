package org.apollo
package display

import core.StateManager
import entity.Entity
import physics.{Position, Size}
import state.State

import java.awt.Rectangle

object Camera {

    private val SAFETY_SPACE = 2 * StateManager.SPRITE_SIZE
    private var position = Position(0,0)
    private var windowSize = Display.windowSize
    private var objectWithFocus: Option[Entity] = None
    private var viewBounds: Rectangle = null
    calculateViewBounds

    private def calculateViewBounds: Unit = {
        viewBounds = new Rectangle(
            position.intX,
            position.intY,
            windowSize.width + SAFETY_SPACE,
            windowSize.height + SAFETY_SPACE
        )
    }

    def focusOn(obj: Entity): Unit = {
        this.objectWithFocus = Some(obj)
    }

    def update(state: State): Unit = {
        if(objectWithFocus.isDefined) {
            val objectPosition = objectWithFocus.get.getPosition
            val newX = (objectPosition.x - windowSize.width) / 2
            val newY = (objectPosition.y - windowSize.height) / 2
            this.position = Position(newX, newY)
            clamp(state)
        }
        calculateViewBounds
    }

    private def clamp(state: State): Unit = {
        if(position.x < 0) position = Position(0, position.y)
        if(position.y < 0) position = Position(position.x, 0)
        if(position.x + windowSize.width > state.getGameMap.getWidth) {
            position = Position(state.getGameMap.getWidth - windowSize.width, position.y)
        }
        if(position.y + windowSize.height > state.getGameMap.getHeigt) {
            position = Position(position.x, state.getGameMap.getHeigt - windowSize.height)
        }
    }

    def isInView(obj: Entity): Boolean = {
        viewBounds.intersects(
            obj.getPosition.intX,
            obj.getPosition.intY,
            obj.getSize.width,
            obj.getSize.height
        )
    }

    def getPosition: Position = position

    def getSize: Size = windowSize

    def setPosition(position: Position): Unit = this.position = position

    def setSize(size: Size): Unit = this.windowSize = size
}
