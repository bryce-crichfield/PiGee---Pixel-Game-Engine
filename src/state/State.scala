package org.apollo
package state

import display.Camera
import display.ui.UIComponent
import display.ui.bridge.UIBridge
import entity.GameObject
import input.Input
import map.GameMap

import scala.collection.mutable.ListBuffer


trait State {

    // [ SUBSCRIBERS ]
    protected val gameObjects = new ListBuffer[GameObject]()
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

    def getGameObjects: List[GameObject] = gameObjects.toList

    def getUIComponents: List[UIComponent] = UIBridge.getUIComponents()
}
