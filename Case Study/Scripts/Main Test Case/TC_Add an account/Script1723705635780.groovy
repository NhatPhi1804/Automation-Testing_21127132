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
    WebUI.callTestCase(findTestCase('Test Requirements/Navigate to Account Management'), [:], FailureHandling.STOP_ON_FAILURE)
} else {
    WebUI.back()

    WebUI.click(findTestObject('Page_MentorUS Dashboard/account_management_btn_nav'))
}

WebUI.click(findTestObject('Object Repository/TC1/Page_MentorUS Dashboard/p_Thm'))

WebUI.setText(findTestObject('TC1/Page_MentorUS Dashboard/input_()_rt'), fullname)

WebUI.setText(findTestObject('Object Repository/TC1/Page_MentorUS Dashboard/input_()_ru'), email)

WebUI.click(findTestObject('TC1/Page_MentorUS Dashboard/input_()_r17'))

WebUI.clearText(findTestObject('TC1/Page_MentorUS Dashboard/input_()_r17'))

WebUI.sendKeys(findTestObject('TC1/Page_MentorUS Dashboard/input_()_r17'), Keys.chord(Keys.COMMAND, 'a'))

WebUI.sendKeys(findTestObject('TC1/Page_MentorUS Dashboard/input_()_r17'), Keys.chord(Keys.DELETE))

if (role != '') {
    WebUI.click(findTestObject('Object Repository/TC1/Page_MentorUS Dashboard/Test/Page_MentorUS Dashboard/input_()_rv (1)'))

    WebUI.sendKeys(findTestObject('TC1/Page_MentorUS Dashboard/Test/Page_MentorUS Dashboard/input_()_rv'), Keys.chord(Keys.ARROW_DOWN))

    WebUI.sendKeys(findTestObject('TC1/Page_MentorUS Dashboard/Test/Page_MentorUS Dashboard/input_()_rv'), Keys.chord(Keys.ARROW_DOWN))

    WebUI.sendKeys(findTestObject('TC1/Page_MentorUS Dashboard/Test/Page_MentorUS Dashboard/input_()_rv'), Keys.chord(Keys.ARROW_DOWN))

    WebUI.sendKeys(findTestObject('TC1/Page_MentorUS Dashboard/Test/Page_MentorUS Dashboard/input_()_rv'), Keys.chord(Keys.ENTER))
}

WebUI.click(findTestObject('Object Repository/TC1/Page_MentorUS Dashboard/button_checkXc nhn'))

if (status == 'true') {
	WebUI.verifyElementText(findTestObject('TC1/Page_MentorUS Dashboard/account_email'), email)
    WebUI.verifyElementText(findTestObject('TC1/Page_MentorUS Dashboard/account_fullname'), fullname)
} else if (status == 'false') {
    if (fullname == '') {
        WebUI.verifyTextPresent('Họ và tên không được rỗng', false)
    } else if (fullname.length() > 100) {
        WebUI.verifyTextPresent('Họ và tên không được vượt quá 100 kí tự.', false)
    }
    
    if (email == '') {
        WebUI.verifyTextPresent('Email không được rỗng', false, FailureHandling.STOP_ON_FAILURE)
    }
    
    if (role == '') {
        WebUI.verifyTextPresent('Vui lòng chọn ít nhất 1 giá trị', false, FailureHandling.STOP_ON_FAILURE)
    }
} else if (status == 'EXIST') {
	WebUI.delay(2)
	WebUI.verifyTextPresent('Tài khoản đã tồn tại!', false)

} else if (status == 'INVALID_EMAIL') {
	WebUI.delay(2)
	WebUI.verifyTextPresent('Email không hợp lệ!', false)
	
} else if (status == 'INVALID_NAME') {
    WebUI.delay(2)
	WebUI.verifyTextPresent('Tên không hợp lệ!', false)
}

if (end == 'true') {
    WebUI.closeBrowser()
}

