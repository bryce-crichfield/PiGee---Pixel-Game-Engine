package org.apollo
package display.ui

import physics.Position

trait Adjustable[S] {

  def setPosition(position: Position)

}
