package cn.smbms.dao.repo;

import cn.smbms.pojo.Repo;
import cn.smbms.pojo.Secret;

import java.sql.Connection;
import java.util.List;

public interface RepoDao {

    /**
     * 获取列表
     * @param connection
     * @param secret
     * @return
     * @throws Exception
     */
    public List<Secret> getSecretList(Connection connection, Secret secret) throws Exception;

    /**
     * 获取列表
     * @param connection
     * @param repo
     * @return
     * @throws Exception
     */
    public List<Repo> getRepoList(Connection connection, Repo repo) throws Exception;


    public int deleteRepoById(Connection connection, String id) throws Exception;

   public Secret queryForOne(Connection connection, String repoId, String providerCode) throws Exception;

   public int modify(Connection connection, Secret secret) throws Exception;
}
