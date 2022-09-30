import { request, METHOD } from '@/utils/request'
import { ADDPRO,LISTPRO, LISTOUt, CHANGESTATUS, QUERYBYPID,UPDATEPRO } from '@/services/api'
export async function listPro(current, size) {
    return request(LISTPRO, METHOD.GET, {
        current: current,
        size: size
    })
}
export async function listOut(current, size) {
    return request(LISTOUt, METHOD.GET, {
        current: current,
        size: size
    })
}
export async function changeStatus(pid) {
    return request(CHANGESTATUS, METHOD.GET, {
        pid: pid
    })
}
export async function queryByPid(pid) {
    return request(QUERYBYPID, METHOD.GET, {
        pid: pid
    })
}
export async function update(source) {
    return request(UPDATEPRO, METHOD.POST, {
        pid:source.pid,
        status: source.status,
        pname: source.pname,
        price: source.price,
        marketprice: source.marketprice,
        category: source.category,
        units: source.units,
        specification: source.specification,
        shelflife: source.shelflife,
        type: source.type,
    })
}
//添加产品和服务
export async function addproduct(source) {
    return request(ADDPRO, METHOD.POST, {
          status: source.status,
          pname: source.pname,
          price: source.price,
          marketprice: source.marketprice,
          category: source.category,
          units: source.units,
          specification: source.specification,
          shelflife: source.shelflife,
          type: source.type,
    })
  }
export default {addproduct, listPro, listOut, changeStatus, queryByPid,update }