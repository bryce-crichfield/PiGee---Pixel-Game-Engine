package org.apollo
package entity

import input.EntityController


class EntityStateManager( protected var entityState: EntityState,
                          protected val entityController: EntityController)
{

  def calculateUpdate(): EntityState = {
    updatePhysics()
    updateEntityAction()
    entityState
  }

  private def updatePhysics(): Unit = {
    entityState = updateMotion(entityController)
    entityState = updatePosition()
    entityState = updateDirection()
  }

  private def updateEntityAction(): Unit = {
    if(entityController.isRequestingAttack) entityState = entityState(Standing)
    else if(entityController.isRequestingCast) entityState = entityState(Casting)
    else if(entityState.motion.isMoving) entityState = entityState(Walking)
    else entityState = entityState(Standing)
  }

  private def updateMotion(entityController: EntityController): EntityState = {
    val newMotion = entityState.motion.update(entityController)
    entityState.copy(motion = newMotion)
  }

 private def updatePosition(): EntityState = {
    val newPosition = entityState.position.applyMotion(entityState.motion)
    entityState.copy(position = newPosition)
  }

  private def updateDirection(): EntityState = {
    val newDirection = entityState.direction.apply(entityState.motion)
    entityState.copy(direction = newDirection)
  }

}
