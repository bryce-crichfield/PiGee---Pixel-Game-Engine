package org.apollo
package display.ui

import physics.{Position, Size}

import java.awt.Color

case class VerticalContainer(
          override val position: Position = Position(0,0),
          override val size: Size = Size(10),
          override val margin: Spacing = Spacing(5),
          override val padding: Spacing = Spacing(5),
          override val backgroundColor: Color = Color.RED,
          override val children: List[UIComponent] = List[UIComponent]())
extends UIContainer with Adjustable[VerticalContainer]
{

    protected def calculateSize: VerticalContainer = {
        val newSize = Size(
            padding.horizontal + calculateContentSize.size.width,
            padding.vertical + calculateContentSize.size.height)
        this.copy(size = newSize)
    }

    protected def calculatePosition: VerticalContainer = {
        val newPosition = Position(
            padding.left + position.intX,
            padding.top + position.intY)
        this.copy(position = newPosition)
    }

    override protected def calculateContentSize: VerticalContainer = {
        if(children.isEmpty) return this
        val combinedChildHeight = children.map(component => {
            component.size.height + component.margin.vertical
        }).sum
        println(children.size)
        val widestChildWidth = children.maxBy(_.size.width).size.width
        val newSize = Size(widestChildWidth, combinedChildHeight)
        this.copy(size = newSize)
    }

    override protected def repositionChildren: VerticalContainer = {
        if(children.isEmpty) return this
        var currentY = padding.top
        val newChildren = children.map(component => {
            currentY += component.margin.top
            val newPosition = Position(padding.left, currentY)
            val newComponent = component.setPosition(newPosition)
            currentY += newComponent.size.height
            currentY += newComponent.margin.bottom
            newComponent
        })
        this.copy(children = newChildren)
    }

    override def recalculate: VerticalContainer = {
        calculateSize.repositionChildren.calculatePosition
    }

    override def setPosition(position: Position): VerticalContainer = {
        this.copy(position = position)
    }

    override def setSize(size: Size): VerticalContainer = {
        this.copy(size = size)
    }

    override def setMargin(margin: Spacing): VerticalContainer = {
       this.copy(margin = margin)
    }

    override def setPadding(padding: Spacing): VerticalContainer = {
        this.copy(padding = padding)
    }

    override def setBackgroundColor(color: Color): VerticalContainer = {
        this.copy(backgroundColor = color)
    }

    override def setChildren(children: List[UIComponent]): VerticalContainer = {
        this.copy(children = children)
    }

    override def addUIComponent(component: UIComponent): VerticalContainer = {
        val newList = children.toBuffer.append(component).toList
        this.copy(children = newList)
    }

}
