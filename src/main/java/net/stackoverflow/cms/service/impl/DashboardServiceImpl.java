package net.stackoverflow.cms.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.stackoverflow.cms.constant.RedisPrefixConst;
import net.stackoverflow.cms.model.dto.CountDTO;
import net.stackoverflow.cms.model.dto.DiskInfoDTO;
import net.stackoverflow.cms.model.dto.UserStatusDTO;
import net.stackoverflow.cms.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.rmi.server.ExportException;
import java.util.*;

/**
 * 首页服务实现类
 *
 * @author 凉衫薄
 */
@Service
@Slf4j
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private UploadService uploadService;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CountDTO count() {
        CountDTO dto = new CountDTO();
        Integer userCount = userService.count();
        Integer roleCount = roleService.count();
        Integer menuCount = menuService.count();
        Integer fileCount = uploadService.count();
        dto.setFile(fileCount);
        dto.setRole(roleCount);
        dto.setUser(userCount);
        dto.setMenu(menuCount);
        return dto;
    }

    @Override
    @Transactional(rollbackFor = ExportException.class)
    public UserStatusDTO userStatus() {
        UserStatusDTO dto = new UserStatusDTO();
        Integer online = redisTemplate.keys(RedisPrefixConst.TOKEN_PREFIX + "*").size();
        Integer lock = redisTemplate.keys(RedisPrefixConst.LOCK_PREFIX + "*").size();
        Integer enable = userService.countEnable();
        Integer disable = userService.countDisable();
        Integer total = userService.count();
        dto.setTotal(total);
        dto.setDisable(disable);
        dto.setEnable(enable);
        dto.setLock(lock);
        dto.setOnline(online);
        return dto;
    }

    @Override
    public Map<String, Integer> topIp() {
        Set<String> keys = redisTemplate.keys(RedisPrefixConst.TOKEN_PREFIX + "*");
        Map<String, Integer> map = new HashMap<>();
        for (String key : keys) {
            Authentication authentication = (Authentication) redisTemplate.opsForValue().get(key);
            WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();
            String address = details.getRemoteAddress();
            if (map.get(address) == null) {
                map.put(address, 1);
            } else {
                int total = map.get(address) + 1;
                map.put(address, total);
            }
        }
        List<Map.Entry<String, Integer>> ips = new ArrayList<>(map.entrySet());
        Collections.sort(ips, (o1, o2) -> o2.getValue().compareTo(o1.getValue()));
        Map<String, Integer> sortMap = new LinkedHashMap<>();
        ips.forEach(entry -> sortMap.put(entry.getKey(), entry.getValue()));
        return sortMap;
    }

    @Override
    public DiskInfoDTO diskInfo() {
        File file = new File(".");
        Long total = file.getTotalSpace() / (1024 * 1024 * 1024);
        Long free = file.getFreeSpace() / (1024 * 1024 * 1024);
        Long used = (total - free) / (1024 * 1024 * 1024);
        DiskInfoDTO dto = new DiskInfoDTO(total, free, used);
        return dto;
    }
}
