import { request,METHOD } from '@/utils/request'
import { ContactPage} from '@/services/api'
export async function pageList(pagination){
    return request(ContactPage,METHOD.POST,{
        query:pagination.query,
        current:pagination.current,
        pagesize:pagination.pageSize,
        oid:pagination.oid
})}
export default pageList