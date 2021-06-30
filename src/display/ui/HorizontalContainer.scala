package org.apollo
package display.ui

import physics.{Position, Size}

import java.awt.Color

case class HorizontalContainer(
          override val position: Position = Position(0,0),
          override val size: Size = Size(10),
          override val margin: Spacing = Spacing(5),
          override val padding: Spacing = Spacing(5),
          override val backgroundColor: Color = Color.RED,
          override val children: List[UIComponent] = List[UIComponent]())
extends UIContainer
{

    protected def calculateSize: HorizontalContainer = {
        val newSize = Size(
            padding.horizontal + calculateContentSize.size.width,
            padding.vertical + calculateContentSize.size.height)
        this.copy(size = newSize)
    }

    protected def calculatePosition: HorizontalContainer = {
        val newPosition = Position(
            padding.left + position.intX,
            padding.top + position.intY)
        this.copy(position = newPosition)
    }

    override protected def calculateContentSize: HorizontalContainer = {
        if(children.isEmpty) return this
        val combinedChildrenWidth = children.map(component => {
            component.size.width + component.margin.horizontal
        }).sum
        println(children.size)
        val tallestChildHeight = children.maxBy(_.size.height).size.height
        val newSize = Size(combinedChildrenWidth, tallestChildHeight)
        this.copy(size = newSize)
    }

    override protected def repositionChildren: HorizontalContainer = {
        if(children.isEmpty) return this
        var currentX = padding.left
        val newChildren = children.map(component => {
            currentX += component.margin.left
            val newPosition = Position(currentX, padding.top)
            val newComponent = component.setPosition(newPosition)
            currentX += newComponent.size.width
            currentX += newComponent.margin.right
            newComponent
        })
        this.copy(children = newChildren)
    }

    override def recalculate: HorizontalContainer = {
        calculateSize.calculatePosition.repositionChildren
    }

    override def setPosition(position: Position): HorizontalContainer = {
        this.copy(position = position)
    }

    override def setSize(size: Size): HorizontalContainer = {
        this.copy(size = size)
    }

    override def setMargin(margin: Spacing): HorizontalContainer = {
       this.copy(margin = margin)
    }

    override def setPadding(padding: Spacing): HorizontalContainer = {
        this.copy(padding = padding)
    }

    override def setBackgroundColor(color: Color): HorizontalContainer = {
        this.copy(backgroundColor = color)
    }

    override def setChildren(children: List[UIComponent]): HorizontalContainer = {
        this.copy(children = children)
    }

    override def addUIComponent(component: UIComponent): HorizontalContainer = {
        val newList = children.toBuffer.append(component).toList
        this.copy(children = newList)
    }

}
