package net.stackoverflow.cms.model.vo;

import lombok.*;

/**
 * 权限VO类
 *
 * @author 凉衫薄
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PermissionVO {

    private String id;
    private String name;
    private String description;
    private Integer deletable;
}
