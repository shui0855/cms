package net.stackoverflow.cms.model.vo;

import lombok.*;

import java.util.List;

/**
 * 分配权限VO类
 *
 * @author 凉衫薄
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GrantPermissionVO {

    private String roleId;
    private List<String> permissionIds;
}
