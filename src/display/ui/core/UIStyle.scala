package org.apollo
package display.ui.core

import java.awt.Color

case class UIStyle(backgroundColor: Color = Color.RED,
                   border: UIBorder = UIBorder(),
                   font: UIFontInfo = UIFontInfo())
