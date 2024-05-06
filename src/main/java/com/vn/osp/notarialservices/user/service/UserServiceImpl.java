package com.vn.osp.notarialservices.user.service;

import com.vn.osp.notarialservices.user.domain.GroupRoleBO;
import com.vn.osp.notarialservices.user.dto.*;
import com.vn.osp.notarialservices.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

/**
 * Created by longtran on 20/10/2016.
 */
@Component
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final GroupRoleConverter groupRoleConverter;
    private final AuthorityConverter authorityConverter;

    @Autowired
    public UserServiceImpl(final UserRepository userRepository,
                           final UserConverter userConverter,
                           final GroupRoleConverter groupRoleConverter,
                           final AuthorityConverter authorityConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
        this.groupRoleConverter = groupRoleConverter;
        this.authorityConverter = authorityConverter;
    }

    @Override
    public Optional<List<User>> getUserByFilter(String stringFiltler) {
        List<User> listUser = userRepository.getUserByFilter(stringFiltler)
                .map(userConverter::converts).orElse(null);
        return Optional.of(listUser);
    }

    @Override
    public Optional<BigInteger> countTotal(String stringFiltler) {
        return userRepository.countTotal(stringFiltler);
    }

    @Override
    public Optional<Integer> addUser(String xml_content) {
        return userRepository.addUser(xml_content);
    }

    @Override
    public Optional<Boolean> updateUser(String xml_content) {
        return userRepository.updateUser(xml_content);
    }

    @Override
    public Optional<Boolean> removeUserById(Long id) {
        return userRepository.removeUserById(id);
    }

    @Override
    public Optional<List<GroupRole>> getGroupRoleByFilter(String stringFiltler) {
        List<GroupRole> listGroupRole = userRepository.getGroupRoleByFilter(stringFiltler)
                .map(groupRoleConverter::converts).orElse(null);
        return Optional.of(listGroupRole);
    }

    @Override
    public Optional<BigInteger> groupRoleCountTotalByFilter(String stringFiltler) {
        return userRepository.groupRoleCountTotalByFilter(stringFiltler);
    }

    @Override
    public List<UserGroupRole> getUserGroupRoleByFilter(String stringFiltler) {
        return userRepository.getUserGroupRoleByFilter(stringFiltler);
    }

    @Override
    public Optional<Boolean> permissionUser(UserPermisson userPermisson) {
        try {
            String[] groupIds = userPermisson.getCb01().split(",");
            long userId = userPermisson.getUserId();
            userRepository.removeUserGroupRole(userId);
            for (int i = 0; i < groupIds.length; i++) {
                userRepository.addUserGroupRole(userId, Long.valueOf(groupIds[i]));
            }
            return Optional.of(true);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.of(false);
        }
    }

    @Override
    public Optional<Boolean> addGroupRoleAuthority(long grouprole_id, String authority_code, int value) {
        return userRepository.addGroupRoleAuthority(grouprole_id, authority_code, value);
    }

    @Override
    public Optional<Boolean> addGroupRole(CreateGroupRoleForm createGroupRoleForm) {
        try {
            //kiem tra dupplicate du lieu
            Integer number = userRepository.groupRoleCountTotalByFilter(" where grouprolename like '"+createGroupRoleForm.getGrouprolename()+"'").orElse(BigInteger.valueOf(0)).intValue();
            if(number > 0) {
                return Optional.of(false);
            }
            //insert du lieu vao bang npo_groprole
            GroupRole groupRole = new GroupRole();
            groupRole.setGrouprolename(createGroupRoleForm.getGrouprolename());
            groupRole.setDescription(createGroupRoleForm.getDescription());
            groupRole.setActive_flg(createGroupRoleForm.getActive_flg());
            groupRole.setEntry_user_id(createGroupRoleForm.getEntry_user_id());
            groupRole.setEntry_user_name(createGroupRoleForm.getEntry_user_name());
            BigInteger id = userRepository.addGroupRole(groupRole).orElse(BigInteger.valueOf(0));

            //insert du lieu vao bang npo_grouprole_authority
            String systemManagerCheckList = createGroupRoleForm.getCb01();
            String functionCheckList = createGroupRoleForm.getCb02();
            String reportCheckList = createGroupRoleForm.getCb03();

            //add vao bang npo_grouprole
            if (systemManagerCheckList != null && !systemManagerCheckList.equals("null")) {
                String[] array = systemManagerCheckList.split(",");
                int a = 0;
                for (int i = 0; i < array.length; i++) {
                    String[] result = array[i].split("_");
                    //add group_id, code, value
                    int temp = Integer.valueOf(result[1]).byteValue();
                    List<GrouproleAuthority> grouproleAuthorities = userRepository.getGroupRoleAuthority(" where grouprole_id =" + id.intValue() + " and authority_code like '" + result[0] + "'");
                    if (grouproleAuthorities == null || grouproleAuthorities.size() == 0) {
                        userRepository.addGroupRoleAuthority(id.intValue(), result[0], Integer.valueOf(result[1]).byteValue());
                    } else {
                        int value = grouproleAuthorities.get(0).getValue();
                        value = value | temp;
                        userRepository.updateGroupRoleAuthorityValue(id.intValue(), result[0], value);
                    }

                }
            }
            if (functionCheckList != null && !functionCheckList.equals("null")) {
                String[] array = functionCheckList.split(",");
                for (int i = 0; i < array.length; i++) {
                    String[] result = array[i].split("_");
                    //add group_id, code, value
                    int temp = Integer.valueOf(result[1]).byteValue();
                    List<GrouproleAuthority> grouproleAuthorities = userRepository.getGroupRoleAuthority(" where grouprole_id =" + id.intValue() + " and authority_code like '" + result[0] + "'");
                    if (grouproleAuthorities == null || grouproleAuthorities.size() == 0) {
                        userRepository.addGroupRoleAuthority(id.intValue(), result[0], Integer.valueOf(result[1]).byteValue());
                    } else {
                        int value = grouproleAuthorities.get(0).getValue();
                        value = value | temp;
                        userRepository.updateGroupRoleAuthorityValue(id.intValue(), result[0], value);
                    }
                }
            }
            if (reportCheckList != null && !reportCheckList.equals("null")) {
                String[] array = reportCheckList.split(",");
                for (int i = 0; i < array.length; i++) {
                    String[] result = array[i].split("_");
                    //add group_id, code, value
                    int temp = Integer.valueOf(result[1]).byteValue();
                    List<GrouproleAuthority> grouproleAuthorities = userRepository.getGroupRoleAuthority(" where grouprole_id =" + id.intValue() + " and authority_code like '" + result[0] + "'");
                    if (grouproleAuthorities == null || grouproleAuthorities.size() == 0) {
                        userRepository.addGroupRoleAuthority(id.intValue(), result[0], Integer.valueOf(result[1]).byteValue());
                    } else {
                        int value = grouproleAuthorities.get(0).getValue();
                        value = value | temp;
                        userRepository.updateGroupRoleAuthorityValue(id.intValue(), result[0], value);
                    }
                }
            }

            return Optional.of(true);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.of(false);
        }
    }

    @Override
    public Optional<Boolean> updateGroupRole(CreateGroupRoleForm createGroupRoleForm) {
        try {
            long id = createGroupRoleForm.getGroupRoleId();
            //update du lieu vao bang npo_groprole
            GroupRole groupRole = new GroupRole();
            groupRole.setId(id);
            groupRole.setGrouprolename(createGroupRoleForm.getGrouprolename());
            groupRole.setDescription(createGroupRoleForm.getDescription());
            groupRole.setActive_flg(createGroupRoleForm.getActive_flg());
            groupRole.setUpdate_user_id(createGroupRoleForm.getUpdate_user_id());
            groupRole.setUpdate_user_name(createGroupRoleForm.getUpdate_user_name());
            Boolean flag = userRepository.updateGroupRole(groupRole).orElse(false);
            System.out.println("flag=" + flag);

            // xoa cac group authority cu
            deleteGroupRoleAuthority(createGroupRoleForm.getGroupRoleId());

            //insert du lieu vao bang npo_grouprole_authority
            String systemManagerCheckList = createGroupRoleForm.getCb01();
            String functionCheckList = createGroupRoleForm.getCb02();
            String reportCheckList = createGroupRoleForm.getCb03();

            //add vao bang npo_grouprole_authority
            if (systemManagerCheckList != null && !systemManagerCheckList.equals("null")) {
                String[] array = systemManagerCheckList.split(",");
                int a = 0;
                for (int i = 0; i < array.length; i++) {
                    String[] result = array[i].split("_");
                    //add group_id, code, value
                    int temp = Integer.valueOf(result[1]).byteValue();
                    List<GrouproleAuthority> grouproleAuthorities = userRepository.getGroupRoleAuthority(" where grouprole_id =" + id + " and authority_code like '" + result[0] + "'");
                    if (grouproleAuthorities == null || grouproleAuthorities.size() == 0) {
                        userRepository.addGroupRoleAuthority(id, result[0], Integer.valueOf(result[1]).byteValue());
                    } else {
                        int value = grouproleAuthorities.get(0).getValue();
                        value = value | temp;
                        userRepository.updateGroupRoleAuthorityValue(id, result[0], value);
                    }

                }
            }
            if (functionCheckList != null && !functionCheckList.equals("null")) {
                String[] array = functionCheckList.split(",");
                for (int i = 0; i < array.length; i++) {
                    String[] result = array[i].split("_");
                    //add group_id, code, value
                    int temp = Integer.valueOf(result[1]).byteValue();
                    List<GrouproleAuthority> grouproleAuthorities = userRepository.getGroupRoleAuthority(" where grouprole_id =" + id + " and authority_code like '" + result[0] + "'");
                    if (grouproleAuthorities == null || grouproleAuthorities.size() == 0) {
                        userRepository.addGroupRoleAuthority(id, result[0], Integer.valueOf(result[1]).byteValue());
                    } else {
                        int value = grouproleAuthorities.get(0).getValue();
                        value = value | temp;
                        userRepository.updateGroupRoleAuthorityValue(id, result[0], value);
                    }
                }
            }
            if (reportCheckList != null && !reportCheckList.equals("null")) {
                String[] array = reportCheckList.split(",");
                for (int i = 0; i < array.length; i++) {
                    String[] result = array[i].split("_");
                    //add group_id, code, value
                    int temp = Integer.valueOf(result[1]).byteValue();
                    List<GrouproleAuthority> grouproleAuthorities = userRepository.getGroupRoleAuthority(" where grouprole_id =" + id + " and authority_code like '" + result[0] + "'");
                    if (grouproleAuthorities == null || grouproleAuthorities.size() == 0) {
                        userRepository.addGroupRoleAuthority(id, result[0], Integer.valueOf(result[1]).byteValue());
                    } else {
                        int value = grouproleAuthorities.get(0).getValue();
                        value = value | temp;
                        userRepository.updateGroupRoleAuthorityValue(id, result[0], value);
                    }
                }
            }

            return Optional.of(true);
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.of(false);
        }
    }

    @Override
    public Optional<Boolean> deleteGroupRoleAuthority(Long id) {
        return userRepository.deleteGroupRoleAuthority(id);
    }

    @Override
    public Optional<Boolean> deleteGroupRole(Long id) {
        return userRepository.deleteGroupRole(id);
    }

    @Override
    public Optional<List<Authority>> getAuthorityByFilter(String stringFilter) {
        List<Authority> listAuthority = userRepository.getAuthorityByFilter(stringFilter)
                .map(authorityConverter::converts).orElse(null);
        return Optional.of(listAuthority);
    }

    @Override
    public List<GrouproleAuthority> getGroupRoleAuthority(String stringFitler) {
        return userRepository.getGroupRoleAuthority(stringFitler);
    }

    @Override
    public Optional<BigInteger> countUserAuthorityByFilter(String stringFitler) {
        return userRepository.countUserAuthorityByFilter(stringFitler);
    }

    @Override
    public List<UserAuthority> getUserAuthorityByFilter(String stringFitler) {
        return userRepository.getUserAuthorityByFilter(stringFitler);
    }


    /*@Override
    public Optional<User> findByUserNameAndPassword(String username, String password) throws NoSuchUserException {
        User user = userRepository.findByUserNameAndPassword(username, password)
                .map(userConverter::convert)
                .orElseThrow(() -> new NoSuchUserException(username));
        return Optional.of(user);
    }

    @Override
    public Optional<List<User>> updateUpdateManyUsers(String xml_content) throws NoSuchUserException {
        List<User> listUser = userRepository.updateUpdateManyUsers(xml_content)
                .map(userConverter::converts)
                .orElseThrow(() -> new NoSuchUserException(xml_content));
        return Optional.of(listUser);
    }*/
}
