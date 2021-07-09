package org.apollo
package display.gfx.spritesheet

import physics._

import scala.collection.mutable

case class LPCSpriteInfo(frameCount: Int, yIndex: Int, size: Int)

case class LPCSubSheet(private val spriteTypes: Map[Direction, LPCSpriteInfo]) {
  def apply(direction: Direction): LPCSpriteInfo = spriteTypes(direction)
}
object LPCSubSheet {
  def apply(subsheets: (Direction, LPCSpriteInfo)*): LPCSubSheet = {
    val newMap = new mutable.ListBuffer[(Direction, LPCSpriteInfo)]()
    subsheets.foreach(s => newMap.addOne(s))
    LPCSubSheet(newMap.toMap)
  }
}
