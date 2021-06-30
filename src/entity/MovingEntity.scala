package org.apollo
package entity

import display.gfx.{AnimationManager, ImageUtils, SpriteSet}
import input.EntityController
import physics.{Direction, Motion, South, Vector}
import state.State

import java.awt.Image

abstract class MovingEntity(val entityController: EntityController) extends GameObject {

    protected var motion: Motion = Motion(1, Vector(0,0))
    val image = ImageUtils.loadImage("resources/sprites/units/light.png")
    val spriteSet = new SpriteSet()
    spriteSet.addSheet("light", image.get)
    protected val animationManager: AnimationManager = new AnimationManager(spriteSet)
    protected var direction: Direction = South
    protected var entityAction: EntityAction = Standing

    override def update(state: State): Unit = {
        motion = motion.update(entityController)
        position = position.applyMotion(motion)
        direction = direction(motion)
        updateEntityAction
        animationManager.update(direction, entityAction)

    }

    override def sprite: Image = {
        animationManager.getSprite
    }

    private def updateEntityAction: Unit = {
        if(entityController.isRequestingAttack) entityAction = Slashing
        else if(entityController.isRequestingCast) entityAction = Casting
        else if(motion.isMoving) entityAction = Walking
        else entityAction = Standing
    }
}

