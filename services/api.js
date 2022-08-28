//跨域代理前缀
//const API_PROXY_PREFIX='http://localhost:8080'
//const BASE_URL = process.env.NODE_ENV === 'production' ? process.env.VUE_APP_API_BASE_URL : API_PROXY_PREFIX
const BASE_URL = process.env.VUE_APP_API_BASE_URL
module.exports = {
  LOGIN: `${BASE_URL}/login`,
  ROUTES: `${BASE_URL}/routes`,
  GOODS: `${BASE_URL}/goods`,
  GOODS_COLUMNS: `${BASE_URL}/columns`,
  ClientAdd :`${BASE_URL}/client/add`,
  ClientList :`${BASE_URL}/client/queryList`,
  ClientById:`${BASE_URL}/client/queryById`,
  ClientUpdate:`${BASE_URL}/client/update`,
  ClientPage:`${BASE_URL}/client/page`,
  ContactAdd:`${BASE_URL}/contact/add`,
  ContactPage:`${BASE_URL}/contact/page`,
  ContactById:`${BASE_URL}/contact/queryById`,
  ContactUpdate:`${BASE_URL}/contact/update`,
  OperatorAdd:`${BASE_URL}/operator/add`,
  OperatorPage:`${BASE_URL}/operator/page`,
  OperatorById:`${BASE_URL}/operator/queryById`,
  OperatorUpdate:`${BASE_URL}/operator/update`,
  OperatorList:`${BASE_URL}/operator/queryContact`,
  RolesList:`${BASE_URL}/roles/queryList`,
  OperatorItem:`${BASE_URL}/operator/queryItems`,
}
