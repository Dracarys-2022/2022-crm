import { request,METHOD } from '@/utils/request'
import { ContactById } from '@/services/api'

export async function queryById(coid)
{
    return request(ContactById,METHOD.GET,{
       coid:coid
    })
}
export default queryById