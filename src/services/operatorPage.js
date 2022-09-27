import { request,METHOD } from '@/utils/request'
import { OperatorPage} from '@/services/api'
export async function pageList(pagination){
    return request(OperatorPage,METHOD.POST,{
        query:pagination.query,
        current:pagination.current,
        pagesize:pagination.pageSize
})}
export default pageList