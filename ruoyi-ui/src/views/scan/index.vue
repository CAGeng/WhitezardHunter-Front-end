<template xmlns:el-col="http://www.w3.org/1999/html">
  <div class="app-container">
    <el-row :gutter="20" class="mb8">

      <el-col :span="3" style="margin-right: 30px">
        <el-input
          size="medium"
          placeholder="请输入target"
          v-model=input
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
        <el-button size="medium" icon="el-icon-check" circle style="margin-left: 15px;" @click="setAnalysisPath(input, type)">RUN</el-button>
      </el-col>

    </el-row>

    <el-table
      :data="tableData"
      style="width: 100%">
      <el-table-column
          align="center"
          prop="category"
          label="规则类型"
          width="170">
      </el-table-column>
      <el-table-column
          align="center"
          prop="name"
          label="规则ID"
          width="160">
      </el-table-column>
      <el-table-column
          align="center"
          prop="dirtcategory"
          label="污点类型"
          width="160">
      </el-table-column>
      <el-table-column label="标识符" align="center">
        <el-table-column label="命名空间" align="center" prop="namespace" width="200"></el-table-column>
        <el-table-column label="类名" align="center" prop="classname" width="200"></el-table-column>
        <el-table-column label="方法名" align="center" prop="methodname" width="200"></el-table-column>
      </el-table-column>
      <el-table-column label="参数" align="center" prop="canshu" width="160" ></el-table-column>
<!--      <el-table-column label="操作" align="center">-->
      <el-table-column label="操作" align="center">
        <el-button
          size="mini"
          type="text"
          icon="el-icon-edit"
          @click="handleUpdate(scope.row)"
          v-hasPermi="['system:menu:edit']"
        >修改</el-button>
      </el-table-column>
    </el-table>

    <!--     添加或修改菜单对话框-->
    <el-dialog :title="title" :visible.sync="open" width="680px" append-to-body>
      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="规则类型">
          <el-select v-model="form.category" placeholder="请选择规则类型">
            <el-option label="SourceRule" value="SourceRule"></el-option>
            <el-option label="SinkRule" value="SinkRule"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="规则ID">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="污点类型">
          <el-select v-model="form.dirtcategory" placeholder="请选择污点类型">
            <el-option label="污点类型1" value="category1"></el-option>
            <el-option label="污点类型2" value="category2"></el-option>
            <el-option label="污点类型3" value="category3"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="标识符">
          <el-input v-model="form.namespace" placeholder="请输入命名空间" ></el-input>
          <el-input v-model="form.className" placeholder="请输入类名"></el-input>
          <el-input v-model="form.methodName" placeholder="请输入方法名"></el-input>
        </el-form-item>
        <el-form-item label="参数">
          <el-select v-model="form.canshu">
            <el-option label="1" value="canshu1"></el-option>
            <el-option label="2" value="canshu2"></el-option>
            <el-option label="3" value="canshu3"></el-option>
            <el-option label="4" value="canshu4"></el-option>
            <el-option label="return" value="return"></el-option>
          </el-select>
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
import { listMenu, getMenu, delMenu, addMenu, updateMenu } from "@/api/system/menu";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
import IconSelect from "@/components/IconSelect";
import {setAnalysisPath} from "@/api";

export default {
  name: "Menu",
  dicts: ['sys_show_hide', 'sys_normal_disable'],
  components: { Treeselect, IconSelect },
  data() {
    return {
      input: '',
      type: '',
      tableData: [{
        category: '',
        name: '',
        dirtcategory: '',
        namespace:'',
        className:'',
        methodName:'',
        canshu:'',
      }],
      // 遮罩层
      loading: true,
      // 显示搜索条件
      showSearch: true,
      // 菜单表格树数据
      menuList: [],
      // 菜单树选项
      menuOptions: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 是否展开，默认全部折叠
      isExpandAll: false,
      // 重新渲染表格状态
      refreshTable: true,
      // 查询参数
      queryParams: {
        menuName: undefined,
        visible: undefined
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        menuName: [
          { required: true, message: "菜单名称不能为空", trigger: "blur" }
        ],
        orderNum: [
          { required: true, message: "菜单顺序不能为空", trigger: "blur" }
        ],
        path: [
          { required: true, message: "路由地址不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    setAnalysisPath,
    // 选择图标
    selected(name) {
      this.form.icon = name;
    },
    /** 查询菜单列表 */
    getList() {
      this.loading = true;
      listMenu(this.queryParams).then(response => {
        this.menuList = this.handleTree(response.data, "menuId");
        this.loading = false;
      });
    },
    /** 转换菜单数据结构 */
    normalizer(node) {
      if (node.children && !node.children.length) {
        delete node.children;
      }
      return {
        id: node.menuId,
        label: node.menuName,
        children: node.children
      };
    },
    /** 查询菜单下拉树结构 */
    getTreeselect() {
      listMenu().then(response => {
        this.menuOptions = [];
        const menu = { menuId: 0, menuName: '主类目', children: [] };
        menu.children = this.handleTree(response.data, "menuId");
        this.menuOptions.push(menu);
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
        menuId: undefined,
        parentId: 0,
        menuName: undefined,
        icon: undefined,
        menuType: "M",
        orderNum: undefined,
        isFrame: "1",
        isCache: "0",
        visible: "0",
        status: "0"
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 新增按钮操作 */
    handleAdd(row) {
      this.reset();
      this.getTreeselect();
      if (row != null && row.menuId) {
        this.form.parentId = row.menuId;
      } else {
        this.form.parentId = 0;
      }
      this.open = true;
      this.title = "添加";
    },
    /** 展开/折叠操作 */
    toggleExpandAll() {
      this.refreshTable = false;
      this.isExpandAll = !this.isExpandAll;
      this.$nextTick(() => {
        this.refreshTable = true;
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.getTreeselect();
      getMenu(row.menuId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改菜单";
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.menuId != undefined) {
            updateMenu(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addMenu(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除名称为"' + row.menuName + '"的数据项？').then(function() {
        return delMenu(row.menuId);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    }
  }
};
</script>

