package fun.mizhuo.hrserver.mapper;

import fun.mizhuo.hrserver.model.JobLevel;

import java.util.List;

public interface JobLevelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JobLevel record);

    int insertSelective(JobLevel record);

    JobLevel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JobLevel record);

    int updateByPrimaryKey(JobLevel record);

    List<JobLevel> getAllJobTitle();

    Integer addJobTitle(JobLevel jobTitle);

    Integer updateJobTitle(JobLevel jobTitle);

    Integer deleteJobTitle(Integer id);

    Integer deleteMoreJobTitle(List<JobLevel> jobTitles);
}