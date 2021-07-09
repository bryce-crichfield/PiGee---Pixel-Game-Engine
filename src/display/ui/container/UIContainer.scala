package org.apollo
package display.ui.container

import display.gfx.ImageUtils
import display.ui.bridge.UISystem
import display.ui.container.ContainerStrategy.ContainerStrategyOperations
import display.ui.container.UIContainer.{DEFAULT_CONTAINER_DIMENSION, DEFAULT_CONTAINER_STYLE}
import display.ui.core.{UIComponent, UIDimension, UISpacing, UIStyle}
import display.ui.typeclasses.UIDimensionable.UIDimensioner
import display.ui.typeclasses.UIStyleable.UIStyler
import physics.Size
import state.State

import java.awt.image.BufferedImage
import java.awt.{Color, Image}

case class UIContainer(id: String,
                       dimension: UIDimension = DEFAULT_CONTAINER_DIMENSION,
                       style: UIStyle = DEFAULT_CONTAINER_STYLE,
                       children: List[UIComponent] = List[UIComponent](),
                       strategy: ContainerStrategy = VerticalContainer)
  extends UIComponent {

  // TODO: overriding copy method from UIComponent is giving trouble

  def setChildren(children: List[UIComponent]): UIContainer = {
    this.copy(children = children)
  }

  def addUIComponent(component: UIComponent): UIContainer = {
    this.copy(children = (children :+ component))
  }

  // TODO: Implement recalculate macro for strategy commands

  def getSprite: Image = {
    val image = ImageUtils
      .createCompatibleImage(this.size, ImageUtils.ALPHA_BIT_MASKED)
      .asInstanceOf[BufferedImage]
    val graphics = image.createGraphics()
    graphics.setColor(this.backgroundColor)
    graphics.fillRect(0, 0, this.size.width, this.size.height)
    //here contains a kind of recursive call to the children
    for (component <- children) {
      graphics.drawImage(
        component.getSprite,
        component.position.intX,
        component.position.intY,
        null
      )
    }
    graphics.dispose()
    image
  }

  override def update(state: State): UIContainer = {
    val updatedChildren = children.map(component => component.update(state))
    setChildren(updatedChildren).applyChanges()
  }

  override def remake(dimension: UIDimension, style: UIStyle = style): UIComponent = {
    UISystem.setChangesMade(true)
    this.copy(dimension = dimension, style = style)
  }

  def applyChanges(): UIContainer = {
    this.calculateSize().repositionChildren().calculateSize()
  }
}

object UIContainer {
  val DEFAULT_CONTAINER_DIMENSION: UIDimension = UIDimension(
    size = Size(1),
    margin = UISpacing(5),
    padding = UISpacing(5)
  )
  val DEFAULT_CONTAINER_STYLE: UIStyle = UIStyle(
    backgroundColor = Color.GRAY
  )
}



