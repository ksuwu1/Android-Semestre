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

        printArray(names)
        println(names.joinToString())
        val numbers = arrayOf(1,2,3,4,5,6,7,8,9)
        evenOdd(numbers)
        println(getDay(3))

        val person = Person(19,"Tony");

        person.displayInformation();
}
fun add():Int{
    val x = 5
    val y = 10

    return (x+y)
}

fun product(x:Int,y:Int):Int{
    return x*y
}

fun printArray(names:Array<String>){
    for(name in names){
        print("Hello $name")

    }
}

fun evenOdd(numbers:Array<Int>){
    for(number in numbers){
        if(number%2 == 0){
            println("$number is odd")
        }else{
            println("$number is even")
        }
    }
}
fun getDay(day:Int):String{
    var name = ""
    when(day){
        1 ->  name = "Monday"
        2 -> name = "Tuesday"
        3 -> name = "Wednesday"
        4 -> name = "Thursday"
        5 -> name ="Friday"
        6 -> name= "Saturday"
        7 -> name = "Sunday"
    }
    return name;
}

class Person(val age:Int, val name:String){
    fun displayInformation(){
        println("Name: $name, Age: $age")
    }
}
//}