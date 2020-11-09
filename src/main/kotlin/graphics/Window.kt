import graphics.DrawingPanel
import graphics.painters.MandelbrotPainter
import math.MandelbrotSet
import util.ConvertData
import util.Converter
import java.awt.Color
import java.awt.Dimension
import java.awt.event.*
import javax.swing.GroupLayout
import javax.swing.JFrame

class Window : JFrame(){

    private val minSize = Dimension(300, 200)
    private val mainPanel: DrawingPanel
    init{
        defaultCloseOperation = EXIT_ON_CLOSE
        title = "Построение множества Мандельброта"
        minimumSize = Dimension(700, 700)
        mainPanel = DrawingPanel()
        mainPanel.background = Color.WHITE
        val gl = GroupLayout(contentPane)

        gl.setVerticalGroup(gl.createSequentialGroup()
            .addGap(4)
            .addComponent(mainPanel, minSize.height, minSize.height, GroupLayout.DEFAULT_SIZE)
            .addGap(4)
        )

        gl.setHorizontalGroup(gl.createSequentialGroup()
            .addGap(4)
            .addGroup(
                gl.createParallelGroup()
                    .addComponent(mainPanel, minSize.width, minSize.width, GroupLayout.DEFAULT_SIZE)
            )
            .addGap(4))
        layout = gl

        pack()

        val plane = ConvertData(
            mainPanel.width, mainPanel.height,
            -2.0, 1.0, -1.0, 1.0
        )

        mainPanel.addComponentListener(object : ComponentAdapter() {
            override fun componentResized(e: ComponentEvent?) {
                plane.width = mainPanel.width
                plane.height = mainPanel.height
            }
        })

        mainPanel.addMouseListener(object: MouseAdapter(){
            override fun mouseClicked(e: MouseEvent?) {
                if (e == null) return
                val cx = Converter.xScr2Crt(e.x, plane)
                val cy = Converter.yScr2Crt(e.y, plane)

                //mainPanel.repaint()
            }
        })
        val fp = MandelbrotPainter(plane)
        val fractal = MandelbrotSet()
        fp.fractalTest = fractal::isInSet

        mainPanel.addPainter(fp)

    }
}