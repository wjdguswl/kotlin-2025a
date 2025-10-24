package com.appweek05

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.motion.widget.KeyPosition
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    // UI component
    private lateinit var buttonAdd: Button
    private lateinit var buttonClear: Button
    private lateinit var listView: ListView
    private lateinit var editTextStudent: EditText
    private lateinit var textViewCount: TextView
    // Collection
    private lateinit var studentList: ArrayList<String>
    private lateinit var adapter: ArrayAdapter<String>

    companion object {
        private const val TAG = "KotlinWeek05App"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate: AppWeek05 started")

        setupViews() // 뷰 연결 및 컬렉션 초기화
        setupListView() // 어댑터 설정
        setupListeners() // 이벤트 리스너 등록

        addInitialData() // 샘플 데이터 추가
    }

    private fun setupViews(){
        // XML에 선언된 뷰들과 Kotlin 변수 연결
        listView = findViewById(R.id.listViewStudents)
        editTextStudent = findViewById(R.id.editTextStudent)
        buttonClear = findViewById(R.id.buttonClear)
        buttonAdd = findViewById(R.id.buttonAdd)
        textViewCount = findViewById(R.id.textViewCount)

        studentList = ArrayList() // 학생 컬렉션 초기화(빈 리스트)
        Log.d(TAG, "Views initialized")
    }
    private fun setupListView(){
        // ArrayAdapter: 데이터 컬렉션(studentList)을 ListView가 보여줄 수 있는 뷰(항목)으로 바꿔주는 중간 매개체
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, studentList)
        listView.adapter = adapter // ListView와 어댑터를 연결

        Log.d(TAG, "ListViews and Adapter setup completed")
    }
    private fun setupListeners(){
        buttonAdd.setOnClickListener {
            addStudent()
        }
        buttonClear.setOnClickListener {
            clearAllStudents()
        }
        listView.setOnItemLongClickListener{ // 항목 롱프레스
                _, _, position, _ -> removeStudent(position) // position : 리스트 안에서 현재 눌린 항목의 순서(index)
            true
        }
        listView.setOnItemClickListener { _, _, position, _ ->
            val studentName = studentList[position]
            Toast.makeText(
                this, // 현재 액티비티
                "Selected: $studentName (Position: ${position+1})", // 표시할 문자열
                Toast.LENGTH_SHORT // 표시 시간 : 짧게
            ).show() // 실제로 화면에 띄움
            Log.d(TAG, "Selected: $studentName at position $position")
        }
        Log.d(TAG, "Event listeners setup completed")
    }

    private fun addStudent(){ // 학생 추가
        val studentName = editTextStudent.text.toString().trim()

        if(studentName.isEmpty()){ // 입력이 빈 문자열일 경우
            Toast.makeText(this, "Please enter a student name", Toast.LENGTH_SHORT).show()
            Log.d(TAG, "Attempted to add empty student name")
            return
        }
        if(studentList.contains(studentName)){ // 이미 존재하는 이름일 경우
            Toast.makeText(this, "Student '$studentName' already exists", Toast.LENGTH_SHORT).show()
            Log.d(TAG, "Attempted to add duplicate student : $studentName")
            return
        }

        // 검증 통과할 경우
        studentList.add(studentName) // 컬렉션에 이름 추가
        adapter.notifyDataSetChanged() // 어댑터에 변경 알림 -> UI 갱신
        editTextStudent.text.clear() // 입력 칸 비움
        updateStudentCount() // 화면의 총 수 갱신

        Toast.makeText(this, "Added: $studentName", Toast.LENGTH_SHORT).show()
        Log.d(TAG, "Added student: $studentName (Total: ${studentList.size})")
    }
    private fun clearAllStudents(){ // 전체 삭제
        if(studentList.isEmpty()){ // 리스트가 비어있는 경우
            Toast.makeText(this, "List is already empty", Toast.LENGTH_SHORT).show()
            return
        }

        // 리스트가 비어있지 않는 경우
        val count = studentList.size // 현재 크기를 count에 저장
        studentList.clear() // 리스트 내용 모두 삭제
        adapter.notifyDataSetChanged() // UI 갱신
        updateStudentCount() // 총 수 업데이트

        Toast.makeText(this, "Cleared all $count students", Toast.LENGTH_SHORT).show()
        Log.d(TAG, "Cleared all students (Total cleared: $count)")
    }
    private fun removeStudent(position: Int){ // 개별 삭제
        if(position >= 0 && position < studentList.size){ // 포지션 유효성 검사
            val removedStudent = studentList.removeAt(position) // 항목 제거
            adapter.notifyDataSetChanged()
            updateStudentCount()

            Toast.makeText(this, "Removed: $removedStudent", Toast.LENGTH_SHORT).show()
            Log.d(TAG, "Removed student : $removedStudent (Remaining: ${studentList.size})")
        }
    }

    private fun updateStudentCount(){ // 텍스트 갱신
        textViewCount.text = "Total Students : ${studentList.size}" // textViewCount의 텍스트를 현재 학생 수로 바꿔줌
    }

    private fun addInitialData(){ // 앱 시작 시 기본 샘플 데이터 3개 추가
        val initialStudents = listOf("Kim", "Lee", "Park")
        studentList.addAll(initialStudents)
        adapter.notifyDataSetChanged()
        updateStudentCount()

        Log.d(TAG, "Added initial data: $initialStudents")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: Current student count: ${studentList.size}")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: Saving state with ${studentList.size} students")
    }
}