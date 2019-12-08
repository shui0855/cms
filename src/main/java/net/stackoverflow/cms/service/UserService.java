package net.stackoverflow.cms.service;

import net.stackoverflow.cms.common.Page;
import net.stackoverflow.cms.pojo.entity.Permission;
import net.stackoverflow.cms.pojo.entity.Role;
import net.stackoverflow.cms.pojo.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    List<User> selectByPage(Page page);

    List<User> selectByCondition(Map<String, Object> searchMap);

    User select(String id);

    int insert(User user);

    int batchInsert(List<User> users);

    int delete(String id);

    int batchDelete(List<String> ids);

    int update(User user);

    int batchUpdate(List<User> users);

    void grantRole(String userId, String roleCode);

    void revokeRole(String userId, String roleCode);

    List<Role> getRoleByUserId(String userId);

    List<Permission> getPermissionByUserId(String userId);
}