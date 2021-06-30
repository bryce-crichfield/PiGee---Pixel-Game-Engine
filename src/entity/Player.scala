package org.apollo
package entity
import input.{EntityController, PlayerController}
import physics.CollisionBox

class Player(override val entityController: EntityController = PlayerController) extends MovingEntity(entityController) {


    override protected var parent: GameObject = null


    override def collisionBox: CollisionBox = ???
}
