package com.kotlinbasics

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class) // 이 클래스의 테스트를 안드로이드 환경에서 JUnit4 방식으로 실행하겠다는 뜻
class ExampleInstrumentedTest {
    @Test // JUnit에게 이 함수가 테스트 함수임을 알려줌
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext // 실제 안드로이드 기기/에뮬레이터에서 앱의 Context를 가져옴
        assertEquals("com.kotlinbasics", appContext.packageName) // 가져온 Context의 패키지 이름이 "com.kotlinbasics"인지 확인
    }
}