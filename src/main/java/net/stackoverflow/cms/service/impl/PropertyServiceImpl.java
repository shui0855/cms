package net.stackoverflow.cms.service.impl;

import lombok.extern.slf4j.Slf4j;
import net.stackoverflow.cms.common.QueryWrapper.QueryWrapperBuilder;
import net.stackoverflow.cms.dao.PropertyDAO;
import net.stackoverflow.cms.model.dto.PropertyDTO;
import net.stackoverflow.cms.model.entity.Property;
import net.stackoverflow.cms.service.PropertyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 属性配置服务实现类
 *
 * @author 凉衫薄
 */
@Service
@Slf4j
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyDAO propertyDAO;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PropertyDTO findByKey(String key) {
        PropertyDTO propertyDTO = null;
        if (!StringUtils.isEmpty(key)) {
            QueryWrapperBuilder builder = new QueryWrapperBuilder();
            builder.eq("key", key);
            List<Property> properties = propertyDAO.querySelect(builder.build());
            if (!CollectionUtils.isEmpty(properties)) {
                Property property = properties.get(0);
                propertyDTO = new PropertyDTO();
                BeanUtils.copyProperties(property, propertyDTO);
            }
        }
        return propertyDTO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<PropertyDTO> findByKeys(List<String> keys) {
        List<PropertyDTO> dtos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(keys)) {
            QueryWrapperBuilder builder = new QueryWrapperBuilder();
            builder.in("key", keys);
            List<Property> properties = propertyDAO.querySelect(builder.build());
            properties.forEach(property -> {
                PropertyDTO dto = new PropertyDTO();
                BeanUtils.copyProperties(property, dto);
                dtos.add(dto);
            });
        }
        return dtos;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchUpdateByKey(List<PropertyDTO> dtos) {
        for (PropertyDTO dto : dtos) {
            updateByKey(dto.getKey(), dto.getValue());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateByKey(String key, String value) {
        QueryWrapperBuilder builder = new QueryWrapperBuilder();
        builder.set("value", value);
        builder.set("ts", new Date());
        builder.eq("key", key);
        propertyDAO.queryUpdate(builder.build());
    }

}
