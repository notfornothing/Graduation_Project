package cn.smbms.servlet.repo;

import cn.smbms.pojo.Repo;
import cn.smbms.pojo.Secret;
import cn.smbms.service.repo.RepoServiceImpl;

import java.util.List;

public class RepoTest {

    public static void main(String[] args) {

        RepoTest.repoListMethod();
        RepoTest.secretListMethod();

        
    }

    public static void secretListMethod() {

        Secret secret = new Secret();
        secret.setProviderName("111111");
        List<Secret> secretList = new RepoServiceImpl().getSecretList(null);
        for (Secret secret11 : secretList) {
            System.out.println(secret11);
        }
    }

    public static void repoListMethod() {
        Repo repo = new Repo();
        repo.setProviderName("优百商贸有限公司");
        List<Repo> repoList = new RepoServiceImpl().getRepoList(repo);
        for (Repo repo11 : repoList) {
            System.out.println(repo11);
        }
    }
}
