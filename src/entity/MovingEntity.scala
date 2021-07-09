package org.apollo
package entity

import display.gfx.{ImageUtils, SpriteSet}
import input.EntityController
import state.State

import java.awt.Image

trait MovingEntity extends Entity {

    // TODO: Make this immutable
    protected var entityState: EntityState
    protected val entityController: EntityController
    protected val stateManager = new EntityStateManager(entityState, entityController)
    protected val animationManager: EntityAnimationManager = initializeAnimationManager()

    override def update(state: State): Unit = {
        entityState = stateManager.calculateUpdate()
        animationManager.calculateCurrentFrame(entityState)
    }

    override def sprite: Image = {
        animationManager.getSprite
    }

    protected def initializeAnimationManager(): EntityAnimationManager = {
        // TODO: In the future, many sheets can be loaded based on character inventory
        val spriteSheet = ImageUtils.loadImage("resources/sprites/units/light.png")
        val spriteSet = new SpriteSet()
        spriteSet.addSheet("light", spriteSheet.get)
        new EntityAnimationManager(spriteSet)
    }

}
object MovingEntity {

}

