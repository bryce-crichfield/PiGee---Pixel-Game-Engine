package org.apollo
package display.ui

import physics.{Position, Size}

trait UIAdjustable[A <: UIComponent] {
  // TODO: This begs the question, should these parameters be a property of a component or an adjustable,
  //  for now the behavior is abstracted here and that is it
  def setPosition(p: Position): A
  def setSize(s: Size): A
  def setMargin(m: UISpacing): A
  def setPadding(p: UISpacing): A
  // TODO: It is possible to abstract the position, size, margin, and padding parameters into a case class
  // Then call apply(info: UIInfo) to force a call of component.remake(info)
}
object UIAdjustable {
  implicit class UIAdjuster[A <: UIComponent](component: A) extends UIAdjustable[A] {
    // TODO: I am skeptical of the implicit conversion here
    override def setPosition(p: Position): A = component.remake(position = p).asInstanceOf[A]
    override def setSize(s: Size): A = component.remake(size = s).asInstanceOf[A]
    override def setMargin(m: UISpacing): A = component.remake(margin = m).asInstanceOf[A]
    override def setPadding(p: UISpacing): A = component.remake(padding = p).asInstanceOf[A]
  }
}
