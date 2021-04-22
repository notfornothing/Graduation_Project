package cn.smbms.dao.repo;

import cn.smbms.dao.BaseDao;
import cn.smbms.pojo.Repo;
import cn.smbms.pojo.Secret;
import com.mysql.jdbc.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RepoDaoImpl implements RepoDao {


    @Override
    public List<Secret>
    getSecretList(Connection connection, Secret secret) throws Exception {
        // TODO Auto-generated method stub
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<Secret> secretList = new ArrayList<Secret>();
        if (connection != null) {
            StringBuffer sql = new StringBuffer();
            sql.append("select r.id 键,r.providerCode 供应商编码,r.providerName 供应商名称,p.proPhone 供应商电话,r.billName 商品名称,r.total 总量,r.unit 单位 from smbms_repo r, smbms_provider p where r.providerName = p.proName ");
            List<Object> list = new ArrayList<Object>();
            if (!StringUtils.isNullOrEmpty(secret.getProviderCode())) {
                sql.append(" and r.providerCode like ? ");
                list.add("%" + secret.getProviderCode() + "%");
            }
            if (secret.getId() != null && secret.getId() > 0) {
                sql.append(" and r.id = ? ");
                list.add(secret.getId());
            }
            if (!StringUtils.isNullOrEmpty(secret.getBillName())) {
                sql.append(" and r.billName like ? ");
                list.add("%" + secret.getBillName() + "%");
            }
            if (!StringUtils.isNullOrEmpty(secret.getProviderName())) {
                sql.append(" and r.providerName like ? ");
                list.add("%" + secret.getProviderName() + "%");
            }
            Object[] params = list.toArray();
            System.out.println("sql --------- > " + sql.toString());
            rs = BaseDao.execute(connection, pstm, rs, sql.toString(), params);
            while (rs.next()) {
                Secret _secret = new Secret();
                _secret.setId(rs.getLong("键"));
                _secret.setProviderCode(rs.getString("供应商编码"));
                _secret.setProviderName(rs.getString("供应商名称"));
                _secret.setProviderPhone(rs.getString("供应商电话"));
                _secret.setBillName(rs.getString("商品名称"));
                _secret.setTotal(rs.getDouble("总量"));
                _secret.setUnit(rs.getString("单位"));
                secretList.add(_secret);
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return secretList;
    }

    @Override
    public List<Repo>
    getRepoList(Connection connection, Repo repo) throws Exception {
        // TODO Auto-generated method stub
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<Repo> repoList = new ArrayList<Repo>();
        if (connection != null) {
            StringBuffer sql = new StringBuffer();
            sql.append("select r.id 键,r.providerCode 供应商编码,r.providerName 供应商名称,p.proPhone 供应商电话,r.billName 商品名称,r.total 总量,r.unit 单位 from smbms_repo r, smbms_provider p where  r.providerName = p.proName ");
            List<Object> list = new ArrayList<Object>();
            if (!StringUtils.isNullOrEmpty(repo.getProviderCode())) {
                sql.append(" and r.providerCode like ? ");
                list.add("%" + repo.getProviderCode() + "%");
            }
            if (repo.getId() != null && repo.getId() > 0) {
                sql.append(" and r.id = ? ");
                list.add(repo.getId());
            }
            if (!StringUtils.isNullOrEmpty(repo.getProviderName())) {
                sql.append(" and r.providerName like ? ");
                list.add("%" + repo.getProviderName() + "%");
            }
            Object[] params = list.toArray();
            System.out.println("sql --------- > " + sql.toString());
            rs = BaseDao.execute(connection, pstm, rs, sql.toString(), params);
            while (rs.next()) {
                Repo _repo = new Repo();
                _repo.setId(rs.getLong("键"));
                _repo.setProviderName(rs.getString("供应商名称"));
                _repo.setProviderCode(rs.getString("供应商编码"));
                _repo.setTotal(rs.getDouble("总量"));
                _repo.setUnit(rs.getString("单位"));
                _repo.setBillName(rs.getString("商品名称"));

                repoList.add(_repo);
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return repoList;
    }

    @Override
    public int deleteRepoById(Connection connection, String id) throws Exception {
        // TODO Auto-generated method stub
        PreparedStatement pstm = null;
        int flag = 0;
        if (null != connection) {
            String sql = "delete from smbms_repo where id=?";
            Object[] params = {id};
            flag = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResource(null, pstm, null);
        }
        return flag;
    }

    @Override
    public Secret queryForOne(Connection connection, String repoId, String providerCode) throws Exception {
        Secret _secret = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        if (null != connection) {
            String sql = "select r.id 键,r.providerCode 供应商编码,r.providerName 供应商名称,p.proPhone 供应商电话,r.billName 商品名称,r.total 总量,r.unit 单位 from smbms_repo r, smbms_provider p where  r.providerName = p.proName   and r.id =? ";
            Object[] params = {repoId};
            rs = BaseDao.execute(connection, pstm, rs, sql.toString(), params);
            if (rs.next()) {
                _secret = new Secret();
                _secret.setId(rs.getLong("键"));
                _secret.setProviderCode(rs.getString("供应商编码"));
                _secret.setProviderName(rs.getString("供应商名称"));
                _secret.setProviderPhone(rs.getString("供应商电话"));
                _secret.setBillName(rs.getString("商品名称"));
                _secret.setTotal(rs.getDouble("总量"));
                _secret.setUnit(rs.getString("单位"));
            }
            System.out.println("sql=====>" + sql.toString());
            System.out.println(providerCode + "," + repoId);
        }
        BaseDao.closeResource(null, pstm, rs);
        return _secret;
    }

    @Override
    public int modify(Connection connection, Secret secret) throws Exception {
        // TODO Auto-generated method stub
        int flag = 0;
        PreparedStatement pstm = null;
        if (null != connection) {
            String sql = "UPDATE smbms_repo r, smbms_provider p  set  r.total = ? ,r.unit = ? where  r.providerName = p.proName   and r.providerCode =?  and r.id = ?";
            Object[] params = {secret.getTotal(), secret.getUnit(), secret.getProviderCode(), secret.getId()};
            flag = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResource(null, pstm, null);
        }
        return flag;
    }

    @Override
    public Integer add(Connection connection, Repo repo) throws Exception {
        // TODO Auto-generated method stub
        PreparedStatement pstm = null;
        int flag = 0;
        if (null != connection) {
            String sql = "INSERT INTO smbms_repo values(null,?,?,?,(SELECT smbms_provider.proCode from smbms_provider WHERE smbms_provider.proName = ?),?)";
            Object[] params = {repo.getTotal(), repo.getUnit(),
                    repo.getProviderName(), repo.getProviderCode(), repo.getBillName()};
            flag = BaseDao.execute(connection, pstm, sql, params);
            BaseDao.closeResource(null, pstm, null);
            System.out.println("dao--------flag " + flag);
            System.out.println("sql --------- > " + sql.toString());
            System.out.println(repo);
        }
        return flag;
    }
}
