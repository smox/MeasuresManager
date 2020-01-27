package persistence.dao;

import persistence.model.Setting;

public class SettingDao extends AbstractDao<Setting> {
    public SettingDao() {
        super(Setting.class);
    }
}
