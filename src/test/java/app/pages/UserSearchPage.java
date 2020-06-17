package app.pages;


import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.page;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

public class UserSearchPage {
	private SelenideElement formElm;

	private void setFormElm() {
		if (formElm == null) {
			formElm = $(By.name("search"));
		}
	}


	public UserSearchPage() {
		setFormElm();
	}
	/**
	 * ID
	 */
	public void setIds(List<String> ids) {
//		formElm.$(By.name("id")).setValue(String.join("\n", ids));
		String text = String.join("\\n", ids);
		Selenide.executeJavaScript("document.getElementsByName('id')[0].value = '" + text  + "';");
	}
	/**
	 * ID
	 * @param ids
	 */
	public void setId(String ids) {
		formElm.$(By.name("id")).setValue(ids);

	}
	/**
	 *  メインメール
	 * @param mail メインメール
	 * @param isAimai 曖昧
	 * @param isHitei 否定
	 */
	public void setMail(String mail) {
		setMail(mail, false, false);
	}
	public void setMail(String mail, boolean isAimai) {
		setMail(mail, isAimai, false);
	}

	public void setMail(String mail, boolean isAimai, boolean isHitei) {
		formElm.$(By.name("mail")).setValue(mail);
		if (isAimai) {
			formElm.$(By.id("L_mailfuzzy")).click();
		}
		if (isHitei) {
			formElm.$(By.id("L_mailnot")).click();
		}
	}

	/**
	 * メールキャリア
	 * @param carrier
	 */
	public void setMailCarrier(String carrier) {
		Select select = new Select(formElm.$(By.name("mob1[]")));
		
		select.deselectByVisibleText("指定しない");
		select.selectByVisibleText(carrier);
	}
	public void setMail(String mail, Boolean aimai, Boolean hitei) {
		$(By.name("mail")).setValue(mail);
		if (aimai) {
			$(By.id("L_mailfuzzy")).click();
		}
		if (hitei) {
			$(By.id("L_mailnot")).click();
		}
	}

	/**
	 * 登録コード
	 */
	public void setCode(String code) {
		formElm.$(By.name("code")).setValue(code);
	}

	/**
	 * 料金ステータス
	 */
	public boolean setPriceStatus(String value) {
		Select select_substatus = new Select(formElm.$(By.id("select_price_status")));
		select_substatus.deselectByVisibleText("指定しない");
		select_substatus.selectByVisibleText(value);
		List<WebElement> selectedOptions = select_substatus.getAllSelectedOptions();
		return shouldSelected(value, selectedOptions);

	}
	/**
	 * サブステータス
	 */
	public boolean setSubStatus(String value) {
		Select select_substatus = new Select(formElm.$(By.id("select_substatus")));
		select_substatus.deselectByVisibleText("指定しない");
		select_substatus.selectByVisibleText(value);
		List<WebElement> selectedOptions = select_substatus.getAllSelectedOptions();
		return shouldSelected(value, selectedOptions);

	}
	/**
	 * サブステータス
	 * 初めと終わりを指定して範囲選択する
	 */
	public void setSubStatusByRange(String start, String end) {
		Select select_substatus = new Select(formElm.$(By.id("select_substatus")));
		List<WebElement> allOptions = select_substatus.getOptions();
		// optionIndexを数える (文字列で選択すると全角、半角があるとうまくいかないため)
		int optionCursor = 0;
		boolean isSelectRange = false;
		for (WebElement option : allOptions) {
			if (isSelectRange) {
				select_substatus.selectByIndex(optionCursor);
			}
			// Mapにつめる
			if (option.getText().equals(start)) {
				select_substatus.deselectByVisibleText("指定しない");
				select_substatus.selectByIndex(optionCursor);
				isSelectRange = true;
			}
			if (option.getText().equals(end)) {
				break;
			}
			optionCursor++;
		}
	}
	/**
	 * 登録日時
	 * @param targetDay
	 */
	public void setRegistrationTime(LocalDate targetDay) {
		$(By.name("jyy")).setValue(targetDay.getYear() + "");
		$(By.name("jmm")).setValue(targetDay.getMonthValue() + "");
		$(By.name("jdd")).setValue(targetDay.getDayOfMonth() + "");
	}
	
