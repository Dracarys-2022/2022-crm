import { request,METHOD } from '@/utils/request'
import { RolesList} from '@/services/api'
export async function queryList(){
    return request(RolesList,METHOD.POST,{
    
})}
export default queryList