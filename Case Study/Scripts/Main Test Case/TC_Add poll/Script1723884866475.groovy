import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys

if (first == 'true') {
    WebUI.callTestCase(findTestCase('Test Requirements/Navigate to Chat'), [:], FailureHandling.STOP_ON_FAILURE)

    WebUI.click(findTestObject('Object Repository/Navigate To Chat Object/Page_MentorUS Dashboard/div_usergroupCuc tr chuyn chung'))
} else {
    WebUI.refresh(FailureHandling.STOP_ON_FAILURE)
}

WebUI.click(findTestObject('TC2/Page_MentorUS Dashboard/Page_MentorUS Dashboard/Page_MentorUS Dashboard/binhchon_btn'))

WebUI.setText(findTestObject('Object Repository/TC2/Page_MentorUS Dashboard/input_Cu hi bnh chn_question'), title)

WebUI.setText(findTestObject('Object Repository/TC2/Page_MentorUS Dashboard/input_La chn 1_choices.0.name'), option1)

WebUI.setText(findTestObject('Object Repository/TC2/Page_MentorUS Dashboard/input_La chn 2_choices.1.name'), option2)

WebUI.click(findTestObject('Object Repository/TC2/Page_MentorUS Dashboard/input_La chn 2_timeEnd'))

if(end=='true') {
	def calendar = Calendar.getInstance()
	def hour = calendar.get(Calendar.HOUR_OF_DAY)
	def minute = calendar.get(Calendar.MINUTE) + 1
	
	// Handle minute overflow
	if (minute == 60) {
		minute = 0
		hour = (hour + 1) % 24
	}
	
	def formattedTime = String.format("%02d%02d", hour, minute)
	time_end=formattedTime
	println "Current time (hhmm): ${time_end}"
}

if(status=='current') {
	def now = new Date()
	def formattedTime = now.format("HHmm")
	time_end = formattedTime
}

WebUI.sendKeys(findTestObject('TC2/Page_MentorUS Dashboard/input_La chn 2_timeEnd'), date_end)

WebUI.sendKeys(findTestObject('TC2/Page_MentorUS Dashboard/input_La chn 2_timeEnd'), time_end)

WebUI.click(findTestObject('TC2/Page_MentorUS Dashboard/Page_MentorUS Dashboard/button_To bnh chn'))

if (title == '') {
    WebUI.verifyTextPresent('Vui lòng nhập câu hỏi cần bình chọn', false)
} else {
    if (title.length() > 100) {
        WebUI.verifyTextPresent('Câu hỏi không được vượt quá 100 ký tự', false, FailureHandling.OPTIONAL)
    }
}

if (option1 == '') {
    WebUI.verifyTextPresent('Không được để trống bình chọn', false)
} else if (option2 == '') {
    WebUI.verifyTextPresent('Không được để trống bình chọn', false)
} else {
    if (option1 == option2) {
        WebUI.verifyTextPresent('Phương án đã tồn tại', false)
    }
    
    if ((option1.length() > 100) || (option2.length() > 100)) {
        WebUI.verifyTextPresent('Lựa chọn không được vượt quá 100 ký tự', false, FailureHandling.OPTIONAL)
    }
}

if (date_end == '') {
    WebUI.verifyTextPresent('Thời hạn phải luôn lớn hơn thời điểm hiện tại', false)
}

if (time_end == '') {
    WebUI.verifyTextPresent('Thời hạn phải luôn lớn hơn thời điểm hiện tại', false)
}

if ((status == 'current') || (status == 'past')) {
    WebUI.verifyTextPresent('Thời hạn phải luôn lớn hơn thời điểm hiện tại', false)
}

if (status == 'true') {
    WebUI.verifyElementText(findTestObject('TC3/Page_MentorUS Dashboard/poll_title'), title)

    def expect1 = option1 + '\n0'
	def expect2 = option2 + '\n0'

    WebUI.verifyElementText(findTestObject('TC3/Page_MentorUS Dashboard/poll_option1'), expect1)

    WebUI.verifyElementText(findTestObject('TC3/Page_MentorUS Dashboard/poll_option2'), expect2)
}

if (end == 'true') {
    WebUI.closeBrowser()
}

