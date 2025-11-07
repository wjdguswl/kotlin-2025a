package com.appweek06

import android.graphics.Color
import java.util.*

// 학생 목록(Student List) 모드에서 한 명의 학생을 표현하는 데이터 구조
data class Student(
    val name: String,
    val id: String = UUID.randomUUID().toString(), // UUID 자동생성
    val addedDate: Date = Date()
)

// 쇼핑카트(Shopping Cart) 모드에서 하나의 상품을 표현
data class CartItem(
    val name: String,
    var quantity: Int = 1,
    val price: Double,
    val id: String = UUID.randomUUID().toString(),
    val addedDate: Date = Date()
) {
    fun getTotalPrice(): Double = quantity * price // 총 가격 계산

    override fun toString(): String {
        return "$name (x$quantity) - $%.2f".format(getTotalPrice())
    }
}

// 할 일(Task Manager) 모드에서 하나의 작업을 표현하는 구조
data class Task(
    val title: String,
    val description: String = "",
    var isCompleted: Boolean = false,
    val priority: TaskPriority,
    val dueDate: Date? = null,
    val id: String = UUID.randomUUID().toString(),
    val createdDate: Date = Date()
) {
    override fun toString(): String {
        val status = if (isCompleted) "✓" else "○"
        val priorityIcon = when (priority) {
            TaskPriority.HIGH -> "!!!"
            TaskPriority.MEDIUM -> "!!"
            TaskPriority.LOW -> "!"
        }
        return "$status $priorityIcon $title"
    }
}

// 할 일(Task)의 우선순위를 정의하는 열거형
enum class TaskPriority(val displayName: String, val color: Int){
    HIGH("High", Color.RED),
    MEDIUM("Medium", Color.BLUE),
    LOW("Low", Color.GREEN)
}

// 앱의 현재 모드(화면)가 어떤 건지를 나타내는 열거형
enum class AppMode(val displayName: String) {
    STUDENT_LIST("Student List"),
    SHOPPING_CART("Shopping Cart"),
    TASK_MANAGER("Task Manager"),
}