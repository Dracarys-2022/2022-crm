import { request,METHOD } from '@/utils/request'
import { OperatorUpdate} from '@/services/api'

export async function update(operator)
{
    return request(OperatorUpdate,METHOD.POST,{
        oname:operator.oname,
        phone:operator.phone,
        password:operator.password,
        roid:operator.roid,
        status:operator.status,
        oid:operator.oid
    })
}
export default update