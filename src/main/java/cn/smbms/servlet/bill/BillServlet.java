package cn.smbms.servlet.bill;

import cn.smbms.pojo.Bill;
import cn.smbms.pojo.Provider;
import cn.smbms.pojo.Secret;
import cn.smbms.pojo.User;
import cn.smbms.service.bill.BillService;
import cn.smbms.service.bill.BillServiceImpl;
import cn.smbms.service.provider.ProviderService;
import cn.smbms.service.provider.ProviderServiceImpl;
import cn.smbms.service.repo.RepoService;
import cn.smbms.service.repo.RepoServiceImpl;
import cn.smbms.tools.Constants;
import com.alibaba.fastjson.JSONArray;
import com.mysql.jdbc.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class BillServlet extends HttpServlet {

    @Override
    public void destroy() {
        super.destroy(); // Just puts "destroy" string in log
        // Put your code here
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

		/*String totalPrice = request.getParameter("totalPrice");
		//23.234   45
		BigDecimal totalPriceBigDecimal = 
				//设置规则，小数点保留两位，多出部分，ROUND_DOWN 舍弃
				//ROUND_HALF_UP 四舍五入(5入) ROUND_UP 进位 
				//ROUND_HALF_DOWN 四舍五入（5不入）
				new BigDecimal(totalPrice).setScale(2,BigDecimal.ROUND_DOWN);*/

        String method = request.getParameter("method");
        if (method != null && method.equals("query")) {
            this.query(request, response);
        } else if (method != null && method.equals("add")) {
            this.add(request, response);
        } else if (method != null && method.equals("view")) {
            this.getBillById(request, response, "billview.jsp");
        } else if (method != null && method.equals("modify")) {
            this.getBillById(request, response, "billmodify.jsp");
        } else if (method != null && method.equals("modifysave")) {
            this.modify(request, response);
        } else if (method != null && method.equals("delbill")) {
            this.delBill(request, response);
        } else if (method != null && method.equals("getproviderlist")) {
            this.getProviderlist(request, response);
        }

    }

    private void getProviderlist(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("getproviderlist ========================= ");

        List<Provider> providerList = new ArrayList<Provider>();
        ProviderService providerService = new ProviderServiceImpl();
        providerList = providerService.getProviderList("", "");
        //把providerList转换成json对象输出
        response.setContentType("application/json");
        PrintWriter outPrintWriter = response.getWriter();
        outPrintWriter.write(JSONArray.toJSONString(providerList));
        outPrintWriter.flush();
        outPrintWriter.close();
    }

    private void getBillById(HttpServletRequest request, HttpServletResponse response, String url)
            throws ServletException, IOException {
        String id = request.getParameter("billid");
        if (!StringUtils.isNullOrEmpty(id)) {
            BillService billService = new BillServiceImpl();
            Bill bill = null;
            bill = billService.getBillById(id);
            request.setAttribute("bill", bill);
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    private void modify(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("modify===============");
        String id = request.getParameter("id");
        String productName = request.getParameter("productName");
        String productDesc = request.getParameter("productDesc");
        String productUnit = request.getParameter("productUnit");
        String productCount = request.getParameter("productCount");
        String totalPrice = request.getParameter("totalPrice");
        String providerId = request.getParameter("providerId");
        String isPayment = request.getParameter("isPayment");

        Bill bill = new Bill();
        bill.setId(Integer.valueOf(id));
        bill.setProductName(productName);
        bill.setProductDesc(productDesc);
        bill.setProductUnit(productUnit);
        bill.setProductCount(new BigDecimal(productCount).setScale(2, BigDecimal.ROUND_DOWN));
        bill.setIsPayment(Integer.parseInt(isPayment));
        bill.setTotalPrice(new BigDecimal(totalPrice).setScale(2, BigDecimal.ROUND_DOWN));
        bill.setProviderId(Integer.parseInt(providerId));

        bill.setModifyBy(((User) request.getSession().getAttribute(Constants.USER_SESSION)).getId());
        bill.setModifyDate(new Date());
        boolean flag = false;
        BillService billService = new BillServiceImpl();

        Bill bill2 = billService.getBillById(bill.getId().toString());
        RepoService service = new RepoServiceImpl();
        Secret secret = service.queryForOne(String.valueOf(bill.getId()), "shit");
        Double total = secret.getTotal();
        BigDecimal count1 = bill.getProductCount();
//
//        int v1 = total.compareTo(count1.doubleValue());
//        if (v1 < 0) {
//            System.out.println("不满足");
//            request.setAttribute("msg", "添加失败,请重新输入,仓库余量不足");
//            request.getRequestDispatcher("billmodify.jsp").forward(request, response);
//        }
        BigDecimal count2 = bill2.getProductCount();
        int i = count2.compareTo(count1);
        if (i == -1) {//修改大了,库存要减少
            BigDecimal divide = count1.subtract(count2);
            Double v = divide.doubleValue();
            int i1 = total.compareTo(v);
            if (i1 == -1) {
                System.out.println("不满足");
                request.setAttribute("msg", "添加失败,请重新输入,仓库余量不足");
                request.getRequestDispatcher("billmodify.jsp").forward(request, response);
            }
            Double total1 = secret.getTotal();
            BigDecimal v10 = BigDecimal.valueOf(total1).subtract(BigDecimal.valueOf(v));
            secret.setTotal(v10.doubleValue());
        } else if (i == 1) {
            BigDecimal divide = count2.subtract(count1);
            Double total1 = secret.getTotal();
            Double v = divide.doubleValue();
            BigDecimal add = BigDecimal.valueOf(total1).add(BigDecimal.valueOf(v));
            secret.setTotal(add.doubleValue());
        }


        if (true) {
            billService.modify(bill);
            service.modify(secret);
            response.sendRedirect(request.getContextPath() + "/jsp/bill.do?method=query");
        } else {
            System.out.println("不满足");
            request.setAttribute("msg", "添加失败,请重新输入,仓库余量不足");
            request.getRequestDispatcher("billmodify.jsp").forward(request, response);
        }
    }

    private void delBill(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("billid");
        HashMap<String, String> resultMap = new HashMap<String, String>();
        if (!StringUtils.isNullOrEmpty(id)) {
            BillService billService = new BillServiceImpl();
            boolean flag = billService.deleteBillById(id);
            if (flag) {//删除成功
                resultMap.put("delResult", "true");
            } else {//删除失败
                resultMap.put("delResult", "false");
            }
        } else {
            resultMap.put("delResult", "notexit");
        }
        //把resultMap转换成json对象输出
        response.setContentType("application/json");
        PrintWriter outPrintWriter = response.getWriter();
        outPrintWriter.write(JSONArray.toJSONString(resultMap));
        outPrintWriter.flush();
        outPrintWriter.close();
    }


    private void jjj(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("不满足");
        request.setAttribute("msg", "添加失败,请重新输入,您需要输入仓库已经存在的商品");
        request.getRequestDispatcher("billadd.jsp").forward(request, response);
    }

    private void add(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String billCode = request.getParameter("billCode");
        String productName = request.getParameter("productName");
        String productUnit = request.getParameter("productUnit");
        String productCount = request.getParameter("productCount");
        String totalPrice = request.getParameter("totalPrice");
        String providerId = request.getParameter("providerId");
        String isPayment = request.getParameter("isPayment");

        BillService billService = new BillServiceImpl();
        Bill bill = new Bill();
        Secret secretR = new Secret();
        Secret secretG = new Secret();
        Boolean b1 = false;
        Boolean b2 = false;
        Boolean b3 = false;

        if (billCode != null && productName != null && productUnit != null && productCount != null && totalPrice != null && providerId != null && isPayment != null) {
            bill = billService.findByName(productName);
            if (bill.getId() == null) {
                jjj(request, response);
            }
            secretR.setBillName(productName);
            secretR.setTotal(Double.parseDouble(productCount));
            secretR.setId(bill.getId().longValue());
            RepoService repoService = new RepoServiceImpl();
            secretG = repoService.queryForOne(String.valueOf(bill.getId()), "fuck shit");
            if (secretG.getTotal() - secretR.getTotal() < 0) {
                jjj(request, response);
            }
            if (!secretR.getBillName().equals(secretG.getBillName())) {
                jjj(request, response);
            }
            secretG.setTotal(secretG.getTotal() - secretR.getTotal());
            bill = new Bill();
            bill.setBillCode(billCode);
            bill.setProductName(productName);
            bill.setProductDesc("null");
            bill.setProductUnit(productUnit);
            bill.setProductCount(new BigDecimal(productCount).setScale(2, BigDecimal.ROUND_DOWN));
            bill.setIsPayment(Integer.parseInt(isPayment));
            bill.setTotalPrice(new BigDecimal(totalPrice).setScale(2, BigDecimal.ROUND_DOWN));
            bill.setProviderId(Integer.parseInt(providerId));
            bill.setCreatedBy(((User) request.getSession().getAttribute(Constants.USER_SESSION)).getId());
            bill.setCreationDate(new Date());
            boolean flag = false;
            billService = new BillServiceImpl();
            flag = billService.add(bill);
            System.out.println("add flag -- > " + flag);
            if (flag) {
                repoService.modify(secretG);
                response.sendRedirect(request.getContextPath() + "/jsp/bill.do?method=query");
            } else {
                System.out.println("不满足");
                request.setAttribute("msg", "添加失败,请重新输入,您需要输入仓库已经存在的商品");
                request.getRequestDispatcher("billadd.jsp").forward(request, response);
            }
        } else {
            jjj(request, response);

        }
    }


    private void query(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Provider> providerList = new ArrayList<Provider>();
        ProviderService providerService = new ProviderServiceImpl();
        providerList = providerService.getProviderList("", "");
        request.setAttribute("providerList", providerList);

        String queryProductName = request.getParameter("queryProductName");
        String queryProviderId = request.getParameter("queryProviderId");
        String queryIsPayment = request.getParameter("queryIsPayment");
        if (StringUtils.isNullOrEmpty(queryProductName)) {
            queryProductName = "";
        }

        List<Bill> billList = new ArrayList<Bill>();
        BillService billService = new BillServiceImpl();
        Bill bill = new Bill();
        if (StringUtils.isNullOrEmpty(queryIsPayment)) {
            bill.setIsPayment(0);
        } else {
            bill.setIsPayment(Integer.parseInt(queryIsPayment));
        }

        if (StringUtils.isNullOrEmpty(queryProviderId)) {
            bill.setProviderId(0);
        } else {
            bill.setProviderId(Integer.parseInt(queryProviderId));
        }
        bill.setProductName(queryProductName);
        billList = billService.getBillList(bill);
        request.setAttribute("billList", billList);
        request.setAttribute("queryProductName", queryProductName);
        request.setAttribute("queryProviderId", queryProviderId);
        request.setAttribute("queryIsPayment", queryIsPayment);
        request.getRequestDispatcher("billlist.jsp").forward(request, response);

    }

    public static void main(String[] args) {
        System.out.println(new BigDecimal("23.235").setScale(2, BigDecimal.ROUND_HALF_DOWN));
    }

    /**
     * Initialization of the servlet. <br>
     *
     * @throws ServletException if an error occurs
     */

}
