package org.apollo
package display.ui.container

import physics.{Position, Size}

import org.apollo.display.ui.UIAdjustable.UIAdjuster

case object VerticalContainer extends ContainerStrategy {

  override def apply(): UIContainer = UIContainer(strategy = this)

  protected override def calculateContentSize(container: UIContainer): UIContainer = {
    if (container.children.isEmpty) return container
    val combinedChildHeight = container.children.map(component => {
      component.size.height + component.margin.vertical
    }).sum
    //TODO: LOG
    println(container.children.size)
    val widestChildWidth = container.children.maxBy(_.size.width).size.width
    val newSize = Size(widestChildWidth, combinedChildHeight)
    container.copy(size = newSize)
  }

  protected override def repositionChildren(container: UIContainer): UIContainer = {
    if (container.children.isEmpty) return container
    var currentY = container.padding.top
    val newChildren = container.children.map(component => {
      currentY += component.margin.top
      val newPosition = Position(container.padding.left, currentY)
      val newComponent = component.setPosition(newPosition)
      currentY += newComponent.size.height
      currentY += newComponent.margin.bottom
      newComponent
    })
    container.copy(children = newChildren)
  }
}
