package cn.smbms.service.repo;

import cn.smbms.dao.BaseDao;
import cn.smbms.dao.repo.RepoDao;
import cn.smbms.dao.repo.RepoDaoImpl;
import cn.smbms.pojo.Repo;
import cn.smbms.pojo.Repo;

import java.sql.Connection;
import java.util.List;

public class RepoServiceImpl implements RepoService{
    private  RepoDao repoDao;

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
        }finally{
            BaseDao.closeResource(connection, null, null);
        }
        return repoList;
    }
}
