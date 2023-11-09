import request from "@/utils/request";
import axios from "axios";

export function indexTest() {
  axios.defaults.baseURL = '/api'
  console.log("ssss")
  return request({
    xf_url: '/api',
    url: '/sat/analysis/result/1',
    method: 'get',
  })
}


export function getTrigger() {
  axios.defaults.baseURL = '/api'
  console.log("ssss")
  return request({
    xf_url: '/api',
    url: '/sat/triggerpoint',
    method: 'get'
  })
}


export function setAnalysisPath(path, type) {
  axios.defaults.baseURL = '/api'
  console.log("ssss2")
  return request({
    xf_url: '/api',
    url: '/sat/analysis/result_con',
    method: 'post',
    data: {
      "projectPath": path,
      "vulType": type
    }
  })
}

export function getAnalysisRes(pathId) {
  axios.defaults.baseURL = '/api'
  console.log("ssss3")
  return request({
    xf_url: '/api',
    url: '/sat/analysis/microservice' + pathId,
    method: 'get'
  })
}
