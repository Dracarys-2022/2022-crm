import {FUNCTIONADD} from '@/services/api'
import {FUNCTIONADD1} from '@/services/api'
import {DELETEFUNCTION} from '@/services/api'
import {LISTFUNCTION} from '@/services/api'
import {UPDATEFUNCTION} from '@/services/api'
import {UPDATEONEFUNCTION} from '@/services/api'
import {BESURE} from '@/services/api'
import {FIRSTFUNCTION} from '@/services/api'
import {FIRSTFUNCTION1} from '@/services/api'
import {request, METHOD} from '@/utils/request'
/**
 * 添加角色服务
 * @param name 账户名
 * @param password 账户密码
 * @returns {Promise<AxiosResponse<T>>}
 */
async function addFunction(name) {
  return request(FUNCTIONADD, METHOD.POST, {
    fname: name,
  })
}
async function addFunction1(name,fatherid) {
  return request(FUNCTIONADD1, METHOD.POST, {
    fname: name,
    fatherid:fatherid
  })
}
async function listFunctions(current, size) {
return request(LISTFUNCTION, METHOD.GET, {
  current: current,
  size: size
})
}
async function firstFunction() {
  return request(FIRSTFUNCTION, METHOD.POST, {})
  }
  async function firstFunction1(fid) {
    return request(FIRSTFUNCTION1, METHOD.POST, {
      fid:fid
    })
    }
async function deleteFunction(fid) {
  return request(DELETEFUNCTION, METHOD.POST, {
      fid:fid,
  })
  }
  async function updateFunction(fid) {
    return request(UPDATEFUNCTION, METHOD.POST, {
        fid:fid,
    })
    }
    async function beSureFunction(fid,permissions) {
      return request(BESURE, METHOD.POST, {
          roid:fid,
          permissions:permissions,
      })
      }
    async function updateoneFunction(fid,fname) {
      return request(UPDATEONEFUNCTION, METHOD.POST, {
          fid:fid,
          fname:fname
      })
      }
export { addFunction,addFunction1,listFunctions,deleteFunction,updateFunction,updateoneFunction,beSureFunction,firstFunction,firstFunction1}