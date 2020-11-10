import graphics.DrawingPanel
import graphics.colorScheme
import graphics.painters.MandelbrotPainter
import graphics.painters.MouseFramePainter
import math.MandelbrotSet
import util.ConvertData
import util.Converter
import java.awt.Color
import java.awt.Dimension
import java.awt.event.ComponentAdapter
import java.awt.event.ComponentEvent
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.lang.Double.max
import java.lang.Double.min
import javax.swing.JFrame

class Window : JFrame() {

    private val minSize = Dimension(300, 200)
    private val mainPanel: DrawingPanel

    init {
        defaultCloseOperation = EXIT_ON_CLOSE
        title = "Построение множества Мандельброта"
        minimumSize = Dimension(700, 700)
        mainPanel = DrawingPanel()
        mainPanel.background = Color.WHITE
        add(mainPanel)
        pack()
        var plane = ConvertData(mainPanel.width, mainPanel.height, -2.0, 1.0, -1.0, 1.0)
        var fp = MandelbrotPainter(plane)
        val fractal = MandelbrotSet()
        fp.fractalTest = fractal::isInSet
        fp.getColor = ::colorScheme

        mainPanel.addPainter(fp)


        var mfp = MouseFramePainter(mainPanel.graphics)

        mainPanel.addComponentListener(object : ComponentAdapter() {
            override fun componentResized(e: ComponentEvent?) {
                mainPanel.updatePainters()
                mfp = MouseFramePainter(mainPanel.graphics)
                repaint()
            }
        })

        mainPanel.addMouseListener(object : MouseAdapter() {
            override fun mousePressed(e: MouseEvent?) {
                mfp.isPressed = true
                e?.let { mfp.start = it.point }
            }

            override fun mouseReleased(e: MouseEvent?) {
                mfp.isPressed = false
                e?.let {
                    mfp.current = it.point
                    mfp.start?.let { s ->
                        mfp.current?.let { c ->
                            val xMin = min(
                                Converter.xScr2Crt(s.x, plane),
                                Converter.xScr2Crt(c.x, plane)
                            )
                            val xMax = max(
                                Converter.xScr2Crt(s.x, plane),
                                Converter.xScr2Crt(c.x, plane)
                            )
                            val yMin = min(
                                Converter.yScr2Crt(s.y, plane),
                                Converter.yScr2Crt(c.y, plane)
                            )
                            val yMax = max(
                                Converter.yScr2Crt(s.y, plane),
                                Converter.yScr2Crt(c.y, plane)
                            )
                            plane = ConvertData(mainPanel.width, mainPanel.height, xMin, xMax, yMin, yMax)
                            mainPanel.removePainter(fp)
                            fp = MandelbrotPainter(plane)
                            fp.fractalTest = fractal::isInSet
                            fp.getColor = ::colorScheme
                            mainPanel.addPainter(fp)
                        }
                    }
                }

            }


        })
        mainPanel.addMouseMotionListener(object : MouseAdapter() {
            override fun mouseDragged(e: MouseEvent?) {
                e?.let { mfp.current = it.point }
            }
        })
    }
}