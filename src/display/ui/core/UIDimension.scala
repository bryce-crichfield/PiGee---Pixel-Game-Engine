package org.apollo
package display.ui.core

import physics.{Position, Size}

case class UIDimension(position: Position = Position(0, 0),
                       size: Size = Size(50, 50),
                       margin: UISpacing = UISpacing(10),
                       padding: UISpacing = UISpacing(10))

object UIDimension {

}