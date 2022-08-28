import { request,METHOD } from '@/utils/request'
import { ClientById } from '@/services/api'

export async function queryById(cid)
{
    return request(ClientById,METHOD.GET,{
       cid:cid
    })
}
export default queryById