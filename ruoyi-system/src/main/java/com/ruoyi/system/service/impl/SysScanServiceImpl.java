package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysScanMapper;
import com.ruoyi.system.domain.SysScan;
import com.ruoyi.system.service.ISysScanService;

/**
 * 扫描配置Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-11-09
 */
@Service
public class SysScanServiceImpl implements ISysScanService 
{
    @Autowired
    private SysScanMapper sysScanMapper;

    /**
     * 查询扫描配置
     * 
     * @param scanRuleId 扫描配置主键
     * @return 扫描配置
     */
    @Override
    public SysScan selectSysScanByScanRuleId(Long scanRuleId)
    {
        return sysScanMapper.selectSysScanByScanRuleId(scanRuleId);
    }

    /**
     * 查询扫描配置列表
     * 
     * @param sysScan 扫描配置
     * @return 扫描配置
     */
    @Override
    public List<SysScan> selectSysScanList(SysScan sysScan)
    {
        return sysScanMapper.selectSysScanList(sysScan);
    }

    /**
     * 新增扫描配置
     * 
     * @param sysScan 扫描配置
     * @return 结果
     */
    @Override
    public int insertSysScan(SysScan sysScan)
    {
        return sysScanMapper.insertSysScan(sysScan);
    }

    /**
     * 修改扫描配置
     * 
     * @param sysScan 扫描配置
     * @return 结果
     */
    @Override
    public int updateSysScan(SysScan sysScan)
    {
        return sysScanMapper.updateSysScan(sysScan);
    }

    /**
     * 批量删除扫描配置
     * 
     * @param scanRuleIds 需要删除的扫描配置主键
     * @return 结果
     */
    @Override
    public int deleteSysScanByScanRuleIds(Long[] scanRuleIds)
    {
        return sysScanMapper.deleteSysScanByScanRuleIds(scanRuleIds);
    }

    /**
     * 删除扫描配置信息
     * 
     * @param scanRuleId 扫描配置主键
     * @return 结果
     */
    @Override
    public int deleteSysScanByScanRuleId(Long scanRuleId)
    {
        return sysScanMapper.deleteSysScanByScanRuleId(scanRuleId);
    }
}
