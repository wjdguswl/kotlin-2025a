package com.kotlinbasics

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kotlinbasics.ui.theme.KotlinBasicsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KotlinBasicsTheme {
                Scaffold( modifier = Modifier.fillMaxSize() ) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
        // week02Variables()
        // week02Functions()
        week03classes()
        // week03Collections()
    }
}

private fun week03Collections(){
    println("== Kotlin Collections ==")

    val fruits = listOf("apple", "banana", "orange") // 불변 리스트
    val mutableFruits = mutableListOf("kiwi", "watermelon") // 가변 리스트

    println("Fruits : $fruits")
    mutableFruits.add("strawberry")
    println("Mutable fruits : $mutableFruits")

    val scores = mapOf("Kim" to 100, "Park" to 97, "Lee" to 99) // mapOf : Key-Value 구조, 불변
    println("Scores : $scores")

    for(fruit in fruits){
        println("I like $fruit")
    }

    scores.forEach{(name, score) -> println("$name scored $score")}
}

private fun week03classes(){
    Log.d("Kotlinweek03", "== Kotlin Classes ==")

    class Person(val name: String, var age: Int){ // val : 읽기 전용, var : 변경 가능
        fun introduce(){
            Log.d("Kotlinweek03", "안녕하세요, $name ($age 세)입니다.")
        }
        fun birthday(){
            age++
            Log.d("Kotlinweek03", "$name 의 생일! 이제 $age 세 입니다.")
        }
    }
    val person1 = Person("홍길동", 27)
    person1.introduce()
    person1.birthday()

    open class Animal(var species: String){
        var weight: Double = 0.0
        constructor(species: String, weight: Double) : this(species){ // 보조 생성자 정의
            this.weight = weight
            Log.d("KotlinWeek03", "$species 의 무게 : $weight kg")
        }
        open fun makeSound(){ // open : 상속 허용
            Log.d("KotlinWeek03", "$species 가 소리를 냅니다.")
        }
    }
    val puppy = Animal("강아지", 10.5)
    puppy.makeSound()

    class Dog(species: String, weight: Double, val bread: String) : Animal(species, weight){ // Dog → Animal 상속
        override fun makeSound(){ // override → 부모 함수 재정의
            Log.d("KotlinWeek03", "$bread($species)가 멍멍 짖습니다.")
        }
    }
    val dog = Dog("개", 12.5, "골든 리트리버")
    dog.makeSound()

    data class Book(val title: String, val author: String, val pages: Int) // data class : 값 비교, toString(), copy() 등 자동 생성

    val book1 = Book("코틀린 입문", "Kim", 400)
    val book2 = Book("코틀린 입문", "Kim", 400)

    Log.d("KotlinWeek03", "book1 == book2 : ${book1 == book2}")
    Log.d("KotlinWeek03", "book1: $book1")
}

private fun week02Functions(){
//    println("Week 02: Functions")
//
//    fun greet(name: String) = "Hello, $name!"
//
//    println(greet("Android developer"))

    println("== Kotlin Functions ==")

    fun greet(name: String): String { // : String → 반환 타입이 String이라는 뜻
        return "Hello, $name!"
    }

    fun add(a: Int, b: Int) = a + b

    fun introduce(name: String, age: Int = 19){ // 기본값 19, age 생략 시 19 적용
        println("My name is $name and I'm $age years old")
    }

    println(greet("Kotlin"))
    println("Sum: ${add(5, -71)}")
    introduce("Kim", 7)
    introduce("Park")
}

private fun week02Variables(){
    println("Week 02: Variables")

    val courseName = "Mobile Programming"
    //courseName = "IoT Programming"
    var week = 1
    week = 2
    println("Course : $courseName")
    println("Week : $week")
//    println("Week 02: Variables")
//
//    val courseName = "Mobile Programming"
//    //courseName = "IoT Programming"
//    var week = 1
//    week = 2
//    println("Course : $courseName")
//    println("Week : $week")

    println("== Kotlin Variables ==")

    // val(immutable) vs var(mutable)
    val name = "Android"
    var version = 8

    println("Hello $name $version")

    val age: Int = 24
    val height: Double = 177.7
    val isStudent: Boolean = false

    println("Age: $age, Height: $height, Student: $isStudent")

    //var nickname: String = null
    var nickname: String? = null // nullable 타입 : ?가 붙으면 null 허용
    nickname = "mirae"
    println("Nickname: $nickname ${nickname?.length}")
    // -> nickname?.length : 안전 호출 연산자(?.), nickname이 null이면 null 반환, null이 아니면 length 값 반환
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!", // 안드로이드 화면에 출력
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KotlinBasicsTheme {
        Greeting("Android")
    }
}