package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysScan;

/**
 * 扫描配置Mapper接口
 * 
 * @author ruoyi
 * @date 2023-11-09
 */
public interface SysScanMapper 
{
    /**
     * 查询扫描配置
     * 
     * @param scanRuleId 扫描配置主键
     * @return 扫描配置
     */
    public SysScan selectSysScanByScanRuleId(Long scanRuleId);

    /**
     * 查询扫描配置列表
     * 
     * @param sysScan 扫描配置
     * @return 扫描配置集合
     */
    public List<SysScan> selectSysScanList(SysScan sysScan);

    /**
     * 新增扫描配置
     * 
     * @param sysScan 扫描配置
     * @return 结果
     */
    public int insertSysScan(SysScan sysScan);

    /**
     * 修改扫描配置
     * 
     * @param sysScan 扫描配置
     * @return 结果
     */
    public int updateSysScan(SysScan sysScan);

    /**
     * 删除扫描配置
     * 
     * @param scanRuleId 扫描配置主键
     * @return 结果
     */
    public int deleteSysScanByScanRuleId(Long scanRuleId);

    /**
     * 批量删除扫描配置
     * 
     * @param scanRuleIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysScanByScanRuleIds(Long[] scanRuleIds);
}
