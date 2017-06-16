package com.aso.kotlin

/**
 * Kotlin语法学习
 * 1、基础语法：函数、变量、类、枚举、控制流
 * 2、基础语法：集合、扩展函数、扩展属性、vararg、infix、析构声明
 * PS:
 * 1、多个Kotlin文件中可以重载，定义同名函数（包括函数名+参数）时，作用域不能使用默认的public，只能是private
 *
 * Created by itsdf07 on 2017/6/7 17:31.
 * E-Mail: 923255742@qq.com
 * GitHub: https://github.com/itsdf07
 */

//顶层函数，类似java的main函数，其实也就是java的main函数，输入main可自动提示
fun main(args: Array<String>) {
    //1、基础语法：函数、变量、类、枚举、控制流
//    grammar1()
    //2、基础语法：集合、扩展函数、扩展属性、vararg、infix、析构声明
    grammar2()
}

/**
 * 1、基础语法：函数、变量、类、枚举、控制流
 */
fun grammar1() {
    println("这个是Kotlin的main函数")
    println(test())
    println(test1())
    test2(7, "这个是函数参数")
    println("返回结果：${test3(2, 4)}")
    println("i = ${i}，j = ${j}，z = ${z}，z.length = ${z.length}")

    var person = Person("itsdf07")
    person.stature = 177
    println("name = ${person.name}, gender = ${person.gender}, stature = ${person.stature}, isMarried = ${person.isMarried}")

    test4()
    test6()
}

/**
 * 2、基础语法：集合、扩展函数、扩展属性、vararg、infix、析构声明
 */
fun grammar2() {
    collection()
    derivativeFun()
    varargFun(5, 4, 3, 2, 1)
    var person = Person("itsdf07")
    println("扩展函数返回值：result = ${person.showInfo()}")
    println("扩展属性值：idCard = ${person.idCard}")
}

//------------------------------------------------------------------------------------------
//1、基础语法：函数、变量、类、枚举、控制流

//顶层变量：只有var、val声明方式，如果没有指定变量的类型时，
// 在编译时由编译器自动根据赋予这个变量的值来推断这个变量的类型
val i = "1" //相当于java中 final ，值不可以更改的
var j = 2 //变量
var z: String = "这是一个String类型数据"

fun test(): String {
    return "这个是Kotlin函数的return内容"
}

fun test1() = "声明函数后直接返回相应结果"

fun test2(x: Int, y: String) {
    println("x:${x},y:${y}")
}

//函数主体换成表达式主体，把if当做是有返回值的表达式，
// 在该函数被调用时，就会执行该if表达式并返回结果,
//并且这种 = if 方式是可以忽略返回类型的
fun test3(x: Int, y: Int) = if (x > y) {
    x
} else {
    y
}

fun test4() {
    //Kotlin中是没有switch关键字，而是使用when
    //when带参数
    when (Color.BLUE) {
    //枚举常量 -> 相当于java中的case xxx: break;
        Color.RED -> println("红色")
    //如果枚举变量后面没有带 -> ,那么就类似java中的case没有写break
        Color.BLUE,
        Color.YELLOW -> println("蓝色或者黄色")
    }

    //when 不带参数，就可以在when里面写任意表达式，比如以下中类似java中的if(){...} else if(){...}写法
    when {
        test3(2, 4) == 2 -> println("test3(2,4) = ${test3(2, 4)}")
        test3(2, 4) == 4 -> println("test3(2,4) = ${test3(2, 4)}")
        test3(2, 4) == 0 -> println("test3(2,4) = ${test3(2, 4)}")
    }

    println("test5(2, 4) = ${test5(2, 4)}")
}

fun test5(x: Int, y: Int) = when {
    x == y -> 0
    else -> {
        when {
            x > y -> x
            else -> y
        }
    }
}

fun test6() {
    //6次循环，打印出0、1、2、3、4、5
    val rang: IntRange = 0..5
    //简单的全部循环
    for (i in rang) {
        println("i = ${i}")
    }
    println("rang 0..5 全部循环完成了")

    //循环过程中处理事件，如跳出 break
    for (i in rang) {
        println("i = ${i}")
        if (i == 2) {
            println("rang 0..5 跳出 i= ${i} 循环完成了")
            break
        }
    }
    //循环过程中处理事件，如跳过 continue
    for (i in rang) {
        if (i == 2) {
            println("rang 0..5 跳过 i= ${i} 循环完成了")
            continue
        }
        println("i = ${i}")
    }

    //Kotlin也是支持goto的
    //在Kotlin中任何表达式都可以用label{:.keyword } （标签）来标记。label的格式是被'@'标识符标记，例如：aso@, dfsu@都是有效的label：
    //如果有多重循环，带标签的break会跳转出标签所指示的那一个循环。带标签的continue会跳转到标签所指向的那个循环,进入该循环的下一次循环。
    for (i in rang) {
        aso@ for (j in rang) {
            if (i == 3) {
                println("rang 0..5 跳过 i= ${i} 循环完成了")
                break@aso
            }
            println("i = ${i}, j = ${j}")
        }

    }
}

