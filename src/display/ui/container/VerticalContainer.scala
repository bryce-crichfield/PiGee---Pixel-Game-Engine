package org.apollo
package display.ui.container

import display.ui.typeclasses.UIDimensionable.UIDimensioner
import physics.{Position, Size}

case object VerticalContainer extends ContainerStrategy {

  override def apply(): UIContainer = UIContainer(strategy = this)

  protected override def calculateContentSize(container: UIContainer): UIContainer = {
    if (container.children.isEmpty) return container
    val combinedChildHeight = container.children.map(component => {
      component.size.height + component.margin.vertical
    }).sum
    val widestChildWidth = container.children.maxBy(_.size.width).size.width
    val newSize = Size(widestChildWidth, combinedChildHeight)
    container.setSize(newSize)
  }

  protected override def repositionChildren(container: UIContainer): UIContainer = {
    if (container.children.isEmpty) return container
    var currentY = container.padding.top
    val newChildren = container.children.map(child => {
      currentY += child.margin.top
      val newPosition = Position(container.padding.left, currentY)
      val newComponent = child.setPosition(newPosition)
      currentY += newComponent.size.height
      currentY += newComponent.margin.bottom
      newComponent
    })
    container.setChildren(newChildren)
  }
}
