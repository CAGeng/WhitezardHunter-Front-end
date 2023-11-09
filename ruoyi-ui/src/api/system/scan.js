import request from '@/utils/request'

// 查询扫描配置列表
export function listScan(query) {
  return request({
    url: '/system/scan/list',
    method: 'get',
    params: query
  })
}

// 查询扫描配置详细
export function getScan(scanRuleId) {
  return request({
    url: '/system/scan/' + scanRuleId,
    method: 'get'
  })
}

// 新增扫描配置
export function addScan(data) {
  return request({
    url: '/system/scan',
    method: 'post',
    data: data
  })
}

// 修改扫描配置
export function updateScan(data) {
  return request({
    url: '/system/scan',
    method: 'put',
    data: data
  })
}

// 删除扫描配置
export function delScan(scanRuleId) {
  return request({
    url: '/system/scan/' + scanRuleId,
    method: 'delete'
  })
}
