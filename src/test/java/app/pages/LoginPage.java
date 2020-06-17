package app.pages;


import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginPage {
	private static final Logger logger = LoggerFactory.getLogger(LoginPage.class);


	public void login(String id, String pw) {
		logger.debug(">>> LoginPage#open id:" + id + " pw:" + pw);
		logger.debug(">>> LoginPage#open body: " + $(By.tagName("body")).getText());

		$(By.name("login_id")).setValue(id);
		$(By.name("password")).setValue(pw).submit();
		logger.debug(">>> LoginPage#open submit success");
	}
}
