package org.apollo
package entity
import input.{EntityController, PlayerController}
import physics.CollisionBox

case class Player(
    entityState: EntityState = EntityState(),
    entitySprite: EntitySprite = EntitySprite(),
    collisionBox: CollisionBox,
    parent: Entity,
    entityController: EntityController = PlayerController
) extends MovingEntity {


    def remake(entityState: EntityState, entitySprite: EntitySprite, parent: Entity): Entity = {
        this.copy(
            entityState = entityState,
            entitySprite = entitySprite,
            collisionBox = collisionBox,
            parent = parent,
            entityController = entityController
        )
    }

}
