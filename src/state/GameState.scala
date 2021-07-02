package org.apollo
package state

import display.Camera

import org.apollo.display.ui.typeclasses.UIDimensionable.UIDimensioner
import org.apollo.display.ui.typeclasses.UIStyleable.UIStyler
import display.ui.bridge.UIBridge
import display.ui.container.{UIContainer, VerticalContainer}
import display.ui.bridge
import entity.Player
import physics.{Position, Size}

import org.apollo.display.ui.components.UIButton

import java.awt.Color

class GameState extends State {

    initializeCharacters()
    initializeUI()


    override def update(): Unit = {
        super.update()
        UIBridge.update(this)

    }

    def initializeUI(): Unit = {

        val child = UIContainer()
          .setSize(Size(25))
          .setBackgroundColor(Color.RED)

        val verticalContainer = VerticalContainer()
          .setPosition(Position(50, 50))
          .setBackgroundColor(Color.GRAY)
          .addUIComponent(child)

        val button = UIButton()
          .setPosition(Position(250,250))

        UIBridge("VC1", verticalContainer)
        UIBridge("Button1", button)
        println(UIBridge("VC1").size)

    }

    def initializeCharacters(): Unit = {
        val player = new Player
        gameObjects.addOne(player)
        Camera.focusOn(player)
    }

}
