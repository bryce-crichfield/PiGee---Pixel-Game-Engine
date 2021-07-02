package org.apollo
package display.ui.typeclasses

import display.ui.core.{UIBorder, UIComponent, UIFontInfo, UIStyle}

import java.awt.Color

trait UIStyleable[A <: UIComponent] {
  // [ ACCESSORS ]
  def style: UIStyle

  def backgroundColor: Color

  def border: UIBorder

  def uiText: UIFontInfo

  // [ MUTATORS ]
  def setStyle(style: UIStyle): A

  def setBackgroundColor(color: Color): A

  def setBorder(border: UIBorder): A

  def setFont(font: UIFontInfo): A
}

object UIStyleable {
  implicit class UIStyler[A <: UIComponent](component: A) extends UIStyleable[A] {
    def style: UIStyle = component.style

    def backgroundColor: Color = component.style.backgroundColor

    def border: UIBorder = component.style.border

    def uiText: UIFontInfo = component.style.font

    // TODO: perhaps even this remaking could be abstracted out
    def setStyle(style: UIStyle): A = {
      component.remake(style = style).asInstanceOf[A]
    }

    def setBackgroundColor(color: Color): A = {
      val newStyle = component.style.copy(backgroundColor = color)
      component.setStyle(newStyle)
    }

    def setBorder(border: UIBorder): A = {
      val newStyle = component.style.copy(border = border)
      component.setStyle(newStyle)
    }

    def setFont(font: UIFontInfo): A = {
      val newStyle = component.style.copy(font = font)
      component.setStyle(newStyle)
    }
  }
}