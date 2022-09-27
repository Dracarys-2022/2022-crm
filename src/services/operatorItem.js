import { request,METHOD } from '@/utils/request'
import { OperatorItem } from '@/services/api'

export async function queryItem(oid)
{
    return request(OperatorItem,METHOD.GET,{
       oid:oid
    })
}
export default queryItem