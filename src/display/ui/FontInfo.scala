package org.apollo
package display.ui

import java.awt.{Color, Font}

case class FontInfo(
       fontSize: Int = 24,
       fontStyle: Int = 1,
       fontFamily: String = Font.SANS_SERIF,
       color: Color = Color.RED)
