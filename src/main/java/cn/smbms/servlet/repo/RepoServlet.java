package cn.smbms.servlet.repo;

import cn.smbms.pojo.*;
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

public class RepoServlet extends HttpServlet {
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

        String method = request.getParameter("method");
        if (method != null && method.equals("query")) {
            this.query(request, response);
//            this.query(request,response);
//        }else if(method != null && method.equals("add")){
//            this.add(request,response);
//        }else if(method != null && method.equals("view")){
//            this.getBillById(request,response,"billview.jsp");
//        }else if(method != null && method.equals("modify")){
//            this.getBillById(request,response,"billmodify.jsp");
//        }else if(method != null && method.equals("modifysave")){
//            this.modify(request,response);
        } else if (method != null && method.equals("delrepo")) {
            this.delRepo(request, response);
        } else if (method != null && method.equals("modify")) {
            this.modify(request, response, "repomodify.jsp");
        } else if (method != null && method.equals("modifysave")) {
            this.modifySave(request, response);
        }
//        }else if(method != null && method.equals("getproviderlist")){

    }

    private void modifySave(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        System.out.println("modify===============");

        String id = request.getParameter("billid");
        String providerCode = request.getParameter("secretProviderCode");
        String total = request.getParameter("total");
        String unit = request.getParameter("unit");

        System.out.println(id);
        System.out.println(providerCode);

        System.out.println("123123123123");
        Secret secret = new Secret();
        secret.setId(Long.parseLong(id));
        secret.setProviderCode(providerCode);
        secret.setTotal(Double.parseDouble(total));
        secret.setUnit(unit);

        System.out.println(secret);
        RepoService repoService = new RepoServiceImpl();
        Boolean flag = repoService.modify(secret);
        if (flag) {
            response.sendRedirect(request.getContextPath() + "/jsp/repo.do?method=query");
        } else {
            request.getRequestDispatcher("repomidify.jsp").forward(request, response);
        }

    }

    private void modify(HttpServletRequest request, HttpServletResponse response, String url)
            throws ServletException, IOException {
        String providerCode = request.getParameter("providerCode");
        String repoId = request.getParameter("repoid");
        if (!StringUtils.isNullOrEmpty(repoId) && !StringUtils.isNullOrEmpty(providerCode)) {
            RepoService repoService = new RepoServiceImpl();
            Secret secret = new Secret();
            secret = repoService.queryForOne(repoId, providerCode);
            request.setAttribute("secret", secret);
            request.getRequestDispatcher(url).forward(request, response);
        }
    }


    private void delRepo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("repoid");
        HashMap<String, String> resultMap = new HashMap<String, String>();
        if (!StringUtils.isNullOrEmpty(id)) {
            RepoService repoService = new RepoServiceImpl();
            boolean flag = repoService.deleteRepoById(id);
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

    private void query(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Secret secret = new Secret();

        String queryProviderCode = request.getParameter("queryProviderCode");
        String queryProviderName = request.getParameter("queryProviderName");
        String queryProductName = request.getParameter("queryProductName");

        if (StringUtils.isNullOrEmpty(queryProviderCode)) {
            ;
        } else {
            secret.setProviderCode(queryProviderCode);
        }
        if (StringUtils.isNullOrEmpty(queryProviderName)) {
            ;
        } else {
            secret.setProviderName(queryProviderName);
        }
        if (StringUtils.isNullOrEmpty(queryProductName)) {
            queryProductName = "";
        } else {
            secret.setProductName(queryProductName);
        }

        System.out.println(secret + "+++++++++++++++++++++++++++++++++++");
//=====================

        List<Secret> secretList = new ArrayList<Secret>();
        RepoService repoService = new RepoServiceImpl();
        secretList = repoService.getSecretList(secret);
        request.setAttribute("secretList", secretList);
        //========================
        List<Provider> providerList = new ArrayList<Provider>();
        ProviderService providerService = new ProviderServiceImpl();
        providerList = providerService.getProviderList("", "");
        request.setAttribute("providerList", providerList);
        //==================
        request.getRequestDispatcher("repolist.jsp").forward(request, response);
    }


    private void getSecretlist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("getSecretList    ========================= ");

        List<Secret> secretList = new ArrayList<Secret>();
        RepoService repoService = new RepoServiceImpl();
        secretList = repoService.getSecretList(null);
        //把providerList转换成json对象输出
        response.setContentType("application/json");
        PrintWriter outPrintWriter = response.getWriter();
        outPrintWriter.write(JSONArray.toJSONString(secretList));
        outPrintWriter.flush();
        outPrintWriter.close();
    }

    private void getRepolist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("getRepoList  ========================= ");

        List<Repo> repoList = new ArrayList<Repo>();
        RepoService repoService = new RepoServiceImpl();
        repoList = repoService.getRepoList(null);
        //把providerList转换成json对象输出
        response.setContentType("application/json");
        PrintWriter outPrintWriter = response.getWriter();
        outPrintWriter.write(JSONArray.toJSONString(repoList));
        outPrintWriter.flush();
        outPrintWriter.close();
    }
}
