package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 扫描配置对象 sys_scan
 * 
 * @author ruoyi
 * @date 2023-11-09
 */
public class SysScan extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long scanRuleId;

    /** 规则类型 */
    @Excel(name = "规则类型")
    private String scanRuleType;

    /** 规则ID */
    @Excel(name = "规则ID")
    private String singleRuleId;

    /** 污点类型 */
    @Excel(name = "污点类型")
    private String scanTaintType;

    /** 命名空间 */
    @Excel(name = "命名空间")
    private String scanNamesapce;

    /** 类名 */
    @Excel(name = "类名")
    private String scanClassName;

    /** 方法名 */
    @Excel(name = "方法名")
    private String scanMethodName;

    /** 参数 */
    @Excel(name = "参数")
    private Long scanPara;

    public void setScanRuleId(Long scanRuleId) 
    {
        this.scanRuleId = scanRuleId;
    }

    public Long getScanRuleId() 
    {
        return scanRuleId;
    }
    public void setScanRuleType(String scanRuleType) 
    {
        this.scanRuleType = scanRuleType;
    }

    public String getScanRuleType() 
    {
        return scanRuleType;
    }
    public void setSingleRuleId(String singleRuleId) 
    {
        this.singleRuleId = singleRuleId;
    }

    public String getSingleRuleId() 
    {
        return singleRuleId;
    }
    public void setScanTaintType(String scanTaintType) 
    {
        this.scanTaintType = scanTaintType;
    }

    public String getScanTaintType() 
    {
        return scanTaintType;
    }
    public void setScanNamesapce(String scanNamesapce) 
    {
        this.scanNamesapce = scanNamesapce;
    }

    public String getScanNamesapce() 
    {
        return scanNamesapce;
    }
    public void setScanClassName(String scanClassName) 
    {
        this.scanClassName = scanClassName;
    }

    public String getScanClassName() 
    {
        return scanClassName;
    }
    public void setScanMethodName(String scanMethodName) 
    {
        this.scanMethodName = scanMethodName;
    }

    public String getScanMethodName() 
    {
        return scanMethodName;
    }
    public void setScanPara(Long scanPara) 
    {
        this.scanPara = scanPara;
    }

    public Long getScanPara() 
    {
        return scanPara;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("scanRuleId", getScanRuleId())
            .append("scanRuleType", getScanRuleType())
            .append("singleRuleId", getSingleRuleId())
            .append("scanTaintType", getScanTaintType())
            .append("scanNamesapce", getScanNamesapce())
            .append("scanClassName", getScanClassName())
            .append("scanMethodName", getScanMethodName())
            .append("scanPara", getScanPara())
            .toString();
    }
}
