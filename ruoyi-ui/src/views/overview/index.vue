<template xmlns:el-col="http://www.w3.org/1999/html">
  <div class="app-container">
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5"></el-col>
    </el-row>
    <el-table
        :data="tableData"
        style="width: 100%">
      <el-table-column
          prop="task_name"
          align="center"
          label="任务名"
          width="170">
      </el-table-column>
      <el-table-column
          prop="target_apk"
          align="center"
          label="目标应用"
          width="160">
      </el-table-column>
      <el-table-column
          align="center"
          label="详情"
          width="60">
        <template>
          <el-button type="primary" plain icon="el-icon-info" @click="open" circle></el-button>
        </template>
        <!--        <el-button-->
        <!--          type="primary"-->
        <!--          plain-->
        <!--          icon="el-icon-info"-->
        <!--          size="mini"-->
        <!--          circle-->
        <!--          @click=""-->
        <!--        ></el-button>-->

        <!--        <el-popover-->
        <!--          placement="right"-->
        <!--          width="100"-->
        <!--          trigger="click">-->
        <!--          <el-table :data="gridData">-->
        <!--            <el-table-column width="150" property="date" label="日期"></el-table-column>-->
        <!--            <el-table-column width="100" property="name" label="姓名"></el-table-column>-->
        <!--            <el-table-column width="300" property="address" label="地址"></el-table-column>-->
        <!--          </el-table>-->
        <!--          <el-button slot="reference">i</el-button>-->
        <!--        </el-popover>-->
      </el-table-column>
      <el-table-column
          prop="scan_config"
          align="center"
          label="扫描配置"
          width="160">
      </el-table-column>
      <el-table-column
          prop="task_time"
          align="center"
          label="任务时间">
      </el-table-column>
      <el-table-column
          prop="task_status"
          align="center"
          label="任务状态">
      </el-table-column>
      <el-table-column align="center" label="漏洞概览">
        <el-table-column label="高危" align="center" prop="high_danger" />
        <el-table-column label="中危" align="center" prop="mid_danger" />
        <el-table-column label="低危" align="center" prop="low_danger" />
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  data() {
    return {
      tableData: [{
        task_name: 'MicroService-SecLab',
        target_apk: 'MicroService-SecLab',
        scan_config: '3',
        task_time: '12',
        task_status: '完成',
        high_danger:'18',
        mid_danger: '0',
        low_danger: '2'
      },
        {
          task_name: 'WebGoat',
          target_apk: 'WebGoat',
          scan_config: '3',
          task_time: '33',
          task_status: '完成',
          high_danger:'29',
          mid_danger: '4',
          low_danger: '0'
        },
        {
          task_name: 'OWASP-Benchmark-0-500',
          target_apk: 'OWASP-Benchmark-0-500',
          scan_config: '3',
          task_time: 'null',
          task_status: '运行中',
          high_danger:'525',
          mid_danger: '7',
          low_danger: '8'
        }]
    }
  },
  methods: {
    open() {
      // this.$alert('该项目包含18个可利用高危漏洞，2个疑似漏洞', 'MicroService-SecLab', {
      //   confirmButtonText: '确定',
      //   callback: action => {
      //     this.$message({
      //       type: 'info',
      //       message: `action: ${ action }`
      //     });
      //   }
      // });
      this.$alert('该项目包含18个可利用高危漏洞，2个疑似漏洞', 'MicroService-SecLab', {
        confirmButtonText: '确定',
        callback: action => {
          this.$message({
            type: 'info',
            message: `action: ${action}`
          });

          // 在回调函数中执行页面跳转
          if (action === 'confirm') {
            // 使用 this.$router.push() 方法进行页面导航
            this.$router.push('/system/chart');
          }
        }
      });
    }
  }
}
</script>


