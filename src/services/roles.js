import {ROLESADD} from '@/services/api'
import {DELETEROLE} from '@/services/api'
import {LISTROLES} from '@/services/api'
import {UPDATEROLE} from '@/services/api'
import {UPDATEONE} from '@/services/api'
import {ROLEONE} from '@/services/api'
import {request, METHOD} from '@/utils/request'
/**
 * 添加角色服务
 * @param name 账户名
 * @param password 账户密码
 * @returns {Promise<AxiosResponse<T>>}
 */
async function addRole(name) {
  return request(ROLESADD, METHOD.POST, {
    roname: name,
  })
}
async function listRoles() {
return request(LISTROLES, METHOD.POST, {})
}
async function deleteRole(roid) {
  return request(DELETEROLE, METHOD.POST, {
      roid:roid,
  })
  }
  async function updateRole(roid) {
    return request(UPDATEROLE, METHOD.POST, {
        roid:roid,
    })
    }
    async function RoleOne(roid) {
      return request(ROLEONE, METHOD.POST, {
          roid:roid,
      })
      }
    async function updateone(roid,roname) {
      return request(UPDATEONE, METHOD.POST, {
          roid:roid,
          roname:roname
      })
      }
export { addRole,listRoles,deleteRole,updateRole,updateone,RoleOne}