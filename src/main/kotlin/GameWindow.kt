import business.Blockable
import business.Moveable
import cn.hutool.core.date.DateTime
import cn.hutool.core.date.DateUtil
import cn.hutool.core.io.FileUtil
import enums.Directron
import javafx.scene.input.KeyCode
import javafx.scene.input.KeyEvent
import org.itheima.kotlin.game.core.Window

class GameWindow(title: String="gaokai", icon: String="map/map1", width: Int=64*13, height: Int=63*9) : Window(title, icon, width, height) {
    private val viewlist= arrayListOf<View>()
    private lateinit var tank:Tank
    override fun  onCreate() {

        var map= FileUtil.readLines("map/map1", charset("utf-8"))
        map.forEachIndexed {
            lineindex,line->
            println("$lineindex+$line")
            line.forEachIndexed(){
                index,char->
                when(char){
                    '砖'->{
                        viewlist.add(Wall(lineindex*Config.width,index*Config.height))
                    }
                    '草'->{
                        viewlist.add(Grass(lineindex*Config.width,index*Config.height))
                    }
                    '铁'->{
                        viewlist.add(Iron(lineindex*Config.width,index*Config.height))
                    }
                    '水'->{
                        viewlist.add(Water(lineindex*Config.width,index*Config.height))
                    }
                }
            }
        }
        //绘制坦克
        tank=Tank(Config.width*12,Config.height*7)
        viewlist.add(tank)

    }

    override fun onDisplay() {
        viewlist.forEach{
           view->view.draw()
        }


    }

    override fun onKeyPressed(event: KeyEvent) {

            when(event.code){

                KeyCode.W->{
                    tank.move(Directron.UP)
                    println("按下上键")
                }
                KeyCode.S-> {
                    tank.move(Directron.DOWN)
                    println("按下下键")
                }
                KeyCode.A-> {
                    tank.move(Directron.LEFT)
                    println("按下左键")
                }
                KeyCode.D-> {
                    tank.move(Directron.RIGHT)
                    println("按下右键")
                }
            }
    }

    override fun onRefresh() {
        //放置耗时业务逻辑

        viewlist.filter { it is Moveable }.forEach {
            move ->
            move as Moveable
            viewlist.filter { it is Blockable }.forEach block@{
                block ->
                block as Blockable
                if(move.iscollison(block)){
                    move.notifyCollison(move.currentDirectron)
                   return@block
                }
            }

        }

    }
}

