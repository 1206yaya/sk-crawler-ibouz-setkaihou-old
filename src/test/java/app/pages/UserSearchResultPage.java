package app.pages;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.Selenide.switchTo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;

public class UserSearchResultPage {
	public UserSearchResultPage() {
		changeNewWindow();
	}

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

	public void closeThisTab() {
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
		WebDriverRunner.getWebDriver().close();
		switchTo().window(resultPage);
	}
	
	/**
	 * 「会報予約へ」をクリックする
	 */
	public KaihouEditPage clickKaihouYoyaku() {
		$("input[value='会報予約へ']").submit();
		return page(KaihouEditPage.class);
	}

	public Integer getResultCount() {

		int resultCount = 0;
		if ($(By.xpath("/html/body/table[1]/tbody/tr/td/div")).exists()) {
			String totalLoginCount = $(By.xpath("/html/body/table[1]/tbody/tr/td/div")).getText();
			resultCount = getSearchResultCount(totalLoginCount);
		} else {
			/*
			 * <body>423142名<br>検索件数が<font color="red"
			 * size="6">100000</font>件を超えました。条件を変えて検索してください。</body>
			 */
			String totalLoginCount = $(By.xpath("/html/body")).getText();
			resultCount = getSearchResultCountLimitOver(totalLoginCount);
		}

		return resultCount;
	}

	public String getTotalId() {
		int count = getResultCount();
		if (count == 0) { 
			return "";
		}
		$("input[value='ID一覧(全件表示)']").submit();
		String value = $(By.tagName("textarea")).val();
		return value;
	}
	
	public List<String> getTotalIdList() {
		String value = getTotalId();
		if (value.length() == 0) {
			return new ArrayList<String>();
		}
		List<String> ids = Arrays.asList(value.split("\n"));
		return ids;
	}

	/**
	 * ■会員検索(男女)1～74/74名 74 を抽出
	 *
	 * @param str
	 * @return
	 */
	public static Integer getSearchResultCount(String str) {
		Integer result = 0;
		Pattern p = Pattern.compile("/\\d+名");
		Matcher m = p.matcher(str);
		if (m.find()) {
			int start = m.start() + 1; // 「/」を除く
			int end = m.end() - 1; // 「名」を除く
			result = Integer.parseInt(str.substring(start, end));
		}
		return result;
	}

	/**
	 * ■会員検索(男女)1～74/74名 74 を抽出
	 *
	 * @param str
	 * @return
	 */
	public static Integer getSearchResultCountLimitOver(String str) {
		Integer result = 0;
		Pattern p = Pattern.compile("\\d+名");
		Matcher m = p.matcher(str);
		if (m.find()) {
			int start = 0; // C find対象文字列の先頭
			int end = m.end() - 1; // C 「名」を除く
			result = Integer.parseInt(str.substring(start, end));
		}
		return result;
	}

	/**
	 * 検索結果一覧の二列目にある「メールアドレス」を重複のない状態にして返す
	 *
	 * @return
	 */
	public Set<String> getUniqueDomainList() {
		SelenideElement mainTable = $(By.xpath("/html/body/table[1]/tbody/tr/td/table/tbody"));
		List<SelenideElement> trs = mainTable.$$(By.tagName("tr"));
		int trsSize = trs.size();
		// ドメイン行を探す
		int listDateStartIndex = 0; // テーブル内のデータ開始行番号
		while (true) {
			// 全ての行を見たがドメインはなかった
			if (listDateStartIndex == trsSize) {
				break;
			}
			String trText = trs.get(listDateStartIndex).getText();
			if (trText.contains("***@")) {
				break;
			} else {
				listDateStartIndex++;
			}
		}


		Set<String> domainSet = new HashSet<String>();
		while (true) {
			for (int i = listDateStartIndex; i < trsSize; i++) {
				List<SelenideElement> tds = trs.get(i).$$(By.tagName("td"));
				SelenideElement mailAddressElm = tds.get(1);
				String address = mailAddressElm.getText();
				String domain = address.replace("***@", "").toLowerCase();
				domainSet.add(domain);
			}
			if ($("input[value='次の100件']").exists()) {
				$("input[value='次の100件']").click();
			} else {

				break;
			}
		}

		return domainSet;
	}



}
