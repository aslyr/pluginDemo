import business.Blockable
import org.itheima.kotlin.game.core.Painter

class Iron (override var x: Int, override var y: Int) :View,Blockable{
    override var width=Config.width
    override var height=Config.height;
    override fun draw() {
        Painter.drawImage("img/steel.gif",x,y)
    }

}