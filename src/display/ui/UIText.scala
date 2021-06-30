package org.apollo
package display.ui
import physics.{Position, Size}
import state.State

import java.awt.Image

case class UIText() extends UIComponent {


    val text: String = "ERROR: EMPTY TEXT"
    val font: FontInfo = FontInfo()
    val dropShadow: Boolean = false
    val dropShadowOffset: Int = 0
    override val position: Position = Position(0,0)
    override val size: Size = Size(20)
    override val margin: Spacing = Spacing(0)
    override val padding: Spacing = Spacing(0)

    override def getSprite: Image = ???

    override def update(state: State): UIComponent = ???

    override def setPosition(position: Position): UIComponent = ???

    override def setSize(size: Size): UIComponent = ???

    override def setMargin(margin: Spacing): UIComponent = ???

    override def setPadding(padding: Spacing): UIComponent = ???
}
