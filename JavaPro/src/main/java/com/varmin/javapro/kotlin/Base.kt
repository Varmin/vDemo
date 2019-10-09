package com.varmin.javapro.kotlin

/**
 * on 2019-10-09  10：37.
 */

/**
 * 10/9
 * ?空安全:可以为null
 *    var ableNull: Int? = null
 *    整数类型：自动装箱
 *    var a = 1; var b = 1; a === b;//true，a、b都是int类型
 *    var a: Int? = 2; var b: Int? = 2; a === b; //true， a、b都是Integer类型,按道理不相等，但是由Java的Integer类决定的（-128 ~ 127之间放到IntegerCache常量池中static，a、b取的是同一个对象）
 *    var a: Int? = 200; var b: Int? = 200; a === b; //false， a、b都是Integer类型
 * 安全调用：
 *  可空类型，不允许直接调用方法、属性；先判断后使用、安全调用
 *  Elvis --> if else: ?:
 * 强制调用：!!.
 *
 */
class Base {
    fun test() {
        val nu = null
        var a: String? = "aaa"
        println(a!!.length)
        a = nu
        if(a != null){
            println(a.length)
        }
        println(a?.length)
        val bb = a?.length ?: "this is null"
        println(bb)
    }
}