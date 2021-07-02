package org.apollo
package display.ui.components

import display.gfx.ImageUtils
import display.ui.bridge.UIEvent
import display.ui.components.UIButton.{DEFAULT_BUTTON_DIMENSION, DEFAULT_BUTTON_STYLE}
import display.ui.core
import display.ui.core.{UIComponent, UIDimension, UISpacing, UIStyle}
import display.ui.typeclasses.UICommandable
import display.ui.typeclasses.UIDimensionable.UIDimensioner
import display.ui.typeclasses.UIStyleable.UIStyler
import physics.{Position, Size}
import state.State

import java.awt.image.BufferedImage
import java.awt.{Color, Image}

case class UIButton(dimension: UIDimension = DEFAULT_BUTTON_DIMENSION,
                    style: UIStyle = DEFAULT_BUTTON_STYLE
) extends UIComponent with UICommandable {

  override def remake(dimension: UIDimension, style: UIStyle): UIComponent = {
      this.copy(dimension = dimension, style = style)
  }

  /** every UIComponent will construct its own sprite */
  override def getSprite: Image = {
    val image = ImageUtils
      .createCompatibleImage(this.size, ImageUtils.ALPHA_BIT_MASKED)
      .asInstanceOf[BufferedImage]
    val graphics = image.createGraphics()
    graphics.setColor(this.backgroundColor)
    graphics.fillRect(0, 0, this.size.width, this.size.height)
    graphics.dispose()
    image
  }

  override def update(state: State): UIComponent = {
    this
  }

  def respondToEvent(UIEvent: UIEvent): Unit = {
    print("Button pressed")
  }

  def command(position: Position): Unit = {
    () => println("BUTTON PRESSED")
  }
}
object UIButton
{
  val DEFAULT_BUTTON_DIMENSION: UIDimension = core.UIDimension(
    size = Size(50),
    margin = UISpacing(5),
    padding = UISpacing(5)
  )
  val DEFAULT_BUTTON_STYLE: UIStyle = UIStyle(
    backgroundColor = Color.BLUE
  )
}