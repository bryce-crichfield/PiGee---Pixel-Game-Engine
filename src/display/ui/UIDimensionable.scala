package org.apollo
package display.ui

import physics.{Position, Size}


case class UIDimension(position: Position = Position(0,0),
                       size: Size = Size(50, 50),
                       margin: UISpacing = UISpacing(10),
                       padding: UISpacing = UISpacing(10))
object UIDimension {

}

trait UIDimensionable[A <: UIComponent] {
  // [ ACCESSORS ]
  def position: Position
  def size: Size
  def margin: UISpacing
  def padding: UISpacing
  // [ MUTATORS ]
  def setDimension(d: UIDimension): A
  def setPosition(p: Position): A
  def setSize(s: Size): A
  def setMargin(m: UISpacing): A
  def setPadding(p: UISpacing): A
}
object UIDimensionable {
  implicit class UIDimensioner[A <: UIComponent](component: A) extends UIDimensionable[A] {
    def position: Position = component.dimension.position
    def size: Size = component.dimension.size
    def margin: UISpacing = component.dimension.margin
    def padding: UISpacing = component.dimension.padding
    // TODO: I am (now less so) skeptical of the implicit conversion here
    def setDimension(d: UIDimension): A = {
      component.remake(dimension = d).asInstanceOf[A]
    }
    def setPosition(p: Position): A = {
      val newDimension = component.dimension.copy(position = p)
      component.setDimension(newDimension)
    }
    def setSize(s: Size): A = {
      val newDimension = component.dimension.copy(size = s)
      component.setDimension(newDimension)
    }
    def setMargin(m: UISpacing): A = {
      val newDimension = component.dimension.copy(margin = m)
      component.setDimension(newDimension)
    }
    def setPadding(p: UISpacing): A = {
      val newDimension = component.dimension.copy(padding = p)
      component.setDimension(newDimension)
    }
  }
}
