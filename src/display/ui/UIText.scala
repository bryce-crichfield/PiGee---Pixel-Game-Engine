package org.apollo
package display.ui
import physics.{Position, Size}
import state.State

import java.awt.Image

case class UIText(text: String = "ERROR: EMPTY TEXT",
                  position: Position = Position(0,0),
                  size: Size = Size(20),
                  margin: UISpacing = UISpacing(0),
                  padding: UISpacing = UISpacing(0),
                  style: UIStyle = UIStyle()
) extends UIComponent {

    val font: UIFontInfo = UIFontInfo()
    val dropShadow: Boolean = false
    val dropShadowOffset: Int = 0
    // TODO: Implement
    def getSprite: Image = ???
    def update(state: State): UIComponent = ???

}
