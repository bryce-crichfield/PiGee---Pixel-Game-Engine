package org.apollo
package display.ui
import display.gfx.ImageUtils
import physics.Size
import state.State

import java.awt.image.BufferedImage
import java.awt.{Color, Image}

trait UIContainer extends UIComponent {

    val backgroundColor: Color
    val children: List[UIComponent]

    protected def calculateContentSize: UIContainer
    protected def repositionChildren: UIContainer
    protected def calculateSize: UIContainer
    protected def calculatePosition: UIContainer

    def setBackgroundColor(color: Color): UIContainer
    def setChildren(children: List[UIComponent]): UIContainer
    def addUIComponent(component: UIComponent): UIContainer
    def recalculate: UIContainer

    override def getSprite: Image = {
        val image = ImageUtils
            .createCompatibleImage(size, ImageUtils.ALPHA_BIT_MASKED)
            .asInstanceOf[BufferedImage]
        val graphics = image.createGraphics()
        graphics.setColor(backgroundColor)
        graphics.fillRect(0,0,size.width,size.height)
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
        setChildren(updatedChildren).calculateSize.calculatePosition
    }
}




