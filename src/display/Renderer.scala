package org.apollo
package display

import display.ui.UIComponent
import display.ui.UIDimensionable.UIDimensioner
import entity.GameObject
import state.State

import java.awt.Graphics

object Renderer {

    def render(state: State, graphics: Graphics): Unit = {
        renderGameObjects(state.getGameObjects, graphics)
        renderUI(state.getUIComponents, graphics)
    }

    private def renderGameObjects(gameObjects: List[GameObject], graphics: Graphics): Unit = {
        gameObjects.foreach(obj => {
            graphics.drawImage(
                obj.sprite,
                obj.getPosition.intX,
                obj.getPosition.intY,
                obj.getSize.width,
                obj.getSize.height,
                null
            )
        })
    }

    private def renderUI(uiComponents: List[UIComponent], graphics: Graphics): Unit = {
        uiComponents.foreach(component => {
            graphics.drawImage(
                component.getSprite,
                component.position.intX,
                component.position.intY,
                null
            )
        })
    }
}
