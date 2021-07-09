package org.apollo
package state

import display.Camera
import display.ui.bridge.UISystem
import display.ui.core.UIComponent
import entity.Entity
import input.Input
import map.GameMap

import scala.collection.mutable.ListBuffer


trait State {

    // [ SUBSCRIBERS ]
    protected val gameObjects = new ListBuffer[Entity]()
    protected val gameMap = new GameMap(null)
    protected val nextState: State = null

    def update(): Unit = {
        gameObjects.foreach(p => p.update(this))
        Camera.update(this)

        // TODO: Not sure what this does tbh
        // if(nextState != null) {StateManager.setState(nextState)}
        handleMouseInput()
    }

    def handleMouseInput(): Unit = {
        if(Input.isMouseClicked) println(s"Mouse Clicked at ${Input.getMousePosition.x}, ${Input.getMousePosition.y}")
        Input.clearMouseClick
    }

    def getGameMap: GameMap = gameMap

    def getGameObjects: List[Entity] = gameObjects.toList

    def getUIComponents: List[UIComponent] = UISystem.getUIComponents()
}
