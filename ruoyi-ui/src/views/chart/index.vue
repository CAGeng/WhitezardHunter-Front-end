<!--<template>-->
<!--  <div class="container">-->
<!--    <div class="image-container">-->
<!--      <img src="./image1.jpg" alt="Image 1" class="image" />-->
<!--    </div>-->
<!--    <div class="image-container">-->
<!--      <img src="./image2.jpg" alt="Image 2" class="image" />-->
<!--    </div>-->
<!--  </div>-->
<!--</template>-->

<!--<style scoped>-->
<!--.container {-->
<!--  display: flex;-->
<!--  justify-content: center;-->
<!--  margin-top: 20px; /* 添加上边距 */-->
<!--}-->

<!--.image-container {-->
<!--  margin-right: 20px;-->
<!--}-->

<!--.image {-->
<!--  width: 500px;-->
<!--  height: 500px;-->
<!--  object-fit: cover;-->
<!--}-->
<!--</style>-->

<template>
  <div class="container">
    <div class="images">
      <div class="image-container">
        <img src="./image1.jpg" alt="Image 1" class="image" />
      </div>
      <div class="image-container">
        <img src="./image2.jpg" alt="Image 2" class="image" />
      </div>
    </div>
    <div class="button-container">
      <button class="button" @click="open">漏洞详情</button>
    </div>
  </div>
</template>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 20px;
}

.images {
  display: flex;
  justify-content: center;
  align-items: center;
}

.image-container {
  margin-right: 20px;
}

.image {
  width: 500px;
  height: 500px;
  object-fit: cover;
}

.button-container {
  margin-top: 20px;
}

.button {
  padding: 10px 20px;
  background-color: #eee;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}
</style>

<script>
export default {

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
      this.$alert('本轮审计发现18个高危漏洞，2个疑似漏洞', 'MicroService-SecLab', {
        confirmButtonText: '确定',
        callback: action => {
          this.$message({
            type: 'info',
            message: `action: ${action}`
          });

          // 在回调函数中执行页面跳转
          if (action === 'confirm') {
            // 使用 this.$router.push() 方法进行页面导航
            this.$router.push('/system/detail');
          }
        }
      });
    }
  }
}
</script>
