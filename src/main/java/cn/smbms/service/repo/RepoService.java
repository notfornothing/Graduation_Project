package cn.smbms.service.repo;

import cn.smbms.pojo.Repo;
import cn.smbms.pojo.Secret;

import java.util.List;

public interface RepoService {

    public List<Repo> getRepoList(Repo repo);


    public List<Secret> getSecretList(Secret secret);

   public boolean deleteRepoById(String id);

   public Secret queryForOne(String repoId, String providerCode);

   public Boolean modify(Secret secret);

    boolean add(Repo repo);
}
