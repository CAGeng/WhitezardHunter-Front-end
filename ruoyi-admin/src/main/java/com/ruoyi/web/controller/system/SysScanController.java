package com.ruoyi.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.SysScan;
import com.ruoyi.system.service.ISysScanService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 扫描配置Controller
 * 
 * @author ruoyi
 * @date 2023-11-09
 */
@RestController
@RequestMapping("/system/scan")
public class SysScanController extends BaseController
{
    @Autowired
    private ISysScanService sysScanService;

    /**
     * 查询扫描配置列表
     */
    @PreAuthorize("@ss.hasPermi('system:scan:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysScan sysScan)
    {
        startPage();
        List<SysScan> list = sysScanService.selectSysScanList(sysScan);
        return getDataTable(list);
    }

    /**
     * 导出扫描配置列表
     */
    @PreAuthorize("@ss.hasPermi('system:scan:export')")
    @Log(title = "扫描配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysScan sysScan)
    {
        List<SysScan> list = sysScanService.selectSysScanList(sysScan);
        ExcelUtil<SysScan> util = new ExcelUtil<SysScan>(SysScan.class);
        util.exportExcel(response, list, "扫描配置数据");
    }

    /**
     * 获取扫描配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:scan:query')")
    @GetMapping(value = "/{scanRuleId}")
    public AjaxResult getInfo(@PathVariable("scanRuleId") Long scanRuleId)
    {
        return success(sysScanService.selectSysScanByScanRuleId(scanRuleId));
    }

    /**
     * 新增扫描配置
     */
    @PreAuthorize("@ss.hasPermi('system:scan:add')")
    @Log(title = "扫描配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysScan sysScan)
    {
        return toAjax(sysScanService.insertSysScan(sysScan));
    }

    /**
     * 修改扫描配置
     */
    @PreAuthorize("@ss.hasPermi('system:scan:edit')")
    @Log(title = "扫描配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysScan sysScan)
    {
        return toAjax(sysScanService.updateSysScan(sysScan));
    }

    /**
     * 删除扫描配置
     */
    @PreAuthorize("@ss.hasPermi('system:scan:remove')")
    @Log(title = "扫描配置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{scanRuleIds}")
    public AjaxResult remove(@PathVariable Long[] scanRuleIds)
    {
        return toAjax(sysScanService.deleteSysScanByScanRuleIds(scanRuleIds));
    }
}
