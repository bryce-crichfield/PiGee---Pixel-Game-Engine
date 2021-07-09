package org.apollo
package entity

import display.Camera
import physics.{CollisionBox, Position, Size}
import state.State

import org.apollo.entity.typeclasses.Stateable.StateOps

import java.awt.Image

trait Entity {

    def remake(
        entityState: EntityState = entityState,
        entitySprite: EntitySprite = entitySprite,
        parent: Entity = parent
    ): Entity

    val entityState: EntityState
    val entitySprite: EntitySprite
    val parent: Entity
    protected var size = Size(64, 64)

    protected var collisionBoxOffset = Position(0,0)
    protected var collisionBoxSize = new Size(size.width, size.height)


    def update(state: State)
    def sprite: Image
    def collisionBox: CollisionBox

    def collidesWith(other: Entity): Boolean = {
        collisionBox.collidesWith(other.collisionBox)
    }

    def getPosition: Position = {
        if(parent != null) this.position.add(parent.getPosition)
        this.position
    }


    def getSize: Size = size

    def getRenderPosition: Position = {
        Position(
            this.position.x - Camera.getPosition.x - entitySprite.renderOffset.x,
            this.position.y - Camera.getPosition.y - entitySprite.renderOffset.y
        )
    }
}

