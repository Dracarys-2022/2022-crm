import { request,METHOD } from '@/utils/request'
import { ClientUpdate} from '@/services/api'

export async function update(client)
{
    return request(ClientUpdate,METHOD.POST,{
        cname:client.cname,
        cid:client.cid,
        legalperson:client.legalperson,
        registered:client.registered,
        address:client.address,
        phone:client.phone,
        category:client.category,
        account:client.account,
        industry:client.industry,
        quality:client.quality,
        remark:client.remark,
        status:client.status
    })
}
export default update