class Person(var name: String?) {
    //val 只有get获取器
    val gender: String
        get() = "男"

    var stature: Int = 0
        get() = field
        set(value) {
            field = value
        }

    var isMarried: Boolean = true
        get() = field
        set(isMarried) {
            //field 就表示isMarried，但是当使用field时，就需要给变量指定一个默认值
            field = isMarried
        }
}

//enum 软关键字，为什么是软关键字？只有跟在class前面才是关键字，否则不是关键字
enum class Color(val r: Int, val g: Int, val b: Int) {
    RED(255, 0, 0),
    BLUE(0, 0, 255),
    YELLOW(255, 255, 0)
}

//------------------------------------------------------------------------------------------
//2、基础语法：集合、扩展函数、扩展属性、vararg、infix、析构声明
/**
 * 集合数据源:同样也有arrayListOf()
 */
fun getListCollection() = listOf(1, 2, 3)//listOf可以传递任意长度的参数，类似java的可变长度的参数，如 String...

/**
 * 集合
 */
fun collection() {
    val list = getListCollection()
    val listFirst = list[0]//获取集合内第一个元素
    val listLast = list.last()//获取集合内最后一个元素
    println("listFirst = ${listFirst}, listLast = ${listLast}")

    //list集合遍历
    list.forEach {
        //方式一：直接遍历，使用it条目变量
        println("集合的遍历方式：forEach -> it = ${it}")//it为遍历时赋予的条目
        //方式二：item为自定义的条目变量名，也就是说不一定是item，也可以是xxx
//        item ->
//        println("集合的遍历方式：forEach -> it = ${item}")//it为遍历时赋予的条目
    }
    for (i in list) {
        println("集合的遍历方式：for -> i = $i")//i为遍历时赋予的条目
    }

    list.forEachIndexed {
        index, i ->
        println("集合的遍历方式：forEachIndexed -> index = ${index}, i = ${i}")//i为遍历时赋予的条目
    }

    /**
     * map映射集合:查看mapOf参数是一个Pair<K, V>，下面mapOf参数为:
     * 1 to "key1" //中缀调用的方式：infix
     * 1.to("key1") //普通调用的方式
     */
    val map = mapOf(1 to "value1", 2.to("value2"), "key3" to "value3")

    println("map映射集合：mapOf -> map[1] = ${map[1]}, map['key3'] = ${map["key3"]}")
    val pair = "key4" with "value4"
    map[pair]
    println("map映射集合的参数追加 -> map['key4'] = ${map["key4"]}")
    val (key, value) = pair//析构声明
    println("析构声明 -> key = ${key}, value = ${value}")
    val compile = "com.android.tools.build:gradle:2.2.2"
    val (group, name, version) = compile.split(":")
    println("析构声明 -> group = $group, name = $name, version = $version")
//
}

/**
 * 扩展函数:derivative 衍生
 */
fun derivativeFun() {
    testFun1()//不带参数，使用参数默认值
    testFun1(9, "我是一个参数：testFun1")//常规带参
    testFun1(6)//按顺序，只赋值部分参数
    testFun1(str = "调用testFun1时只传了str参数")//指定参数赋值，这种方式，可以使参数无序掉，这种方法可以避免大量的重载方法
}

/**
 * 扩展函数
 */
inline fun Person.showInfo(): String {
    return "inline关键字 Person.showInfo() idCard = ${name}"
}

/**
 * 扩展属性：身份证
 */
inline val Person.idCard: String
    get() = "3502121989xxxxxxxx"

/**
 *
 * vararg 伪关键字
 * 创建可变长度的方法，类似java的写法：String ...
 */
fun varargFun(vararg item: Int) {
    println("vararg伪关键字 varargFun：item.size = ${item.size}")
    //item就是一个数组
    item.forEach {
        println("vararg伪关键字 varargFun：item = ${it}")
    }
}

/**
 * 对参数设置默认值：调用时，
 * 1、可以传递所有参数，也可以不传递任何参数，则使用默认值
 * 2、可以传递其中的一个或者多个值进行设置
 */
fun testFun1(i: Int = 7, str: String = "这是扩展函数测试案例：testFun1") {
    println("i:${i}, str:${str}")
}

/**
 * infix：中缀调用方式，中缀调用方式的中缀函数
 * A.with本是一个普通的泛型扩展方法，但是使用了infix关键字指定函数，所以with就可以使用中缀调用的方式
 */
infix fun <A, B> A.with(that: B): Pair<A, B> = Pair(this, that)
