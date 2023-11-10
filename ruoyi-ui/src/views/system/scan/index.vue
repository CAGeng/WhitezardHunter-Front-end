<template>
  <div class="app-container">
    <el-row :gutter="20" class="mb8">

      <el-col :span="12" style="margin-right: 30px; display: flex; align-items: center;">
        <label for="input-field" style="font-size:24px; color: #ff33c2; white-space: nowrap;">项目路径：</label>
        <el-input
          size="medium"
          placeholder="请输入审计项目/路径"
          v-model="input"
          id="input-field"
        ></el-input>
      </el-col>


      <el-col :span="3" style="margin-right: 10px">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="medium"
          @click="handleAdd"
          v-hasPermi="['system:menu:add']"
        >自定义规则</el-button>
      </el-col>

      <el-col :span="2" style="margin-right: 20px">
        <el-button
          type="primary"
          plain
          size="medium"
          @click="handleAdd"
          v-hasPermi="['system:menu:add']"
        >扫描漏洞类型</el-button>
      </el-col>

      <el-col :span="1">
        <el-button size="medium" icon="el-icon-check" circle style="margin-left: 15px;" @click="setAnalysisPath(input, '')">RUN</el-button>
      </el-col>

    </el-row>
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="规则类型" prop="scanRuleType">
        <el-select v-model="queryParams.scanRuleType" placeholder="请选择规则类型" clearable>
          <el-option
            v-for="dict in dict.type.sys_rule_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="规则ID" prop="singleRuleId">
        <el-input
          v-model="queryParams.singleRuleId"
          placeholder="请输入规则ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="污点类型" prop="scanTaintType">
        <el-select v-model="queryParams.scanTaintType" placeholder="请选择污点类型" clearable>
          <el-option
            v-for="dict in dict.type.sys_taint_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="命名空间" prop="scanNamesapce">
        <el-input
          v-model="queryParams.scanNamesapce"
          placeholder="请输入命名空间"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="类名" prop="scanClassName">
        <el-input
          v-model="queryParams.scanClassName"
          placeholder="请输入类名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="方法名" prop="scanMethodName">
        <el-input
          v-model="queryParams.scanMethodName"
          placeholder="请输入方法名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:scan:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:scan:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:scan:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:scan:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="scanList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" prop="scanRuleId" />
      <el-table-column label="规则类型" align="center" prop="scanRuleType">
<!--        <template slot-scope="scope">-->
<!--          <dict-tag :options="dict.type.sys_rule_type" :value="scope.row.scanRuleType"/>-->
<!--        </template>-->
      </el-table-column>
      <el-table-column label="规则ID" align="center" prop="singleRuleId" />
      <el-table-column label="污点类型" align="center" prop="scanTaintType">
<!--        <template slot-scope="scope">-->
<!--          <dict-tag :options="dict.type.sys_taint_type" :value="scope.row.scanTaintType"/>-->
<!--        </template>-->
      </el-table-column>
      <el-table-column label="命名空间" align="center" prop="scanNamesapce" />
      <el-table-column label="类名" align="center" prop="scanClassName" />
      <el-table-column label="方法名" align="center" prop="scanMethodName" />
      <el-table-column label="参数" align="center" prop="scanPara">
<!--        <template slot-scope="scope">-->
<!--          <dict-tag :options="dict.type.sys_para" :value="scope.row.scanPara ? scope.row.scanPara.split(',') : []"/>-->
<!--        </template>-->
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:scan:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:scan:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改扫描配置对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="规则类型" prop="scanRuleType">
          <el-radio-group v-model="form.scanRuleType">
            <el-radio
              v-for="dict in dict.type.sys_rule_type"
              :key="dict.value"
              :label="dict.value"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="规则ID" prop="singleRuleId">
          <el-input v-model="form.singleRuleId" placeholder="请输入规则ID" />
        </el-form-item>
        <el-form-item label="污点类型" prop="scanTaintType">
          <el-radio-group v-model="form.scanTaintType">
            <el-radio
              v-for="dict in dict.type.sys_taint_type"
              :key="dict.value"
              :label="dict.value"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="命名空间" prop="scanNamesapce">
          <el-input v-model="form.scanNamesapce" placeholder="请输入命名空间" />
        </el-form-item>
        <el-form-item label="类名" prop="scanClassName">
          <el-input v-model="form.scanClassName" placeholder="请输入类名" />
        </el-form-item>
        <el-form-item label="方法名" prop="scanMethodName">
          <el-input v-model="form.scanMethodName" placeholder="请输入方法名" />
        </el-form-item>
        <el-form-item label="参数" prop="scanPara">
          <el-checkbox-group v-model="form.scanPara">
            <el-checkbox
              v-for="dict in dict.type.sys_para"
              :key="dict.value"
              :label="dict.value">
              {{dict.label}}
            </el-checkbox>
          </el-checkbox-group>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listScan, getScan, delScan, addScan, updateScan } from "@/api/system/scan";
import {setAnalysisPath} from "@/api";

export default {
  name: "Scan",
  dicts: ['sys_para', 'sys_rule_type', 'sys_taint_type'],
  data() {
    return {
      input: '',
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 扫描配置表格数据
      scanList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        scanRuleType: null,
        singleRuleId: null,
        scanTaintType: null,
        scanNamesapce: null,
        scanClassName: null,
        scanMethodName: null,
        scanPara: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        scanRuleType: [
          { required: true, message: "规则类型不能为空", trigger: "change" }
        ],
        singleRuleId: [
          { required: true, message: "规则ID不能为空", trigger: "blur" }
        ],
        scanTaintType: [
          { required: true, message: "污点类型不能为空", trigger: "change" }
        ],
        scanNamesapce: [
          { required: true, message: "命名空间不能为空", trigger: "blur" }
        ],
        scanClassName: [
          { required: true, message: "类名不能为空", trigger: "blur" }
        ],
        scanMethodName: [
          { required: true, message: "方法名不能为空", trigger: "blur" }
        ],
        scanPara: [
          { required: true, message: "参数不能为空", trigger: "blur" }
        ]
      }
    };
  },
  mounted() {
    this.getList();
  },
  methods: {
    setAnalysisPath,
    /** 查询扫描配置列表 */
    getList() {
      this.loading = true;
      listScan(this.queryParams).then(response => {
        console.log(response)
        this.scanList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        scanRuleId: null,
        scanRuleType: null,
        singleRuleId: null,
        scanTaintType: null,
        scanNamesapce: null,
        scanClassName: null,
        scanMethodName: null,
        scanPara: []
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.scanRuleId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加扫描配置";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const scanRuleId = row.scanRuleId || this.ids
      getScan(scanRuleId).then(response => {
        this.form = response.data;
        this.form.scanPara = this.form.scanPara.split(",");
        this.open = true;
        this.title = "修改扫描配置";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.scanPara = this.form.scanPara.join(",");
          if (this.form.scanRuleId != null) {
            updateScan(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addScan(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const scanRuleIds = row.scanRuleId || this.ids;
      this.$modal.confirm('是否确认删除扫描配置编号为"' + scanRuleIds + '"的数据项？').then(function() {
        return delScan(scanRuleIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/scan/export', {
        ...this.queryParams
      }, `scan_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
