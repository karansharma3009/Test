package Empirix;

public interface HomePageConstants {

    String loginuserTextBox ="//input[@name='callback_0']";
    String loginPasswordTextBox = "//input[@name='callback_1']";
    String signIn = "//input[@name='callback_2']";
    String voiceWatch = "//span[@ng-show=\"vwuser.current_app == 'VoiceWatch'\"]";
    String forgotPassword = "//a[@href='#forgotPassword']";
    String dashboard = "(//a[@data-i18n='_dashboard_'])[1]";
    String alerts = "(//a[@data-i18n='_alerts_'])[1]";
    String clientDetails = "//h3[@data-i18n=\"_clientDetails_\"]";
    String variables = "(//a[@data-i18n='_variables_'])[1]";
    String notifs = "(//a[@data-i18n='_notifications_'])[1]";
    String dropdown = "//a[@class='dropdown-toggle ng-binding']/span[@class=\"caret\"]";
    String japanese = "//a[@data-ng-click=\"setSelectedLanguage('ja-JP');\"]";
    String english = "//a[@data-ng-click=\"setSelectedLanguage('en-US');\"]";
    String clientLink = "//a[@href=\"/client\"]";

    String getClientDetailsLabels = "(//div[@class=\"row\"]/label[1])";
    String getGetClientDetailsLabelsValues = "(//div[@class=\"row\"]/label[2])";

}
