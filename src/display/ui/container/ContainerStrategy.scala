package org.apollo
package display.ui.container

import physics.{Position, Size}

import org.apollo.display.ui.container.ContainerStrategy.DEFAULT_CONTAINER

// A strategy shouldn't really need to know about a specific container but this works for know
trait ContainerStrategy {
  def apply(): UIContainer = DEFAULT_CONTAINER
  protected def calculateContentSize(container: UIContainer): UIContainer
  protected def repositionChildren(container: UIContainer): UIContainer
  protected def calculatePosition(container: UIContainer): UIContainer = {
    val newPosition = Position(
      container.padding.left + container.position.intX,
      container.padding.top + container.position.intY)
    container.copy(position = newPosition)
  }
  protected def calculateSize(container: UIContainer): UIContainer = {
    // TODO: This odd reference to the container's strategy may break the code
    val newSize = Size(
      container.padding.horizontal + container.strategy.calculateContentSize(container).size.width,
      container.padding.vertical + container.strategy.calculateContentSize(container).size.height)
    container.copy(size = newSize)
  }
  protected def setStrategy(s: ContainerStrategy, container: UIContainer): UIContainer = {
    UIContainer(strategy = s)
  }
}
object ContainerStrategy {
  // This implicit class provides bindings to Container strategy class
  implicit class ContainerStrategyOperations(container: UIContainer) {
     def calculateContentSize(): UIContainer = {
      container.strategy.calculateContentSize(container)
    }
    def repositionChildren(): UIContainer = {
      container.strategy.repositionChildren(container)
    }
    def calculateSize(): UIContainer = {
      container.strategy.calculateSize(container)
    }
    def calculatePosition(): UIContainer = {
      container.strategy.calculatePosition(container)
    }
    def setStrategy(s: ContainerStrategy): UIContainer = {
      container.copy(strategy = s)
    }
  }

  val DEFAULT_CONTAINER: UIContainer = VerticalContainer.apply()
}




