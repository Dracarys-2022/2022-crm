import { request, METHOD } from '@/utils/request'
import { PETITIONADD ,PETITIONLIST,PETITIONOUTCOME,GETOPER,PETITIONLISTNULL} from '@/services/api'

export async function add(petition) {
    return request(PETITIONADD, METHOD.POST, {
        createtime: petition.createtime,
        executor: petition.executor,
        content: petition.content
    })
}

export async function list(current,size) {
    return request(PETITIONLIST, METHOD.GET,{
        current:current,
        size:size
    })
}
export async function listNull(current,size) {
    return request(PETITIONLISTNULL, METHOD.GET,{
        current:current,
        size:size
    })
}
export async function getOper() {
    return request(GETOPER, METHOD.POST)
}
export async function addoutcome(petition) {
    return request(PETITIONOUTCOME, METHOD.POST,{
        peid:petition.peid,
        outcome:petition.outcome
    })
}
export default {add,list,addoutcome ,getOper,listNull}