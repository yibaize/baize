package org.baize.dao.sqlmapper;

import org.baize.dao.manager.PersistPlayerMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 作者：---->泡泡大湿<-----
 * 时间：********2017/10/12******
 * 描述：
 */
@Service
public interface PlayerMapper{
    PersistPlayerMapper selectOneForId(Integer id);
    PersistPlayerMapper selectOneForAccount(String account);
    List<PersistPlayerMapper> selectAll();
    void insert(PersistPlayerMapper mapper);
    void update(PersistPlayerMapper mapper);
    void delete(Integer id);
    void update();
    void updateField(Map<String, String> map);
}