package org.apollo
package display.ui.typeclasses

import display.ui.core.UIComponent

trait UICommandable {
  // TODO: Allow this to take a position argument
  val command: UIComponent => Unit
  def applyCommand(): Unit
}
object UICommandable {
  def nullCommand(): Unit = ()
}
