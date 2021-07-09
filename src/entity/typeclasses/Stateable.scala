package org.apollo
package entity.typeclasses

import entity.{Entity, EntityAction}
import physics.{Direction, Motion, Position}

trait Stateable[E <: Entity] {

  def position: Position
  def motion: Motion
  def action: EntityAction
  def direction: Direction

  def setDirection(direction: Direction): E
  def setAction(action: EntityAction): E
  def setMotion(motion: Motion): E
  def setPosition(position: Position): E
  // TODO: This seems sketchy
  def setParent[A <: Entity](parent: A): E
}
object Stateable {
  implicit class StateOps[E <: Entity](e: Entity) extends Stateable[E] {
    def position: Position = {
      if(e.parent != null) this.position.add(e.parent.position)
      e.entityState.position
    }
    def motion: Motion = e.entityState.motion
    def action: EntityAction = e.entityState.entityAction
    def direction: Direction = e.entityState.direction
    def setDirection(direction: Direction): E = ???
    def setAction(action: EntityAction): E = ???
    def setMotion(motion: Motion): E = ???
    def setPosition(position: Position): E = {
      val state = e.entityState.copy(position = position)
      e.remake(state).asInstanceOf[E]
    }
    def setParent[A <: Entity](parent: A): E = {
      val repositioned: E = e.setPosition(Position(0,0))
      repositioned.remake(parent = parent).asInstanceOf[E]
    }
  }
}