package org.apollo
package display.ui.typeclasses

import org.apollo.display.ui.core.UIComponent

import java.awt.Color

trait UIBorderable[A <: UIComponent] {
  // [ ACCESSORS ]  Border is not accessed because all borders are contained in a style
  def borderColor: Color

  def borderThickness: Int

  // [ MUTATORS ]   Border is not mutated because that is handle via the style
  def setBorderColor(color: Color): A

  def setBorderThickness(thickness: Int): A
}

object UIBorderable {
  implicit class UIBorderer[A <: UIComponent](component: A) extends UIBorderable[A] {
    def borderColor: Color = component.style.border.borderColor

    def borderThickness: Int = component.style.border.borderThickness

    def setBorderColor(color: Color): A = {
      val newBorder = component.border.copy(borderColor = color)
      component.setBorder(newBorder)
    }

    def setBorderThickness(thickness: Int): A = {
      val newBorder = component.border.copy(borderThickness = thickness)
      component.setBorder(newBorder)
    }

  }
}