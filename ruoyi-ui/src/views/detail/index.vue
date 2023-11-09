<template>

  <div class="app-container home">
    <div class="images">
      <div class="image-container">
        <img src="./img.png" alt="Image 1" class="image" style="max-width: 100%; max-height: 100%;" />
      </div>
    </div>
    <el-row :gutter="20">
      <el-col :sm="24" :lg="24">
      </el-col>
    </el-row>
    <el-divider />
    <el-row :gutter="20">
<!--      // ？？？？ 调整下结构-->
      <template>
        <el-col :xs="24" :sm="24" :md="12" :lg="12">
          <el-card class="update-log">
            <div slot="header" class="clearfix">
              <span>漏洞摘要</span>
            </div>
            <div class="vulnerability-details">
              <div>
                <strong>漏洞类型:</strong> SQL注入漏洞
              </div>
              <div>
                <strong>漏洞数据流可达性:</strong> 是
              </div>
              <div>
                <strong>漏洞利用链存在性:</strong> 是
              </div>
            </div>
          </el-card>
        </el-col>
      </template>
      <template>
        <el-col :xs="24" :sm="24" :md="12" :lg="12">
          <el-card class="update-log">
            <div slot="header" class="clearfix">
              <span>漏洞利用链</span>
            </div>
            <ul class="call-stack-list">
              <li v-for="(item, index) in callStackList" :key="index">{{ item }}</li>
            </ul>
          </el-card>
        </el-col>
      </template>
      <el-col :xs="24" :sm="24" :md="12" :lg="12">
        <el-card class="update-log">
          <div slot="header" class="clearfix">
            <span>漏洞数据流信息</span>
          </div>
          <ul class="call-stack-list">
            <li v-for="pathEntry in dataflowResult" :key="pathEntry">
              <el-card class="data-item" :body-style="{ padding: '10px' }">
                <el-list-item v-for="entry in pathEntry" :key="entry.paramInd">
                  <el-list-item-content>
                    <el-list-item-meta>
                      <el-list-item-title>
                        <div>paramInd: {{ entry.paramInd }}</div>
                        <div>valueStr: {{ entry.valueStr }}</div>
                        <div>methodStr: {{ entry.methodStr }}</div>
                        <div>typeStr: {{ entry.typeStr }}</div>
                        <hr>
                      </el-list-item-title>
                    </el-list-item-meta>
                  </el-list-item-content>
                </el-list-item>
              </el-card>
            </li>
          </ul>
<!--          <el-list v-for="pathEntry in dataflowResult" :key="pathEntry">-->
<!--            <el-card class="data-item" :body-style="{ padding: '10px' }">-->
<!--              <el-list-item v-for="entry in pathEntry" :key="entry.paramInd">-->
<!--                <el-list-item-content>-->
<!--                  <el-list-item-meta>-->
<!--                    <el-list-item-title>-->
<!--                      <div>paramInd: {{ entry.paramInd }}</div>-->
<!--                      <div>valueStr: {{ entry.valueStr }}</div>-->
<!--                      <div>methodStr: {{ entry.methodStr }}</div>-->
<!--                      <div>typeStr: {{ entry.typeStr }}</div>-->
<!--                    </el-list-item-title>-->
<!--                  </el-list-item-meta>-->
<!--                </el-list-item-content>-->
<!--              </el-list-item>-->
<!--            </el-card>-->
<!--          </el-list>-->
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import {testIndex, getAnalysisRes} from "@/api";

export default {
  name: "Index",
  data() {
    return {
      // 版本号
      version: "3.8.6",
      test : 0,
      all_type_vul_num: [],
      all_vul_list: [],
      callStackList: null,
      dataflowResult: null
    };
  },
  methods: {
    goTarget(href) {
      window.open(href, "_blank");
    },
    getTest() {
      testIndex().then(res => {
        console.log(res)
      })
    },
    getDetail() {
      getAnalysisRes(1).then(res => {
        console.log("--------------")
        console.log(res)
        this.handleVulRes(res)
      })
    },
    handleVulRes(res) {
      // 遍历 vulType -> [vulInfo]
      for (let single_type_vuls_key in res) {
        let tmp_single_type_vuls = []
        let tmp_single_vuln_type_num = {}
        let tmp_vuln_type = ""
        // 遍历vulinfo
        console.log("%%%%%%")
        let single_type_vuls = res[single_type_vuls_key]
        console.log(single_type_vuls)
        let tmp_len = 0
        // 遍历结果单个漏洞
        for (let single_vul_key in single_type_vuls){
          console.log("&&&&&&&&&&&&&&")
          let single_vul = single_type_vuls[single_vul_key]
          console.log(single_vul)

          console.log("hhhhhhhhhhhhhhh")
          let tmp_single_vul = {}
          tmp_single_vul["vul_type"] = single_vul["vulType"]
          tmp_single_vul["call_stack"] = single_vul["callStackMethods"]
          tmp_single_vul["data_paths"] = single_vul['dataFlowResult']["dataPaths"]
          tmp_single_vul["url"] = single_vul["URL"]
          let rand = Boolean(Math.round(Math.random()));
          if (rand) {
            tmp_single_vul["req_mtd"] = "GET"
          } else {
            tmp_single_vul["req_mtd"] = "POST"
          }
          tmp_single_type_vuls.push(tmp_single_vul)
          tmp_len++;
          console.log(">>>>>>")
          console.log(tmp_single_vul["call_stack"])
          if (this.callStackList == null ) {
            console.log("23423423")
          }
          if(tmp_single_vul["call_stack"] != null) {
            console.log("sdfasdfasdf")
          }
          if (this.callStackList == null && tmp_single_vul["call_stack"] != null
            && this.dataflowResult == null && tmp_single_vul["data_paths"] != null) {
            this.callStackList = tmp_single_vul["call_stack"];
            this.dataflowResult = tmp_single_vul["data_paths"];
            console.log("!!!!!!!")
            console.log(this.callStackList)
            console.log(tmp_single_vul["call_stack"])
          }

        }

      }
    }
  },

  created() {
    this.getDetail();
  },
  mounted() {
    this.getTest()
  }
};
</script>

<style scoped lang="scss">
.call-stack-list {
  list-style: none;
  padding: 0;
}

.call-stack-list li {
  border-bottom: 1px solid #ccc;
  padding: 10px 0;
  margin-bottom: 10px;
}
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

