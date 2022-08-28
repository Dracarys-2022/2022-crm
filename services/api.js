//跨域代理前缀
const API_PROXY_PREFIX='http://localhost:8081'
const BASE_URL = process.env.NODE_ENV === 'production' ? process.env.VUE_APP_API_BASE_URL : API_PROXY_PREFIX

// const BASE_URL = process.env.VUE_APP_API_BASE_URL
module.exports = {
  LOGIN: `${BASE_URL}/login`,
  ROUTES: `${BASE_URL}/routes`,
  GOODS: `${BASE_URL}/goods`,
  GOODS_COLUMNS: `${BASE_URL}/columns`,
  StOCKIN:`${BASE_URL}/inventory/add`,
  StOCKQUERY:`${BASE_URL}/orders/toInventory`,
  StOCKQUERYOUT:`${BASE_URL}/orders/toInventoryOut`,
  StOCKQUERYLACK:`${BASE_URL}/orders/toInventoryLack`,
  StOCKOUT:`${BASE_URL}/inventory/sub`,
  GETPRODUCT:`${BASE_URL}/inventory/getproduct`,
  StOCKLIST:`${BASE_URL}/inventory/list`,
  StOCKLISTNULL:`${BASE_URL}/inventory/listNull`,
  STOCKLACK:`${BASE_URL}/inventory/lack`,
  StOCKQUERYBYID:`${BASE_URL}/inventory/queryById`,
  StOCKUPDATE:`${BASE_URL}/inventory/update`,
  PETITIONADD:`${BASE_URL}/petition/add`,
  PETITIONLIST:`${BASE_URL}/petition/list`,
  PETITIONLISTNULL:`${BASE_URL}/petition/listNull`,
  PETITIONOUTCOME:`${BASE_URL}/petition/addoutcome`,
  GETOPER:`${BASE_URL}/petition/getOper`,
  ADDORDERS:`${BASE_URL}/orders/add`,
  LISTORDERS:`${BASE_URL}/orders/list`,
  LISTPRO:`${BASE_URL}/product/list`,
  LISTOUt:`${BASE_URL}/product/listOut`,
  CHANGESTATUS:`${BASE_URL}/product/changeStatus`,
  QUERYBYPID:`${BASE_URL}/product/queryByPid`,
  UPDATEPRO:`${BASE_URL}/product/update`,

}
