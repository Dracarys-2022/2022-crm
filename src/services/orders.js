import { ORDERSADD } from '@/services/api'
import { SELECTCOID, LISTGET, LISTORDERS, REQUESTOUT, OUT, RETURNSTATE, COMPLATE, PREFIT, PREFIT1, ALLPRO } from '@/services/api'
import { request, METHOD } from '@/utils/request'
/**
 * 添加角色服务
 * @param name 账户名
 * @param password 账户密码
 * @returns {Promise<AxiosResponse<T>>}
 */
//添加订单
async function addorders(source) {
  return request(ORDERSADD, METHOD.POST, {
    state: source.state,
    price: source.price,
    numbers: source.numbers,
    coid: source.coid,
    cid: source.cid,
    pid: source.pid,
    createtime: source.createtime,
    deadline: source.deadline,
    type: source.type
  })
}
//查询所有关于订单的下拉框
async function getproduct() {
  return request(LISTGET, METHOD.POST, {})
}
//查询订单记录
async function listorders(current, size) {
  return request(LISTORDERS, METHOD.GET, {
    current: current,
    size: size
  })
}
//根据客户选择客户联系人
async function selectcoid(cid) {
  return request(SELECTCOID, METHOD.GET, {
    cid: cid
  })
}
//请求出库
async function requestout(oid) {
  return request(REQUESTOUT, METHOD.GET, {
    oid: oid,
    current: 1,
    size: 5
  })
}
//出库
async function out(oid) {
  return request(OUT, METHOD.GET, {
    oid: oid,
    current: 1,
    size: 5
  })
}
//退换
async function returnstate(productstatue, procreatetime, prodeadline, oid) {
  return request(RETURNSTATE, METHOD.POST, {
    productstatue: productstatue,
    procreatetime: procreatetime,
    prodeadline: prodeadline,
    oid: oid,
    current: 1,
    size: 5
  })
}
//完成订单
async function complate(oid) {
  return request(COMPLATE, METHOD.GET, {
    oid: oid,
    current: 1,
    size: 5
  })
}
//利润
async function prefit() {
  return request(PREFIT, METHOD.GET, {})
}
//利润
async function prefit1() {
  return request(PREFIT1, METHOD.GET, {})
}
//获取服务或产品编号
async function allpro(sid) {
  return request(ALLPRO, METHOD.GET, {
    oid: sid
  })
}
export { selectcoid, addorders, getproduct, listorders, requestout, out, returnstate, complate, prefit, prefit1, allpro }