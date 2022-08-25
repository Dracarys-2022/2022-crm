import { request,METHOD } from '@/utils/request'
import { ClientPage} from '@/services/api'
export async function pageList(pagination){
    return request(ClientPage,METHOD.POST,{
        query:pagination.query,
        current:pagination.current,
        pagesize:pagination.pageSize
})}
export default pageList