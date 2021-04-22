package cn.smbms.service.repo;

import cn.smbms.dao.BaseDao;
import cn.smbms.dao.repo.RepoDao;
import cn.smbms.dao.repo.RepoDaoImpl;
import cn.smbms.pojo.Repo;
import cn.smbms.pojo.Secret;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class RepoServiceImpl implements RepoService {
    private RepoDao repoDao;

    public RepoServiceImpl() {
        repoDao = new RepoDaoImpl();
    }

    @Override
    public List<Repo> getRepoList(Repo repo) {
        // TODO Auto-generated method stub
        Connection connection = null;
        List<Repo> repoList = null;
//        System.out.println("query productName ---- > " + repo.getProductName());
//        System.out.println("query providerId ---- > " + repo.getProviderId());
//        System.out.println("query isPayment ---- > " + repo.getIsPayment());

        try {
            connection = BaseDao.getConnection();
            repoList = repoDao.getRepoList(connection, repo);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return repoList;
    }

    @Override
    public List<Secret> getSecretList(Secret secret) {
        // TODO Auto-generated method stub
        Connection connection = null;
        List<Secret> secretList = null;
//        System.out.println("query productName ---- > " + secret.getProductName());
//        System.out.println("query providerId ---- > " + secret.getProviderId());
//        System.out.println("query isPayment ---- > " + secret.getIsPayment());

        try {
            connection = BaseDao.getConnection();
            secretList = repoDao.getSecretList(connection, secret);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return secretList;
    }

    @Override
    public boolean deleteRepoById(String id) {
        // TODO Auto-generated method stub
        Connection connection = null;
        boolean flag = false;
        try {
            connection = BaseDao.getConnection();
            if (repoDao.deleteRepoById(connection, id) > 0)
                flag = true;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }
        return flag;
    }

    @Override
    public Secret queryForOne(String repoId, String providerCode) {
        // TODO Auto-generated method stub
        Secret secret = null;
        Connection connection = null;
        try{
            connection = BaseDao.getConnection();
            secret = repoDao.queryForOne(connection, repoId,providerCode);
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            secret = null;
        }finally{
            BaseDao.closeResource(connection, null, null);
        }
        return secret;
    }

    @Override
    public Boolean modify(Secret secret) {

        // TODO Auto-generated method stub
        Connection connection = null;
        boolean flag = false;
        try {
            connection = BaseDao.getConnection();
            if(repoDao.modify(connection,secret) > 0)
                flag = true;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            BaseDao.closeResource(connection, null, null);
        }
        return flag;

    }

    @Override
    public boolean add(Repo repo) {
        // TODO Auto-generated method stub
        boolean flag = false;
        Connection connection = null;
        try {
            connection = BaseDao.getConnection();
            connection.setAutoCommit(false);//开启JDBC事务管理
            if(repoDao.add(connection,repo) > 0) {
                flag = true;
            }
            connection.commit();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            try {
                System.out.println("rollback==================");
                connection.rollback();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }finally{
            //在service层进行connection连接的关闭
            BaseDao.closeResource(connection, null, null);
        }
        return flag;
    }
}
