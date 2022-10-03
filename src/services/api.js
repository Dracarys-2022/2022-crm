//跨域代理前缀
const API_PROXY_PREFIX='http://localhost:8081'
const BASE_URL = process.env.NODE_ENV === 'production' ? process.env.VUE_APP_API_BASE_URL : API_PROXY_PREFIX

// const BASE_URL = process.env.VUE_APP_API_BASE_URL
module.exports = {
  LOGIN: `${BASE_URL}/user/login`,
  UserLog: `${BASE_URL}/user-log/save`,
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
  LISTPRO:`${BASE_URL}/product/list`,
  LISTOUt:`${BASE_URL}/product/listOut`,
  CHANGESTATUS:`${BASE_URL}/product/changeStatus`,
  QUERYBYPID:`${BASE_URL}/product/queryByPid`,
  UPDATEPRO:`${BASE_URL}/product/update`,
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
  //添加用户的时候查角色
  RolesList:`${BASE_URL}/roles/queryList`,
  OperatorItem:`${BASE_URL}/operator/queryItems`,
   //角色添加
   ROLESADD:`${BASE_URL}/roles/add`,
   //添加订单
   ORDERSADD:`${BASE_URL}/orders/add`,
   //父功能添加
   FUNCTIONADD:`${BASE_URL}/functions/add`,
   //子功能添加
   FUNCTIONADD1:`${BASE_URL}/functions/add1`,
   //查看角色
   LISTROLES:`${BASE_URL}/roles/list`,
   //查看功能
   LISTFUNCTION:`${BASE_URL}/functions/list`,
   //删除角色
   DELETEROLE:`${BASE_URL}/roles/delete`,
   //删除功能
   DELETEFUNCTION:`${BASE_URL}/functions/delete`,
   //修改角色
   UPDATEROLE:`${BASE_URL}/roles/update`,
   //修改功能
   UPDATEFUNCTION:`${BASE_URL}/functions/update`,
   //查看父功能
   FIRSTFUNCTION:`${BASE_URL}/functions/listFunction`,
   //查看子功能
   FIRSTFUNCTION1:`${BASE_URL}/functions/listFunction1`,
   //修改角色
   UPDATEONE:`${BASE_URL}/roles/updateone`,
   //修改功能
   UPDATEONEFUNCTION:`${BASE_URL}/functions/updateone`,
   //查看角色详情
   ROLEONE:`${BASE_URL}/roles/roleOne`,
   //分配权限
   BESURE:`${BASE_URL}/functions/beSureFunctions`,
   //拜访记录添加
   VISITADD:`${BASE_URL}/visit/add`,
   //查看拜访记录
   LISTVISIT:`${BASE_URL}/visit/list`,
   //填写记录者可删除
   DELETEVISIT:`${BASE_URL}/visit/delete`,
   //查询所有下拉框
   LISTGET:`${BASE_URL}/orders/getlist`,
   //查询订单记录
   LISTORDERS:`${BASE_URL}/orders/list`,
   //请求出库
   REQUESTOUT:`${BASE_URL}/orders/request`,
   //出库
   OUT:`${BASE_URL}/orders/out`,
   //退换
   RETURNSTATE:`${BASE_URL}/orders/return`,
   //完成订单
   COMPLATE:`${BASE_URL}/orders/complate`,
   //查看利润
   PREFIT:`${BASE_URL}/orders/prefit`,
   //查看利润
   PREFIT1:`${BASE_URL}/orders/prefit1`,
   //获取对应服务/产品
   ALLPRO:`${BASE_URL}/orders/allpro`,
   //添加服务
  ADDPRO:`${BASE_URL}/product/add`,
   //根据cid找coid
   SELECTCOID:`${BASE_URL}/orders/selectcid`,
}
