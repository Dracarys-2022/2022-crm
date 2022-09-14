import {VISITADD} from '@/services/api'
import {LISTVISIT,DELETEVISIT} from '@/services/api'
import {request, METHOD} from '@/utils/request'
/**
 * 添加角色服务
 * @param name 账户名
 * @param password 账户密码
 * @returns {Promise<AxiosResponse<T>>}
 */
//添加拜访记录
async function addvisit(visit) {
  return request(VISITADD, METHOD.POST, {
    company: visit.company,
    client:visit.client,
    phone:visit.phone,
    accompany:visit.accompany,
    theme:visit.theme,
    purpose:visit.purpose,
    ready:visit.ready,
    outcome:visit.outcome,
    attitude:visit.attitude,
    fund:visit.fund,
    remark:visit.remark,
    createtime:visit.createtime
  })
}
//查询拜访记录
async function listvisit(current,size) {
return request(LISTVISIT, METHOD.GET, {
  current:current,
  size:size
})
}
//记录者本人删除拜访记录
async function deletevisit(vid) {
    return request(DELETEVISIT, METHOD.POST, {
        vid:vid
    })
    }
export { addvisit,listvisit,deletevisit}