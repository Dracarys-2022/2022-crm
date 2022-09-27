import { request,METHOD } from '@/utils/request'
import { OperatorAdd} from '@/services/api'

export async function add(operator)
{
    return request(OperatorAdd,METHOD.POST,{
        oname:operator.oname,
        phone:operator.phone,
        password:operator.password,
        roid:operator.roid
    })
}
export default add