import { request,METHOD } from '@/utils/request'
import { ContactUpdate} from '@/services/api'

export async function update(contact)
{
    return request(ContactUpdate,METHOD.POST,{
        coid:contact.coid,
        cid:contact.cid,
        coname:contact.coname,
        address:contact.address,
        phone:contact.phone,
        remark:contact.remark,
        status:contact.status,
        operid:contact.operid
    })
}
export default update