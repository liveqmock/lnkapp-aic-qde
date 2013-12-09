package org.fbi.aicqde.repository.dao.common;

import org.apache.ibatis.annotations.Select;

public interface AicqdeMapper {
    @Select("SELECT count(*) FROM  ptoper")
    int selectCount();
}
