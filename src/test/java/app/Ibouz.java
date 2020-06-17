package app;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Ibouz {
	private String loginid;
	private String loginpw;

	private String baseURL;
	private String logintURL;
	private String profitReportURL;
	private String userSearchURL;
	//C 登録コード別レポート検索画面
	private String adReportSearchURL;
	//C 開封ユーザー詳細レポート
	private String userReadDetailReportURL;
	//C 時間別ユーザー送信結果レポート
	private String userSendReportURL;
	//C 退会
	private String deactiveReportURL;
	private String userRegistrationReportURL;
	//C 会員ID指定削除追加
	private String userDeleteReserveURL;
	//C 一括会員削除
	private String batchDeleteURL;

	public void setBaseURL(String ip, String suburl) {
		this.baseURL = "http://" + ip + "/" + suburl + "/";
	}

	public void setLoginURL() {
		this.logintURL = baseURL + "login.php";
	}
	public void setUserSearchURL() {
		this.userSearchURL = baseURL + "user_search_all.php";
	}

	public void setAdReportSearchURL() {
		this.adReportSearchURL = baseURL + "report_agency.php";
	}

	public void setProfitReportURL(String yyyy, String mm) {
		this.profitReportURL = baseURL + "report_trade.php?sex=0&yy=" + yyyy + "&mm=" + mm + "&currency=1";
	}

	public void setUserReadDetailReportURL(String yyyy, String mm, String dd) {
		this.userReadDetailReportURL = baseURL + "report_hour_open_user_list.php?limit=0&type=31000&year=" + yyyy + "&month="
				+ mm + "&day=" + dd + "&hour=24";
	}

	public void setDeactiveReportURL(String yyyy, String mm) {
		this.deactiveReportURL = baseURL + "report_retire.php?month=" + mm + "&year=" + yyyy;
	}
	public void setUserSendReportURL(String yyyy, String mm, String dd) {
		this.userSendReportURL = baseURL + "report_hour.php?mode=result&year=" + yyyy + "&month="+ mm + "&day=" + dd;
	}

	public void setUserRegistrationReportURL(String yyyy, String mm) {
		this.userRegistrationReportURL = baseURL + "report_new.php?month=" + mm + "&year=" + yyyy;
	}
	public void setUserDeleteReserveURL() {
		this.userDeleteReserveURL = baseURL + "user_delete_reserve.php";
	}
	public void setBatchDeleteURL() {
		this.batchDeleteURL = baseURL + "batch_delete.php";
	}
}

