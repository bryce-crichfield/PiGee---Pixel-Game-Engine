package org.apollo
package display.ui.bridge

import display.ui.typeclasses.UICommandable
import display.ui.typeclasses.UICommandable.{Command, nullCommand}
import physics.Position

import java.awt.event.{InputEvent, MouseEvent}


/** receives event calls, determines appropriate action, and then dispatches that command to the UISystem (or State???) */
// Right now this is really acting more in terms of an adapter rather than a standalone component
trait UIEventHandler[A <: InputEvent] {
  def dispatchToUI(): Unit
  protected def receive: Option[UIEvent]
  protected def determineCommand(uiEvent: Option[UIEvent]): Command
}

object UIEventHandler {

  implicit class UIMouseEventHandler(event: MouseEvent) extends UIEventHandler[MouseEvent] {
    def dispatchToUI(): Unit = {
      val received = receive
      val action = determineCommand(received)
      action.apply()
    }
    protected def receive: Option[UIEvent] = {
      val eventPosition = Position(event.getX, event.getY)
      val queriedComponent = UIBridge.query(eventPosition)
      queriedComponent match {
        case Some(_) => Some(UIEvent(queriedComponent.get, eventPosition))
        case None => None
      }
    }
    protected def determineCommand(uiEvent: Option[UIEvent]): Command = {
      if(uiEvent.isEmpty) return nullCommand
      uiEvent.get.component match {
        case commandable: UICommandable => commandable.command()
        case _ => nullCommand
      }
    }
  }





}
