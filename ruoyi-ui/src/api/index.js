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
