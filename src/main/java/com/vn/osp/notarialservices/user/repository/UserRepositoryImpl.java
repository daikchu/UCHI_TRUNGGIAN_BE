/*
 * Copyright 2008-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.vn.osp.notarialservices.user.repository;

//import com.vn.osp.notarialservices.user.domain.UserBO;

import com.vn.osp.notarialservices.user.domain.AuthorityBO;
import com.vn.osp.notarialservices.user.domain.GroupRoleBO;
import com.vn.osp.notarialservices.user.domain.UserBO;
import com.vn.osp.notarialservices.user.dto.GroupRole;
import com.vn.osp.notarialservices.user.dto.GrouproleAuthority;
import com.vn.osp.notarialservices.user.dto.UserAuthority;
import com.vn.osp.notarialservices.user.dto.UserGroupRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaContext;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Dummy implementation to allow check for invoking a custom implementation.
 *
 * @author longtran on 27/10/2016
 */
public class UserRepositoryImpl implements UserRepositoryCustom {

    private static final Logger LOG = LoggerFactory.getLogger(UserRepositoryImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public UserRepositoryImpl(JpaContext context) {
        Assert.notNull(context, "JpaContext must not be null!");
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.jpa.repository.sample.AccessHistoryRepositoryCustom#findByOverrridingMethod()
     */
    public void findByOverridingMethod() {
        LOG.info("A method overriding a finder was invoked!");
    }

    @Override
    public void delete(Integer id) {
        LOG.info("A method overriding a finder was invoked!");
    }

    @Override
    public Optional<List<UserBO>> getUserByFilter(String stringFiller) {
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("stp_npo_user_select_by_filter");
            storedProcedureQuery.setParameter("stringFilter", stringFiller);
            // execute StoredProcedureQuery
            storedProcedureQuery.execute();
            List<UserBO> list = storedProcedureQuery.getResultList();
            return Optional.of(list);
        } catch (Exception e) {
            LOG.error("Có lỗi xảy ra khi thao tác csdl tại hàm getUserByFilter");
            //e.printStackTrace();
            return Optional.of(new ArrayList<UserBO>());
        }
    }

    @Override
    public Optional<BigInteger> countTotal(String stringFiltler) {
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("stp_npo_user_count_total_by_filter");
            storedProcedureQuery.setParameter("stringFilter", stringFiltler);
            // execute StoredProcedureQuery
            storedProcedureQuery.execute();
            BigInteger result = (BigInteger) storedProcedureQuery.getSingleResult();
            return Optional.of(result);
        } catch (Exception e) {
            LOG.error("Có lỗi xảy ra khi thao tác csdl tại hàm countTotal");
            //e.printStackTrace();
            return Optional.of(BigInteger.valueOf(0));
        }
    }

