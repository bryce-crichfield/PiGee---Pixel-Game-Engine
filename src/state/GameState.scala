package org.apollo
package state

import display.Camera
import display.ui.{HorizontalContainer, Spacing, VerticalContainer}
import entity.Player
import physics.{Position, Size}

import java.awt.Color

class GameState extends State {

    initializeCharacters()
    initializeUI()

    override def update(): Unit = {
        super.update()

    }

    def initializeUI(): Unit = {

        val VContainer = VerticalContainer()
            .setSize(Size(100))
            .setPadding(Spacing(20))
            .setMargin(Spacing(0))
            .setBackgroundColor(Color.GRAY)
//            .addUIComponent(HorizontalContainer(size = Size(20)))
//            .addUIComponent(HorizontalContainer(size = Size(20)))
//            .addUIComponent(HorizontalContainer(size = Size(20)))
//            .addUIComponent(HorizontalContainer(size = Size(20)))
//            .addUIComponent(HorizontalContainer(size = Size(20)))
//            .recalculate

        uiContainers.addOne(VContainer)

//        val HContainer = HorizontalContainer()
//            .setPosition(Position(400,400))
//            .setSize(Size(100))
//            .setPadding(Spacing(20))
//            .setMargin(Spacing(0))
//            .setBackgroundColor(Color.GRAY)
//            .recalculate
//
//        uiContainers.addOne(HContainer)
    }

    def initializeCharacters(): Unit = {
        val player = new Player
        gameObjects.addOne(player)
        Camera.focusOn(player)
    }

}
