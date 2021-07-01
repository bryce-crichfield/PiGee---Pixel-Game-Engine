package org.apollo
package display.ui

import physics.{Position, Size}

import com.sun.tools.classfile.TypeAnnotation.Position
import org.apollo.state.State

import java.awt.Image

case class UIButton(position: Position = Position(0,0),
                    size: Size = Size(10),
                    margin: UISpacing = UISpacing(5),
                    padding: UISpacing = UISpacing(5),
                    style: UIStyle
) extends UIComponent {

  override def remake(position: Position, size: Size, margin: UISpacing, padding: UISpacing, style: UIStyle): UIComponent = {
      this.copy(position = position, size = size, margin = margin, padding = padding, style = style)
  }

  /** every UIComponent will construct its own sprite */
  override def getSprite: Image = ???

  override def update(state: State): UIComponent = ???
}