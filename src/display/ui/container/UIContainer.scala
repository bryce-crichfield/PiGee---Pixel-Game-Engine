package org.apollo
package display.ui.container

import display.gfx.ImageUtils
import display.ui.{UIComponent, UISpacing, UIStyle}
import physics.{Position, Size}
import state.State

import org.apollo.display.ui.UIStyleable.UIStyler
import org.apollo.display.ui.container.ContainerStrategy.ContainerStrategyOperations

import java.awt.image.BufferedImage
import java.awt.{Color, Image}
import scala.language.existentials

case class UIContainer(position: Position = Position(0,0),
                       size: Size = Size(10),
                       margin: UISpacing = UISpacing(5),
                       padding: UISpacing = UISpacing(5),
                       style: UIStyle,
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
            .createCompatibleImage(size, ImageUtils.ALPHA_BIT_MASKED)
            .asInstanceOf[BufferedImage]
        val graphics = image.createGraphics()
        graphics.setColor(this.backgroundColor)
        graphics.fillRect(0,0,size.width,size.height)
       //here contains a kind of recursive call to the children
        for(component <- children) {
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
        setChildren(updatedChildren).calculateSize().calculatePosition()
    }

    override def remake(position: Position, size: Size, margin: UISpacing, padding: UISpacing, style: UIStyle = style): UIComponent = {
      this.copy(position = position, size = size, margin = margin, padding = padding, style = style)
    }

}
object UIContainer {

}



