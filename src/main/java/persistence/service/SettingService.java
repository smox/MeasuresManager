package persistence.service;

import persistence.dao.SettingDao;
import persistence.model.Setting;

import java.util.List;


public class SettingService {

    private static SettingDao settingDao;

    public SettingService() {
        settingDao = new SettingDao();
    }

    public void persist(Setting setting) {
        settingDao.openCurrentSessionwithTransaction();
        settingDao.persist(setting);
        settingDao.closeCurrentSessionwithTransaction();
    }

    public void update(Setting setting) {
        settingDao.openCurrentSessionwithTransaction();
        settingDao.update(setting);
        settingDao.closeCurrentSessionwithTransaction();
    }

    public Setting findById(Long id) {
        settingDao.openCurrentSession();
        Setting setting = settingDao.findById(id);
        settingDao.closeCurrentSession();
        return setting;
    }

    public void delete(Long id) {
        settingDao.openCurrentSessionwithTransaction();
        Setting setting = settingDao.findById(id);
        settingDao.delete(setting);
        settingDao.closeCurrentSessionwithTransaction();
    }

    public List<Setting> findAll() {
        settingDao.openCurrentSession();
        List<Setting> settings = settingDao.findAll();
        settingDao.closeCurrentSession();
        return settings;
    }

    public SettingDao settingDao() {
        return settingDao;
    }
}
