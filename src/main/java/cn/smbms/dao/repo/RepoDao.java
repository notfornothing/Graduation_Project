package cn.smbms.dao.repo;

import cn.smbms.pojo.Bill;
import cn.smbms.pojo.Repo;

import java.sql.Connection;
import java.util.List;

public interface RepoDao {

    /**
     * 获取列表
     * @param connection
     * @param repo
     * @return
     * @throws Exception
     */
    public List<Repo> getRepoList(Connection connection, Repo repo) throws Exception;



}
