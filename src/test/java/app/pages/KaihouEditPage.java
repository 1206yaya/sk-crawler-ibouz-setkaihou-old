package app.pages;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import com.codeborne.selenide.WebDriverRunner;

/**
 * 会員検索画面 「検索」クリック -> 会員検索結果画面 「会報予約へ」クリック
 * 【会員 会報送信予定】
 */
public class KaihouEditPage {
	public void changeNewWindow() {
		/**
		 * 新規タブで開かれたwindowに切り替える
		 */
		// C 現在アクティブなWindowのHandleを取得する
		String handle = WebDriverRunner.getWebDriver().getWindowHandle();
		// WebDriverから開いたすべての画面のHandleを取得する
		Set<String> handles = WebDriverRunner.getWebDriver().getWindowHandles();
		String resultPage = null;
		for (Iterator<String> it = handles.iterator(); it.hasNext();) {
			String f = it.next();
			if (!handle.equals(f)) {
				resultPage = f;
			}
		}
		switchTo().window(resultPage);
	}
	public void setTitle(String title) {
		$(By.name("title")).setValue(title);
	}
	public void setMessage(String message) {
		$(By.name("message")).setValue(message);
	}

	public void setReservationDateAndTime(int yyyy, int mm, int dd, int hh, int ii) {
		$(By.name("yy")).setValue(yyyy + "");
		$(By.name("mm")).setValue(mm + "");
		$(By.name("dd")).setValue(dd + "");
		$(By.name("hh")).setValue(hh + "");
		$(By.name("ii")).setValue(ii + "");
	}

	public void submit() {
		$("input[value='　登　録　']").submit();
		
	}
	public void newSameTab() {
		$("input[value='続けて同じ条件＆文面で予約']").sendKeys(Keys.chord(Keys.CONTROL,Keys.RETURN));
	}
	public void newSameTab(int size) {
		for (int i = 0; i < size; i++) {
			newSameTab();
		}
	}
}
