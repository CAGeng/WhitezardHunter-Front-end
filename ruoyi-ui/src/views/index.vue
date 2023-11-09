<template>
  <div class="app-container home">
    <el-row :gutter="20">
      <el-col :sm="24" :lg="24">
      </el-col>
    </el-row>
    <el-row :gutter="20">
      <el-col :sm="24" :lg="8" style="padding-left: 20px">
        <h2>高危漏洞</h2>
        <center><p>
          {{test}}
        </p></center>
      </el-col>

      <el-col :sm="24" :lg="8" style="padding-left: 20px">
        <h2>中危漏洞</h2>
        <center><p>
          0
        </p></center>
      </el-col>

      <el-col :sm="24" :lg="8" style="padding-left: 20px">
        <h2>低危漏洞</h2>
        <center><p>
          0
        </p></center>
      </el-col>

    </el-row>
    <el-divider />
    <el-row :gutter="20">
      <el-col :xs="24" :sm="24" :md="12" :lg="8">
        <el-card class="update-log">
          <div slot="header" class="clearfix">
            <span>最多漏洞排序</span>
          </div>
          <el-card v-for="trigger in list1" :key="trigger.ossId">
            <div>ID: {{ trigger.ossId }}</div>
            <div>CVE ID: {{ trigger.cveId }}</div>
            <div>Project: {{ trigger.project }}</div>
            <div>Classes: {{ trigger.classes }}</div>
            <div>Methods: {{ trigger.methods }}</div>
            <div>Is Complete: {{ trigger.isComplete }}</div>
          </el-card>
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="24" :md="12" :lg="8">
        <el-card class="update-log">

          <div slot="header" class="clearfix">
            <span>最高危漏洞排序</span>
          </div>
          <el-card v-for="trigger in list2" :key="trigger.ossId">
            <div>ID: {{ trigger.ossId }}</div>
            <div>CVE ID: {{ trigger.cveId }}</div>
            <div>Project: {{ trigger.project }}</div>
            <div>Classes: {{ trigger.classes }}</div>
            <div>Methods: {{ trigger.methods }}</div>
            <div>Is Complete: {{ trigger.isComplete }}</div>
          </el-card>
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="24" :md="12" :lg="8">
        <el-card class="update-log">
          <div slot="header" class="clearfix">
            <span>当前任务状态</span>
          </div>
          <el-card v-for="trigger in list3" :key="trigger.ossId">
            <div>ID: {{ trigger.ossId }}</div>
            <div>CVE ID: {{ trigger.cveId }}</div>
            <div>Project: {{ trigger.project }}</div>
            <div>Classes: {{ trigger.classes }}</div>
            <div>Methods: {{ trigger.methods }}</div>
            <div>Is Complete: {{ trigger.isComplete }}</div>
          </el-card>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import {getConfig} from "@/api/system/config";
import {indexTest, getTrigger} from "@/api";

export default {
  name: "Index",
  data() {
    return {
      // 版本号
      version: "3.8.6",
      test: 0,
      test2: 0,
      list: [
        { id: 1, name: 'Item 1' },
        { id: 2, name: 'Item 2' },
        { id: 3, name: 'Item 3' },
      ],
      list1: [],
      list2: [],
      list3: [],
    };
  },
  methods: {
    goTarget(href) {
      window.open(href, "_blank");

      // const a () {
      //   getConfig().then(res-> {
      //
      //   })
      // }
    },
    a(){
      indexTest().then(res => {
        console.log(res)
      })
    },
    getTriggers(){
      getTrigger().then(res => {
        // 在请求成功的回调函数中，将触发器数据赋值给 list 数组
        console.log("-----------")
        console.log(res)
        this.list = res.triggerPoint;
        // 取前10位
        this.list1 = this.list.slice(0, 20);
        this.list2 = this.list.slice(20, 40);
        this.list3 = this.list.slice(40, 60);
      }).catch(error => {
        // 处理请求失败的情况
        console.error(error);
      });
    }
  },
  created() {
    this.getTriggers();
  },
  mounted() {
    this.a()
  }
};
</script>

<style scoped lang="scss">
.home {
  blockquote {
    padding: 10px 20px;
    margin: 0 0 20px;
    font-size: 17.5px;
    border-left: 5px solid #eee;
  }
  hr {
    margin-top: 20px;
    margin-bottom: 20px;
    border: 0;
    border-top: 1px solid #eee;
  }
  .col-item {
    margin-bottom: 20px;
  }

  ul {
    padding: 0;
    margin: 0;
  }

  font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-size: 13px;
  color: #676a6c;
  overflow-x: hidden;

  ul {
    list-style-type: none;
  }

  h4 {
    margin-top: 0px;
  }

  h2 {
    margin-top: 10px;
    font-size: 26px;
    font-weight: 100;
  }

  p {
    margin-top: 10px;

    b {
      font-weight: 700;
    }
  }

  .update-log {
    ol {
      display: block;
      list-style-type: decimal;
      margin-block-start: 1em;
      margin-block-end: 1em;
      margin-inline-start: 0;
      margin-inline-end: 0;
      padding-inline-start: 40px;
    }
  }
}
</style>

