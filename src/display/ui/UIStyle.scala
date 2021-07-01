package org.apollo
package display.ui

import java.awt.Color
import scala.language.implicitConversions

case class UIStyle(backgroundColor: Color = Color.RED, border: UIBorder = UIBorder(), font: UIFontInfo = UIFontInfo())


trait UIStyleable[A <: UIComponent] {
  def backgroundColor: Color
  def border: UIBorder
  def uiText: UIFontInfo
  def setBackgroundColor(color: Color): A
  def setBorder(border: UIBorder): A
  def setFont(font: UIFontInfo): A
  def apply(style: UIStyle): A
}
object UIStyleable
{

  implicit class UIStyler[A <: UIComponent](component: A) extends UIStyleable[A] {
    def backgroundColor: Color = component.style.backgroundColor
    def border: UIBorder = component.style.border
    def uiText: UIFontInfo = component.style.font
    // TODO: perhaps even this remaking could be abstracted out
    def setBackgroundColor(color: Color): A = {
      val newStyle = component.style.copy(backgroundColor = color)
      component(newStyle)
    }
    def setBorder(border: UIBorder): A = {
      val newStyle = component.style.copy(border = border)
      component(newStyle)
    }
    def setFont(font: UIFontInfo): A = {
      val newStyle = component.style.copy(font = font)
      component(newStyle)
    }
    def apply(style: UIStyle): A = {
      component.remake(style = style).asInstanceOf[A]
    }
  }
}


