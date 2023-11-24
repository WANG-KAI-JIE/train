package top.kjwang.train.business.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import top.kjwang.train.business.domain.Programmer;
import top.kjwang.train.business.domain.ProgrammerExample;

public interface ProgrammerMapper {
    long countByExample(ProgrammerExample example);

    int deleteByExample(ProgrammerExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Programmer row);

    int insertSelective(Programmer row);

    List<Programmer> selectByExample(ProgrammerExample example);

    Programmer selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") Programmer row, @Param("example") ProgrammerExample example);

    int updateByExample(@Param("row") Programmer row, @Param("example") ProgrammerExample example);

    int updateByPrimaryKeySelective(Programmer row);

    int updateByPrimaryKey(Programmer row);
}