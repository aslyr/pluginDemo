import cn.hutool.core.io.FileUtil
import javafx.application.Application
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import javafx.scene.paint.Color
import org.itheima.kotlin.game.core.Composer
import org.itheima.kotlin.game.core.Painter
import org.itheima.kotlin.game.core.Window
import java.io.File

class MyWindow : Window() {

    override fun onCreate() {
        println(File("").canonicalPath+"\\1.jpg")
    }

    override fun onDisplay() {

            Painter.drawImage("img/tank_d.gif",0,0)
            Painter.drawColor(Color.BLUE,50,100,40,32)

            Painter.drawText("你好，我是坦克世界",30,30,Color.CYAN)
    }

    override fun onKeyPressed(event: KeyEvent) {
        when(event.code){
            KeyCode.A->
                Composer.play("sound/fire.wav")


        }
    }

    override fun onRefresh() {
    }
}

fun main() {
    Application.launch(MyWindow()::class.java)
}