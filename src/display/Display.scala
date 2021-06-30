package org.apollo
package display

import input.Input
import physics.Size
import state.State

import java.awt.{Canvas, Color, Dimension}
import javax.swing.{JFrame, WindowConstants}

object Display extends JFrame {

    val DEFAULT_WINDOW_WIDTH = 1280
    val DEFAULT_WINDOW_HEIGHT = 720

    private val canvas: Canvas = new Canvas()

    setTitle("org.apollo.Game")
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
    setResizable(false)

    canvas.setPreferredSize(new Dimension(DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT))
    canvas.setFocusable(false)

    canvas.addMouseListener(Input)
    canvas.addMouseMotionListener(Input)
    add(canvas)
    addKeyListener(Input)
    pack()

    canvas.createBufferStrategy(2)

    setLocationRelativeTo(null)
    setVisible(true)

    def render(state: State): Unit = {
        val bufferStrategy = canvas.getBufferStrategy
        val graphics = bufferStrategy.getDrawGraphics

        graphics.setColor(Color.BLACK)
        graphics.fillRect(0, 0, canvas.getWidth, canvas.getHeight)

        Renderer.render(state, graphics)

        graphics.dispose()
        bufferStrategy.show()
    }

    def windowSize: Size = Size(getWidth, getHeight)
}
