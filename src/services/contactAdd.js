import { request,METHOD } from '@/utils/request'
import { ContactAdd} from '@/services/api'

export async function add(contact)
{
    return request(ContactAdd,METHOD.POST,{
        cid:contact.cid,
        coname:contact.coname,
        address:contact.address,
        phone:contact.phone,
        remark:contact.remark
    })
}
export default add