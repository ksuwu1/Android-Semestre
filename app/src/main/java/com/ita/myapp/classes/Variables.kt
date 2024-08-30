package com.ita.myapp.classes

//class Variables {
    fun main(){
        //Numeric Variables
        val age:Int = 20
        val long_number:Long = 98765431
        val temperature:Float = 27.123f
        val weight:Double = 60.4

        //String
        val gender:Char = 'M'
        val name:String ="Tony Castillo"


        //Boolean
        val isGreater:Boolean = false

        //Array
        val names = arrayOf("Erick","Silvia","Hector","Gabriela")
        println(age)
        println(long_number)
        println(temperature)
        println(weight)
        println(gender)
        println(name)
        println(isGreater)
        println(names.get(0))

        println("Welcome $name, to your first Kotlin project")

        println(add())
        println(product(5,2))

    }
fun add():Int{
    val x = 5
    val y = 10

    return (x+y)
}

fun product(x:Int,y:Int):Int{
    return x*y
}
//}