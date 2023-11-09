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
          <el-card accordion>
            <div >
              {{ test2 }}
            </div>
          </el-card>
          <el-card accordion>
            <div >
              3.8.5 - 2023-11-06
            </div>
          </el-card>
          <el-card accordion>
            <div >
              3.8.4 - 2023-11-05
            </div>
          </el-card>
          <el-card accordion>
            <div >
              3.8.3 - 2023-11-04
            </div>
          </el-card>
          <el-card accordion>
            <div >
              3.8.2 - 2023-11-02
            </div>
          </el-card>
          <el-card accordion>
            <div >
              3.8.1 - 2023-11-01
            </div>
          </el-card>
          <el-card accordion>
            <div >
              3.8.0 - 2023-10-31
            </div>
          </el-card>
          <el-card accordion>
            <div >
              3.7.9 - 2023-10-30
            </div>
          </el-card>
          <el-card accordion>
            <div >
              3.7.8 - 2023-10-29
            </div>
          </el-card>
          <el-card accordion>
            <div >
              3.7.7 - 2023-10-28
            </div>
          </el-card>
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="24" :md="12" :lg="8">
        <el-card class="update-log">
          <div slot="header" class="clearfix">
            <span>最高危漏洞排序</span>
          </div>
          <el-card accordion>
            <div >
              3.8.6 - 2023-11-07
            </div>
          </el-card>
          <el-card accordion>
            <div >
              3.8.5 - 2023-11-06
            </div>
          </el-card>
          <el-card accordion>
            <div >
              3.8.4 - 2023-11-05
            </div>
          </el-card>
          <el-card accordion>
            <div >
              3.8.3 - 2023-11-04
            </div>
          </el-card>
          <el-card accordion>
            <div >
              3.8.2 - 2023-11-02
            </div>
          </el-card>
          <el-card accordion>
            <div >
              3.8.1 - 2023-11-01
            </div>
          </el-card>
          <el-card accordion>
            <div >
              3.8.0 - 2023-10-31
            </div>
          </el-card>
          <el-card accordion>
            <div >
              3.7.9 - 2023-10-30
            </div>
          </el-card>
          <el-card accordion>
            <div >
              3.7.8 - 2023-10-29
            </div>
          </el-card>
          <el-card accordion>
            <div >
              3.7.7 - 2023-10-28
            </div>
          </el-card>
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="24" :md="12" :lg="8">
        <el-card class="update-log">
          <div slot="header" class="clearfix">
            <span>当前任务状态</span>
          </div>
          <el-card accordion>
            <div >
              3.8.6 - 2023-11-07
            </div>
          </el-card>
          <el-card accordion>
            <div >
              3.8.5 - 2023-11-06
            </div>
          </el-card>
          <el-card accordion>
            <div >
              3.8.4 - 2023-11-05
            </div>
          </el-card>
          <el-card accordion>
            <div >
              3.8.3 - 2023-11-04
            </div>
          </el-card>
          <el-card accordion>
            <div >
              3.8.2 - 2023-11-02
            </div>
          </el-card>
          <el-card accordion>
            <div >
              3.8.1 - 2023-11-01
            </div>
          </el-card>
          <el-card accordion>
            <div >
              3.8.0 - 2023-10-31
            </div>
          </el-card>
          <el-card accordion>
            <div >
              3.7.9 - 2023-10-30
            </div>
          </el-card>
          <el-card accordion>
            <div >
              3.7.8 - 2023-10-29
            </div>
          </el-card>
          <el-card accordion>
            <div >
              3.7.7 - 2023-10-28
            </div>
          </el-card>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import {getConfig} from "@/api/system/config";
import {indexTest} from "@/api";

export default {
  name: "Index",
  data() {
    return {
      // 版本号
      version: "3.8.6",
      test: 0,
      test2: 0
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
    }
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

