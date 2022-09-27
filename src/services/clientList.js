import { request,METHOD } from '@/utils/request'
import { ClientList} from '@/services/api'
export async function queryList(){
    return request(ClientList,METHOD.GET,{
    
})}
export default queryList