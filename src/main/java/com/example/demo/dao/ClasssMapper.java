package com.example.demo.dao;

import com.example.demo.model.Classs;
import com.example.demo.model.ClasssExample;
import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

@Mapper
public interface ClasssMapper {
    @SelectProvider(type=ClasssSqlProvider.class, method="countByExample")
    int countByExample(ClasssExample example);

    @DeleteProvider(type=ClasssSqlProvider.class, method="deleteByExample")
    int deleteByExample(ClasssExample example);

    @Delete({
        "delete from classs",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into classs (description)",
        "values (#{description,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(Classs record);

    @InsertProvider(type=ClasssSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(Classs record);

    @SelectProvider(type=ClasssSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR)
    })
    List<Classs> selectByExample(ClasssExample example);

    @Select({
        "select",
        "id, description",
        "from classs",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR)
    })
    Classs selectByPrimaryKey(Integer id);

    @UpdateProvider(type=ClasssSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Classs record, @Param("example") ClasssExample example);

    @UpdateProvider(type=ClasssSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Classs record, @Param("example") ClasssExample example);

    @UpdateProvider(type=ClasssSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Classs record);

    @Update({
        "update classs",
        "set description = #{description,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Classs record);
}