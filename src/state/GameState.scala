package org.apollo
package state

import display.Camera
import display.ui.bridge.UISystem
import display.ui.components.UIButton
import display.ui.core.UIComponent
import display.ui.typeclasses.UIDimensionable.UIDimensioner
import display.ui.typeclasses.UIStyleable.UIStyler
import entity.Player
import physics.Position

import java.awt.Color

class GameState extends State {

    initializeCharacters()
    initializeUI()


    override def update(): Unit = {
        super.update()
        UISystem.update(this)

    }

    def initializeUI(): Unit = {

        val buttonCommand = (c: UIComponent) => {
            val newColor = if(c.backgroundColor == Color.RED) Color.BLUE else Color.RED
            val newButton = c.setBackgroundColor(newColor)
            UISystem.alterComponent(newButton)
        }

        val button = UIButton("button1", buttonCommand)
          .setPosition(Position(500,500))

        UISystem.addComponent(button)
    }

    def initializeCharacters(): Unit = {
        val player = Player
        gameObjects.addOne(player)
        Camera.focusOn(player)
    }

}