    @Override
    public Optional<Integer> addUser(String xml_content) {
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("stp_npo_user_add");
            storedProcedureQuery.setParameter("xml_content", xml_content);
            storedProcedureQuery.execute();
            Integer id = (Integer) storedProcedureQuery.getSingleResult();
            return Optional.of(id);
        } catch (Exception e) {
            //System.out.println("loi duplicate account");
            LOG.error("Có lỗi xảy ra khi thao tác csdl tại hàm addUser");
            //e.printStackTrace();
            return Optional.of(-1);

        }
    }

    @Override
    public Optional<Boolean> updateUser(String xml_content) {
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("stp_npo_user_update");
            storedProcedureQuery.setParameter("xml_content", xml_content);
            storedProcedureQuery.execute();
            return Optional.of(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.of(false);
    }

    @Override
    public Optional<Boolean> removeUserById(Long id) {
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("stp_npo_user_delete_by_id");
            storedProcedureQuery.setParameter("id", id);
            storedProcedureQuery.execute();
            return Optional.of(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.of(false);
    }

    @Override
    public Optional<List<GroupRoleBO>> getGroupRoleByFilter(String stringFiltler) {
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("stp_npo_grouprole_select_by_filter");
            storedProcedureQuery.setParameter("stringFilter", stringFiltler);
            // execute StoredProcedureQuery
            storedProcedureQuery.execute();
            List<GroupRoleBO> list = storedProcedureQuery.getResultList();
            return Optional.of(list);
        } catch (Exception e) {
            LOG.error("Có lỗi xảy ra khi thao tác csdl tại hàm getGroupRoleByFilter");
            //e.printStackTrace();
            return Optional.of(new ArrayList<GroupRoleBO>());
        }
    }

    @Override
    public Optional<BigInteger> groupRoleCountTotalByFilter(String stringFiltler) {
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("stp_npo_grouprole_count_total");
            storedProcedureQuery.setParameter("stringFilter", stringFiltler);
            // execute StoredProcedureQuery
            storedProcedureQuery.execute();
            BigInteger result = (BigInteger) storedProcedureQuery.getSingleResult();
            return Optional.of(result);
        } catch (Exception e) {
            LOG.error("Có lỗi xảy ra khi thao tác csdl tại hàm groupRoleCountTotalByFilter");
            e.printStackTrace();
            return Optional.of(BigInteger.valueOf(0));
        }
    }

    @Override
    public List<UserGroupRole> getUserGroupRoleByFilter(String stringFiltler) {
        try {
            ArrayList<UserGroupRole> userGroupRoles = new ArrayList<UserGroupRole>();
            StoredProcedureQuery storedProcedure = entityManager.createNamedStoredProcedureQuery("stp_npo_user_get_grouprole_by_user");

            storedProcedure.setParameter("stringFilter", stringFiltler);
            storedProcedure.execute();

            List<Object[]> results = storedProcedure.getResultList();

            results.stream().forEach((record) -> {
                UserGroupRole userGroupRole = new UserGroupRole();

                userGroupRole.setUserId(record[0] == null ? null : ((Integer) record[0]).longValue());
                userGroupRole.setGroupid(record[1] == null ? null : ((Integer) record[1]).longValue());
                userGroupRole.setGrouprolename(record[2] == null ? null : ((String) record[2]));

                userGroupRoles.add(userGroupRole);
            });
            return userGroupRoles;
        } catch (Exception e) {
            LOG.error("Có lỗi xảy ra khi thao tác csdl tại hàm getUserGroupRoleByFilter");
            return new ArrayList<UserGroupRole>();
        }
    }

    @Override
    public Optional<Boolean> removeUserGroupRole(long userId) {
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("stp_npo_user_authority_delete");
            storedProcedureQuery.setParameter("userId", userId);
            storedProcedureQuery.execute();
            return Optional.of(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.of(false);
    }

    @Override
    public Optional<Boolean> addUserGroupRole(long userId, long groupId) {
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("stp_npo_user_authority_add");
            storedProcedureQuery.setParameter("userId", userId);
            storedProcedureQuery.setParameter("groupId", groupId);
            storedProcedureQuery.execute();
            return Optional.of(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.of(false);
    }

    @Override
    public Optional<Boolean> addGroupRoleAuthority(long grouprole_id, String authority_code, int value) {
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("stp_npo_grouprole_authority_add");
            storedProcedureQuery.setParameter("grouprole_id", grouprole_id);
            storedProcedureQuery.setParameter("authority_code", authority_code);
            storedProcedureQuery.setParameter("value", value);
            storedProcedureQuery.execute();
            return Optional.of(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.of(false);
    }

    @Override
    public Optional<BigInteger> addGroupRole(GroupRole groupRole) {
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("stp_npo_grouprole_add");
            storedProcedureQuery.setParameter("grouprolename", groupRole.getGrouprolename());
            storedProcedureQuery.setParameter("description", groupRole.getDescription());
            storedProcedureQuery.setParameter("active_flg", groupRole.getActive_flg());
            storedProcedureQuery.setParameter("entry_user_id", groupRole.getActive_flg());
            storedProcedureQuery.setParameter("entry_user_name", groupRole.getEntry_user_name());

            // execute StoredProcedureQuery
            storedProcedureQuery.execute();
            BigInteger result = (BigInteger) storedProcedureQuery.getSingleResult();
            return Optional.of(result);
        } catch (Exception e) {
            //LOG.error("Có lỗi xảy ra khi thao tác csdl tại hàm countTotal");
            e.printStackTrace();
            return Optional.of(BigInteger.valueOf(0));
        }
    }

    @Override
    public Optional<Boolean> updateGroupRole(GroupRole groupRole) {
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("stp_npo_grouprole_update");
            storedProcedureQuery.setParameter("id", groupRole.getGroupRoleId());
            storedProcedureQuery.setParameter("grouprolename", groupRole.getGrouprolename());
            storedProcedureQuery.setParameter("description", groupRole.getDescription());
            storedProcedureQuery.setParameter("active_flg", groupRole.getActive_flg());
            storedProcedureQuery.setParameter("update_user_id", groupRole.getUpdate_user_id());
            storedProcedureQuery.setParameter("update_user_name", groupRole.getUpdate_user_name());
            storedProcedureQuery.execute();
            return Optional.of(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.of(false);
    }

    @Override
    public Optional<List<AuthorityBO>> getAuthorityByFilter(String stringFilter) {
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("stp_npo_authority_select_by_filter");
            storedProcedureQuery.setParameter("stringFilter", stringFilter);
            // execute StoredProcedureQuery
            storedProcedureQuery.execute();
            List<AuthorityBO> list = storedProcedureQuery.getResultList();
            return Optional.of((List<AuthorityBO>) list);
        } catch (Exception e) {
            LOG.error("Có lỗi xảy ra khi thao tác csdl tại hàm getAuthorityByFilter");
            //e.printStackTrace();
            return Optional.of(new ArrayList<AuthorityBO>());
        }
    }

    @Override
    public List<GrouproleAuthority> getGroupRoleAuthority(String stringFitler) {
        try {
            ArrayList<GrouproleAuthority> grouproleAuthorities = new ArrayList<GrouproleAuthority>();
            StoredProcedureQuery storedProcedure = entityManager.createNamedStoredProcedureQuery("stp_npo_grouprole_authority_select_by_filter");

            storedProcedure.setParameter("stringFilter", stringFitler);
            storedProcedure.execute();

            List<Object[]> results = storedProcedure.getResultList();
            results.stream().forEach((record) -> {
                GrouproleAuthority grouproleAuthority = new GrouproleAuthority();
                grouproleAuthority.setGrouprole_id(record[0] == null ? 0 : ((Integer) record[0]).longValue());
                grouproleAuthority.setAuthority_code(record[1] == null ? null : ((String) record[1]));
                grouproleAuthority.setValue(record[2] == null ? 0 : ((Integer) record[2]).intValue());

                grouproleAuthorities.add(grouproleAuthority);
            });
            return grouproleAuthorities;
        } catch (Exception e) {
            LOG.error("Có lỗi xảy ra khi thao tác csdl tại hàm getGroupRoleAuthority");
            //e.printStackTrace();
            return new ArrayList<GrouproleAuthority>();
        }
    }

    @Override
    public Optional<Boolean> updateGroupRoleAuthorityValue(long grouprole_id, String authority_code, int value) {
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("stp_npo_grouprole_authority_update_value");
            storedProcedureQuery.setParameter("grouprole_id", grouprole_id);
            storedProcedureQuery.setParameter("authority_code", authority_code);
            storedProcedureQuery.setParameter("value", value);
            storedProcedureQuery.execute();
            return Optional.of(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.of(false);
    }

    @Override
    public Optional<Boolean> deleteGroupRoleAuthority(Long id) {
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("stp_npo_grouprole_authority_delete");
            storedProcedureQuery.setParameter("grouprole_id", id);
            storedProcedureQuery.execute();
            return Optional.of(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.of(false);
    }

    @Override
    public Optional<Boolean> deleteGroupRole(Long id) {
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("stp_npo_grouprole_delete");
            storedProcedureQuery.setParameter("id", id);
            storedProcedureQuery.execute();
            return Optional.of(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.of(false);
    }

    @Override
    public Optional<BigInteger> countUserAuthorityByFilter(String stringFitler) {
        try {
            StoredProcedureQuery storedProcedureQuery = entityManager.createNamedStoredProcedureQuery("stp_npo_user_authority_count_by_filter");
            storedProcedureQuery.setParameter("stringFilter", stringFitler);
            // execute StoredProcedureQuery
            storedProcedureQuery.execute();
            BigInteger result = (BigInteger) storedProcedureQuery.getSingleResult();
            return Optional.of(result);
        } catch (Exception e) {
            LOG.error("Có lỗi xảy ra khi thao tác csdl tại hàm countUserAuthorityByFilter");
            //e.printStackTrace();
            return Optional.of(BigInteger.valueOf(0));
        }
    }

    @Override
    public List<UserAuthority> getUserAuthorityByFilter(String stringFitler) {
        try {
            ArrayList<UserAuthority> userAuthorities = new ArrayList<UserAuthority>();
            StoredProcedureQuery storedProcedure = entityManager.createNamedStoredProcedureQuery("stp_npo_user_get_grouprole_by_user");

            storedProcedure.setParameter("stringFilter", stringFitler);
            storedProcedure.execute();

            List<Object[]> results = storedProcedure.getResultList();

            results.stream().forEach((record) -> {
                UserAuthority userAuthority = new UserAuthority();

                userAuthority.setUser_id(record[0] == null ? null : ((Integer) record[0]).longValue());
                userAuthority.setGroupid(record[1] == null ? null : ((Integer) record[1]).longValue());

                userAuthorities.add(userAuthority);
            });
            return userAuthorities;
        } catch (Exception e) {
            LOG.error("Có lỗi xảy ra khi thao tác csdl tại hàm getUserAuthorityByFilter");
            return new ArrayList<UserAuthority>();
        }
    }

    /*@Override
    public Optional<UserBO> findByUserNameAndPassword(String usename, String password) {
        LOG.info("Retrieves users by useName and password. Acts as a dummy method declaration to test finder query creation.");
        StoredProcedureQuery findByUserNameAndPassword = entityManager.createNamedStoredProcedureQuery("findByUserNameAndPassWord");
        StoredProcedureQuery storedProcedure = findByUserNameAndPassword
                                                                    .setParameter("user_name", usename)
                                                                    .setParameter("pass_word", password);
        // execute StoredProcedureQuery
        storedProcedure.execute();
        return Optional.ofNullable((UserBO)storedProcedure.getSingleResult());
    }

    @Override
    public Optional<List<UserBO>> updateUpdateManyUsers(String xml_content) throws NoSuchUserException {
        LOG.info("Update Many Users");
        StoredProcedureQuery storedProcedureQueryUpdateUserAccount = entityManager.createNamedStoredProcedureQuery("updateUserAccount");
        StoredProcedureQuery storedProcedureQuery = storedProcedureQueryUpdateUserAccount.setParameter("xml_content", xml_content);
        // execute StoredProcedureQuery
        storedProcedureQuery.execute();
        List<UserBO> list = new ArrayList<UserBO>();
        return Optional.ofNullable(list);
    }*/
}
