import { request, METHOD } from '@/utils/request'
import { StOCKIN, StOCKQUERY, StOCKOUT, StOCKLIST,StOCKQUERYBYID ,StOCKUPDATE,GETPRODUCT,STOCKLACK,StOCKQUERYLACK,StOCKQUERYOUT,StOCKLISTNULL} from '@/services/api'

export async function add(source) {
    console.log(source);
    return request(StOCKIN, METHOD.POST, {
        pid: source.pid,
        numbers: source.numbers,
        price: source.price,
        location: source.location,
        createtime: source.createtime,
        endtime: source.endtime,
        type: source.type,
        state: source.state,
        producetime: source.producetime
    })
}
export async function update(source) {
    return request(StOCKUPDATE, METHOD.POST, {
        id:source.id,
        pid: source.pid,
        numbers: source.numbers,
        price: source.price,
        location: source.location,
        createtime: source.createtime,
        endtime: source.endtime,
        type: source.type,
        state: source.state,
        producetime: source.producetime
    })
}
export async function query(current,size) {
    return request(StOCKQUERY, METHOD.GET,{
        current:current,
        size:size
    })
}
export async function queryOut(current,size) {
    return request(StOCKQUERYOUT, METHOD.GET,{
        current:current,
        size:size
    })
}
export async function queryLack(current,size) {
    return request(StOCKQUERYLACK, METHOD.GET,{
        current:current,
        size:size
    })
}
export async function list(current,size) {
    return request(StOCKLIST, METHOD.GET,{
        current:current,
        size:size
    })
}
export async function listNull(current,size) {
    return request(StOCKLISTNULL, METHOD.GET,{
        current:current,
        size:size
    })
}
export async function sub(id) {
    return request(StOCKOUT, METHOD.GET, {
        oid: id
    })
}
export async function queryById(id) {
    return request(StOCKQUERYBYID, METHOD.GET, {
        id: id
    })
}
export async function getproduct() {
    return request(GETPRODUCT, METHOD.GET)
}
export async function lack(oid) {
    return request(STOCKLACK, METHOD.GET,{
        oid:oid
    })
}
export default { add, query, sub, list,queryById ,update,getproduct,lack,queryOut,queryLack,listNull}