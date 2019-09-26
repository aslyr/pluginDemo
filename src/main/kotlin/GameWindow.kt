import business.Blockable
import business.Moveable
import cn.hutool.core.date.DateTime
import cn.hutool.core.date.DateUtil
import cn.hutool.core.io.FileUtil
import enums.Directron
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import org.itheima.kotlin.game.core.Window
import java.util.concurrent.CopyOnWriteArrayList

class GameWindow(title: String = "gaokai", icon: String = "map/map1",width:Int=Config.windowWidth, height: Int = Config.windowHeight) :
    Window(title, icon, width, height) {
    //array线程不安全，CopyOnWriteArrayList线程安全
    private val viewlist = CopyOnWriteArrayList<View>()
    private lateinit var tank: Tank
    override fun onCreate() {

        var map = FileUtil.readLines("map/map1", charset("utf-8"))
        map.forEachIndexed { lineindex, line ->
            println("$lineindex+$line")
            line.forEachIndexed() { index, char ->
                when (char) {
                    '砖' -> {
                        viewlist.add(Wall(index * Config.width, lineindex * Config.height))
                        println("${lineindex}行+$index")
                    }
                    '草' -> {
                        viewlist.add(Grass(index * Config.width, lineindex * Config.height))
                    }
                    '铁' -> {
                        viewlist.add(Iron(index * Config.width, lineindex * Config.height))
                    }
                    '水' -> {
                        viewlist.add(Water(index * Config.width, lineindex * Config.height))
                    }
                    '敌'->{
                        viewlist.add(EnemyTank(index * Config.width, lineindex * Config.height))
                    }
                }
            }
        }
        //绘制坦克
        tank = Tank(Config.width * 12, Config.height *8)
        viewlist.add(tank)

    }

    override fun onDisplay() {

        viewlist.filter {
            it.x < 0 || it.x > Config.windowWidth || it.y < 0 || it.y > Config.windowHeight || it.isDestory
        }.forEach { view ->
            viewlist.remove(view)
        }

        viewlist.forEach {
            it.draw()
        }
    }

    override fun onKeyPressed(event: KeyEvent) {

        when (event.code) {

            KeyCode.W -> {
                tank.move(Directron.UP)

            }
            KeyCode.S -> {
                tank.move(Directron.DOWN)

            }
            KeyCode.A -> {
                tank.move(Directron.LEFT)

            }
            KeyCode.D -> {
                tank.move(Directron.RIGHT)

            }
            KeyCode.ENTER -> {
                var bullets = tank.fire()
                viewlist.add(bullets)
            }

        }
    }

    override fun onRefresh() {
        //放置耗时业务逻辑

        viewlist.filter { it is Moveable }.forEach { move ->
            move as Moveable
            viewlist.filter { it is Blockable && it!=move }.forEach block@{ block ->
                block as Blockable
                if (move.iscollison(block)) {
                    move.notifyCollison(move.currentDirectron)
                    return@block
                }
            }

        }
        viewlist.filter { it is AutoMove }.forEach { auto ->
            (auto as AutoMove).autoMove()
        }
        viewlist.filter { it is Attackable }.forEach {
            attack->
            attack as Attackable
            viewlist.filter { it is Surrferable && it !=attack }.forEach surrfer@{
                surrfer->
                surrfer as Surrferable
                //判断是否碰撞
                if (attack.isCollison(surrfer)){
                    //通知碰撞体发生碰撞了
                    attack.notifyAttack(surrfer)
                    surrfer.notifySurrfer(attack)
                    return@surrfer
                }

            }
            //判断是否有自动射击功能
            viewlist.filter { it is AutoShot }.forEach{
                it as AutoShot
                it.autoShot()?.let {
                    bullets ->  viewlist.add(bullets)
                }
            }
        }
    }
}