	public void setRegistrationTime(LocalDateTime since, LocalDateTime until) {
		// 早い時間から
		$(By.name("jyy")).setValue(since.getYear() + "");
		$(By.name("jmm")).setValue(since.getMonthValue() + "");
		$(By.name("jdd")).setValue(since.getDayOfMonth() + "");
		$(By.name("jhh")).setValue(since.getHour() + "");
		$(By.name("jii")).setValue(since.getMinute() + "");
		// 範囲指定
		$(By.name("jchk")).click();
		// 遅い時間
		$(By.name("jyy2")).setValue(until.getYear() + "");
		$(By.name("jmm2")).setValue(until.getMonthValue() + "");
		$(By.name("jdd2")).setValue(until.getDayOfMonth() + "");
		$(By.name("jhh2")).setValue(until.getHour() + "");
		$(By.name("jii2")).setValue(until.getMinute() + "");
	}
	
	
	/**
	 * 最終送信日時
	 * @param targetDay
	 */
	public void setLastSendTime(LocalDate targetDay) {
		formElm.$(By.name("syy")).setValue(targetDay.getYear() + "");
		formElm.$(By.name("smm")).setValue(targetDay.getMonthValue() + "");
		formElm.$(By.name("sdd")).setValue(targetDay.getDayOfMonth() + "");
	}
	/**
	 * 最終ログイン日時
	 * @param targetDay
	 */
	public void setLastLoginTime(LocalDate targetDay) {
		$$(By.name("lyy")).get($$(By.name("lyy")).size() - 1).setValue(targetDay.getYear() + "");
		$$(By.name("lmm")).get($$(By.name("lmm")).size() - 1).setValue(targetDay.getMonthValue() + "");
		$$(By.name("ldd")).get($$(By.name("ldd")).size() - 1).setValue(targetDay.getDayOfMonth() + "");
	}
	public void clearLastLoginTime() {
		$$(By.name("lyy")).get($$(By.name("lyy")).size() - 1).clear();
		$$(By.name("lmm")).get($$(By.name("lmm")).size() - 1).clear();
		$$(By.name("ldd")).get($$(By.name("ldd")).size() - 1).clear();
	}
	/**
	 * 初回ログイン日時
	 * @param targetDay
	 */
	public void setNewaccLoginTime(LocalDate targetDay) {
		$(By.name("fyy")).setValue(targetDay.getYear() + "");
		$(By.name("fmm")).setValue(targetDay.getMonthValue() + "");
		$(By.name("fdd")).setValue(targetDay.getDayOfMonth() + "");
	}
	/**
	 * 初回ログイン 範囲
	 */
	public void clickNewaccLoginRangeBox() {
		$(By.id("L_fchk")).click();
	}
	/**
	 * 初回ログイン 未ログインON
	 */
	public void clickNewaccLoginNotLogin() {
		$(By.name("fnull")).click();
	}
	

	/**
	 * 最終購入
	 * @param targetDay
	 */
	public void setLastBuy(LocalDate targetDay) {
		formElm.$(By.name("byy")).setValue(targetDay.getYear() + "");
		formElm.$(By.name("bmm")).setValue(targetDay.getMonthValue() + "");
		formElm.$(By.name("bdd")).setValue(targetDay.getDayOfMonth() + "");
	}
	/**
	 * 累計購入回数
	 * @param count
	 */
	public void setBuyCount(Integer count) {
		if (count == 0) {
			formElm.$(By.name("buy_count2")).setValue(count + "");
		}
		if (count == 1) {
			formElm.$(By.name("buy_count")).setValue(count + "");
		}

	}
	/**
	 * 累計購入回数
	 * @param count
	 */
	public void setBuyCount(Integer buy_count, Integer buy_count2) {
		formElm.$(By.name("buy_count")).setValue(buy_count + "");
		formElm.$(By.name("buy_count2")).setValue(buy_count2 + "");
	}
	/**
	 * 累計送信数
	 * @param count
	 */
	public void setTotalSendingCount(int count) {

//		$(By.name("send_count")).setValue(count + "");
		ElementsCollection inputElms = $$(By.name("send_count"));
		inputElms.get(inputElms.size() - 1).setValue(count + "");

	}


	/**
	 * 検索ボタン押下
	 * @return
	 */
	public UserSearchResultPage search() {
		$(By.name("search_button")).submit();
		return page(UserSearchResultPage.class);
	}
	/**
	 * メインメールを結果に表示させるため、項目をチェックする
	 */
	public void showMailInResult() {
		$(By.xpath("//input[@name='col_mail'][@type='checkbox']")).click();
	}

	/**
	 * selectボックスが指定通りに選択されたか
	 * @param value
	 * @param selectedOptions
	 * @return
	 */
	private boolean shouldSelected(String value, List<WebElement> selectedOptions) {
		boolean valueIsSelected = selectedOptions.stream().map(elm -> elm.getText()).anyMatch(Predicate.isEqual(value));
		boolean defaultValueIsNotSelected = selectedOptions.stream().map(elm -> elm.getText()).noneMatch(Predicate.isEqual("指定しない"));
		return valueIsSelected && defaultValueIsNotSelected;
	}

}
