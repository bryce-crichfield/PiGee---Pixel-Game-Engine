package org.apollo
package core

object GameLoop extends Runnable {

    val UPDATES_PER_SECOND = 60

    private var isRunning: Boolean = false
    private val updateRate: Double = 1.0 / UPDATES_PER_SECOND

    private var nextStatTime: Long = 0
    private var fps: Int = 0
    private var ups: Int = 0

    private def time: Long = System.currentTimeMillis()

    override def run(): Unit = {
        isRunning = true
        var accumulator: Double = 0
        var currentTime: Double = time
        var lastUpdate: Double = currentTime
        nextStatTime = (currentTime + 1000).toLong
        while (isRunning) {
            currentTime = time
            var lastRenderTimeInSeconds = (currentTime - lastUpdate) / 1000d
            accumulator += lastRenderTimeInSeconds
            lastUpdate = currentTime
            if (accumulator >= updateRate) {
                while (accumulator >= updateRate) {
                    update()
                    accumulator -= updateRate
                }
            }
            render()
            printStats()
        }
    }

    private def printStats() = {
        if (time > nextStatTime) {
            System.out.println(s"FPS: $fps \t UPS: $ups")
            fps = 0
            ups = 0
            nextStatTime = time + 1000
        }
    }

    private def update(): Unit = {
        StateManager.update
        ups += 1
    }

    private def render(): Unit = {
        StateManager.render
        fps += 1
    }


}
