import business.Blockable
import business.Moveable
import enums.Directron
import org.itheima.kotlin.game.core.Painter

class Bullets : AutoMove, Attackable {
    override var power: Int=1
    override var isDestory: Boolean=false


    override fun isCollison(surrferable: Surrferable): Boolean {
        //判断碰撞关键代码，中心点距离判断。
        var ml = this
        var bl = surrferable
        val blCenterX = bl.x + bl.width / 2
        val blCenterY = bl.y + bl.height / 2
        val mlCenterx = x + ml.width / 2
        val mlCenterY = y + ml.height / 2
        if (Math.abs(blCenterX - mlCenterx) < (ml.width + bl.width) / 2 && Math.abs(blCenterY - mlCenterY) < (ml.height + bl.height) / 2) {
            return true
        } else {
            return false
        }
    }

    override fun notifyAttack(surfer: Surrferable) {
        this.isDestory=true
    }

    override var currentDirectron: Directron = Directron.UP


    override var speed: Int = 8


    override fun autoMove() {
        when (currentDirectron) {
            Directron.UP -> {
                this.y -= speed
            }
            Directron.DOWN -> {
                this.y += speed
            }
            Directron.LEFT -> {
                this.x -= speed
            }
            Directron.RIGHT -> {
                this.x += speed
            }
        }
    }

    override var x: Int = 0
    override var y: Int = 0
    override var width: Int = Config.bulletWidth
    override var height: Int = Config.bulletHeight

    override fun draw() {
        var imgPath = when (currentDirectron) {
            Directron.UP -> "img/shot_bottom.gif"
            Directron.DOWN -> "img/shot_top.gif"
            Directron.LEFT -> "img/shot_right.gif"
            Directron.RIGHT -> "img/shot_left.gif"
        }

        Painter.drawImage(imgPath, x, y)


    }


    override fun iscollison(blockable: Blockable): Boolean {
        return false
    }

    override fun notifyCollison(currentDirectron: Directron) {
        
    }


}
