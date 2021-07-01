package org.apollo
package display.ui.container

import physics.{Position, Size}
import org.apollo.display.ui.UIAdjustable.UIAdjuster


case object HorizontalContainer extends ContainerStrategy {

  override def apply(): UIContainer = UIContainer(strategy = this)

  protected override def calculateContentSize(container: UIContainer): UIContainer = {
    if (container.children.isEmpty) return container
    val combinedChildrenWidth = container.children.map(component => {
      component.size.width + component.margin.horizontal
    }).sum
    println(container.children.size)
    val tallestChildHeight = container.children.maxBy(_.size.height).size.height
    val newSize = Size(combinedChildrenWidth, tallestChildHeight)
    container.copy(size = newSize)
  }

  protected override def repositionChildren(container: UIContainer): UIContainer = {
    if (container.children.isEmpty) return container
    var currentX = container.padding.left
    val newChildren = container.children.map(component => {
      currentX += component.margin.left
      val newPosition = Position(currentX, container.padding.top)
      val newComponent = component.setPosition(newPosition)
      currentX += newComponent.size.width
      currentX += newComponent.margin.right
      newComponent
    })
    container.copy(children = newChildren)
  }
}
