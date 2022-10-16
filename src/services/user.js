import {LOGIN, ROUTES,UserLog} from '@/services/api'
import {request, METHOD, removeAuthorization} from '@/utils/request'

/**
 * 登录服务
 * @param name 账户名
 * @param password 账户密码
 * @returns {Promise<AxiosResponse<T>>}
 */
export async function login(oname, password) {
  return request(LOGIN, METHOD.POST,{
    oname: oname,
    password: password
  })
}
export async function user_log(oname, password) {
  return request(UserLog, METHOD.POST, {
    user: oname,
    password: password
  })
}

export async function getRoutesConfig() {
  return request(ROUTES, METHOD.GET)
}

/**
 * 退出登录
 */
export function logout() {
  localStorage.removeItem('localOperator')
  localStorage.removeItem('oname')
  localStorage.removeItem('access-admin')
  removeAuthorization()
}
export default {
  login,
  logout,
  getRoutesConfig
}
