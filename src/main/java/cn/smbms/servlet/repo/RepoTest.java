package cn.smbms.servlet.repo;

import cn.smbms.pojo.Repo;
import cn.smbms.service.repo.RepoServiceImpl;

import java.util.List;

public class RepoTest {

    public static void main(String[] args) {
            List<Repo> repoList = new RepoServiceImpl().getRepoList(null);
            for (Repo repo : repoList) {
                System.out.println(repo);
            }
        }
}
