package dev.icerock.redwoodapp.screens.demo.navigation

object Screens {
    const val LOGIN = "LOGIN"
    const val MAIN = "MAIN"
    const val TABS = "TABS"
    const val PROFILE = "PROFILE"
    const val TEST_LIST = "TEST_LIST"
    const val HR = "HR"
    const val ONBOARDING = "ONBOARDING"
    const val TEST_STEP = "TEST_STEP/{testId}"
    fun testStep(testId: Int) = "TEST_STEP/${testId}"
    const val TEST_FINAL = "TEST_FINAL"
    const val TOGGLE = "TOGGLE"
}