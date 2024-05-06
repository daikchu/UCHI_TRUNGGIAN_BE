package com.vn.osp.notarialservices.citizenVerifyPackage.repository;

//import com.vn.osp.notarialservices.Package.repository.PackageRepositoryCustom;
import com.vn.osp.notarialservices.citizenVerifyPackage.domain.CitizenVerifyPackageBO;
import com.vn.osp.notarialservices.common.repository.BaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitizenVerifyPackageRepository extends JpaRepository<CitizenVerifyPackageBO, String> {
    CitizenVerifyPackageBO findFirstByCodeEquals(String code);
}
