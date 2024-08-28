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

    WebUI.click(findTestObject('TC3/Page_MentorUS Dashboard/div_Testing'))
} else {
    WebUI.refresh()
}

WebUI.click(findTestObject('TC3/Page_MentorUS Dashboard/button_to_create_task'))

WebUI.setText(findTestObject('Object Repository/TC3/Page_MentorUS Dashboard/input_Tiu_title'), title)

WebUI.setText(findTestObject('Object Repository/TC3/Page_MentorUS Dashboard/input_M t_description'), description)

if ((due_hour == '') || (due_minute == '')) {
    WebUI.click(findTestObject('TC3/Page_MentorUS Dashboard/input_date_deadline'))

    WebUI.sendKeys(findTestObject('TC3/Page_MentorUS Dashboard/input_date_deadline'), Keys.chord(Keys.DELETE))
} else {
    WebUI.click(findTestObject('TC3/Page_MentorUS Dashboard/input_time_deadline'))

    def offsets_hour = getOffsetForHour(due_hour.toInteger())

    WebUI.clickOffset(findTestObject('TC3/Page_MentorUS Dashboard/div_clock'), offsets_hour[0], offsets_hour[1])

    WebUI.delay(1)

    def offsets_min = getOffsetForMinute(due_minute.toInteger())

    WebUI.clickOffset(findTestObject('TC3/Page_MentorUS Dashboard/div_clock'), offsets_min[0], offsets_min[1])

    WebUI.delay(1)

    if (isAM == 'true') {
        WebUI.click(findTestObject('TC3/Page_MentorUS Dashboard/span_AM'))
    } else {
        WebUI.click(findTestObject('TC3/Page_MentorUS Dashboard/span_PM'))
    }
    
    WebUI.click(findTestObject('TC3/Page_MentorUS Dashboard/button_OK_time_deadline'))
}

WebUI.click(findTestObject('TC3/Page_MentorUS Dashboard/input_date_deadline'))

if (due_date != '') {
    WebUI.sendKeys(findTestObject('TC3/Page_MentorUS Dashboard/input_date_deadline'), due_date)
} else if (due_date == '') {
    WebUI.sendKeys(findTestObject('TC3/Page_MentorUS Dashboard/input_date_deadline'), Keys.chord(Keys.DELETE, Keys.ARROW_RIGHT, 
            Keys.DELETE, Keys.ARROW_RIGHT, Keys.DELETE, Keys.ARROW_LEFT, Keys.ARROW_LEFT))
} else if ((due_date != '') && (due_date.length() < 8)) {
    WebUI.sendKeys(findTestObject('TC3/Page_MentorUS Dashboard/input_date_deadline'), Keys.chord(Keys.DELETE, Keys.ARROW_RIGHT, 
            Keys.DELETE, Keys.ARROW_RIGHT, Keys.DELETE))

    WebUI.sendKeys(findTestObject('TC3/Page_MentorUS Dashboard/input_date_deadline'), due_date)

    WebUI.verifyTextPresent('Ngày tới hạn phải lớn hơn hoặc bằng ngày hiện tại', false)
}

if (assignees == '0') {
    WebUI.click(findTestObject('TC3/Page_MentorUS Dashboard/input_assignees'))

    WebUI.click(findTestObject('TC3/Page_MentorUS Dashboard/button_clear_assignees'))

    WebUI.click(findTestObject('TC3/Page_MentorUS Dashboard/input_Tiu_title'))
} else if (assignees == '-1') {
    WebUI.click(findTestObject('TC3/Page_MentorUS Dashboard/input_assignees'))

    WebUI.click(findTestObject('TC3/Page_MentorUS Dashboard/button_clear_assignees'))

    WebUI.setText(findTestObject('TC3/Page_MentorUS Dashboard/input_assignees'), 'Non-exist Assignee')

    WebUI.click(findTestObject('TC3/Page_MentorUS Dashboard/input_Tiu_title'))
}

WebUI.click(findTestObject('TC3/Page_MentorUS Dashboard/button_create_task'))

if (title == '') {
    WebUI.verifyTextPresent('Vui lòng nhập tiêu đề', false)
} else if (title.length() > 100) {
    WebUI.verifyTextPresent('Tiêu đề không được vượt quá 100 ký tự', false)
}

if ((description != '') && (description.length() > 250)) {
    WebUI.verifyTextPresent('Mô tả không được vượt quá 250 ký tự', false)
}

if (due_hour == '') {
    WebUI.verifyTextPresent('Thời hạn phải lớn hơn thời gian hiện tại', false)
}

if ((assignees == '0') || (assignees == '-1')) {
    WebUI.verifyTextPresent('Vui lòng chọn người sẽ thực hiện công việc', false)
}

if (status == 'true') {
    WebUI.verifyElementText(findTestObject('TC3/Page_MentorUS Dashboard/task_title'), title)

    def actualString = WebUI.getText(findTestObject('TC3/Page_MentorUS Dashboard/task_deadline'))

    String day = due_date.substring(0, 2)

    String month = due_date.substring(2, 4)

    String year = due_date.substring(4, 8)

    String formatted_date = (((day + '/') + month) + '/') + year

    String converted_time = convertTo24HourFormat(due_hour, due_minute, isAM)

    String expectedString = (('Tới hạn ' + converted_time) + ', ngày ') + formatted_date

    print(expectedString)

    WebUI.verifyElementText(findTestObject('TC3/Page_MentorUS Dashboard/task_deadline'), expectedString)
}

if (end == 'true') {
    WebUI.closeBrowser()
}

def getOffsetForHour(int hour) {
    int radius = 100

    double angle = Math.toRadians(30 * (hour - 3))

    int offsetX = ((radius * Math.cos(angle)) as int)

    int offsetY = ((radius * Math.sin(angle)) as int)

    return [offsetX, offsetY]
}

def getOffsetForMinute(int minute) {
    int radius = 100

    double angle = Math.toRadians(6 * (minute - 15))

    int offsetX = ((radius * Math.cos(angle)) as int)

    int offsetY = ((radius * Math.sin(angle)) as int)

    return [offsetX, offsetY]
}

String convertTo24HourFormat(String hour, String minute, String period) {
    int hourInt = Integer.parseInt(hour)

    int minuteInt = Integer.parseInt(minute)

    if (minuteInt < 10) {
        minute = ('0' + minute)
    }
    
    if (period.equalsIgnoreCase('false') && (hourInt != 12)) {
        hourInt += 12
    } else if (period.equalsIgnoreCase('true') && (hourInt == 12)) {
        hourInt = 0
    }
    
    return (String.format('%02d', hourInt) + ':') + minute
}

