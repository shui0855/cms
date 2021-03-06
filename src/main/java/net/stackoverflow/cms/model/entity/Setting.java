package net.stackoverflow.cms.model.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * (Setting)实体类
 *
 * @author 凉衫薄
 * @since 2020-09-26 20:22:45
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Setting implements Serializable {
    private static final long serialVersionUID = -56070523503491860L;

    private String id;
    private String title;
    private String head;
    private String copyright;
    private Date ts;
}