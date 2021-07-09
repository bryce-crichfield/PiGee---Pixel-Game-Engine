package org.apollo
package entity

import org.apollo.physics.Position

import java.awt.Image

case class EntitySprite(
  sprite: () => Image,
  renderOffset: Position = Position(0,0),
  renderOrder: Int = 5
)
