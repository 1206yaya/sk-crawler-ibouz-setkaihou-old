package app;

public class IbouzBuilder {


	public static Ibouz createIbouz(String loginid, String loginpw, String ip, String suburl) {
		Ibouz ibouz = new Ibouz();
		ibouz.setLoginid(loginid);
		ibouz.setLoginpw(loginpw);
		ibouz.setBaseURL(ip, suburl);
		ibouz.setLoginURL();
		ibouz.setUserSearchURL();
		ibouz.setAdReportSearchURL();
		ibouz.setUserDeleteReserveURL();
		ibouz.setBatchDeleteURL();
		return ibouz;
	}

	public static Ibouz createIbouz(String loginid, String loginpw, String ip, String suburl, String yyyy, String mm, String dd) {
		Ibouz ibouz = new Ibouz();
		ibouz.setLoginid(loginid);
		ibouz.setLoginpw(loginpw);
		ibouz.setBaseURL(ip, suburl);
		ibouz.setLoginURL();
		ibouz.setUserSearchURL();
		ibouz.setProfitReportURL(yyyy, mm);
		ibouz.setUserReadDetailReportURL(yyyy, mm, dd);
		ibouz.setDeactiveReportURL(yyyy, mm);
		ibouz.setUserSendReportURL(yyyy, mm, dd);
		ibouz.setUserReadDetailReportURL(yyyy, mm, dd);
		ibouz.setUserRegistrationReportURL(yyyy, mm);

		return ibouz;
	}
}
