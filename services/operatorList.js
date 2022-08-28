import { request,METHOD } from '@/utils/request'
import { OperatorList} from '@/services/api'
export async function queryList(){
    return request(OperatorList,METHOD.POST,{
    
})}
export default queryList