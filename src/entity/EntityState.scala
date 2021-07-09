package org.apollo
package entity

import physics._

case class EntityState(
  motion: Motion = Motion(1, Vector(0,0)),
  position: Position = Position(0,0),
  direction: Direction = South,
  entityAction: EntityAction = Standing
) {

  def apply(action: EntityAction): EntityState = {
    this.copy(entityAction = action)
  }
}
