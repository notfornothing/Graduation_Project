package cn.smbms.dao.repo;

import cn.smbms.dao.BaseDao;
import cn.smbms.pojo.Repo;
import cn.smbms.pojo.Repo;
import com.mysql.jdbc.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RepoDaoImpl implements RepoDao {

    @Override
    public List<Repo>
    getRepoList(Connection connection, Repo repo) throws Exception {
        // TODO Auto-generated method stub
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<Repo> billList = new ArrayList<Repo>();
        if(connection != null){
            StringBuffer sql = new StringBuffer();
            sql.append("select b.*,p.proName as providerName" +
                    " from smbms_bill b, smbms_provider p " +
                    "where b.providerId = p.id");
            sql.append("select ");
            List<Object> list = new ArrayList<Object>();
            if(!StringUtils.isNullOrEmpty(repo.getProviderCode())){
                sql.append(" and providerName like ?");
                list.add("%"+repo.getProviderName()+"%");
            }
            if(repo.getProviderId() > 0){
                sql.append(" and providerId = ?");
                list.add(bill.getProviderId());
            }
            if(bill.getIsPayment() > 0){
                sql.append(" and isPayment = ?");
                list.add(bill.getIsPayment());
            }
            Object[] params = list.toArray();
            System.out.println("sql --------- > " + sql.toString());
            rs = BaseDao.execute(connection, pstm, rs, sql.toString(), params);
            while(rs.next()){
                Repo _bill = new Repo();
                _bill.setId(rs.getInt("id"));
                _bill.setRepoCode(rs.getString("billCode"));
                _bill.setProductName(rs.getString("productName"));
                _bill.setProductDesc(rs.getString("productDesc"));
                _bill.setProductUnit(rs.getString("productUnit"));
                _bill.setProductCount(rs.getBigDecimal("productCount"));
                _bill.setTotalPrice(rs.getBigDecimal("totalPrice"));
                _bill.setIsPayment(rs.getInt("isPayment"));
                _bill.setProviderId(rs.getInt("providerId"));
                _bill.setProviderName(rs.getString("providerName"));
                _bill.setCreationDate(rs.getTimestamp("creationDate"));
                _bill.setCreatedBy(rs.getInt("createdBy"));
                billList.add(_bill);
            }
            BaseDao.closeResource(null, pstm, rs);
        }
        return billList;
    }

}
