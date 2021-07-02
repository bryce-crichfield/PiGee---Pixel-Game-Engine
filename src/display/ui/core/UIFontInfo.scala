package org.apollo
package display.ui.core

import java.awt.{Color, Font}

case class UIFontInfo(
                       fontSize: Int = 24,
                       fontStyle: Int = 1,
                       fontFamily: String = Font.SANS_SERIF,
                       color: Color = Color.RED)
