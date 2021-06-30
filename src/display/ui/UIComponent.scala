package org.apollo
package display.ui

import physics.{Position, Size}
import state.State

import java.awt.Image

/** super class of all UI elements */
trait UIComponent {

    val position: Position
    val size: Size
    val margin: Spacing
    val padding: Spacing

    /** every UIComponent will construct its own sprite */
    def getSprite: Image

    def update(state: State): UIComponent

    def setPosition(position: Position): UIComponent
    def setSize(size: Size): UIComponent
    def setMargin(margin: Spacing): UIComponent
    def setPadding(padding: Spacing): UIComponent
}

