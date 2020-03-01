package tk.leaflame.framework;

import tk.leaflame.framework.core.ConfigHelper;

/**
 * @author leaflame
 * @date 2020/2/29 17:39
 */
public interface FrameworkConstant {

    String UTF_8 = "UTF-8";

    String CONFIG_PROPS = "rookie.properties";

    String SQL_PROPS = "rookie-sql.properties";

    String PLUGIN_PACKAGE = "tk.leaflame.plugin";

    String TEMPLATE_PATH = ConfigHelper.getString("rookie.framwork.app.template.path");

    String WWW_PATH = "";

    String HOME_PAGE = ConfigHelper.getString("rookie.framwork.app.home_page");

    int UPLOAD_LIMMIT = 0; //getInt

    String PK_NAME = "id";
}
