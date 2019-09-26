import business.Blockable
import org.itheima.kotlin.game.core.Composer
import org.itheima.kotlin.game.core.Painter

class Iron (override var x: Int, override var y: Int) :View,Blockable,Surrferable{
    override fun BoomFx() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override var blood: Int=100

    override fun notifySurrfer(attack: Attackable) {
        blood -= attack.power
        Composer.play("sound/hit.wav")
        BoomFx()

    }

    override var isDestory: Boolean=false

    override var width=Config.width
    override var height=Config.height;
    override fun draw() {
        Painter.drawImage("img/steel.gif",x,y)
    }

}