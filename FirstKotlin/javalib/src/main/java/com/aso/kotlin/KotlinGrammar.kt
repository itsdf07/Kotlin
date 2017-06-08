package com.aso.kotlin

/**
 * Kotlin语法学习
 *
 * Created by itsdf07 on 2017/6/7 17:31.
 * E-Mail: 923255742@qq.com
 * GitHub: https://github.com/itsdf07
 */
//1、基础语法：函数、变量、类、枚举、控制流

//顶层变量：只有var、val声明方式，如果没有指定变量的类型时，
// 在编译时由编译器自动根据赋予这个变量的值来推断这个变量的类型
val i = "1" //相当于java中 final ，值不可以更改的
var j = 2 //变量
var z: String = "这是一个String类型数据"

//顶层函数，类似java的main函数，其实也就是java的main函数，输入main可自动提示
fun main(args: Array<String>) {
    println("这个是Kotlin的main函数")
    println(test())
    println(test1())
    test2(7, "这个是函数参数")
    println("返回结果：${test3(2, 4)}")
    println("i = ${i}，j = ${j}，z = ${z}，z.length = ${z.length}")

    var person = Person("itsdf07")
    person.stature = 177
    println("name = ${person.name}, sex = ${person.sex}, stature = ${person.stature}, isMarried = ${person.isMarried}")

    test4()
    test6()
}

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
    val sex: String
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

