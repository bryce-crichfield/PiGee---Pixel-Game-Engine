package org.apollo
package display.ui

trait UICommandable {
  // TODO: Allow this to take a position argument
  def command(): Unit
}
object UICommandable {
  type Command = () => Unit
  val noFoundCommand: () => Unit = () => println("NO APPROPRIATE COMMAND WAS FOUND")
  val nullCommand: () => Unit = () => ()
}
