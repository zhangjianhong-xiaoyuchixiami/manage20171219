package org.qydata.mapper.mapper2;

import org.qydata.dst.api.Aid;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/19.
 */
public interface ValidSelectMapper {

    /**
     * 根据请求地址查询上游
     * @param map
     * @return
     */
    public List<Aid> queryAidByUrl(Map<String, Object> map);

}
