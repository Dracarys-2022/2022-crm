import { request,METHOD } from '@/utils/request'
import { ClientList} from '@/services/api'
export async function queryList(){
    return request(ClientList,METHOD.POST,{
    
})}
export default queryList