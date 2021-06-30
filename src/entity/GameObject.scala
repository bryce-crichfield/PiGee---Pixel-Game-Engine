package org.apollo
package entity

import display.Camera
import physics.{CollisionBox, Position, Size}
import state.State

import java.awt.Image

abstract class GameObject {

    protected var position = Position(0,0)
    protected var renderOffset = Position(0,0)
    protected var collisionBoxOffset = Position(0,0)
    protected var size = Size(64, 64)
    protected var collisionBoxSize = new Size(size.width, size.height)
    protected var renderOrder: Int = 5
    protected var parent: GameObject

    def update(state: State)
    def sprite: Image
    def collisionBox: CollisionBox

    def collidesWith(other: GameObject): Boolean = {
        collisionBox.collidesWith(other.collisionBox)
    }

    def getPosition: Position = {
        if(parent != null) position.add(parent.getPosition)
        position
    }

    def setPosition(position: Position): Unit = this.position = position

    def getSize: Size = size

    def setParent(parent: GameObject) = {
        this.position = Position(0,0)
        this.parent = parent
    }

    def getRenderPosition: Position = {
        Position(
            position.x - Camera.getPosition.x - renderOffset.x,
            position.y - Camera.getPosition.y - renderOffset.y
        )
    }


}
