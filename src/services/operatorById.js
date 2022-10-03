import { request,METHOD } from '@/utils/request'
import { OperatorById } from '@/services/api'

export async function queryById(oid)
{
    return request(OperatorById,METHOD.GET,{
       oid:oid
    })
}
export default queryById