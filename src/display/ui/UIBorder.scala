package org.apollo
package display.ui

import display.ui.UIStyleable.UIStyler

import java.awt.Color

case class UIBorder(borderColor: Color = Color.BLACK, borderThickness: Int = 5)

trait UIBorderable[A <: UIComponent] {
  def borderColor: Color
  def borderThickness: Int
  def setBorderColor(color: Color): A
  def setBorderThickness(thickness: Int): A
  def apply(border: UIBorder): A
}
object UIBorderable {
  implicit class UIBorderer[A <: UIComponent](component: A) extends UIBorderable[A] {
    def borderColor: Color = component.style.border.borderColor
    def borderThickness: Int = component.style.border.borderThickness
    def setBorderColor(color: Color): A = {
      val newBorder = component.border.copy(borderColor = color)
      component(newBorder)
    }
    def setBorderThickness(thickness: Int): A = {
      val newBorder = component.border.copy(borderThickness = thickness)
      component(newBorder)
    }
    def apply(border: UIBorder): A = {
      component.setBorder(border)
    }
  }
}