package cn.smbms.dao.repo;

import cn.smbms.dao.BaseDao;
import cn.smbms.pojo.Repo;
import cn.smbms.pojo.Repo;
import com.mysql.jdbc.StringUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RepoDaoImpl implements RepoDao {

    @Override
    @Test
    public List<Repo>
    getRepoList(Connection connection, Repo repo) throws Exception {
        // TODO Auto-generated method stub
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<Repo> repoList = new ArrayList<Repo>();
        if(connection != null){
            StringBuffer sql = new StringBuffer();
            sql.append("select r.id 键,r.providerCode 供应商编码,r.providerName 供应商名称,p.proPhone 供应商电话,b.productName 商品名称,r.total 总量,r.unit 单位 from smbms_repo r, smbms_provider p, smbms_bill b where r.providerCode = p.proCode and r.id = b.id ;");
            List<Object> list = new ArrayList<Object>();
//            if(!StringUtils.isNullOrEmpty(repo.getProviderCode())){
//                sql.append(" and r.providerCode like ?");
//                list.add("%"+repo.getProviderCode()+"%");
//            }
//            if(repo.getId() > 0){
//                sql.append(" and r.id = ?");
//                list.add(repo.getId());
//            }
//            if(!StringUtils.isNullOrEmpty(repo.getProviderName())){
//                sql.append(" and r.providerName like ?");
//                list.add("%"+repo.getProviderName()+"%");
//            }
            Object[] params = list.toArray();
            System.out.println("sql --------- > " + sql.toString());
            rs = BaseDao.execute(connection, pstm, rs, sql.toString(), params);
            while(rs.next()){
                Repo _repo = new Repo();
                _repo.setId(rs.getLong("键"));
                _repo.setProviderName(rs.getString("供应商名称"));
                _repo.setProviderCode(rs.getString("供应商编码"));
                _repo.setTotal(rs.getDouble("总量"));
                _repo.setUnit(rs.getString("单位"));
                repoList.add(_repo);
            }
            BaseDao.closeResource(null, pstm, rs);
        };
        for (Repo repo1 : repoList) {
            System.out.println(repo1);
        }
        return repoList;
    }

}
