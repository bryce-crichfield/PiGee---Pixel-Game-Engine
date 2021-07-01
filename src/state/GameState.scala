package org.apollo
package state

import display.Camera
import display.ui.UISpacing
import display.ui.UIAdjustable.UIAdjuster
import display.ui.container.ContainerStrategy.ContainerStrategyOperations
import display.ui.container.{UIContainer, VerticalContainer}
import entity.Player
import physics.Size

import java.awt.Color

class GameState extends State {

    initializeCharacters()
    initializeUI()

    override def update(): Unit = {
        super.update()

    }

    def initializeUI(): Unit = {

//        val VContainer = VerticalContainer()
//            .setSize(Size(100))
//            .setPadding(Spacing(20))
//            .setMargin(Spacing(0))
//            .setBackgroundColor(Color.GRAY)
//            .addUIComponent(HorizontalContainer(size = Size(20)))
//            .addUIComponent(HorizontalContainer(size = Size(20)))
//            .addUIComponent(HorizontalContainer(size = Size(20)))
//            .addUIComponent(HorizontalContainer(size = Size(20)))
//            .addUIComponent(HorizontalContainer(size = Size(20)))
//            .recalculate
//
//        uiContainers.addOne(VContainer)

        val verticalContainer = UIContainer()
          .setStrategy(VerticalContainer)
          .setSize(Size(100))
          .setPadding(UISpacing(20))
          .setMargin(UISpacing(0))
          .setBackgroundColor(Color.GRAY)

        uiContainers.addOne(verticalContainer)

    }

    def initializeCharacters(): Unit = {
        val player = new Player
        gameObjects.addOne(player)
        Camera.focusOn(player)
    }

}
