package org.apollo
package display.ui

import physics.{Position, Size}

import shapeless.{HNil, Poly1}
import state.State

import org.apollo.display.ui.container.UIContainer

import java.awt.Image

/** super class of all UI elements */
trait UIComponent {
    val position: Position
    val size: Size
    val margin: UISpacing
    val padding: UISpacing
    val style: UIStyle
    def remake(position: Position = position,
               size: Size = size,
               margin: UISpacing = margin,
               padding: UISpacing = padding,
               style: UIStyle = style): UIComponent


    /** every UIComponent will construct its own sprite */
    // TODO: We may want to abstract this behavior away as well
    def getSprite: Image
    def update(state: State): UIComponent
}




