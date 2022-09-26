import { request,METHOD } from '@/utils/request'
import { ClientAdd} from '@/services/api'

export async function add(client)
{
    return request(ClientAdd,METHOD.POST,{
        cname:client.cname,
        legalperson:client.legalperson,
        registered:client.registered,
        address:client.address,
        phone:client.phone,
        category:client.category,
        account:client.account,
        industry:client.industry,
        quality:client.quality,
        remark:client.remark
    })
}
export default add
