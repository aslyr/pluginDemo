import business.Blockable
import kotlinx.coroutines.delay
import org.itheima.kotlin.game.core.Composer
import org.itheima.kotlin.game.core.Painter
import kotlin.concurrent.thread

class Wall(override var x: Int, override var y: Int) : View, Blockable, Surrferable {
    override fun BoomFx() {
        var boomimgPath = arrayListOf<String>()

        for (index in 1..32) {
            Painter.drawImage("img/blast_$index.png", x, y)

        }



    }

    override var blood: Int = 3
    override var isDestory: Boolean = false

    override fun notifySurrfer(attack: Attackable) {
        blood -= attack.power
        Composer.play("sound/hit.wav")
        BoomFx()
    }

    override var width = Config.width
    override var height = Config.height;
    override fun draw() {
        if (blood <= 0) {
            isDestory = true
            return
        }
        Painter.drawImage("img/wall.gif", x, y)
    }

}