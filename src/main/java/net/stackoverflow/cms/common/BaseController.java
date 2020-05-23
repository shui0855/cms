package net.stackoverflow.cms.common;

import lombok.extern.slf4j.Slf4j;
import net.stackoverflow.cms.security.CmsUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

/**
 * Controller基类
 *
 * @author 凉衫薄
 */
@Slf4j
public class BaseController {

    /**
     * 获取UserDetails
     *
     * @return
     */
    protected CmsUserDetails getUserDetails() {
        return (CmsUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    /**
     * 获取WebAuthenticationDetails
     *
     * @return
     */
    protected WebAuthenticationDetails getDetails() {
        return (WebAuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
    }
}
