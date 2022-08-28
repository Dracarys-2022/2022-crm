import { request, METHOD } from '@/utils/request'
import {ADDORDERS ,GETPRODUCT,LISTORDERS} from '@/services/api'

export async function add(orders) {
    return request(ADDORDERS, METHOD.POST, {
        createtime: orders.createtime,
        type:orders.type,
        numbers:orders.numbers,
        pid:orders.pid,
        cid:orders.cid,
        price:orders.price,
        coid:orders.coid,
        deadline:orders.deadline,
    })
}
export async function getproduct() {
    return request(GETPRODUCT, METHOD.POST)
}
export async function list(current,size) {
    return request(LISTORDERS, METHOD.GET,{
        current:current,
        size:size
    })
}
export default {add,getproduct,list}