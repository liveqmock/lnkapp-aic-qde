package org.fbi.aicqde.repository.dao;

import org.apache.ibatis.annotations.Select;

public interface AicqdeMapper {
    @Select("SELECT count(*) FROM  ptoper")
    int selectCount();
}
